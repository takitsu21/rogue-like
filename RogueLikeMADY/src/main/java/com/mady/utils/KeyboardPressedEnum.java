package com.mady.utils;

public enum KeyboardPressedEnum {
    NONE(0), I(1), ESC(2),P(3), PLUS(4);

    public int type;

    KeyboardPressedEnum(int type) {
        this.type = type;
    }
}
