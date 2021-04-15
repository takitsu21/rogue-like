package com.mady.utils;

public enum KeyboardPressedEnum {
    NONE(0), I(1), ESC(2);

    public int type;

    KeyboardPressedEnum(int type) {
        this.type = type;
    }
}
