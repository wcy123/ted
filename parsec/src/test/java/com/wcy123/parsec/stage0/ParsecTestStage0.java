package com.wcy123.parsec.stage0;

import static com.wcy123.parsec.impl.ParserResult.makeResult;

import java.io.IOException;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import com.wcy123.parsec.impl.ParserResult;
import com.wcy123.test.logger.TestWithLogger;

public class ParsecTestStage0 extends TestWithLogger {
    @Test
    public void main() throws IOException {
        Parsec<Integer> intParser = ss -> {
            StringBuilder bs = new StringBuilder();
            int n = 0;
            for (Character s : ss) {
                if (s >= '0' && s <= '9') {
                    bs.append(s);
                    n = n + 1;
                } else {
                    break;
                }
            }
            if (n == 0) {
                return Collections.emptyList();
            }
            return Collections
                    .singleton(makeResult(Integer.parseInt(bs.toString()), Iterables.skip(ss, n)));
        };

        final Iterable<ParserResult<Integer>> result =
                intParser.parse(Lists.charactersOf("12hello"));
        logger.log("{}", result);
    }
}
