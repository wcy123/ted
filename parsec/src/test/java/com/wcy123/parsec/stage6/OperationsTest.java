package com.wcy123.parsec.stage6;

import java.io.IOException;

import org.junit.Test;

import com.wcy123.parsec.impl.ParserResult;
import com.wcy123.test.logger.TestWithLogger;

public class OperationsTest extends TestWithLogger {
    @Test
    public void consTest1() throws Exception {
        logger.log("testing hello");

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
