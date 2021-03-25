package com.mady.utils.entities;

public interface Entities {
    Position getPosition();
    void setPos(Position position);

    int getMaxHitPoints();
    void setMaxHitPoints(int maxHitPoints);

    int getHitPoints();
    void setHitPoints(int hitPoints);

    void takeDamages(int damages);

    int getDamages();
    void setDamages(int damages);

    double getMovement();

    int getMaxDammages();
    void setMaxDammages(int maxDammages);

    String getRepr();

    void doTurn(Player player);



}
