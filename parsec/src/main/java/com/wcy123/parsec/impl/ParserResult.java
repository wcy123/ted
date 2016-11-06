package com.wcy123.parsec.impl;

public class ParserResult<T> {
    private final T value;
    private final Iterable<Character> remain;

    private ParserResult(T value, Iterable<Character> remain) {
        this.value = value;
        this.remain = remain;
    }

    public static <T> ParserResult<T> makeResult(T value, Iterable<Character> remain) {
        return new ParserResult<T>(value, remain);
    }

    @Override
    public String toString() {
        return "ParserResult{" +
                "value=" + value +
                ", remain=" + remain +
                '}';
    }

    public T getValue() {
        return value;
    }

    public Iterable<Character> getRemain() {
        return remain;
    }
}
