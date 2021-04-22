package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class PoisonVie extends AbstractItem {
    public PoisonVie(Position position, int lvl, double multiplicateur) {
        super("V", position, 0, (int) (Math.random() * 2) + 1, "Poison de vie", true, false, lvl, multiplicateur);
    }

    /**
     * Utilise la poison vie sur le joueur.
     *
     * @param player Player
     */
    @Override
    public void act(Player player) {
        if (player.getHitPoints() - getDamages() >= 0) {
            int oldHp = (int) player.getHitPoints();
            player.setHitPoints(player.getHitPoints() - getDamages());
            Util.currentAction.append(Ansi.colorize(String.format("%s vous réduit la vie de %d à %d (-%d)",
                    getName(), oldHp, (int) player.getHitPoints(), getDamages()), Attribute.YELLOW_TEXT()));

        } else {
            player.setHitPoints(0);
        }
    }
}

