package com.yujunyang.intellij.plugin.sonar.gui.toolwindow;

import com.google.common.base.Splitter;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.ui.components.JBBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.JBUI;
import com.yujunyang.intellij.plugin.sonar.messages.MessageBusManager;
import com.yujunyang.intellij.plugin.sonar.service.ProblemCacheService;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Set;

public class SearchPanel extends JBPanel {

    private final Project project;

    private JBTextField searchField;

    private JButton searchButton;

    public SearchPanel(Project project) {
        this.project = project;
        setLayout(new BorderLayout());
        init();
    }

    private void init() {
        JBBox horizontalBox = JBBox.createHorizontalBox();
        horizontalBox.setBorder(JBUI.Borders.empty(2, 5));

        horizontalBox.add(new JBLabel("RuleKey: "));

        searchField = new JBTextField("");
        searchField.getEmptyText().setText("Example：S112 S3518");
        horizontalBox.add(searchField);

        searchButton = new JButton(AllIcons.Actions.Search);
        horizontalBox.add(searchButton);

        searchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    searchButton.doClick();
                }
            }
        });

        searchButton.addActionListener(actionEvent -> {
            Set<String> ruleKeys = ProblemCacheService.getInstance(project).getRuleKeys();
            ruleKeys.clear();
            String ruleKey = searchField.getText();
            if (StringUtils.isNoneBlank(ruleKey)) {
                Splitter.on(" ")
                        .omitEmptyStrings()
                        .trimResults()
                        .split(ruleKey.toUpperCase())
                        .forEach(ruleKeys::add);
            }
            MessageBusManager.publishIssueFilter(project);
        });

        add(horizontalBox, BorderLayout.CENTER);
    }

    public void reset() {
        searchField.setText("");
    }
}
