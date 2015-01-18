package com.jeremyworboys.incDecValue.actions;

import com.jeremyworboys.incDecValue.IncDecAmount;

public class DecAllAction extends IncDecValueAction
{
    protected IncDecAmount getAction()
    {
        return IncDecAmount.DEC_ALL;
    }
}
