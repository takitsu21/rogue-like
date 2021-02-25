package com.mady.utils.entities;


public class PotionForce extends AbstractItem {
    public PotionForce(Position position) {
        super(position, 0, (int) (Math.random() * 3), "Potion de force");
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
