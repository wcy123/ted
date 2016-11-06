package com.wcy123.parsec.stage5;

import java.util.Collections;

import com.google.common.collect.Iterables;

import com.wcy123.parsec.impl.ParserResult;

import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

public class Monad {
    public static <R> Parsec<R> ret(R value) {
        return iterableCharacter -> Collections
                .singleton(new ParserResult(value, iterableCharacter));
    }

    public static Parsec fail() {
        return iterableCharacter -> Collections.emptyList();
    }

    public static <T, R> Parsec<R> bind(Parsec<T> self, Func1<T, Parsec<R>> f) {
        return iterableCharacter -> Iterables.concat(
                Iterables.transform(
                        self.parse(iterableCharacter),
                        result -> f.call(result.getValue()).parse(result.getRemain())));
    }

    public static <T> Func0<Parsec<T>> lift0(Func0<T> f) {
        return () -> ret(f.call());
    }

    public static <T, R> Func1<Parsec<T>, Parsec<R>> lift1(Func1<T, R> f) {
        return parsecA -> parsecA.bind(
                a -> ret(f.call(a)));
    }

    public static <T, S, R> Func2<Parsec<T>, Parsec<S>, Parsec<R>> lift2(Func2<T, S, R> f) {
        return (parsecA, parsecB) -> parsecA.bind(
                a -> parsecB.bind(
                        b -> ret(f.call(a, b))));
    }
}
