package com.jeremyworboys.incDecValue;

import com.intellij.openapi.editor.Caret;

/**
 * Created by jeremyworboys on 18/01/15.
 */
public class IncDecValue
{
    private final IncDecValueActions action;

    public IncDecValue(IncDecValueActions action)
    {
        this.action = action;
    }

    public void execute(Caret caret)
    {
        if (applyString(caret)) {
            return;
        }
    }

    protected Boolean applyString(Caret caret)
    {
        if (this.action == IncDecValueActions.INC_ALL || this.action == IncDecValueActions.DEC_ALL) {
            return false;
        }

        String s = caret.getSelectedText();
        String r = transformString(s);

        if (!r.equals(s)) {
            caret.getEditor().getDocument().replaceString(caret.getSelectionStart(), caret.getSelectionEnd(), r);
        }

        return true;
    }

    protected String transformString(String s)
    {
        switch (this.action) {
            case INC_MIN:
                return Character.isUpperCase(s.charAt(0))
                        ? s.toUpperCase()
                        : Character.toString(s.charAt(0)).toUpperCase() + s.substring(1);
            case DEC_MIN:
                return s.toUpperCase().equals(s)
                        ? Character.toString(s.charAt(0)).toUpperCase() + s.substring(1).toLowerCase()
                        : s.toLowerCase();
            case INC_MAX:
                return s.toUpperCase();
            case DEC_MAX:
                return s.toLowerCase();
            default:
                return s;
        }
    }
}
