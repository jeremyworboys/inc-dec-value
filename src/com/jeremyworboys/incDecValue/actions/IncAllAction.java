package com.jeremyworboys.incDecValue.actions;

import com.jeremyworboys.incDecValue.IncDecAmount;

public class IncAllAction extends IncDecValueAction
{
    protected IncDecAmount getAction()
    {
        return IncDecAmount.INC_ALL;
    }
}
