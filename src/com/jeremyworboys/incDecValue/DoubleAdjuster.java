package com.jeremyworboys.incDecValue;

import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.util.TextRange;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jeremyworboys on 18/01/15.
 */
public class DoubleAdjuster extends Adjuster
{
    protected Boolean doTransform(Amount action, Caret caret)
    {
        Integer numberStart = caret.getSelectionStart() - 1;
        String prevCharacter = caret.getEditor().getDocument().getText(new TextRange(numberStart, numberStart + 1));

        if (prevCharacter.equals(".")) {
            do {
                numberStart -= 1;
                prevCharacter = caret.getEditor().getDocument().getText(new TextRange(numberStart, numberStart + 1));
            } while (prevCharacter.matches("\\d"));
        }

        if (!prevCharacter.equals("-")) {
            numberStart += 1;
        }

        TextRange region = new TextRange(numberStart, caret.getSelectionEnd());
        String word = caret.getEditor().getDocument().getText(region);
        Pattern pattern = Pattern.compile("(-*\\d+\\.(\\d+))([a-zA-Z%]+)?$");
        Matcher matcher = pattern.matcher(word);

        if (matcher.matches()) {
            String mantissa = matcher.group(2);
            Double origDouble = Double.parseDouble(matcher.group(1));
            Double result = origDouble + (getDelta(action) / Math.pow(10, mantissa.length()));
            String suffix = matcher.group(3) != null ? matcher.group(3) : "";
            String format = String.format("%%.%df%%s", mantissa.length());
            String replacement = String.format(format, result, suffix);

            replaceSelection(caret, replacement, region);
            caret.setSelection(region.getStartOffset(), region.getStartOffset() + replacement.length());

            return true;
        }

        return false;
    }

    protected Double getDelta(Amount action)
    {
        switch (action) {
            case INC_MIN:
                return 1.0;
            case DEC_MIN:
                return -1.0;
            case INC_MAX:
                return 10.0;
            case DEC_MAX:
                return -10.0;
            case INC_ALL:
                return 100.0;
            case DEC_ALL:
                return -100.0;
        }
        return 0.0;
    }
}
