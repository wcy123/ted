package com.wcy123.parsec.stage5;

import static com.wcy123.parsec.impl.Pair.makePair;

import org.wcy123.fp.imp.Cons;
import org.wcy123.fp.imp.List;

import com.google.common.collect.Iterables;

import com.wcy123.parsec.impl.Pair;

import rx.functions.Func2;

public enum Operations {
    ;
    public static <T, R> Parsec<Pair<T, R>> and(Parsec<T> parsecA, Parsec<R> parsecB) {
        return parsecA.bind(
                a -> parsecB.bind(
                        b -> Monad.ret(makePair(a, b))));
    }

    public static <T> Func2<Parsec<T>, Parsec<Cons<T>>, Parsec<Cons<T>>> fcons(Parsec<T> parsecA,
            Parsec<Cons<T>> parsecB) {
        return Monad.lift2(List::cons);
    }

    public static <T> Parsec<Cons<T>> nil() {
        return Monad.ret(Cons.nil());
    }

    public static <T> Parsec<T> or(Parsec<T> parsecA, Parsec<T> parsecB) {
        return iterableChars -> Iterables.concat(parsecA.parse(iterableChars),
                parsecB.parse(iterableChars));
    }
}
