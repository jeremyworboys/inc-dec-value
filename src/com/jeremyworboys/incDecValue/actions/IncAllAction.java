package com.jeremyworboys.incDecValue.actions;

import com.jeremyworboys.incDecValue.Amount;

public class IncAllAction extends IncDecValueAction
{
    protected Amount getAction()
    {
        return Amount.INC_ALL;
    }
}
