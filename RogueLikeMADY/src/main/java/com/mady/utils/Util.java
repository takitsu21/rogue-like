package com.mady.utils;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.entities.AbstractStuffItem;
import com.mady.utils.entities.Deplacement;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Stuff;
import com.mady.utils.entities.factories.items.Inventory;
import com.mady.utils.entities.factories.items.Item;
import com.mady.utils.entities.factories.items.Price;
import com.mady.utils.entities.factories.items.Shop;

import java.util.Map;
import java.util.Random;

public class Util {
    public static final Random r = new Random();
    public static volatile boolean playerTurn = true;
    public static volatile KeyboardPressedEnum keyPressed = KeyboardPressedEnum.NONE;
    public static volatile StringBuilder currentAction = new StringBuilder();

    public static Deplacement randomDirection() {
        int randomDirection = Util.r.nextInt(4);
        switch (randomDirection) {
            case 0:
                return Deplacement.BAS;
            case 1:
                return Deplacement.HAUT;
            case 2:
                return Deplacement.GAUCHE;
            default:
                return Deplacement.DROITE;
        }
    }

    public static String getRandomItem() {
        int randomIt = Util.r.nextInt(25);
        if (randomIt < 4) {
            return "potion_vie";
        }
        if (randomIt == 4) {
            return "poison_vie";
        }
        if (randomIt < 9) {
            return "potion_force";
        }
        if (randomIt == 9) {
            return "poison_force";
        }
        if (randomIt < 19) {
            return "coin";
        } else {
            return "chest";
        }
    }

    public static String showInventoryMenu(Player player) {
        StringBuilder sb = new StringBuilder();

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
//        sb.append(Ansi.colorize("\"\"\"\"\"\"\"\"\"\"\"\"\"", Attribute.BRIGHT_BLACK_BACK(),
//                Attribute.BRIGHT_BLACK_TEXT()));
        sb.append(" STUFF : ");
        sb.append(String.format("           HElMET: %s\n", HelmetStats));
//        sb.append("\n\"");
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
        }
        sb.append("\tInventory \n");
        for (Item i : inventory.getInventory()) {
            AbstractStuffItem it = (AbstractStuffItem) i;
            StringBuilder sbTmp = new StringBuilder();
            if (acc == selectedItem) {
                sbTmp.append('[');
            }

            sbTmp.append('<').append(i.getName().substring(0, 1).toUpperCase()).append(i.getName().substring(1))
                    .append('>')
                    .append(" : ")
                    .append("|AGI ")
                    .append((int) it.getAGI())
                    .append("|ATK ")
                    .append((int) it.getATK())
                    .append("|HP ")
                    .append((int) it.getHP())
                    .append("|MP ")
                    .append((int) it.getMP())
                    .append("|LUK ")
                    .append((int) it.getLUK());
            if (acc == selectedItem) {
                sbTmp.append(']');
                sbTmp = new StringBuilder(Ansi.colorize(sbTmp.toString(), Attribute.MAGENTA_TEXT()));
            }
            sb.append(sbTmp).append("\n");
            acc++;
        }
        sb.append("\n").append("\tStats\n");
        for (Map.Entry<String, Double> stat : player.getStats().entrySet()) {
            sb.append(String.format("%s : %d ", stat.getKey(), stat.getValue().intValue()));
            switch (stat.getKey()) {
                case "ATK":
                    sb.append(Ansi.colorize(String.format("(+%d)", totalAtk), Attribute.GREEN_TEXT()));
                    break;
                case "MAX_HP":
                    sb.append(Ansi.colorize(String.format("(+%d)", totalHp), Attribute.GREEN_TEXT()));
                    break;
                case "AGI":
                    sb.append(Ansi.colorize(String.format("(+%d)", totalAgi), Attribute.GREEN_TEXT()));
                    break;
                case "DEF":
                    sb.append(Ansi.colorize(String.format("(+%d)", totalDef), Attribute.GREEN_TEXT()));
                    break;
                case "LUK":
                    sb.append(Ansi.colorize(String.format("(+%d)", totalLuk), Attribute.GREEN_TEXT()));
                    break;
                case "MAX_MP":
                    sb.append(Ansi.colorize(String.format("(+%d)", totalMp), Attribute.GREEN_TEXT()));
                    break;
                default:
                    break;
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    public static String showShop(Player player) {
        Shop shop = new Shop(player,player.getPosition());
        shop.generateItems();
        if (shop.isEmpty()) {
            return Ansi.colorize("Aucun objet dans le shop!", Attribute.BRIGHT_RED_TEXT());
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Ansi.colorize("SHOP\n", Attribute.MAGENTA_TEXT()));
        for (Map.Entry<AbstractStuffItem, Price> entry : shop.getItems().entrySet()) {
            sb.append(String.format("%s : ", entry.getKey()))
                    .append(Ansi.colorize(String.format("%s MADY Coins.\n", entry.getValue()),
                            Attribute.BRIGHT_YELLOW_TEXT()));
        }
        return sb.toString();
    }

    public static String filler(int i) {
        return " ".repeat(Math.max(0, i));
    }
}
