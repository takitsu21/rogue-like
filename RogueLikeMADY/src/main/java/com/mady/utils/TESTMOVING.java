package com.mady.utils;

import com.mady.utils.entities.Player;
import com.mady.utils.listener.MoveListener;

public class TESTMOVING {
   Player player;
    Map m ;

    public TESTMOVING(Player player, Map m) {
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Map getM() {
        return m;
    }

    public void setM(Map m) {
        this.m = m;
    }

    public TESTMOVING() {
        Map map = new Map(6, 24, 128);

        map.createMap();
        this.player = new Player(map.randomPosPlayerInSalle(), 10, 5, 1, "@");
        this.m = map;

    }

    public static void main(String[] args) {

        TESTMOVING test =  new TESTMOVING();
        test.getM().getFrame().getFrame().addKeyListener(new MoveListener(test));
        test.getM().addPlayerToMap(test.getPlayer());
        System.out.println(test.getM());
    }
}
