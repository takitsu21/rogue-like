package com.mady.utils.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;

public class PotionVie extends AbstractItem {
    public PotionVie(Position position, int lvl, double multiplicateur) {
        super("V", position, 0, (int) (Math.random() * 2) + 1, "Potion de vie", true, false, lvl, multiplicateur);

    }


    @Override
    public void act(Player player) {
// une potion de vie peut faire gagner des points de vie bonus supérieur aux max hitpoint le comportement 101/100 est voulu pour permettre de stack de la vie en revanche le joueur ne pourra pas se reposer pour avoir plus de  maxhitpoint
        int oldHp = player.getHitPoints();
        player.setHitPoints(player.getHitPoints() + getDamages());
        Util.currentAction.append(Ansi.colorize(String.format("%s vous augmente la vie de %d à %d (+%d)\n",
                getName(), oldHp, player.getHitPoints(), getDamages()), Attribute.GREEN_TEXT()));

    }
}



