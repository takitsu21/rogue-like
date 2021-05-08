package com.mady.utils.enums;

public enum KeyboardPressedEnum {
    NONE(0), I(1), ESC(2), WELCOME(3), HELP(4), SELL(5), T(6);

    public int type;

    KeyboardPressedEnum(int type) {
        this.type = type;
    }
}
