package com.mady.utils.entities.factories.items;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.AbstractItem;

public class PotionVie extends AbstractItem {
    public PotionVie(Position position) {
        super(position, 0, (int) (Math.random() * 2)+1, "Potion de vie",true,false);
    }


    @Override
    public void act(Player player) {
//        if (player.getHP()+getDamages() <= player.getMaxHitPoints()) {
// une potion de vie peut faire gagner des points de vie bonus supérieur aux max hitpoint le comportement 101/100 est voulu pour permettre de stack de la vie en revanche le joueur ne pourra pas se reposer pour avoir plus de  maxhitpoint
            player.setHP(player.getHP() + getDamages());
//        }
//        else{
//            player.setHitPoints(player.getMaxHitPoints());
//        }

    }
    @Override
    public String getRepresentation() {
        return "V";
    }
}



