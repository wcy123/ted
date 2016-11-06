package com.wcy123.parsec.stage3;

import static com.wcy123.parsec.stage3.Parsec.item;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.google.common.collect.Lists;

import com.wcy123.parsec.impl.ParserResult;
import com.wcy123.test.logger.TestWithLogger;

public class AndOrTest extends TestWithLogger {

    @Test
    public void main() throws IOException {
        logger.log("testing hello");
        item('h').and(item('e')).and(item('l')).and(item('l')).and(item('o'))
                .parse(Lists.charactersOf("hello"))
                .forEach(this::dumpResult);
        final Parsec<Character> x_or_y = item('x').or(item('y'));
        logger.log("testing x_or_y.parse(\"x\"):");
        x_or_y.parse(Lists.charactersOf("x")).forEach(this::dumpResult);
        logger.log("testing x_or_y.parse(\"y\"):");
        x_or_y.parse(Lists.charactersOf("y")).forEach(this::dumpResult);
    }

    private void dumpResult(ParserResult<?> result){
        try {
            logger.log("value = {}", result.getValue());
            logger.log("remains {}", result.getRemain());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
