package com.yujunyang.intellij.plugin.sonar.core;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;

public class Issue extends AbstractIssue {
    private int lineStart;
    private int lineEnd;
    private TextRange textRange;

    public Issue(
            PsiFile psiFile,
            String ruleRepository,
            String ruleKey,
            String msg,
            String severity,
            int lineStart,
            int lineEnd,
            TextRange textRange,
            String type,
            String name,
            String htmlDesc) {
        super(psiFile, ruleRepository, ruleKey, msg, severity, type, name, htmlDesc);
        this.lineStart = lineStart;
        this.lineEnd = lineEnd;
        this.textRange = textRange;
        this.type = type;
    }

    public int getLineStart() {
        return lineStart;
    }

    public int getLineEnd() {
        return lineEnd;
    }

    public TextRange getTextRange() {
        return textRange;
    }
}
