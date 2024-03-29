package com.mady.utils;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position incrementPos(Position pos) {
        return new Position(pos.getX() + getX(), pos.getY() + getY());
    }

    public Position getRandomPos(int maxX, int maxY) {
        return new Position(Util.r.nextInt(maxX) + 1, Util.r.nextInt(maxY) + 1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position o = (Position) obj;
            return o.getX() == getX() && o.getY() == this.getY();
        }

        return false;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", getX(), getY());
    }


    public boolean nextTo(Position pos) {
        return x == pos.getX() + 1 || x == pos.getX() - 1 ||
                y == pos.getY() + 1 || y == pos.getY() - 1;
    }


    public double getDistance(Position pos) {
        double dx = x - pos.x;
        double dy = y - pos.y;
        return Math.hypot(dx, dy);
    }


    public Position multiplyPos(int Mouvement) {
        return new Position(this.getX() * Mouvement, this.getY() * Mouvement);
    }
}
