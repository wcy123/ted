package com.wcy123.parsec.stage6;

import static com.wcy123.parsec.impl.ParserResult.makeResult;
import static com.wcy123.parsec.stage6.Operations.many;
import static com.wcy123.parsec.stage6.Operations.or;

import java.util.Collections;

import org.wcy123.list.Cons;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class Parsecs {
    private static final List<Character> list = new List();

    public final Parsec<Character> any =
            chars -> Iterables.isEmpty(chars) ? Collections.emptyList()
                    : Collections.singleton(
                            makeResult(Iterables.get(chars, 0), Iterables.skip(chars, 1)));
    final Parsec<Character> digit =
            any.bind(c -> c >= '0' && c <= '9' ? Monad.ret(c)
                    : Monad.fail());
    final Parsec<Character> alphabet =
            any.bind(c -> (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') ? Monad.ret(c)
                    : Monad.fail());
    final Parsec<Character> whiteSpace =
            any.bind(c -> c == ' ' || c == '\t' || c == '\f' || c == '\r' || c == '\n'
                    ? Monad.ret(c) : Monad.fail());

    final Parsec<Cons<Character>> whiteSpaces = many(whiteSpace);

    final Parsec<Cons<Character>> digits = many(digit);

    final Parsec<Character> underscore;
    final Parsec<Cons<Character>> id;
    final Parsec<Cons<Character>> kClass;
    final Parsec<Character> openBrace;
    final Parsec<Character> closeBrace;
    final Parsec<Character> semicolon;

    public Parsecs() {
        underscore = item('_');
        id = list.cons.call(or(underscore, alphabet), many(or(underscore, or(digit, alphabet))));
        kClass = keyword("class");
        openBrace = item('{');
        closeBrace = item('}');
        semicolon = item(';');
    }

    public Parsec<Character> item(char expected) {
        return any.bind(c -> c == expected ? Monad.ret(c) : Monad.fail());
    }

    public Parsec<Cons<Character>> keyword(String expected) {
        Parsec<Cons<Character>> ret = list.nil.call();

        for (Character character : Lists
                .charactersOf(new StringBuilder(expected).reverse().toString())) {
            ret = list.cons.call(item(character), ret);
        }
        return ret;
    }
}
