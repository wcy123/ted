package org.wcy123.fp.imp;

import org.wcy123.fp.Pattern;

public class PatternVariable implements Pattern {
    private Object value;

    @Override
    public boolean match(Object any) {
        if (value == null) {
            value = any;
            return true;
        } else {
            return value.equals(any);
        }
    }
}
