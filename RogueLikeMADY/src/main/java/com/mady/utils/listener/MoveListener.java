package com.mady.utils.listener;

import com.mady.utils.Map;
import com.mady.utils.Util;
import com.mady.utils.entities.AbstractStuffItem;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.Chest;
import com.mady.utils.entities.factories.items.Item;

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
            case 'x':
                int x = map.getPlayer().getPosition().getX();
                int y = map.getPlayer().getPosition().getY();

                System.out.printf("Player pos : %s\n", map.getPlayer().getPosition());
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        Item item = map.getCase(i, j).getItem();
                        System.out.println(new Position(i, j));
                        if (item instanceof Chest) {
                            System.out.printf("Picking item at %s", new Position(i, j));
                            map.getPlayer().pickItem(map.getCase(i, j));

                        }
                    }
                }
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
