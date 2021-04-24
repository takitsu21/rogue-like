package com.mady.utils;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Map> maps = new ArrayList<>();
    private final Frame frame;
    private Map currentMap;

    public World(Frame frame) {
        this.frame = frame;
    }

    public void createWorld() {
        addMap();
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void addMap() {
        boolean bCreatWorld;
        Map map;
        do {
            map = new Map(6, 24, 128);
            bCreatWorld = map.createMap();
        }
        while (bCreatWorld);


        maps.add(map);
        currentMap = map;

    }
}
