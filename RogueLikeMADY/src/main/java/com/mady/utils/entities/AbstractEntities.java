package com.mady.utils.entities;

public abstract class AbstractEntities implements Entities {
    private Position pos;
    private int maxHitPoints;
    private int hitPoints;
    private int damages;
    private final double movement;
    private final String repr;

    public AbstractEntities(Position pos, int hitPoints, int damages, double movement, String repr) {
        this.pos = pos;
        maxHitPoints = hitPoints;
        this.hitPoints = hitPoints;
        this.damages = damages;
        this.movement = movement;
        this.repr = repr;
    }

    @Override
    public Position getPosition() {
        return pos;
    }

    @Override
    public void setPos(Position position) {
        this.pos = position;
    }

    @Override
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    @Override
    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints=maxHitPoints;}

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints=hitPoints;
    }

    @Override
    public void takeDamages(int damages) {

        setHitPoints(getHitPoints()-damages);

        /* TODO : gérer la mort */
    }

    @Override
    public int getDamages() {
        return damages;
    }

    @Override
    public void setDamages(int damages) {
        this.damages=damages;
    }

    @Override
    public double getMovement() {
        return movement;
    }
}
