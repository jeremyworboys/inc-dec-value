package com.jeremyworboys.incDecValue.actions;

import com.jeremyworboys.incDecValue.Amount;

public class DecMinAction extends IncDecValueAction
{
    protected Amount getAction()
    {
        return Amount.DEC_MIN;
    }
}
