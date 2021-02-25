package com.mady.utils;

public class PoisonForce extends AbstractItem {
    public PoisonForce(Position position) {
        super(position, 0, (int) (Math.random() * 3), "Poison de force");
    }

    @Override
    public void act(Player player) {
        if (player.getAttack()-getDamages() >= 0) {
            player.setAttack(player.getAttack()-getDamages());
        }
    }
}
