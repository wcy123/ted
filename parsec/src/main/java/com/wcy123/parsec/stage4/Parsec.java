package com.wcy123.parsec.stage4;

import com.wcy123.parsec.impl.Pair;
import com.wcy123.parsec.impl.ParserResult;

import rx.functions.Func1;

public interface Parsec<T>
        extends Func1<Iterable<Character>, Iterable<ParserResult<T>>> {

    static Parsec<Character> item(char x) {
        return Item.build(x);
    }

    default Iterable<ParserResult<T>> parse(Iterable<Character> charIterator) {
        return this.call(charIterator);
    }

    default <R> Parsec<R> bind(Func1<T, Parsec<R>> f) {
        return Monad.bind(this, f);
    }

    default <R> Parsec<Pair<T, R>> and(Parsec<R> other) {
        return Operations.and(this, other);
    }

    default Parsec or(Parsec other) {
        return Operations.or(this, other);
    }

}
