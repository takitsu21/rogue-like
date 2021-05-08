package com.mady.utils.items;


import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;

public class PotionMana extends AbstractItem {
    public PotionMana(Position position, int lvl, double multiplicateur) {
        super("M", position, 0, (int) (Math.random() * 2) + 1, "Potion de mana", true, false, lvl, multiplicateur);
    }

    @Override
    public void act(Player player) {
//        if (player.getDamages()+getDamages() <= player.getMaxDammages()) {
        int oldMP = player.getMP();
        player.setMP(player.getMP() + getDamages());
        Util.currentAction.append(Ansi.colorize(String.format("%s vous augmente votre mana de %d à %d (+%d)\n",
                getName(), oldMP, player.getMP(), getDamages()), Attribute.GREEN_TEXT()));
//        }
//        else{
//            player.setDamages(player.getMaxDammages());
//        }
    }

}
