package org.wcy123.fp;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

import com.google.common.collect.ImmutableMap;

public class PatternTest {
    @Test
    public void main1() throws Exception {
        Pattern var = Pattern.var();
        final Pattern pattern = Pattern.from(JsonNodeFactory.instance.numberNode(1),
                JsonNodeFactory.instance.textNode("hello"), var,
                Pattern.fromMap(ImmutableMap.of(var, var, JsonNodeFactory.instance.textNode("one"),
                        JsonNodeFactory.instance.numberNode(1))));
        // final boolean hello = pattern.match("hello");
        // assertFalse(hello);
        // language=JSON
        String json = "[1, \"hello\", \"abc\", {\"abc\":\"abc\", \"one\":1, \"two\":2}]";
        final JsonNode jsonNode = new ObjectMapper().readTree(json);
        final boolean jsonMatch = pattern.match(jsonNode);
        assertTrue(jsonMatch);
    }
}
