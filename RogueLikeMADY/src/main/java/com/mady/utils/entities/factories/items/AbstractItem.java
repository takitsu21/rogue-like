package com.mady.utils.entities.factories.items;


import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.entities.Position;

public abstract class AbstractItem implements Item {

    private final double movement;
    private final int damages;
    private final String name;
    private final Boolean drinkable;
    private final Boolean pickable;
    private final String repr;
    private Position position;

    public AbstractItem(String repr, Position position, double movement, int damages, String name, Boolean drinkable, Boolean pickable) {
        this.repr = repr;
        this.position = position;
        this.movement = movement;
        this.damages = damages;
        this.name = name;
        this.drinkable = drinkable;
        this.pickable = pickable;
    }


    @Override
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public double getMovement() {
        return movement;
    }

    public int getDamages() {
        return damages;
    }

    public String getName() {
        return name;
    }

    public Boolean isDrinkable() {
        return drinkable;
    }

    public Boolean isPickable() {
        return pickable;
    }

    @Override
    public String getRepresentation() {
        return Ansi.colorize(repr, Attribute.YELLOW_TEXT());
    }
}
