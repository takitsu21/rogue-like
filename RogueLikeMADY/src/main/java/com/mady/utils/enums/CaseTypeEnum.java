package com.mady.utils.enums;

public enum CaseTypeEnum {
    MAP(1), WALL(2), SALLE(3), PATH(4), PORTAL(5), SHOPPORTAL(6), SHOPLEAVE(7), PRICE(8), TRAP(9);

    public int type;

    CaseTypeEnum(int type) {
        this.type = type;
    }
}
