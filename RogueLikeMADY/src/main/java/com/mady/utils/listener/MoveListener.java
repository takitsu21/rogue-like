package com.mady.utils.listener;

import com.mady.utils.KeyboardPressedEnum;
import com.mady.utils.Map;
import com.mady.utils.Util;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.Chest;
import com.mady.utils.entities.factories.items.Item;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class MoveListener implements KeyListener {
    private Map map;

    public MoveListener(Map map) {
        this.map = map;
    }


    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println((int)e.getKeyChar());
//        System.out.println(KeyEvent.VK_Z);
//        System.out.println(e.getExtendedKeyCode());
//        System.out.println((int)e.getKeyChar() - KeyEvent.VK_A);
//        switch((int)e.getKeyChar() - KeyEvent.VK_A){
//            case KeyEvent.VK_Z: // Touche Z
//                map.move(map.getPlayer(),new Position(-1,0 ));
//                break;
//            case KeyEvent.VK_S://touche s
//                map.move(map.getPlayer(),new Position(1, 0));
//                break;
//            case KeyEvent.VK_Q://touche q
//                map.move(map.getPlayer(),new Position(0, -1));
//                break;
//            case KeyEvent.VK_D: //touche d
//                map.move(map.getPlayer(),new Position(0, 1));
//                break;
//
//            case KeyEvent.VK_X: // Touche x
//                int x = map.getPlayer().getPosition().getX();
//                int y = map.getPlayer().getPosition().getY();
//
//                System.out.printf("Player pos : %s\n", map.getPlayer().getPosition());
//                for (int i = x - 1; i <= x + 1; i++) {
//                    for (int j = y - 1; j <= y + 1; j++) {
//                        Item item = map.getCase(i, j).getItem();
//                        System.out.println(new Position(i, j));
//                        if (item instanceof Chest) {
//                            System.out.printf("Picking item at %s", new Position(i, j));
//                            map.getPlayer().pickItem(map.getCase(i, j));
//
//                        }
//                    }
//                }
//                break;
//            case KeyEvent.VK_I: // Touche i
//                if (Util.keyPressed == KeyboardPressedEnum.I) {
//                    Util.keyPressed = KeyboardPressedEnum.NONE;
//                    break;
//                }
//                Util.keyPressed = KeyboardPressedEnum.I;
//            case KeyEvent.VK_ESCAPE: // Touche escape
//                if (Util.keyPressed == KeyboardPressedEnum.I) {
//                    Util.keyPressed = KeyboardPressedEnum.NONE;
//                    break;
//                }
//            default:
//                return;
//
//        }
//        Util.playerTurn = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.printf("Key pressed : %d\n", e.getKeyCode());

        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z: // Touche Z
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    map.getPlayer().getInventory().setSelectedItem(((map.getPlayer().getInventory().getSelectedItem() - 1) < 0) ?
                            map.getPlayer().getInventory().getInventory().size() - 1 : (map.getPlayer().getInventory().getSelectedItem() - 1));
                } else {
                    map.move(map.getPlayer(), new Position(-1, 0));
                }
                break;
            case KeyEvent.VK_S:
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    map.getPlayer().getInventory().setSelectedItem((map.getPlayer().getInventory().getSelectedItem()
                            + 1) % ((map.getPlayer().getInventory().getInventory().size() > 0) ?
                            map.getPlayer().getInventory().getInventory().size() : 1));
                } else {
                    map.move(map.getPlayer(), new Position(1, 0));
                }
                break;
            case KeyEvent.VK_Q://touche q

                map.move(map.getPlayer(), new Position(0, -1));

                break;
            case KeyEvent.VK_D: //touche d


                map.move(map.getPlayer(), new Position(0, 1));

                break;
            case KeyEvent.VK_A:
//                List<Entities> et= map.zoneCheckAround();
//                System.out.printf("dans la zone : ");
//                for (Entities ez : et) {
//                    System.out.printf("repr : %s |  pos : %s HP : %s", ez.getRepr(), ez.getPosition(), ez.getHitPoints());
//                }
                map.getPlayer().zoneAttack(map.zoneCheckAround(), map);
                System.out.println(map.zoneCheckAround());
                break;
            case KeyEvent.VK_E:
                map.getPlayer().closeAttack(map.closeCheckAround(), map);
                break;

            case KeyEvent.VK_X: // Touche x
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
            case KeyEvent.VK_I: // Touche i
                System.out.println("On est dans le I");
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                    break;
                }
                Util.keyPressed = KeyboardPressedEnum.I;
                break;

            case KeyEvent.VK_ESCAPE: // Touche escape
                System.out.println("On est dans le esc");
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                }
                break;
            case KeyEvent.VK_ENTER:
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    System.out.println(map.getPlayer().getInventory().getSelectedItem());
                    System.out.println(map.getPlayer().getInventory().getInventory().size());
                    System.out.println(map.getPlayer().getInventory().getInventory().get(map.getPlayer().getInventory().getSelectedItem()));
                    map.getPlayer().equipItem(map.getPlayer().getInventory().getSelectedItem());
                    map.getPlayer().getInventory().setSelectedItem(0);
                    if(map.getPlayer().getInventory().getInventory().size() == 0 ){
                        Util.keyPressed = KeyboardPressedEnum.NONE;
                    }
                }
                break;
            case 'a':
                List<Entities> et= map.zoneCheckAround();
                System.out.printf("dans la zone : ");
                for (Entities ez : et) {
                    System.out.printf("repr : %s |  pos : %s HP : %s", ez.getRepr(), ez.getPosition(), ez.getHitPoints());
                }
                map.getPlayer().zoneAttack(map.zoneCheckAround(), map);
                System.out.println(map.zoneCheckAround());
                break;
            case 'e':
                map.getPlayer().closeAttack(map.closeCheckAround(), map);
                break;
            default:
                return;

        }
        Util.playerTurn = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
