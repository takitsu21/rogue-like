package com.mady.utils.items.stuff;

import com.mady.utils.Position;

public class Pants extends AbstractStuffItem {
    public Pants(int HP, int MP, int ATK, int DEF, int AGI, int LUK) {
        super("pant", HP, MP, ATK, DEF, AGI, LUK);
    }

    @Override
    public Boolean isDrinkable() {
        return false;
    }

    @Override
    public Boolean isPickable() {
        return true;
    }

    @Override
    public String getRepr() {
        return null;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position position) {

    }
}
