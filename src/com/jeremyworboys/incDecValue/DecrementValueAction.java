package com.jeremyworboys.incDecValue;

public class DecrementValueAction extends IncDecValueAction
{
    public String transform(String s)
    {
        boolean isFullyUpperCase = s.toUpperCase().equals(s);

        if (isFullyUpperCase) {
            return Character.toString(s.charAt(0)) + s.substring(1).toLowerCase();
        } else {
            return s.toLowerCase();
        }
    }
}
