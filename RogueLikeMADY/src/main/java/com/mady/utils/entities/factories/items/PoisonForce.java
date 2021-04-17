package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class PoisonForce extends AbstractItem {
    public PoisonForce(Position position) {
        super("F", position, 0, (int) (Math.random() * 2) + 1, "Poison de force", true, false);
    }


    @Override
    public void act(Player player) {
        if (player.getDamages() - getDamages() > 1) {
            int oldDamages = player.getDamages();
            player.setDamages(player.getDamages() - getDamages());
            Util.currentAction.append(Ansi.colorize(String.format("%s vous réduit l'ATK de %d à %d (-%d)\n",
                    getName(), oldDamages, player.getDamages(), getDamages()), Attribute.RED_TEXT()));
        } else {
            player.setDamages(0);
        }
    }
}
