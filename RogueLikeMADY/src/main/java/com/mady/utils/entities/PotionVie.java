package com.mady.utils.entities;

public class PotionVie extends AbstractItem {
    public PotionVie(Position position) {
        super(position, 0, (int) (Math.random() * 3), "Potion de vie");
    }



    @Override
    public void act(Player player) {
        if (player.getHitPoints()+getDamages() <= player.getMaxHitPoints()) {
            player.setHitPoints(player.getHitPoints() + getDamages());
        }
        else{
            player.setHitPoints(player.getMaxHitPoints());
        }

    }
}




