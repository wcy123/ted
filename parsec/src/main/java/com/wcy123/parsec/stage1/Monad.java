package com.wcy123.parsec.stage1;

import java.util.Collections;

import com.google.common.collect.Iterables;

import com.wcy123.parsec.impl.ParserResult;
import com.wcy123.parsec.stage0.Parsec;

import rx.functions.Func1;

import static com.wcy123.parsec.impl.ParserResult.makeResult;

public class Monad {
    public static <R> Parsec<R> ret(R value) {
        return iterableCharacter -> Collections.singleton(makeResult(value, iterableCharacter));
    }

    static Parsec fail() {
        return iterableCharacter -> Collections.emptyList();
    }

    static <T, R> Parsec<R> bind(Parsec<T> self, Func1<T, Parsec<R>> f) {
        return iterableCharacter -> Iterables.concat(
                Iterables.transform(
                        self.parse(iterableCharacter),
                        result -> f.call(result.getValue()).parse(result.getRemain())));
    }
}
