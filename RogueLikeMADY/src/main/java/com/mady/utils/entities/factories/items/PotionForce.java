package com.mady.utils.entities.factories.items;


import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class PotionForce extends AbstractItem {
    public PotionForce(Position position) {
        super("F", position, 0, (int) (Math.random() * 2) + 1, "Potion de force", true, false);
    }

    @Override
    public void act(Player player) {
//        if (player.getDamages()+getDamages() <= player.getMaxDammages()) {
        int oldDamages = player.getDamages();
        player.setDamages(player.getDamages() + getDamages());
        Util.currentAction.append(Ansi.colorize(String.format("%s vous augmente l'ATK de %d à %d (+%d)",
                getName(), oldDamages, player.getDamages(), getDamages()), Attribute.GREEN_TEXT()));
//        }
//        else{
//            player.setDamages(player.getMaxDammages());
//        }
    }

}
