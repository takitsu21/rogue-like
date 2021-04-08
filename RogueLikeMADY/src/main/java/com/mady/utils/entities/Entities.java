package com.mady.utils.entities;

import com.mady.utils.Map;

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

    int getMovement();

    int getMaxDammages();
    void setMaxDammages(int maxDammages);

    String getRepr();

    Map doTurn(Map map);



}
