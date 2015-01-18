package com.jeremyworboys.incDecValue;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.util.TextRange;

/**
 * Created by jeremyworboys on 18/01/15.
 */
abstract public class Adjuster
{
    private Adjuster next;

    public void setNext(Adjuster adjuster) {
        next = adjuster;
    }

    public void transform(Amount action, Caret caret)
    {
        Boolean transformed = doTransform(action, caret);

        if (!transformed && next != null) {
            next.transform(action, caret);
        }
    }

    protected void replaceSelection(Caret caret, String replacement)
    {
        replaceSelection(caret, replacement, new TextRange(caret.getSelectionStart(), caret.getSelectionEnd()));
    }

    protected void replaceSelection(Caret caret, String replacement, TextRange region)
    {
        caret.getEditor().getDocument().replaceString(region.getStartOffset(), region.getEndOffset(), replacement);
    }

    abstract protected Boolean doTransform(Amount action, Caret caret);
}
