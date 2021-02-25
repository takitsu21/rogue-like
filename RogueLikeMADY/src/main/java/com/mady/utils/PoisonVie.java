package com.mady.utils;

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
