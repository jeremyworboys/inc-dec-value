package com.jeremyworboys.incDecValue;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.util.TextRange;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jeremyworboys on 18/01/15.
 */
public class IntegerAdjuster extends Adjuster
{
    protected Boolean doTransform(IncDecValueActions action, Caret caret)
    {
        TextRange region = new TextRange(caret.getSelectionStart(), caret.getSelectionEnd());

        Integer prevPosition = caret.getSelectionStart() - 1;
        String prevCharacter = caret.getEditor().getDocument().getText(new TextRange(prevPosition, caret.getSelectionStart()));

        if (prevCharacter.equals("-")) {
            region = new TextRange(prevPosition, caret.getSelectionEnd());
        }

        String word = caret.getEditor().getDocument().getText(region);
        Pattern pattern = Pattern.compile("(-*[0-9]+)([a-zA-Z%]+)?$");
        Matcher matcher = pattern.matcher(word);

        if (matcher.matches()) {
            Integer origInt = Integer.decode(matcher.group(1));
            Integer result = origInt + getDelta(action);
            String suffix = matcher.group(2) != null ? matcher.group(2) : "";

            replaceSelection(caret, String.format("%d%s", result, suffix), region);

            Integer lengthChange = result.toString().length() - origInt.toString().length();
            if (lengthChange != 0) {
                caret.setSelection(region.getStartOffset(), region.getEndOffset() + lengthChange);
            }

            return true;
        }

        return false;
    }

    protected Integer getDelta(IncDecValueActions action)
    {
        switch (action) {
            case INC_MIN:
                return 1;
            case DEC_MIN:
                return -1;
            case INC_MAX:
                return 10;
            case DEC_MAX:
                return -10;
            case INC_ALL:
                return 100;
            case DEC_ALL:
                return -100;
        }
        return 0;
    }
}
