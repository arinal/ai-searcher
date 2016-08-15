package org.stei.sample.skiing;

public enum Direction {
    North(0, -1),
    South(0, 1),
    West(-1, 0),
    East(1, 0);

    private final int deltaX;
    private final int deltaY;

    public int deltaX() {
        return deltaX;
    }

    public int deltaY() {
        return deltaY;
    }

    Direction(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }
}