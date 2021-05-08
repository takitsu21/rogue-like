package com.mady.utils.entities;

import com.mady.utils.Position;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;

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

    String getRepr();

    Map doTurn(Map map);

    boolean isDead(Map map);

    Salle getSalle();

    void setSalle(Salle salle);

    String getName();

    int getNbDeplacement();

    void setNbDeplacement(int nbDeplacement);


}
