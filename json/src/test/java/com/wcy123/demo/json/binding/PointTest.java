package com.wcy123.demo.json.binding;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class PointTest {
    @Rule
    public TestLogger logger = new TestLogger(PointTest.class);

    @Test
    public void main() throws IOException {
        final Point point1 = new Point();
        point1.setX(3);
        point1.setY(4);
        final ObjectMapper mapper = new ObjectMapper();
        final String json = mapper.writeValueAsString(point1);
        final Point point2 = mapper.readValue(json, Point.class);

        logger.log("point1 is " + point1);
        logger.log("point2 is " + point2);
        logger.log("json is " + json);
    }
}
