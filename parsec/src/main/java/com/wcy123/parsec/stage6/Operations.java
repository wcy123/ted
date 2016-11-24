package com.wcy123.parsec.stage6;

import static com.wcy123.parsec.impl.Pair.makePair;
import static com.wcy123.parsec.stage6.Monad.ret;
import static org.wcy123.fp.Cons.nil;
import static org.wcy123.fp.List.cons;

import org.wcy123.fp.Cons;

import com.google.common.collect.Iterables;

import com.wcy123.parsec.impl.Pair;
import com.wcy123.parsec.impl.ParserResult;

public enum Operations {
    ;
    public static <T, R> Parsec<Pair<T, R>> and(Parsec<T> parsecA, Parsec<R> parsecB) {
        return parsecA.bind(
                a -> parsecB.bind(
                        b -> ret(makePair(a, b))));
    }

    public static <T> Parsec<Cons<T>> comma(Parsec<T> parsecA, Parsec<Cons<T>> parsecB) {
        return parsecA.bind(
                a -> parsecB.bind(
                        b -> ret(cons(a, b))));
    }

    public static <T> Parsec<Cons<T>> period() {
        return ret(nil());
    }
    public static <T> Parsec<T> or(Parsec<T> parsecA, Parsec<T> parsecB) {
        return iterableChars -> {
            final Iterable<ParserResult<T>> resultsA = parsecA.parse(iterableChars);
            return Iterables.isEmpty(resultsA) ? parsecB.parse(iterableChars)
                    : resultsA;
        };

    }

    public static <T> Parsec<Cons<T>> many(Parsec<T> parsecA) {
        return many1(parsecA, ret(nil())).bind(
                result -> ret(result));
    }

    public static <T> Parsec<Cons<T>> many1(Parsec<T> parsecA, Parsec<Cons<T>> acc) {
        return or(parsecA.bind(
                        a -> many1(parsecA, acc).bind(
                                as -> ret(cons(a, as)))),
                acc);
    }
}
