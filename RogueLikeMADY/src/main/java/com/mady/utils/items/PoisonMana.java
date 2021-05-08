package com.mady.utils.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;

public class PoisonMana extends AbstractItem {
    public PoisonMana(Position position, int lvl, double multiplicateur) {
        super("M", position, 0, (int) (Math.random() * 2) + 1, "Poison de mana", true, false, lvl, multiplicateur);
    }


    @Override
    public void act(Player player) {
        if (player.getMP() - getDamages() > 1) {
            int oldMP = player.getMP();
            player.setMP(Math.max(player.getMP() - getDamages(), 0));
            Util.currentAction.append(Ansi.colorize(String.format("%s vous réduit votre mana de %d à %d (-%d)\n",
                    getName(), oldMP, player.getMP(), getDamages()), Attribute.YELLOW_TEXT()));
        } else {
            player.setDamages(0);
        }
    }
}
