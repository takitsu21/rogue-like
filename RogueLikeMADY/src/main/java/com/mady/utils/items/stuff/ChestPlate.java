package com.mady.utils.items.stuff;

import com.mady.utils.Position;

public class ChestPlate extends AbstractStuffItem {
    public ChestPlate(int HP, int MP, int ATK, int DEF, int AGI, int LUK) {
        super("chest", HP, MP, ATK, DEF, AGI, LUK);
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
