package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public interface Item {
    Position getPosition();

    void setPosition(Position position);


    int getDamages();

    String getName();

    void act(Player player);

    Boolean isDrinkable();

    Boolean isPickable();

    String getRepr();
}
