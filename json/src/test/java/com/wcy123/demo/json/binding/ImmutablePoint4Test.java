package com.wcy123.demo.json.binding;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ImmutablePoint4Test {
    @Rule
    public TestLogger logger = new TestLogger(ImmutablePoint4Test.class);

    @Test
    public void main() throws IOException {
        final ImmutablePoint4 point1 = new ImmutablePoint4(3, 4);

        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(point1);
        final ImmutablePoint4 point2 = mapper.readValue(json, ImmutablePoint4.class);

        logger.log("point1 is " + point1);
        logger.log("point2 is " + point2);
        logger.log("json is " + json);
    }
}
