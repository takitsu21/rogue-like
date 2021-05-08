package com.mady.utils.entities;

public enum Deplacement {
    HAUT(new Position(-1, 0)),
    BAS(new Position(1, 0)),
    DROITE(new Position(0, 1)),
    GAUCHE(new Position(0, -1));

    public Position pos;

    Deplacement(Position pos) {
        this.pos = pos;
    }
}
