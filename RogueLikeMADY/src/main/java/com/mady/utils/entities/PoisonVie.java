package com.mady.utils.entities;

public class PoisonVie extends AbstractItem {
    public PoisonVie(Position position) {
        super(position, 0, (int) (Math.random() * 3), "Poison de vie");
    }

    @Override
    public void act(Player player) {
        if (player.getHitPoints()-getDamages() >= 0) {
            player.setHitPoints(player.getHitPoints()-getDamages());
        }
        else{
            player.setHitPoints(0);
        }
    }
}
