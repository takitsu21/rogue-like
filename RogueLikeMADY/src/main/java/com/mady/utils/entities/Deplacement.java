package com.mady.utils.entities;

public enum Deplacement {
    HAUT(new Position(0, 1)), BAS (new Position(0, -1)), DROITE (new Position(1, 0)), GAUCHE (new Position(-1, 0));

    public Position pos;

     Deplacement(Position pos) {
        this.pos = pos;
    }
}
