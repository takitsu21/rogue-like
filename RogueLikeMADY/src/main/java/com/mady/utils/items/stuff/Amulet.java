package com.mady.utils.items.stuff;

import com.mady.utils.Position;

public class Amulet extends AbstractStuffItem {
    /**
     * @param HP  double
     * @param MP  double
     * @param ATK double
     * @param DEF double
     * @param AGI double
     * @param LUK double
     */
    public Amulet(int HP, int MP, int ATK, int DEF, int AGI, int LUK) {
        super("amulet", HP, MP, ATK, DEF, AGI, LUK);
    }

    public Amulet(int LUK) {
        this(0, 0, 0, 0, 0, LUK);
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position position) {

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
}
