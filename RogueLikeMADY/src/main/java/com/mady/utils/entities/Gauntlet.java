package com.mady.utils.entities;

public class Gauntlet extends AbstractStuffItem {

    public Gauntlet(int HP, int MP, int ATK, int DEF, int AGI, int LUK) {
        super("gauntlet", HP, MP, ATK, DEF, AGI, LUK);
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
