package org.wcy123.fp;

import static org.wcy123.fp.List.eql;
import static org.wcy123.fp.List.myToString;

import java.util.Iterator;

import org.wcy123.fp.internal.ConsIterator;

/**
 * mimic fp
 *
 * Created by wangchunye on 10/15/16.
 */
public final class Cons<T> implements Iterable<T> {
    private static final Cons<?> NIL = new Cons<>();
    private final T car;
    private Cons<T> cdr;

    /**
     * the constructor for the NIL
     */
    private Cons() {
        car = null;
        cdr = null;
    }

    public Cons(T car, Cons<T> cdr) {
        this.car = car;
        this.cdr = cdr;
    }

    /**
     * use this function to avoid a lot of type casting.
     */
    @SuppressWarnings("unchecked")
    public static <T> Cons<T> nil() {
        return (Cons<T>) NIL;
    }

    public T car() {
        return car;
    }

    public Cons<T> cdr() {
        return cdr;
    }

    /**
     * this function should not be used because it has side effect.
     *
     * @deprecated there is no alternative, try to avoid calling this function.
     * @param cdr new cdr
     */
    @SuppressWarnings("deprecation")
    @Deprecated
    public void setCdr(Cons<T> cdr) {
        this.cdr = cdr;
    }

    public boolean isNil() {
        return this == NIL;
    }

    @Override
    public String toString() {
        return myToString(this);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Cons<?>)) {
            return false;
        }
        return eql(this, (Cons<T>) other);
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("Cons cannot be a key, so no hashCode");
    }

    @Override
    public Iterator<T> iterator() {
        return new ConsIterator(this);
    }

}
