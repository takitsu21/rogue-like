package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.AbstractItem;

public class PoisonVie extends AbstractItem {
    public PoisonVie(Position position) {
        super(position, 0, (int) (Math.random() * 3), "Poison de vie");
    }

    @Override
    public void act(Player player) {
        if (player.getPV()-getDamages() >= 0) {
            player.setPV(player.getPV()-getDamages());
        }
    }
}
