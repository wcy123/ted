package com.wcy123.demo.json.binding;

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

public class TestLogger extends TestWatcher {
    private final Class<?> clazz;
    private BufferedWriter writer;
    private Formatter formatter;

    public TestLogger(Class<?> clazz) {
        this.clazz = clazz;
    }

    public void log(String format, Object...args) throws IOException {
        System.out.format(format, args);
        System.out.println();
        formatter.format(format, args);
        formatter.out().append('\n');
    }

    @Override
    protected void starting(Description description) {
        Path logFile = Paths.get("target/" + clazz.getName() + ".log");
        try {
            writer = Files.newBufferedWriter(logFile, StandardCharsets.UTF_8,
                    StandardOpenOption.WRITE , StandardOpenOption.CREATE);
            formatter = new Formatter(writer, Locale.US);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void succeeded(Description description) {
        formatter.close();
    }

}
