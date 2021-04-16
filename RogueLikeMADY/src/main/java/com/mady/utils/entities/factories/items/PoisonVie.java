package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.AbstractItem;

public class PoisonVie extends AbstractItem {
    public PoisonVie(Position position) {
        super(position, 0, (int) (Math.random() * 2)+1 , "Poison de vie",true,false);
    }

    /**
     * Utilise la poison vie sur le joueur.
     * @param player Player
     */
    @Override
    public void act(Player player) {
        if (player.getHP()-getDamages() >= 0) {
            int oldHp = (int)player.getHP();
            player.setHP(player.getHP()-getDamages());
            Util.currentAction.append(Ansi.colorize(String.format("%s vous réduit la vie de %d à %d (-%d)",
                    getName(), oldHp, (int)player.getHP(), getDamages()), Attribute.RED_TEXT()));

        }
        else{
            player.setHP(0);
        }
    }
    @Override
    public String getRepresentation() {
        return "V";
    }
}

