package com.wcy123.test.logger;

import org.junit.Test;

public class TestLoggerTest extends TestWithLogger {

    @Test
    public void log() throws Exception {
        logger.log("hello world");
    }
}
