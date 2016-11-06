package com.wcy123.parsec.stage6;

import java.io.IOException;

import org.junit.Test;

import com.google.common.collect.Lists;

import com.wcy123.parsec.impl.ParserResult;
import com.wcy123.test.logger.TestWithLogger;

public class ParsecsTest extends TestWithLogger {
    // import
    private final Parsecs parsecs = new Parsecs();

    @Test
    public void consTest1() throws Exception {
        // logger.log("testing digit");
        // parsecs.digit.parse(Lists.charactersOf("123abc"))
        // .forEach(this::dumpResult);
        // logger.log("testing digits");
        parsecs.digits.parse(Lists.charactersOf("123456789b"))
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
