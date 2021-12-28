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

package com.yujunyang.intellij.plugin.sonar.gui.toolwindow;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;

import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBViewport;
import com.intellij.util.ui.JBEmptyBorder;
import com.intellij.util.ui.JBUI;
import com.yujunyang.intellij.plugin.sonar.gui.common.UIUtils;

public class IssuesPanel extends JBPanel {
    private Project project;
    private SummaryPanel summaryPanel;
    private IssueListPanel issueListPanel;
    private JBScrollPane listScrollPane;
    private JBScrollPane displayControlScrollPane;
    private IssuesDisplayControlPanel issuesDisplayControlPanel;

    public IssuesPanel(Project project) {
        this.project = project;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setSize(300, 300);

        displayControlScrollPane = new JBScrollPane();
        displayControlScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        displayControlScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        issuesDisplayControlPanel = new IssuesDisplayControlPanel(project);
        displayControlScrollPane.setViewport(new JBViewport() {
            @Override
            public void scrollRectToVisible(Rectangle bounds) {
            }
        });
        displayControlScrollPane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(
                0, 0, 0, 1, UIUtils.borderColor()),
                JBUI.Borders.empty(5)));
        displayControlScrollPane.setViewportView(issuesDisplayControlPanel);
        displayControlScrollPane.setPreferredSize(new Dimension(180, 0));
        add(displayControlScrollPane, BorderLayout.WEST);


        summaryPanel = new SummaryPanel(project);
//        add(summaryPanel, BorderLayout.NORTH);

        listScrollPane = new JBScrollPane();
        listScrollPane.setBorder(JBUI.Borders.empty());
        listScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        listScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//        listScrollPane.setPreferredSize(new Dimension(200, 100));
        add(listScrollPane, BorderLayout.CENTER);

        issueListPanel = new IssueListPanel(project);

        // 设置Viewport以便内部issueListPanel内容增加时滚动条不要滚动到底部
        listScrollPane.setViewport(new JBViewport() {
            @Override
            public void scrollRectToVisible(Rectangle bounds) {
            }
        });
        listScrollPane.setViewportView(issueListPanel);

        UIUtils.setBackgroundRecursively(this, UIUtils.backgroundColor());
    }

    public void refresh() {
        // 每次加载数据把纵向滚动条回原，再配合设置的Viewport可以使每次加载数据纵向滚动条都在顶部
        listScrollPane.getVerticalScrollBar().setValue(0);
        issuesDisplayControlPanel.refresh();
        issueListPanel.refresh();
        UIUtils.setBackgroundRecursively(this, UIUtils.backgroundColor());
    }

    public void reset() {
        issuesDisplayControlPanel.reset();
        issueListPanel.reset();
    }
}
