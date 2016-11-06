package com.wcy123.parsec.stage4;

import static com.wcy123.parsec.impl.Pair.makePair;

import org.wcy123.list.Cons;
import org.wcy123.list.Functions;

import com.google.common.collect.Iterables;

import com.wcy123.parsec.impl.Pair;

public enum Operations {
    ;
    public static <T, R> Parsec<Pair<T, R>> and(Parsec<T> parsecA, Parsec<R> parsecB) {
        return parsecA.bind(
                a -> parsecB.bind(
                        b -> Monad.ret(makePair(a, b))));
    }

    public static <T> Parsec<Cons<T>> cons(Parsec<T> parsecA, Parsec<Cons<T>> parsecB) {
        return parsecA.bind(
                a -> parsecB.bind(
                        b -> Monad.ret(Functions.cons(a, b))));
    }

    public static <T> Parsec<Cons<T>> nil() {
        return Monad.ret(Cons.nil());
    }

    public static <T> Parsec<T> or(Parsec<T> parsecA, Parsec<T> parsecB) {
        return iterableChars -> Iterables.concat(parsecA.parse(iterableChars),
                parsecB.parse(iterableChars));
    }
}
