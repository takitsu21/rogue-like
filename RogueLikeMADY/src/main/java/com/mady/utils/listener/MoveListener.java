package com.mady.utils.listener;

import com.mady.utils.Map;
import com.mady.utils.Util;
import com.mady.utils.entities.AbstractStuffItem;
import com.mady.utils.entities.Position;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveListener implements KeyListener {
    private Map map;
    public MoveListener(Map map) {
        this.map = map;

    }


    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'z':
                map.move(map.getPlayer(),new Position(-1,0 ));
                break;
            case 's':
                map.move(map.getPlayer(),new Position(1, 0));
                break;
            case 'q':
                map.move(map.getPlayer(),new Position(0, -1));
                break;
            case 'd':
                map.move(map.getPlayer(),new Position(0, 1));
                break;
            case 'm':
                map.getPlayer().getStuff().setHelmet(new AbstractStuffItem(null) {
                    @Override
                    public Boolean isDrinkable() {
                        return null;
                    }

                    @Override
                    public Boolean isPickable() {
                        return null;
                    }

                    @Override
                    public String getRepresentation() {
                        return null;
                    }
                });
                map.getPlayer().getStuff().setWeapon(new AbstractStuffItem(null) {
                    @Override
                    public Boolean isDrinkable() {
                        return null;
                    }

                    @Override
                    public Boolean isPickable() {
                        return null;
                    }

                    @Override
                    public String getRepresentation() {
                        return null;
                    }
                });
                map.getPlayer().getStuff().setAmulet(new AbstractStuffItem(null) {
                    @Override
                    public Boolean isDrinkable() {
                        return null;
                    }

                    @Override
                    public Boolean isPickable() {
                        return null;
                    }

                    @Override
                    public String getRepresentation() {
                        return null;
                    }
                });
            default:
                return;

        }
        Util.playerTurn = false;
//        System.out.println(Util.playerTurn);
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
