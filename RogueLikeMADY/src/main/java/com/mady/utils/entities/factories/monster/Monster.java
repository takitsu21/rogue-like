package com.mady.utils.entities.factories.monster;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Entities;

public interface Monster extends Entities {
    void act(Player player);
}
