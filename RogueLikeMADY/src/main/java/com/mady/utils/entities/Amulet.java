package com.mady.utils.entities;

public class Amulet extends AbstractStuffItem {
    /**
     *
     * @param HP
     * @param MP
     * @param ATK
     * @param DEF
     * @param AGI
     * @param LUK
     */
    public Amulet(double HP, double MP, double ATK, double DEF, double AGI, double LUK) {
        super("amulet", HP, MP, ATK, DEF, AGI, LUK);
    }

    public Amulet(double LUK) {
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
    public String getRepresentation() {
        return null;
    }
}
