package org.wcy123.fp.imp;

import org.wcy123.fp.Pattern;

public class ConstantPattern implements Pattern {
    public final Object value;

    public ConstantPattern(Object any) {
        if (any == null) {
            throw new IllegalArgumentException("object cannot be null");
        }
        value = any;
    }

    @Override
    public boolean match(Object any) {
        return value.equals(any);
    }
}
