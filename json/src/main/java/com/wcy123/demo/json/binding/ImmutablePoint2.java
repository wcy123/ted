package com.wcy123.demo.json.binding;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImmutablePoint2 {
    private final int x;
    private final int y;

    @JsonCreator
    public ImmutablePoint2(
            @JsonProperty("x") int x,
            @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Point{" + "x=" + x + ", y=" + y + '}';
    }
}
