package com.mady.utils.enums;

import com.mady.utils.Position;

public enum DeplacementEnum {
    HAUT(new Position(-1, 0)),
    BAS(new Position(1, 0)),
    DROITE(new Position(0, 1)),
    GAUCHE(new Position(0, -1));

    public Position pos;

    DeplacementEnum(Position pos) {
        this.pos = pos;
    }
}
