package org.wcy123.fp.imp.internal;

import static org.wcy123.fp.imp.List.cons;

import org.wcy123.fp.imp.Cons;

/**
 * efficiently build a fp. `revserse-foldl` is a common idiom, so this class efficiently implement
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

    @SuppressWarnings("deprecation")
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

    @SuppressWarnings("deprecation")
    public Cons<T> build(Cons<T> tail) {
        if (header == null) {
            return tail;
        }
        // noinspection deprecation
        lastCell.setCdr(tail);
        return header;
    }
}
