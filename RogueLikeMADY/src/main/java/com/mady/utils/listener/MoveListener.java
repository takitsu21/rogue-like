package com.mady.utils.listener;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.GameLoop;
import com.mady.GameStatus;
import com.mady.utils.*;
import com.mady.utils.entities.Deplacement;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import com.mady.utils.entities.factories.items.Chest;
import com.mady.utils.entities.factories.items.Item;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
                    WelcomeMenu.CURSOR = Math.abs((WelcomeMenu.CURSOR - 1) % 4);
                } else {
                    move(Deplacement.HAUT, e);
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
                } else {
                    move(Deplacement.BAS, e);
                }
                break;
            case KeyEvent.VK_Q: // Touche Q
                if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                    move(Deplacement.GAUCHE, e);
                }
                break;
            case KeyEvent.VK_D: // Touche D
                if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                    move(Deplacement.DROITE, e);
                }
                break;
            case KeyEvent.VK_A: // Touche A
                if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                    map.getPlayer().zoneAttack(map.zoneCheckAround(), map);
                }
                break;
            case KeyEvent.VK_E: // Touche E
                if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                    map.getPlayer().closeAttack(map.closeCheckAround(), map);
                }
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
                } else if (Util.keyPressed == KeyboardPressedEnum.HELP) {
                    if (GameLoop.getStatus() == GameStatus.PAUSE) {
                        Util.keyPressed = KeyboardPressedEnum.NONE;
                    } else {
                        Util.keyPressed = KeyboardPressedEnum.WELCOME;
                    }
                    Util.inHelp = false;
                } else if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                    Util.keyPressed = KeyboardPressedEnum.ESC;
                } else if (Util.keyPressed == KeyboardPressedEnum.T) {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                    GameLoop.setStatus(GameStatus.RUNNING);
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
                            Util.keyPressed = KeyboardPressedEnum.WELCOME;
                            GameLoop.restart();

                            break;
                        case "Help":
                            Util.keyPressed = KeyboardPressedEnum.HELP;
                            break;
                        case "Quit":
                            GameLoop.quit();
                            break;
                    }
                } else if (Util.keyPressed == KeyboardPressedEnum.WELCOME) {
                    if (WelcomeMenu.CURSOR == 0) {
                        Util.keyPressed = KeyboardPressedEnum.NONE;
                    } else if (WelcomeMenu.CURSOR == 1) {
                        Util.keyPressed = KeyboardPressedEnum.HELP;
                    } else if (WelcomeMenu.CURSOR == 2) {
                        GameLoop.quit();
                    }

                } else if (Util.keyPressed == KeyboardPressedEnum.T) {
                    map.getPlayer().rangeAttack(map.getPlayer().getMonsterAround().get(Player.ATTACK_CURSOR), map);
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                    map.getPlayer().setMonsterAround(new ArrayList<>());
                    Player.ATTACK_CURSOR = 0;
                    GameLoop.setStatus(GameStatus.RUNNING);
                }

                else {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                }
                break;
            case KeyEvent.VK_T:
                if (Util.keyPressed == KeyboardPressedEnum.NONE) {
                    Util.keyPressed = KeyboardPressedEnum.T;
                    Player.ATTACK_CURSOR = 0;
                    map.getPlayer().entitiesLongRangeAvailaible(map);
                    GameLoop.setStatus(GameStatus.RANGE_ATTACK_CHOICE);
                } else if (Util.keyPressed == KeyboardPressedEnum.T) {
                    Util.keyPressed = KeyboardPressedEnum.NONE;
                    map.getPlayer().setMonsterAround(new ArrayList<>());
                    Player.ATTACK_CURSOR = 0;
                    GameLoop.setStatus(GameStatus.RUNNING);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (Util.keyPressed == KeyboardPressedEnum.T) {
                    Player.ATTACK_CURSOR = Math.abs((Player.ATTACK_CURSOR - 1) % map.getPlayer().getMonsterAround().size());
                    Entities monster = map.getPlayer().getMonsterAround().get(Player.ATTACK_CURSOR);
                    Util.currentAction.append(Ansi.colorize(String.format("%s<%d/%d HP> est sélectionné.\n",
                            monster.getName(), monster.getHitPoints(), monster.getMaxHitPoints()), Attribute.BLUE_TEXT()));
                }

                break;
            case KeyEvent.VK_RIGHT:
                if (Util.keyPressed == KeyboardPressedEnum.T) {
                    Player.ATTACK_CURSOR = (Player.ATTACK_CURSOR + 1) % map.getPlayer().getMonsterAround().size();
                    Entities monster = map.getPlayer().getMonsterAround().get(Player.ATTACK_CURSOR);
                    Util.currentAction.append(Ansi.colorize(String.format("%s<%d/%d HP> est sélectionné.\n",
                            monster.getName(), monster.getHitPoints(), monster.getMaxHitPoints()), Attribute.BLUE_TEXT()));
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

    private Position nextDirection(Position pos, boolean isDash) {
        if (isDash) {
            Position incrementedPos = pos.incrementPos(pos);
            if (map.getCase(map.getPlayer().getPos().incrementPos(incrementedPos)).isSalle()) {
                return incrementedPos;
            }
        }
        return pos;
    }

    private void move(Deplacement d, KeyEvent e) {
        if (GameLoop.isGameRunning()) {
            if (e.isShiftDown()) {
                int nextMp = map.getPlayer().getMP() - Util.getPercent(map.getPlayer().getMaxMp(),
                        map.getPlayer().getDASH_MP_COST());
                if (nextMp < 0) {
                    map.move(map.getPlayer(), nextDirection(d.pos, false));
                } else {
                    map.move(map.getPlayer(), nextDirection(d.pos, true));
                    map.getPlayer().setMP(nextMp);
                }
            } else {
                map.move(map.getPlayer(), nextDirection(d.pos, false));
            }
        }

    }
}
