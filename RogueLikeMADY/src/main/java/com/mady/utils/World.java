package com.mady.utils;

import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.Shop;

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

    public void addShop() {
        Shop s;
        s = new Shop(getCurrentMap().getPlayer(),getCurrentMap().getPlayer().getPosition());
        maps.add(s);
        currentMap = s;

    }
    public void LeaveShop(){
        Map m = maps.get(maps.indexOf(currentMap)-1);
        Position oldPos;
        if( currentMap instanceof Shop){
            oldPos = ((Shop) currentMap).getOldPos();
        }
        else{
            oldPos = m.chooseSalle().getFreePos();
        }
        m.getMap()[oldPos.getX()][oldPos.getY()] = new Case(" ",CaseType.SALLE);
        m.setPlayer(currentMap.getPlayer());
        m.getPlayer().setPos(oldPos);
        maps.remove(currentMap);
        currentMap = m;
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
