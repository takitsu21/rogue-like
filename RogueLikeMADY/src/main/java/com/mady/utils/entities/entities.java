package com.mady.utils.entities;

public interface entities {
    Position getPosition();
    void setPos(Position position);
    int getMaxHitPoints();
    int getHitPoints();
    void takeDamages(int damages);
    int getDamages();
    double getMovement();
}
