package com.wcy123.parsec.stage4;

import static com.wcy123.parsec.stage4.Operations.cons;
import static com.wcy123.parsec.stage4.Operations.nil;
import static com.wcy123.parsec.stage4.Parsec.item;
import static org.wcy123.fp.imp.List.fromIterable;

import java.io.IOException;

import org.junit.Test;
import org.wcy123.fp.imp.Cons;

import com.google.common.collect.Lists;

import com.wcy123.parsec.impl.ParserResult;
import com.wcy123.test.logger.TestWithLogger;

public class OperationsTest extends TestWithLogger {
    @Test
    public void consTest() throws Exception {
        logger.log("testing hello");
        cons(item('h'), cons(item('e'), cons(item('l'), cons(item('l'), cons(item('o'), nil())))))
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

    @Test
    public void testIt() throws Exception {

        final Cons<Character> hello = fromIterable(Lists.charactersOf("hello world"));
        System.out.println(hello);
    }

}
