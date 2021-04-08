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
                System.out.println("avance");
                map.move(map.getPlayer(),new Position(-1,0 ));
                break;
            case 's':
                System.out.println("descend");
                map.move(map.getPlayer(),new Position(1, 0));

                break;
            case 'q':
                System.out.println("gauche");
                map.move(map.getPlayer(),new Position(0, -1));
                break;
            case 'd':
                System.out.println("droite");
                map.move(map.getPlayer(),new Position(0, 1));

                break;
            default:
                System.out.println("touche non bindé");
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
