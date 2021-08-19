package com.yujunyang.intellij.plugin.sonar.gui.popup;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.function.Supplier;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopup;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.util.ui.JBUI;
import com.yujunyang.intellij.plugin.sonar.core.AbstractIssue;
import com.yujunyang.intellij.plugin.sonar.core.DuplicatedBlocksIssue;
import com.yujunyang.intellij.plugin.sonar.core.Issue;
import com.yujunyang.intellij.plugin.sonar.gui.common.UIUtils;

public class LineMarkerProviderPopupPanel extends JBPanel {
    private Project project;
    private List<AbstractIssue> issues;
    private JBPopup popup;

    public LineMarkerProviderPopupPanel(Project project, List<AbstractIssue> issues) {
        this.project = project;
        this.issues = issues;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        setBorder(JBUI.Borders.empty(5));

        JBLabel title = new JBLabel("问题: " + issues.size() + "个");
        add(title, BorderLayout.NORTH);

        add(createIssues(), BorderLayout.CENTER);

        UIUtils.setBackgroundRecursively(this, UIUtils.backgroundColor());
    }

    private JBPanel createIssues() {
        JBPanel ret = new JBPanel();
        BoxLayout layout = new BoxLayout(ret, BoxLayout.Y_AXIS);
        ret.setLayout(layout);
        for (AbstractIssue issue : issues) {
            ret.add(Box.createVerticalStrut(5));
            IssueItemPanel issueItemPanel = new IssueItemPanel(issue);
            Supplier<JBPopup> getOwnerPopupFunction = () -> this.popup;
            issueItemPanel.putClientProperty("IssueItemPanel.getOwnerPopupFunction",  getOwnerPopupFunction);
            ret.add(issueItemPanel);
        }

        return ret;
    }

    public void setPopup(JBPopup popup) {
        this.popup = popup;
    }
}
