package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.AbstractItem;

public class PotionVie extends AbstractItem {
    public PotionVie(Position position) {
        super(position, 0, (int) (Math.random() * 3), "Potion de vie");
    }


    @Override
    public void act(Player player) {
        if (player.getPV()+getDamages() <= player.getMaxPV()) {
            player.setPV(player.getPV()+getDamages());
    }
}




