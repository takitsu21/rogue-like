package com.mady.utils.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;

public class PoisonMana extends AbstractItem {
    public PoisonMana(Position position, int lvl, double multiplicateur) {
        super("M", position, 0, 2, "Poison de mana", true, false, lvl, multiplicateur);
    }


    @Override
    public void act(Player player) {
        int MP=Util.getPercent(player.getMaxMp(),getDamages());
        int oldMP = player.getMP();
        player.setMP(Math.max(player.getMP() - MP, 0));
        Util.currentAction.append(Ansi.colorize(String.format("%s vous réduit votre mana de %d à %d (-%d)\n",
                getName(), oldMP, player.getMP(), MP), Attribute.YELLOW_TEXT()));

    }
}
