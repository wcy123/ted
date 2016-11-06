package com.wcy123.parsec.stage6;

import java.io.IOException;

import org.junit.Test;

import com.google.common.collect.Lists;

import com.wcy123.parsec.impl.ParserResult;
import com.wcy123.test.logger.TestWithLogger;

public class ParseClassTest extends TestWithLogger {
    // import
    private static final List<Character> list = new List();
    private static final Parsecs parsecs = new Parsecs();

    @Test
    public void consTest1() throws Exception {

        parsecs.whiteSpaces
                .and(parsecs.kClass)
                .and(parsecs.whiteSpaces)
                .and(parsecs.id).bind(
                        className -> parsecs.whiteSpaces
                                .and(parsecs.openBrace)
                                .and(parsecs.whiteSpaces)
                                .and(parsecs.closeBrace)
                                .and(parsecs.whiteSpaces)
                                .bind(
                                        _ignore1 -> Monad.ret(className)))
                .parse(Lists.charactersOf(" class aClass{}"))
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
}
