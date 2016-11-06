package com.wcy123.demo.json.binding;

public class ImmutablePoint1 {
    private final int x;
    private final int y;

    public ImmutablePoint1(int x, int y) {
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
