package com.wcy123.test.logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Formatter;
import java.util.Locale;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestLogger extends TestWatcher {
    private final Class<?> clazz;
    private Formatter formatter;
    private BufferedWriter writer;

    public TestLogger(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void log(String format, Object... args) throws IOException {
        log.info(format, args);
        formatter.format(format, args);
        formatter.out().append('\n');
    }

    @Override
    protected void starting(Description description) {
        Path logFile = Paths.get("target/" + clazz.getSimpleName() + ".log");
        try {
            writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8,
                    StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            formatter = new Formatter(writer, Locale.US);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void succeeded(Description description) {
        try {
            writer.close();
        } catch (IOException e) {
            log.error("error when writing to log", e);
            throw new IllegalArgumentException(e);
        }
        formatter.close();
    }

}
