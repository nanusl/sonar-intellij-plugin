/*
 * Copyright 2021 Yu Junyang
 * https://github.com/lowkeyfish
 *
 * This file is part of Sonar Intellij plugin.
 *
 * Sonar Intellij plugin is free software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation, either version 3 of
 * the License, or (at your option) any later version.
 *
 * Sonar Intellij plugin is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Sonar Intellij plugin.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.yujunyang.intellij.plugin.sonar.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yujunyang.intellij.plugin.sonar.common.exceptions.ApiRequestFailedException;
import com.yujunyang.intellij.plugin.sonar.config.WorkspaceSettings;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class SonarApiImpl {
    private static SonarApi sonarApi;

    static {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new SonarQubeBasicAuthInterceptor());
        OkHttpClient client = httpClient.build();
        Retrofit retrofit = ApiUtils.createRetrofit(WorkspaceSettings.getInstance().sonarHostUrl, client);
        sonarApi = retrofit.create(SonarApi.class);
    }

    public Map<String, String> getDefaultProfiles(List<String> languages) throws ApiRequestFailedException {
        try {
            QualityProfilesSearchResponse qualityProfilesSearchResponse = sonarApi.qualityProfilesSearch().execute().body();
            List<QualityProfilesSearchResponse.Profile> profiles = qualityProfilesSearchResponse.getProfiles();
            if (profiles == null || profiles.size() == 0) {
                throw new ApiRequestFailedException("The default profiles empty");
            }
            return profiles.stream().filter(n -> languages.contains(n.getLanguage())).collect(Collectors.toMap(n -> n.getLanguage(), n -> n.getKey()));
        } catch (IOException e) {
            throw new ApiRequestFailedException("The default profiles search failed:" + e.getMessage(), e);
        }
    }

    public List<RulesSearchResponse.Rule> getRules(List<String> languages) throws ApiRequestFailedException {
        Map<String, String> profiles = getDefaultProfiles(languages);
        if (profiles.size() == 0) {
            return new ArrayList<>();
        }

        List<RulesSearchResponse.Rule> ret = new ArrayList<>();
        for (Map.Entry<String, String> item : profiles.entrySet()) {
            ret.addAll(getRules(item.getValue()));
        }
        return ret;
    }

    public List<RulesSearchResponse.Rule> getRules(String profileKey) throws ApiRequestFailedException {
        try {
            List<RulesSearchResponse.Rule> ret = new ArrayList<>();
            RulesSearchResponse rulesSearchResponse;
            int page = 1;
            do {
                rulesSearchResponse = sonarApi.rulesSearch(profileKey, page).execute().body();
                ret.addAll(rulesSearchResponse.getRules());

                page++;
            } while (rulesSearchResponse.getTotal() > rulesSearchResponse.getPage() * rulesSearchResponse.getPageSize());

            return ret;
        } catch (IOException e) {
            throw new ApiRequestFailedException("The rules of profile[" + profileKey + "] search failed:" + e.getMessage(), e);
        }
    }
}
