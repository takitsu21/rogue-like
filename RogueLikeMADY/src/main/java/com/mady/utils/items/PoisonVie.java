package com.mady.utils.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;

public class PoisonVie extends AbstractItem {
    public PoisonVie(Position position, int lvl, double multiplicateur) {
        super("V", position, 0, 2, "Poison de vie", true, false, lvl, multiplicateur);
    }

    /**
     * Utilise la poison vie sur le joueur.
     *
     * @param player Player
     */
    @Override
    public void act(Player player) {
        int HP=Util.getPercent(player.getMaxHitPoints(),getDamages());
        System.out.println(HP);
        int oldHp = player.getHitPoints();
        player.setHitPoints(Math.max(player.getHitPoints() - HP, 0));
        Util.currentAction.append(Ansi.colorize(String.format("%s vous réduit la vie de %d à %d (-%d)\n",
                getName(), oldHp, player.getHitPoints(), HP), Attribute.YELLOW_TEXT()));


    }
}

