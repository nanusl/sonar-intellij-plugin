package com.yujunyang.intellij.plugin.sonar.gui.toolwindow;

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.Box;
import javax.swing.BoxLayout;

import com.intellij.psi.PsiFile;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBTextArea;
import com.intellij.util.ui.JBUI;
import com.yujunyang.intellij.plugin.sonar.common.IdeaUtils;
import com.yujunyang.intellij.plugin.sonar.core.AbstractIssue;
import com.yujunyang.intellij.plugin.sonar.core.DuplicatedBlocksIssue;
import com.yujunyang.intellij.plugin.sonar.core.Issue;
import com.yujunyang.intellij.plugin.sonar.gui.common.UIUtils;

public class IssueFileGroupPanel extends JBPanel {
    private PsiFile psiFile;
    private List<AbstractIssue> issues;

    public IssueFileGroupPanel(PsiFile psiFile, List<AbstractIssue> issues) {
        this.psiFile = psiFile;
        this.issues = issues;
        init();
    }

    private void init() {
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(layout);
        setBorder(JBUI.Borders.empty(0, 5));

        List<DuplicatedBlocksIssue> duplicatedBlocksIssues = issues.stream()
                .filter(n -> n instanceof DuplicatedBlocksIssue).map(n -> (DuplicatedBlocksIssue)n).collect(Collectors.toList());
        List<Issue> normalIssues = issues.stream().filter(n -> n instanceof Issue).map(n -> (Issue)n).collect(Collectors.toList());
        int count = (duplicatedBlocksIssues.size() > 0 ? 1 : 0) + normalIssues.size();
        addTitleTextArea(count);
        add(Box.createVerticalStrut(2));

        if (duplicatedBlocksIssues.size() > 0) {
            addIssue(duplicatedBlocksIssues);
        }
        normalIssues.forEach(n -> addIssue(n));
        add(Box.createVerticalStrut(10));
    }

    private void addTitleTextArea(int count) {
        JBTextArea textArea = UIUtils.createWrapLabelLikedTextArea(String.format("%s %s个问题", IdeaUtils.getPath(psiFile), count));
        textArea.setForeground(Color.GRAY);
        textArea.setAlignmentX(LEFT_ALIGNMENT);
        add(textArea);
    }

    private void addIssue(List<DuplicatedBlocksIssue> issues) {
        IssueItemPanel panel = new IssueItemPanel(issues);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        add(panel);
        add(Box.createVerticalStrut(5));
    }

    private void addIssue(Issue issue) {
        IssueItemPanel panel = new IssueItemPanel(issue);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        add(panel);
        add(Box.createVerticalStrut(5));
    }
}