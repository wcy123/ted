package org.wcy123.list.internal;

import static org.wcy123.list.Functions.cons;

import org.wcy123.list.Cons;

/**
 * efficiently build a list. `revserse-foldl` is a common idiom, so this class efficiently implement
 * this idiom. Instead of reversing it, a new element is appended at the tail.
 *
 * @param <T> type
 */
public class EfficientBuilder<T> {
    private Cons<T> header;
    private Cons<T> lastCell;

    public EfficientBuilder(T car) {
        header = cons(car, Cons.nil());
        lastCell = header;
    }

    public EfficientBuilder() {
        header = null;
    }

    public EfficientBuilder<T> append(T car) {
        if (header == null) {
            header = cons(car, Cons.nil());
            lastCell = header;
        } else {
            // noinspection deprecation
            lastCell.setCdr(cons(car, Cons.nil()));
            lastCell = lastCell.cdr();
        }
        return this;
    }

    public Cons<T> build() {
        return build(Cons.nil());
    }

    public Cons<T> build(Cons<T> tail) {
        if (header == null) {
            return tail;
        }
        // noinspection deprecation
        lastCell.setCdr(tail);
        return header;
    }
}
