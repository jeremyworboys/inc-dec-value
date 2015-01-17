package com.jeremyworboys.incDecValue;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;

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
                    final IncDecValue incDecValue = new IncDecValue(getAction());

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

                            incDecValue.execute(caret);
                        }
                    });
                }
            });
        }

    }

    abstract protected IncDecValueActions getAction();
}
