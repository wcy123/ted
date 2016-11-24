package com.wcy123.parsec.stage5;

import static com.wcy123.parsec.stage5.Parsec.item;
import static org.wcy123.fp.List.fromIterable;

import java.io.IOException;

import org.junit.Test;
import org.wcy123.fp.Cons;

import com.google.common.collect.Lists;

import com.wcy123.parsec.impl.ParserResult;
import com.wcy123.test.logger.TestWithLogger;

public class OperationsTest extends TestWithLogger {
    // import fp functions
    private final List<Character> list = new List();

    @Test
    public void consTest1() throws Exception {
        logger.log("testing hello");
        list.cons.call(item('h'), list.cons.call(item('e'), list.cons.call(item('l'),
                list.cons.call(item('l'), list.cons.call(item('o'), list.nil.call())))))
                .parse(Lists.charactersOf("hello world"))
                .forEach(this::dumpResult);
    }

    @Test
    public void consTest2() throws Exception {
        logger.log("testing hello");
        symbol("hello")
                .parse(Lists.charactersOf("hello world"))
                .forEach(this::dumpResult);
    }

    private void dumpResult(ParserResult<?> result) {
        try {
            logger.log("value = {}", result.getValue());
            logger.log("remains {}", result.getRemain());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Parsec<Cons<Character>> symbol(String symbolName) {
        Parsec<Cons<Character>> ret = list.nil.call();

        for (Character character : Lists
                .charactersOf(new StringBuilder(symbolName).reverse().toString())) {
            ret = list.cons.call(item(character), ret);
        }
        return ret;
    }

    @Test
    public void testIt() throws Exception {

        final Cons<Character> hello = fromIterable(Lists.charactersOf("hello world"));
        System.out.println(hello);
    }
}
