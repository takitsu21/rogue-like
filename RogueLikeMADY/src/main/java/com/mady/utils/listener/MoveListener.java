package com.mady.utils.listener;

import com.mady.utils.Map;
import com.mady.utils.TESTMOVING;
import com.mady.utils.entities.Deplacement;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveListener implements KeyListener {
    TESTMOVING test;
    public MoveListener(TESTMOVING test) {
        this.test = test;

    }


    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyChar()){
            case 'z':
                System.out.println("avance");
                test.getM().move(test.getM().getPlayer(),new Position(-1,0 ));
                break;
            case 's':
                System.out.println("descend");
                test.getM().move(test.getM().getPlayer(),new Position(1, 0));

                break;
            case 'q':
                System.out.println("gauche");
                test.getM().move(test.getM().getPlayer(),new Position(0, -1));
                break;
            case 'd':
                System.out.println("droite");
                test.getM().move(test.getM().getPlayer(),new Position(0, 1));

                break;
            default:
                System.out.println("touche non bindé");

        }
        System.out.println(test.getM());

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
