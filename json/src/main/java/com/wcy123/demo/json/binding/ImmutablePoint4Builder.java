package com.wcy123.demo.json.binding;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonPOJOBuilder(withPrefix = "set", buildMethodName = "createImmutablePoint4")
public class ImmutablePoint4Builder {
    private int x;
    private int y;

    public ImmutablePoint4Builder setX(int x) {
        this.x = x;
        return this;
    }

    public ImmutablePoint4Builder setY(int y) {
        this.y = y;
        return this;
    }

    public ImmutablePoint4 createImmutablePoint4() {
        return new ImmutablePoint4(x, y);
    }
}
