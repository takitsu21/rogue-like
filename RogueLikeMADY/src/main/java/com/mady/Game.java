package com.mady;

import com.mady.utils.Map;

public class Game {
    public static void main(String[] args) {
        Map map = new Map(5, 24, 64);
        map.createMap();
        System.out.println(map.toString());
    }
}
