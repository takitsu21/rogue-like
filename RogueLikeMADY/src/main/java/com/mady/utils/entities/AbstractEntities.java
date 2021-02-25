package com.mady.utils.entities;

public abstract class AbstractEntities implements  entities{
    private Position pos;
    private  final int maxHitPoints;
    private int hitPoints;
    private final int damages;
    private final double movement;

    public AbstractEntities(Position pos, int hitPoints, int damages, double movement) {
        this.pos = pos;
        maxHitPoints = hitPoints;
        this.hitPoints = hitPoints;
        this.damages = damages;
        this.movement = movement;
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
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public void takeDamages(int damages) {
        hitPoints -= damages;
        /* TODO : gérer la mort */
    }

    @Override
    public int getDamages() {
        return damages;
    }

    @Override
    public double getMovement() {
        return movement;
    }
}
