package com.jeremyworboys.incDecValue;

public class DecrementValueAction extends IncDecValueAction
{
    protected IncDecValueActions getAction()
    {
        return IncDecValueActions.DEC_MIN;
    }
}
