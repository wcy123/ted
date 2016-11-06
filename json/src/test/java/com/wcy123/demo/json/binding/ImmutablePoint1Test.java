package com.wcy123.demo.json.binding;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImmutablePoint1Test {
    @Rule
    public TestLogger logger = new TestLogger(ImmutablePoint1Test.class);

    @Test
    public void main() throws IOException {
        final ImmutablePoint1 point1 = new ImmutablePoint1(3, 4);
        final ObjectMapper mapper = new ObjectMapper();
        try {
            final String json = mapper.writeValueAsString(point1);
            final ImmutablePoint1 point2 = mapper.readValue(json, ImmutablePoint1.class);

            logger.log("point1 is " + point1);
            logger.log("point2 is " + point2);
            logger.log("json is " + json);
        } catch (JsonMappingException e) {
            logger.log(e.toString());
        }
    }

}
