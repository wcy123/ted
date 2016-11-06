package com.wcy123.parsec.stage6;

import java.util.Collections;

import org.wcy123.list.Cons;

import com.google.common.collect.Iterables;

import com.wcy123.parsec.impl.ParserResult;

public class Parsecs {
    public final Parsec<Character> any =
            chars -> {
                final boolean empty = Iterables.isEmpty(chars);
                if (empty) {
                    return Collections.emptyList();
                } else {
                    final ParserResult result =
                            new ParserResult(Iterables.get(chars, 0), Iterables.skip(chars, 1));
                    return Collections.singleton(result);
                }
            };

    final Parsec<Character> digit =
            any.bind(c -> {
                if (c >= '0' && c <= '9') {
                    return Monad.ret(c);
                } else {
                    return Monad.fail();
                }
            });

    final Parsec<Cons<Character>> digits = Operations.many(digit);

    public Parsecs() {

    }
}
