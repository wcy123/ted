package com.wcy123.parsec.stage6;

import java.util.Collections;

import com.google.common.collect.Iterables;

import com.wcy123.parsec.impl.ParserResult;

public class Item {
    public static Parsec<Character> build(char x) {
        return charIterator -> Iterables.getFirst(charIterator, Character.MIN_VALUE) == x
                ? Collections.singleton(new ParserResult(x, Iterables.skip(charIterator, 1)))
                : Collections.emptyList();
    }
}
