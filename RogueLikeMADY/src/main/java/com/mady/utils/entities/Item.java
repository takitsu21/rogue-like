package com.mady.utils.entities;

public interface Item {
    Position getPosition();
    void setPosition(Position position);
    double getMovement();
    int getDamages();
    String getName();
    void act(Player player);
}
