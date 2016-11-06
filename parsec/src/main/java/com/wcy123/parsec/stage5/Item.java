package com.wcy123.parsec.stage5;

import static com.wcy123.parsec.impl.ParserResult.makeResult;

import java.util.Collections;

import com.google.common.collect.Iterables;

public class Item {
    public static Parsec<Character> build(char x) {
        return charIterator -> Iterables.getFirst(charIterator, Character.MIN_VALUE) == x
                ? Collections.singleton(makeResult(x, Iterables.skip(charIterator, 1)))
                : Collections.emptyList();
    }
}
