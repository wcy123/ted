package org.wcy123.fp;

import java.util.Arrays;
import java.util.Iterator;

import org.wcy123.fp.internal.EfficientBuilder;

import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.FuncN;

/**
 * why put these functions here?
 *
 * 1. it seems that with little dependency, just, {@link Cons#car} and {@link Cons#cdr}, we can
 * implement these functions. 2. we cannot rely on JVM supporting on tail call optimization, so that
 * some implementations are using imperative style.
 */
public class List<T> {
    // -------------- constructor -------------
    @SafeVarargs
    public final FuncN Cons<T>fromArray=(T...array)
    {
        return fromIterable(Arrays.asList(array));
    }

    public static <T> Cons<T> fromIterable(Iterable<T> iterable) {
        return fromIterator(iterable.iterator());
    }

    public static <T> Cons<T> fromIterator(Iterator<T> iterator) {
        EfficientBuilder<T> builder = new EfficientBuilder<>();
        while (iterator.hasNext()) {
            builder.append(iterator.next());
        }
        return builder.build();
    }

    public static <T> Cons<T> cons(T car, Cons<T> cdr) {
        return new Cons<>(car, cdr);
    }

    // ------------ toString and equals ---------------
    public static <T> boolean eql(Cons<T> l1, Cons<T> l2) {
        Cons<T> c1 = l1;
        Cons<T> c2 = l2;
        boolean ret = true;
        while (ret) {// tail call:
            if (c1.isNil() && c2.isNil()) {
                break;
            } else if ((c1.isNil() && !c2.isNil()) ||
                    (!c1.isNil() && c2.isNil())) {
                ret = false;
            } else {
                if (c1.car().equals(c2.car())) {
                    c1 = c1.cdr();
                    c2 = c2.cdr();
                    ret = true;
                } else {
                    ret = false;
                }
            }
        }
        return ret;
    }

    public static <T> String myToString(Cons<T> list) {
        StringBuilder buf = new StringBuilder();
        buf.append('[');
        int i = 0;
        for (T val : list) {
            if (i != 0) {
                buf.append(',');
            }
            i++;
            buf.append(val.toString());
        }
        buf.append(']');
        return buf.toString();
    }

    // -------- other functions ---
    public static <T, R> R foldl(Func2<T, R, R> f, R acc0, Cons<T> list) {
        R acc = acc0;
        Cons<T> cur = list;
        while (!cur.isNil()) {
            acc = f.call(cur.car(), acc);
            cur = cur.cdr();
        }
        return acc;
    }

    public static <T> Cons<T> reverse(Cons<T> list) {
        return foldl(List::cons, Cons.nil(), list);
    }

    public static <T, R> Cons<R> map(Func1<T, R> f, Cons<T> list) {
        /**
         * the elegant but slightly inefficient
         * <code>return reverse(foldl((x, acc) -> cons(f.call(x), acc), nil(), fp));</code>
         */

        EfficientBuilder<R> builder = new EfficientBuilder<>();
        foreach0(f, list, builder);
        return builder.build();
    }

    public static <T> Cons<T> concat(Cons<T> list1, Cons<T> list2) {
        EfficientBuilder<T> builder = new EfficientBuilder<>();
        foreach0(x -> x, list1, builder);
        return builder.build(list2);
    }

    public static <T, R> Cons<R> concatMap(Func1<T, Cons<R>> f, Cons<T> list) {
        if (list.isNil()) {
            return Cons.nil();
        }
        // tricky here, we must have a dummy header, because f might returns nil always.
        EfficientBuilder<R> builder = new EfficientBuilder<>(null);

        Cons<T> cur = list;
        while (!cur.isNil()) {
            Cons<R> listOfY = f.call(cur.car());
            foreach0(y -> y, listOfY, builder);
            cur = cur.cdr();
        }
        // because of the above trick, we have to return the cdr.
        return builder.build().cdr();
    }

    /**
     * this is an efficient but ugly implementation. it is difficult to use. it returns the header
     * and the tail.
     */
    private static <T, R> void foreach0(Func1<T, R> f, Cons<T> list,
            EfficientBuilder<R> builder) {
        Cons<T> cur = list;
        while (!cur.isNil()) {
            builder.append(f.call(cur.car()));
            cur = cur.cdr();
        }
    }
}
