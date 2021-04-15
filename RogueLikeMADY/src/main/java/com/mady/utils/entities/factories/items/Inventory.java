package com.mady.utils.entities.factories.items;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Util;
import com.mady.utils.entities.AbstractStuffItem;
import com.mady.utils.entities.Stuff;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Inventory {
    private final List<Item> inventory = new ArrayList<>();
    private final int MAX_SIZE = 10;
    private int selectedItem = 0;
    private Stuff s;

    public Inventory(Stuff s ) {
        this.s = s;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public boolean isFull() {
        return inventory.size() == MAX_SIZE;
    }

    public boolean addItem(Item i) {
        if (isFull()) {
            return false;
        }
        inventory.add(i);
        return true;
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int acc = 0;

        char rph = s.getHelmet() == null ? ' ' : '*';
        char rpg = s.getGauntlet() == null ? ' ' : '*';
        char rpc = s.getChest() == null ? ' ' : '*';
        char rpp = s.getPant() == null ? ' ' : '*';
        char rps = s.getShoes() == null ? ' ' : '*';
        char rpa = s.getAmulet() == null ? ' ' : '*';
        char rpw = s.getWeapon() == null ? ' ' : '*';
        String HelmetStats = s.getHelmet() == null ? " " : s.getHelmet().toString();
        String GauntletStats = s.getGauntlet() == null ? " " : s.getGauntlet().toString();
        String ChestPlateStats = s.getChest()  == null ? " " : s.getChest() .toString();
        String PantStats = s.getPant() == null ? " " : s.getPant().toString();
        String AmuletStats = s.getAmulet() == null ? " " : s.getAmulet().toString();
        String ShoesStats = s.getShoes() == null ? " " : s.getShoes().toString();
        String WeaponStats = s.getWeapon() == null ? " " : s.getWeapon().toString();
//        String fillerH = Util.filler(BASE_WIDTH-(32+HelmetStats.length()));
//        String fillerW =  Util.filler(BASE_WIDTH-(41+WeaponStats.length()+AmuletStats.length()));
//        String fillerG = Util.filler(BASE_WIDTH-(34+GauntletStats.length()));
//        String fillerC = Util.filler(BASE_WIDTH-(36+ChestPlateStats.length()));
//        String fillerP = Util.filler (BASE_WIDTH - (30+PantStats.length()));
//        String fillerS = Util.filler((BASE_WIDTH - (31+ShoesStats.length())));
        sb.append("\"\"\"\"\"\"\"\"\"\"\"\"\"");
        sb.append(" STUFF : ");
        sb.append(String.format("HElMET: %s",HelmetStats));
        sb.append("\n\"");
        sb.append(String.format("    [%c] [%c] \"         WEAPON: %s AMULET: %s\n\"",rph,rpa,WeaponStats,AmuletStats));
        sb.append(String.format(" [%c][%c][%c]  \"         GAUNTLET: %s\n\"",rpg,rpc,rpw,GauntletStats));
        sb.append(String.format("    [%c]     \"         CHESTPLATE: %s\n\"",rpp,ChestPlateStats));
        sb.append(String.format("  [%c] [%c]   \"         PANT: %s\n\"",rps,rps,PantStats));
        sb.append(String.format("\"\"\"\"\"\"\"\"\"\"\"\"\"         SHOES: %s",ShoesStats));
        sb.append("\n");
//        for(int i =  0; i < BASE_WIDTH; i++){
//            sb.append('"');
//        }




        for (Item i : inventory) {
            AbstractStuffItem it = (AbstractStuffItem) i;
//            StringBuilder sbTmp = new StringBuilder();
            if (acc == selectedItem) {
                sb.append('[');
            }

            sb.append('<').append(i.getName().substring(0, 1).toUpperCase()).append(i.getName().substring(1))
                    .append('>')
                    .append(" : ")
                    .append("AGI ")
                    .append(it.getAGI())
                    .append(" ATK ")
                    .append(it.getATK())
                    .append(" HP ")
                    .append(it.getHP())
                    .append(" MP")
                    .append(it.getMP())
                    .append(" LUK")
                    .append(it.getLUK());
            if (acc == selectedItem) {
                sb.append(']');
//                sb.append(Ansi.colorize(sbTmp.toString(), Attribute.RED_TEXT()));
//                System.out.println();
            }
//            else {
//                sb.append(sbTmp);
//            }

            sb.append("\n");
            acc++;
        }
        return sb.toString();
    }
}
