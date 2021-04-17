package com.mady.utils.listener;

import com.mady.utils.KeyboardPressedEnum;
import com.mady.utils.Map;
import com.mady.utils.Util;
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
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z: // Touche Z
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    map.getPlayer().getInventory().
                            setSelectedItem(((map.getPlayer().getInventory().getSelectedItem() - 1) < 0) ?
                                    map.getPlayer().getInventory().getInventory().size() - 1 :
                                    (map.getPlayer().getInventory().getSelectedItem() - 1));
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
                map.getPlayer().zoneAttack(map.zoneCheckAround(), map);
                break;
            case KeyEvent.VK_E:
                map.getPlayer().closeAttack(map.closeCheckAround(), map);
                break;
            case KeyEvent.VK_BACK_SPACE:
                if (Util.keyPressed == KeyboardPressedEnum.I && map.getPlayer().getInventory().getInventory().size() >= 1) {
                    map.getPlayer().getInventory().getInventory().remove( map.getPlayer().getInventory().getSelectedItem());
                    map.getPlayer().getInventory().setSelectedItem(0);
                    if (map.getPlayer().getInventory().getInventory().size() == 0) {
                        Util.keyPressed = KeyboardPressedEnum.NONE;
                    }
                }
                else{
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                }
                break;

            case KeyEvent.VK_X: // Touche x
                int x = map.getPlayer().getPosition().getX();
                int y = map.getPlayer().getPosition().getY();
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        Item item = map.getCase(i, j).getItem();
                        if (item instanceof Chest) {
                            map.getPlayer().pickItem(map.getCase(i, j));
                        }
                    }
                }

                break;
            case KeyEvent.VK_I: // Touche i
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                    break;
                }
                Util.keyPressed = KeyboardPressedEnum.I;
                break;

            case KeyEvent.VK_ESCAPE: // Touche escape
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                }
                break;
            case KeyEvent.VK_ENTER:
                if (Util.keyPressed == KeyboardPressedEnum.I && map.getPlayer().getInventory().getInventory().size() >= 1) {
                    map.getPlayer().equipItem(map.getPlayer().getInventory().getSelectedItem());
                    map.getPlayer().getInventory().setSelectedItem(0);
                    if (map.getPlayer().getInventory().getInventory().size() == 0) {
                        Util.keyPressed = KeyboardPressedEnum.NONE;
                    }
                }
                else{
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                }
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
