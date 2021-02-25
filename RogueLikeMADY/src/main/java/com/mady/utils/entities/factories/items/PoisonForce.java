package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.AbstractItem;

public class PoisonForce extends AbstractItem {
    public PoisonForce(Position position) {
        super(position, 0, (int) (Math.random() * 3), "Poison de force");
    }

    @Override
    public void act(Player player) {
        if (player.getDamages()-getDamages() >= 0) {
            player.setDamages(player.getDamages()-getDamages());
        }
        else{
            player.setDamages(0);
        }
    }
}
