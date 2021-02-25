package com.mady.utils;


public abstract class AbstractItem implements Item{

    private Position position;
    private final double movement;
    private final int damages;
    private final String name;

    public AbstractItem(Position position, double movement, int damages, String name) {
        this.position = position;
        this.movement = movement;
        this.damages = damages;
        this.name = name;
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

}
