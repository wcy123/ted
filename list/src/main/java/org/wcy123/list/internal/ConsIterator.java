package org.wcy123.list.internal;

import java.util.Iterator;

import org.wcy123.list.Cons;

public class ConsIterator<T> implements Iterator<T> {
    private Cons<T> cur;

    public ConsIterator(Cons<T> cur) {
        this.cur = cur;
    }

    @Override
    public boolean hasNext() {
        return !cur.isNil();
    }

    @Override
    public T next() {
        final T ret = cur.car();
        cur = cur.cdr();
        return ret;
    }
}
