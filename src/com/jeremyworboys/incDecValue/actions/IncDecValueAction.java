package com.jeremyworboys.incDecValue.actions;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.jeremyworboys.incDecValue.*;

abstract public class IncDecValueAction extends EditorAction
{
    protected IncDecValueAction()
    {
        this(true);
    }

    protected IncDecValueAction(boolean setupHandler)
    {
        super(null);
        if (setupHandler) {
            this.setupHandler(new EditorWriteActionHandler()
            {
                public void executeWriteAction(final Editor editor, DataContext dataContext)
                {
                    editor.getCaretModel().runForEachCaret(new CaretAction()
                    {
                        public void perform(Caret caret)
                        {
                            String selectedText = caret.getSelectedText();

                            if (selectedText == null) {
                                caret.selectWordAtCaret(false);
                                selectedText = caret.getSelectedText();

                                if (selectedText == null) {
                                    return;
                                }
                            }

                            Adjuster adjuster = buildAdjusterChain();
                            adjuster.transform(getAction(), caret);
                        }
                    });
                }
            });
        }
    }

    protected static Adjuster buildAdjusterChain()
    {
        Adjuster stringAdjuster = new StringAdjuster();
        Adjuster integerAdjuster = new IntegerAdjuster();
        Adjuster doubleAdjuster = new DoubleAdjuster();

//        self.apply_date()
//        self.apply_hex_color()
//        self.apply_floating_point()
//        self.apply_integer()
//        self.apply_enums()
//        self.apply_string()
        doubleAdjuster.setNext(integerAdjuster);
        integerAdjuster.setNext(stringAdjuster);

        return doubleAdjuster;
    }

    abstract protected Amount getAction();
}
