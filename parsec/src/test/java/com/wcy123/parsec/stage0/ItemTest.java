package com.wcy123.parsec.stage0;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import com.wcy123.test.logger.TestWithLogger;

public class ItemTest extends TestWithLogger{

    @Test
    public void main() throws IOException {
        final ImmutableList<Character> hello = Lists.charactersOf("hello");
        final Parsec<Character> h = Item.build('h');
        final Parsec<Character> d = Item.build('d');
        logger.log("{}", h.parse(hello));
        logger.log("{}", d.parse(hello));
    }
}
