package com.mady;

import com.mady.utils.Map;
import com.mady.utils.entities.Player;

public class Game {
    public static void main(String[] args) {
        Map map = new Map(6, 24, 128);

        map.createMap();
        Player player = new Player(map.randomPosPlayerInSalle(), 10, 5, 1, "@");
        map.addPlayerToMap(player);
        System.out.println(map);
    }
}
