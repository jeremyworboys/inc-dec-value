package com.jeremyworboys.incDecValue.actions;

import com.jeremyworboys.incDecValue.Amount;

public class DecAllAction extends IncDecValueAction
{
    protected Amount getAction()
    {
        return Amount.DEC_ALL;
    }
}
