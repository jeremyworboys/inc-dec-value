package com.jeremyworboys.incDecValue;

public class IncrementValueAction extends IncDecValueAction
{
    public String transform(String s)
    {
        if (Character.isUpperCase(s.charAt(0))) {
            return s.toUpperCase();
        } else {
            return Character.toString(s.charAt(0)).toUpperCase() + s.substring(1);
        }
    }
}
