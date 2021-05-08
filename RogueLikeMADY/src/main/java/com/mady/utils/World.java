package com.mady.utils;

import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.Shop;

import java.util.ArrayList;
import java.util.List;

public class World {

    private final List<Map> maps = new ArrayList<>();
    private final Frame frame;
    private Map currentMap;
    private int compteur=0;

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
        m.getMap()[ m.getPlayer().getPosition().getX()][ m.getPlayer().getPosition().getY()].setEntity(m.getPlayer());
        maps.remove(currentMap);
        currentMap = m;
    }

    public void addMap() {
        boolean bCreatWorld;
        Map map;
        compteur+=1;
        do {
            map = new Map(6, 24, 128, compteur%3==0);
            bCreatWorld = map.createMap();
        }
        while (bCreatWorld);


        maps.add(map);
        currentMap = map;

    }
}
