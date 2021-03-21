package com.mady.utils.entities.factories.items;


import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.AbstractItem;

public class PotionForce extends AbstractItem {
    public PotionForce(Position position) {
        super(position, 0, (int) (Math.random() * 3), "Potion de force",true,false);
    }

    @Override
    public void act(Player player) {
        if (player.getDamages()+getDamages() <= player.getMaxDammages()) {
            player.setDamages(player.getDamages()+getDamages());
        }
        else{
            player.setDamages(player.getMaxDammages());
        }
    }
}
