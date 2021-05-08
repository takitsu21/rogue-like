package com.mady.utils.items;

import com.mady.utils.Position;
import com.mady.utils.entities.Player;

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
