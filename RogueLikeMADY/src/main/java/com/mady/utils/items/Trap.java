package com.mady.utils.items;

import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.entities.Player;
import com.mady.utils.environment.Map;

public class Trap extends AbstractItem {
    private Map map;

    public Trap(Position position, int lvl, double multiplicateur, Map map) {
        super("?", position, 0, 0, "piege", false, false, lvl, multiplicateur);
        this.map = map;
    }


    @Override
    public void act(Player player) {
        switch (Util.r.nextInt(2)) {
            case 0:
                Coins coins = new Coins(this.getPosition());
                coins.act(player);
                break;
            case 1:
                map.addEntity(5, player.getSalle());
                break;
            default:
                //erreur
        }
    }
}





