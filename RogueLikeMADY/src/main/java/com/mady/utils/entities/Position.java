package com.mady.utils.entities;

import com.mady.utils.Util;

import java.util.Random;

public class Position {
    private int x;
    private int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }



    public Position getRandomPos(int maxX, int maxY){
        return new Position(Util.r.nextInt(maxX) + 1,Util.r.nextInt(maxY) + 1);
    }
    public Position moveTo(Position playerPos, double distance) {
        return getRandomPos(64,64);
    }
}
