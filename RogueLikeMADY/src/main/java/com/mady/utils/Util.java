package com.mady.utils;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.game.WindowGameIntegration;
import com.mady.game.menus.WelcomeMenu;
import com.mady.utils.entities.Player;
import com.mady.utils.enums.DeplacementEnum;
import com.mady.utils.enums.KeyboardPressedEnum;
import com.mady.utils.items.Inventory;
import com.mady.utils.items.stuff.AbstractStuffItem;
import com.mady.utils.items.stuff.Amulet;
import com.mady.utils.items.stuff.Stuff;
import com.mady.utils.listener.MoveListener;

import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Util {
    public static final Random r = new Random();
    public static volatile boolean playerTurn = true;
    public static volatile KeyboardPressedEnum keyPressed = KeyboardPressedEnum.WELCOME;
    public static volatile StringBuilder currentAction = new StringBuilder();
    public static volatile boolean inHelp = false;

    public static DeplacementEnum randomDirection() {
        int randomDirection = Util.r.nextInt(4);
        switch (randomDirection) {
            case 0:
                return DeplacementEnum.BAS;
            case 1:
                return DeplacementEnum.HAUT;
            case 2:
                return DeplacementEnum.GAUCHE;
            default:
                return DeplacementEnum.DROITE;
        }
    }

    public static String getRandomItem() {
        int randomIt = Util.r.nextInt(25);
        if (randomIt < 4) {  //4
            return "potion_vie";
        }
        if (randomIt == 4) {  //1
            return "poison_vie";
        }
        if (randomIt < 9) {  //4
            return "potion_force";
        }
        if (randomIt == 9) { //1
            return "poison_force";
        }
        if (randomIt < 19) {  //9
            return "coin";
        } else { //5
            return "chest";
        }
    }

    public static String showInventoryMenu(Player player) {
        StringBuilder sb = new StringBuilder();
        if (keyPressed == KeyboardPressedEnum.SELL) {
            sb.append(Ansi.colorize("\nVOUS VENDEZ A HENRY\n", Attribute.GREEN_TEXT()));
            sb.append(String.format("\t\t vous avez %d MadyCoin\n", player.getCoins()));
        }
        Inventory inventory = player.getInventory();
        Stuff s = player.getStuff();
        int selectedItem = inventory.getSelectedItem();
        int acc = 0;
        int totalLuk, totalHp, totalAtk, totalAgi, totalDef, totalMp;
        totalLuk = totalHp = totalAtk = totalAgi = totalDef = totalMp = 0;
        char rph = s.getHelmet() == null ? ' ' : '*';
        char rpg = s.getGauntlet() == null ? ' ' : '*';
        char rpc = s.getChest() == null ? ' ' : '*';
        char rpp = s.getPant() == null ? ' ' : '*';
        char rps = s.getShoes() == null ? ' ' : '*';
        char rpa = s.getAmulet() == null ? ' ' : '*';
        char rpw = s.getWeapon() == null ? ' ' : '*';
        String HelmetStats = s.getHelmet() == null ? " " : s.getHelmet().toString();
        String GauntletStats = s.getGauntlet() == null ? " " : s.getGauntlet().toString();
        String ChestPlateStats = s.getChest() == null ? " " : s.getChest().toString();
        String PantStats = s.getPant() == null ? " " : s.getPant().toString();
        String AmuletStats = s.getAmulet() == null ? " " : s.getAmulet().toString();
        String ShoesStats = s.getShoes() == null ? " " : s.getShoes().toString();
        String WeaponStats = s.getWeapon() == null ? " " : s.getWeapon().toString();

        sb.append(" STUFF   ");
        sb.append(String.format("           HElMET: %s\n", HelmetStats));
        sb.append(String.format("    [%c] [%c]          WEAPON: %s AMULET: %s\n", rph, rpa, WeaponStats, AmuletStats));
        sb.append(String.format(" [%c][%c][%c]           GAUNTLET: %s\n", rpg, rpc, rpw, GauntletStats));
        sb.append(String.format("    [%c]              CHESTPLATE: %s\n", rpp, ChestPlateStats));
        sb.append(String.format("  [%c] [%c]            PANT: %s\n", rps, rps, PantStats));
        sb.append(String.format("                     SHOES: %s\n\n", ShoesStats));
        for (Map.Entry<String, AbstractStuffItem> pair : s.getItems().entrySet()) {
            AbstractStuffItem it = pair.getValue();
            totalLuk += it.getLUK();
            totalAgi += it.getAGI();
            totalAtk += it.getATK();
            totalHp += it.getHP();
            totalMp += it.getMP();
            totalDef += it.getDEF();
        }
        sb.append("\tInventory \n");
        for (AbstractStuffItem i : inventory.getInventory()) {
            AbstractStuffItem it = i;
            StringBuilder sbTmp = new StringBuilder();
            if (acc == selectedItem) {
                sbTmp.append('[');
            }
            if (i instanceof Amulet) {
                sbTmp.append('<').append(i.getName().substring(0, 1).toUpperCase()).append(i.getName().substring(1))
                        .append('>')
                        .append(" : ")
                        .append("|LUK ")
                        .append(it.getLUK());
            } else {
                sbTmp.append('<').append(i.getName().substring(0, 1).toUpperCase()).append(i.getName().substring(1))
                        .append('>')
                        .append(" : ")
                        .append("|HP ")
                        .append(it.getHP())
                        .append("|MP ")
                        .append(it.getMP())
                        .append("|ATK ")
                        .append(it.getATK())
                        .append("|DEF ")
                        .append(it.getDEF())
                        .append("|AGI ")
                        .append(it.getAGI());
            }
            if (acc == selectedItem) {
                sbTmp.append(']');
                sbTmp = new StringBuilder(Ansi.colorize(sbTmp.toString(), Attribute.MAGENTA_TEXT()));
                if (keyPressed == KeyboardPressedEnum.SELL) {
                    int resellPrice = (i.getPRIX() / 2) > 0 ? (i.getPRIX() / 2) : 1;
                    sbTmp.append(Ansi.colorize(String.format(" --> Prix: %d MadyCoin", resellPrice), Attribute.YELLOW_TEXT()));
                }
            }
            sb.append(sbTmp).append("\n");
            acc++;
        }
        sb.append("\n").append("\tStats\n");
        HashMap<String, Integer> stat = player.getStats();
        sb.append(String.format("%s : %d ", "LVL", stat.get("LVL")));
        sb.append("\n");
        sb.append(String.format("%s : %d ", "MAX_HP", stat.get("MAX_HP")));
        sb.append(Ansi.colorize(String.format("(+%d)", totalHp), Attribute.GREEN_TEXT()));
        sb.append("\n");
        sb.append(String.format("%s : %d ", "HP", stat.get("HP")));
        sb.append(Ansi.colorize(String.format("(+%d)", totalHp), Attribute.GREEN_TEXT()));
        sb.append("\n");
        sb.append(String.format("%s : %d ", "MAX_MP", stat.get("MAX_MP")));
        sb.append(Ansi.colorize(String.format("(+%d)", totalMp), Attribute.GREEN_TEXT()));
        sb.append("\n");
        sb.append(String.format("%s : %d ", "MP", stat.get("MP")));
        sb.append(Ansi.colorize(String.format("(+%d)", totalMp), Attribute.GREEN_TEXT()));
        sb.append("\n");
        sb.append(String.format("%s : %d ", "ATK", stat.get("ATK")));
        sb.append(Ansi.colorize(String.format("(+%d) ", totalAtk), Attribute.GREEN_TEXT()));
        sb.append(String.format("(Dégâts brut %d)", player.getDamages()));
        sb.append("\n");
        sb.append(String.format("%s : %d ", "DEF", stat.get("DEF")));
        sb.append(Ansi.colorize(String.format("(+%d)", totalDef), Attribute.GREEN_TEXT()));
        sb.append("\n");
        sb.append(String.format("%s : %d ", "AGI", stat.get("AGI")));
        sb.append(Ansi.colorize(String.format("(+%d)", totalAgi), Attribute.GREEN_TEXT()));
        sb.append("\n");
        sb.append(String.format("%s : %d ", "LUK", stat.get("LUK")));
        sb.append(Ansi.colorize(String.format("(+%d)", totalLuk), Attribute.GREEN_TEXT()));
        sb.append("\n");
        return sb.toString();
    }

    public static void showWelcomeScreen() {
        WelcomeMenu wm = new WelcomeMenu();
        System.out.println(wm);
    }

    public static void printHELP() {
        if (!inHelp) {
            System.out.println("Déplacement:\n" +
                    "z monter\n" +
                    "s descendre\n" +
                    "q gauche\n" +
                    "d droite\n" +
                    "Attaque:\n" +
                    "attaque de zone autour du joueur a\n" +
                    "attaque un unique monstre e\n" +
                    "Inventaire: i (navigation z s, équiper l'objet enter, jeter l'objet backspace)\n" +
                    "interaction: x (ouverture d'un coffre)" +
                    "\n" +
                    "\n" +
                    "Representation du jeu :\n" +
                    "Monstre:\n" +
                    "Orc o\n" +
                    "Goblin g\n" +
                    "Coffre au trésor C, vous offre un item aléatoire a équiper.\n" +
                    "Potion ou poison de force F, vous octroie un malus ou bonus d'ATK.\n" +
                    "Potion ou poison de vie V, vous octroie un malus ou bonus de d'HP.\n" +
                    "Portail §, vous téléporte à la prochaine map.\n" +
                    "Coming soon...\n" +
                    "Nouveaux :\n" +
                    "Monstres\n" +
                    "Items\n" +
                    "Effets visuels\n" +
                    "Stats des équipements à ajouter au joueur (ATK, HP, etc...)\n" +
                    "Official Soundtrack");
        }
        inHelp = true;

    }

    /**
     * @param windowGameIntegration windowGameIntegration swing pour les KeyListener
     * @param map                   Map du jeu
     */
    public static void refreshKeyListener(WindowGameIntegration windowGameIntegration, com.mady.utils.environment.Map map) {
        for (KeyListener c : windowGameIntegration.getFrame().getListeners(KeyListener.class)) {
            windowGameIntegration.getFrame().removeKeyListener(c);
        }
        windowGameIntegration.getFrame().addKeyListener(new MoveListener(map));
    }

    public static int getPercent(double max, double reduce) {
        if (max > 0.0 && reduce > 0.0) {
            return (int) (max * (reduce / 100));
        }
        return 0;
    }
}

