package com.mady.utils;

public enum CaseType {
    MAP(1), WALL(2), SALLE(3), PATH(4), PORTAL(5);

    public int type;

    CaseType(int type) {
        this.type = type;
    }
}
