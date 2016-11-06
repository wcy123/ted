package com.wcy123.parsec.stage3;

import static com.wcy123.parsec.impl.Pair.makePair;

import com.google.common.collect.Iterables;

import com.wcy123.parsec.impl.Pair;

public class Operations {
    static <T, R> Parsec<Pair<T, R>> and(Parsec<T> parsecA, Parsec<R> parsecB) {
        return parsecA.bind(
                a -> parsecB.bind(
                        b -> Monad.ret(makePair(a, b))));
    }

    static <T> Parsec<T> or(Parsec<T> parsecA, Parsec<T> parsecB) {
        return iterableChars ->
                Iterables.concat(parsecA.parse(iterableChars),
                            parsecB.parse(iterableChars));
    }
}
