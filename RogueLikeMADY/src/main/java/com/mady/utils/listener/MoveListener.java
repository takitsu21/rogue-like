package com.mady.utils.listener;

import com.mady.GameLoop;
import com.mady.utils.*;
import com.mady.utils.entities.Deplacement;
import com.mady.utils.entities.factories.items.Chest;
import com.mady.utils.entities.factories.items.Item;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MoveListener implements KeyListener {
    private final Map map;
    private final Pause pause;

    public MoveListener(Map map) {
        this.map = map;
        this.pause = map.getPause();
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * @param e switch case for each key that the player can use and actions performed.
     */

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z: // Touche Z
                if (Util.keyPressed == KeyboardPressedEnum.I) {   // si l'on se trouve dans l'inventaire
                    map.getPlayer().getInventory().
                            setSelectedItem(((map.getPlayer().getInventory().getSelectedItem() - 1) < 0) ?
                                    map.getPlayer().getInventory().getInventory().size() - 1 :
                                    (map.getPlayer().getInventory().getSelectedItem() - 1));
                } else if (Util.keyPressed == KeyboardPressedEnum.ESC) {
                    pause.setSelection(
                            ((pause.getSelection() - 1) < 0) ?
                                    pause.getListe().size() - 1 :
                                    pause.getSelection() - 1);

                } else if (Util.keyPressed == KeyboardPressedEnum.WELCOME) {
                    WelcomeMenu.CURSOR = Math.abs((WelcomeMenu.CURSOR - 1) % 3);
                }
                else {
                    map.move(map.getPlayer(), Deplacement.HAUT.pos);
                }
                break;
            case KeyEvent.VK_S: // Touche S
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    map.getPlayer().getInventory().setSelectedItem((map.getPlayer().getInventory().getSelectedItem()
                            + 1) % ((map.getPlayer().getInventory().getInventory().size() > 0) ?
                            map.getPlayer().getInventory().getInventory().size() : 1));
                } else if (Util.keyPressed == KeyboardPressedEnum.ESC) {
                    pause.setSelection((pause.getSelection() + 1) % pause.getListe().size());
                } else if (Util.keyPressed == KeyboardPressedEnum.WELCOME) {
                    WelcomeMenu.CURSOR = (WelcomeMenu.CURSOR + 1) % 3;
                }
                else {
                    map.move(map.getPlayer(), Deplacement.BAS.pos);
                }
                break;
            case KeyEvent.VK_Q: // Touche Q

                map.move(map.getPlayer(), Deplacement.GAUCHE.pos);

                break;
            case KeyEvent.VK_D: // Touche D
                map.move(map.getPlayer(), Deplacement.DROITE.pos);

                break;
            case KeyEvent.VK_A: // Touche A
                map.getPlayer().zoneAttack(map.zoneCheckAround(), map);
                break;
            case KeyEvent.VK_E: // Touche E
                map.getPlayer().closeAttack(map.closeCheckAround(), map);
                break;
            case KeyEvent.VK_BACK_SPACE:
                if (Util.keyPressed == KeyboardPressedEnum.I && map.getPlayer().getInventory().getInventory().size() >= 1) {
                    map.getPlayer().getInventory().getInventory().remove(map.getPlayer().getInventory().getSelectedItem());
                    map.getPlayer().getInventory().setSelectedItem(0);
                    if (map.getPlayer().getInventory().getInventory().size() == 0) {
                        Util.keyPressed = KeyboardPressedEnum.NONE;
                    }
                } else {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                }
                break;

            case KeyEvent.VK_X: // Touche X
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
            case KeyEvent.VK_I: // Touche I
                if (Util.keyPressed == KeyboardPressedEnum.I) {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                    break;
                }
                Util.keyPressed = KeyboardPressedEnum.I;
                break;
            case KeyEvent.VK_ESCAPE: // Touche Escape
                if (Util.keyPressed == KeyboardPressedEnum.I || Util.keyPressed == KeyboardPressedEnum.ESC) {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                } else if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                    Util.keyPressed = KeyboardPressedEnum.ESC;
                }
                break;
            case KeyEvent.VK_ENTER: // Touche Enter
                if (Util.keyPressed == KeyboardPressedEnum.I && map.getPlayer().getInventory().getInventory().size() >= 1) {
                    map.getPlayer().equipItem(map.getPlayer().getInventory().getSelectedItem());
                    map.getPlayer().getInventory().setSelectedItem(0);
                    if (map.getPlayer().getInventory().getInventory().size() == 0) {
                        Util.keyPressed = KeyboardPressedEnum.NONE;
                    }
                } else if (Util.keyPressed == KeyboardPressedEnum.ESC) {
                    switch (pause.getListe().get(pause.getSelection())) {
                        case "Resume":
                            Util.keyPressed = KeyboardPressedEnum.NONE;
                            break;
                        case "Restart":
                            //toDO
                            break;
                        case "Quit":
                            GameLoop.quit();
                            break;

                    }
                } else if (Util.keyPressed == KeyboardPressedEnum.WELCOME) {
                    if (WelcomeMenu.CURSOR == 0) {
                        Util.keyPressed = KeyboardPressedEnum.NONE;
                    } else if (WelcomeMenu.CURSOR == 2) {
                        GameLoop.quit();
                    }
                } else {
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
