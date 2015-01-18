package com.jeremyworboys.incDecValue.actions;

import com.jeremyworboys.incDecValue.IncDecAmount;

public class IncMinAction extends IncDecValueAction
{
    protected IncDecAmount getAction()
    {
        return IncDecAmount.INC_MIN;
    }
}
