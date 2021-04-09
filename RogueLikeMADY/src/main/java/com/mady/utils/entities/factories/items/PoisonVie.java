package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.AbstractItem;

public class PoisonVie extends AbstractItem {
    public PoisonVie(Position position) {
        super(position, 0, (int) (Math.random() * 2)+1 , "Poison de vie",true,false);
    }

    @Override
    public void act(Player player) {
        if (player.getHitPoints()-getDamages() >= 0) {
            player.setHP(player.getHP()-getDamages());
        }
        else{
            player.setHP(0);
        }
    }
    @Override
    public String getRepresentation() {
        return "V";
    }
}

