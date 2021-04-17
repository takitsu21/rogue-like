package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class PoisonVie extends AbstractItem {
    public PoisonVie(Position position) {
        super("V", position, 0, (int) (Math.random() * 2) + 1, "Poison de vie", true, false);
    }

    /**
     * Utilise la poison vie sur le joueur.
     *
     * @param player Player
     */
    @Override
    public void act(Player player) {
        if (player.getHP() - getDamages() >= 0) {
            int oldHp = (int) player.getHP();
            player.setHP(player.getHP() - getDamages());
            Util.currentAction.append(Ansi.colorize(String.format("%s vous réduit la vie de %d à %d (-%d)",
                    getName(), oldHp, (int) player.getHP(), getDamages()), Attribute.YELLOW_TEXT()));

        } else {
            player.setHP(0);
        }
    }
}

