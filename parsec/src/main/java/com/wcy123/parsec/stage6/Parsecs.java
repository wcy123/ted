package com.wcy123.parsec.stage6;

import static com.wcy123.parsec.impl.ParserResult.makeResult;

import java.util.Collections;

import org.wcy123.list.Cons;

import com.google.common.collect.Iterables;

public class Parsecs {
    public final Parsec<Character> any =
            chars -> Iterables.isEmpty(chars) ? Collections.emptyList()
                    : Collections.singleton(
                            makeResult(Iterables.get(chars, 0), Iterables.skip(chars, 1)));

    final Parsec<Character> digit =
            any.bind(c -> c >= '0' && c <= '9' ? Monad.ret(c)
                    : Monad.fail());

    final Parsec<Cons<Character>> digits = Operations.many(digit);

    public Parsecs() {
    }
}
