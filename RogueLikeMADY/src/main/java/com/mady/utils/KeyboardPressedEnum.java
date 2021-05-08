package com.mady.utils;

public enum KeyboardPressedEnum {
    NONE(0), I(1), ESC(2), WELCOME(3), HELP(4), SELL(5);

    public int type;

    KeyboardPressedEnum(int type) {
        this.type = type;
    }
}
