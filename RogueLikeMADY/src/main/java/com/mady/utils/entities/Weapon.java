package com.mady.utils.entities;

public class Weapon extends AbstractStuffItem {
    public Weapon(double HP, double MP, double ATK, double DEF, double AGI, double LUK) {
        super("weapon", HP, MP, ATK, DEF, AGI, LUK);
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
    public String getRepresentation() {
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
