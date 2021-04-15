package com.mady.utils;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Map> maps = new ArrayList<>();
    private Map currentMap;
    private final Frame frame;

    public World(Frame frame) {
        this.frame = frame;
    }

    public void createWorld() {
        addMap();
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map map) {
        currentMap = map;
    }

    public void addMap() {
        boolean bCreatWorld;
        Map map;
        do {
            map = new Map(5, 24, 100, frame);
            bCreatWorld = map.createMap();
        }
        while (bCreatWorld);


        /*if(maps.size()==2){
            maps.remove(0);
        }*/
        maps.add(map);
        currentMap = map;

    }
}
