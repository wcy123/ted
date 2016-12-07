package org.wcy123.fp.imp;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.wcy123.fp.Pattern;

public class ListPattern implements Pattern {
    final List<Pattern> lst;

    public ListPattern(Stream<Object> s) {
        lst = s.map(Pattern::p).collect(Collectors.toList());
    }

    @Override
    public boolean match(Object any) {
        if (!(any instanceof Iterable)) {
            return false;
        }
        Iterable<Object> anyList = (Iterable<Object>) any;
        Iterator<Object> i1 = anyList.iterator();
        Iterator<Pattern> i2 = lst.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            if (!(i2.next().match(i1.next()))) {
                return false;
            }
        }
        // the size of two list must be same.
        return !i1.hasNext() && !i2.hasNext();
    }
}
