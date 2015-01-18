package com.jeremyworboys.incDecValue;

import com.intellij.openapi.editor.Caret;

/**
 * Created by jeremyworboys on 18/01/15.
 */
public class StringAdjuster extends Adjuster
{
    protected Boolean doTransform(IncDecValueActions action, Caret caret)
    {
        if (action == IncDecValueActions.INC_ALL || action == IncDecValueActions.DEC_ALL) {
            return false;
        }

        String newWord;
        String word = caret.getSelectedText();
        assert word != null;

        switch (action) {
            case INC_MIN:
                newWord = Character.isUpperCase(word.charAt(0))
                        ? word.toUpperCase()
                        : Character.toString(word.charAt(0)).toUpperCase() + word.substring(1);
                break;
            case DEC_MIN:
                newWord = word.toUpperCase().equals(word)
                        ? Character.toString(word.charAt(0)).toUpperCase() + word.substring(1).toLowerCase()
                        : word.toLowerCase();
                break;
            case INC_MAX:
                newWord = word.toUpperCase();
                break;
            case DEC_MAX:
                newWord = word.toLowerCase();
                break;
            default:
                newWord = word;
        }

        if (!newWord.equals(word)) {
            replaceSelection(caret, newWord);
        }

        return true;
    }
}
