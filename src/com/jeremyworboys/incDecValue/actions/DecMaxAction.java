package com.jeremyworboys.incDecValue.actions;

import com.jeremyworboys.incDecValue.IncDecAmount;

public class DecMaxAction extends IncDecValueAction
{
    protected IncDecAmount getAction()
    {
        return IncDecAmount.DEC_MAX;
    }
}
