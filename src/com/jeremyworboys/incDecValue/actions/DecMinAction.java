package com.jeremyworboys.incDecValue.actions;

import com.jeremyworboys.incDecValue.IncDecAmount;

public class DecMinAction extends IncDecValueAction
{
    protected IncDecAmount getAction()
    {
        return IncDecAmount.DEC_MIN;
    }
}
