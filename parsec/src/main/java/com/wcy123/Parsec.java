package com.wcy123;

import static com.wcy123.parsec.impl.Pair.makePair;
import static com.wcy123.parsec.impl.ParserResult.makeResult;

import java.util.Collections;

import com.google.common.collect.Iterables;

import com.wcy123.parsec.impl.Pair;
import com.wcy123.parsec.impl.ParserResult;

import rx.functions.Func1;

/**
 * 什么是 parsec?
 *
 * Created by wangchunye on 11/5/16.
 */
public interface Parsec<T>
        extends Func1<Iterable<Character>, Iterable<ParserResult<T>>> {

    static Parsec<Character> item(char x) {
        return charIterator -> Iterables.getFirst(charIterator, Character.MIN_VALUE) == x
                ? Collections.singleton(makeResult(x, Iterables.skip(charIterator, 1)))
                : Collections.emptyList();
    }

    static <R> Parsec<R> returnx(R x) {
        return charIterator -> Collections.singleton(makeResult(x, charIterator));
    }

    static Parsec fail() {
        return charIterator -> Collections.emptyList();
    }

    static <T, R> Parsec<R> bind(Parsec<T> self, Func1<T, Parsec<R>> f) {
        return charIterator -> Iterables.concat(
                Iterables.transform(
                        self.parse(charIterator),
                        pair -> f.call(pair.getValue()).parse(pair.getRemain())));
    }

    static <T, R> Parsec<Pair<T, R>> and(Parsec<T> parsecA, Parsec<R> parsecB) {
        return parsecA.bind(
                a -> parsecB.bind(
                        b -> returnx(makePair(a, b))));
    }

    default Iterable<ParserResult<T>> parse(Iterable<Character> charIterator) {
        return this.call(charIterator);
    }

    default <R> Parsec<R> bind(Func1<T, Parsec<R>> f) {
        return bind(this, f);
    }

    default <R> Parsec<Pair<T, R>> and(Parsec<R> next) {
        return and(this, next);
    }
}
