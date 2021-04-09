package com.mady.utils.listener;

import com.mady.utils.Map;
import com.mady.utils.Util;
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
            case 'a':
                map.getPlayer().zoneAttack(map.zoneCheckAround(), map);
                break;
            case 'e':
                map.getPlayer().closeAttack(map.closeCheckAround(), map);
                break;
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
