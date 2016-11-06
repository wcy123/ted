package com.wcy123.demo.json.binding;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ImmutablePoint3 {
    private final int x;
    private final int y;

    public ImmutablePoint3(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @JsonCreator
    public static ImmutablePoint3 createPoint(@JsonProperty("x") int x, @JsonProperty("y") int y) {
        return new ImmutablePoint3(x, y);
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
