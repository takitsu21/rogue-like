package com.mady.utils;

import com.mady.utils.entities.Position;

public class PairPos {
    private final Position p1;
    private final Position p2;


    public PairPos(Position p1, Position p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Position getP1() {
        return p1;
    }

    public Position getP2() {
        return p2;
    }

}
