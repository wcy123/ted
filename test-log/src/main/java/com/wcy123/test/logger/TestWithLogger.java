package com.wcy123.test.logger;

import org.junit.Rule;

public class TestWithLogger {
    @Rule
    public TestLogger logger;

    public TestWithLogger() {
        this.logger = new TestLogger(this.getClass());
    }
}
