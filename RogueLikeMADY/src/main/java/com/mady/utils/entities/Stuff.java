package com.mady.utils.entities;

import com.mady.utils.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stuff {

    private static AbstractStuffItem helmet;
    private static AbstractStuffItem weapon;
    private static AbstractStuffItem shoes;
    private static AbstractStuffItem pant;
    private static AbstractStuffItem chestPlate;
    private static AbstractStuffItem amulet;
    private static AbstractStuffItem gauntlet;
    private final List<AbstractStuffItem> items = new ArrayList<>(
            Arrays.asList(helmet, weapon, shoes, pant, chestPlate,amulet,gauntlet)
    );

    public AbstractStuffItem getHelmet() {
        return helmet;
    }

    public void setHelmet(AbstractStuffItem helmet) {
        Stuff.helmet = helmet;
    }

    public AbstractStuffItem getWeapon() {
        return weapon;
    }

    public void setWeapon(AbstractStuffItem weapon) {
        Stuff.weapon = weapon;
    }

    public AbstractStuffItem getShoes() {
        return shoes;
    }

    public void setShoes(AbstractStuffItem shoes) {
        Stuff.shoes = shoes;
    }

    public AbstractStuffItem getPant() {
        return pant;
    }

    public void setPant(AbstractStuffItem pant) {
        Stuff.pant = pant;
    }

    public AbstractStuffItem getChest() {
        return chestPlate;
    }

    public void setChest(AbstractStuffItem chest) {
        Stuff.chestPlate = chest;
    }

    public List<AbstractStuffItem> getItems() {
        return items;
    }

    public static AbstractStuffItem getAmulet() {
        return amulet;
    }

    public static void setAmulet(AbstractStuffItem amulet) {
        Stuff.amulet = amulet;
    }

    public static AbstractStuffItem getGauntlet() {
        return gauntlet;
    }

    public static void setGauntlet(AbstractStuffItem gauntlet) {
        Stuff.gauntlet = gauntlet;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        if (items == null){
//            sb.append("Empty");
//        }
//       else{ for (AbstractStuffItem i : items) {
//            if( i == null){
//                sb.append("    ");
//            }
//            else {sb.append(String.format("%s : %d ",i.getName(),i.getDamages()));}
//        char rph = helmet == null ? ' ' : '*';
//        char rpg = gauntlet == null ? ' ' : '*';
//        char rpc = chestPlate == null ? ' ' : '*';
//        char rpp = pant == null ? ' ' : '*';
//        char rps = shoes == null ? ' ' : '*';
//        char rpa = amulet == null ? ' ' : '*';
//        char rpw = weapon == null ? ' ' : '*';
//        String HelmetStats = helmet == null ? " " : helmet.toString();
//        String GauntletStats = gauntlet == null ? " " : gauntlet.toString();
//        String ChestPlateStats = chestPlate == null ? " " : chestPlate.toString();
//        String PantStats = pant == null ? " " : pant.toString();
//        String AmuletStats = amulet == null ? " " : amulet.toString();
//        String ShoesStats = shoes == null ? " " : shoes.toString();
//        String WeaponStats = weapon == null ? " " : weapon.toString();
//
//
//        sb.append("\"\"\"\"\"\"\"\"\"\"\"\"\"");
//        sb.append(" STUFF : ");
//        sb.append(String.format("HElMET: %s",HelmetStats));
//        sb.append("\n\"");
//        sb.append(String.format("    [%c] [%c] \"         WEAPON: %s AMULET: %s\n\"",rph,rpa,WeaponStats,AmuletStats));
//        sb.append(String.format(" [%c][%c][%c]  \"         GAUNTLET: %s\n\"",rpg,rpc,rpw,GauntletStats));
//        sb.append(String.format("    [%c]     \"         CHESTPLATE: %s\n\"",rpp,ChestPlateStats));
//        sb.append(String.format("  [%c] [%c]   \"         PANT: %s\n\"",rps,rps,PantStats));
//        sb.append(String.format("\"\"\"\"\"\"\"\"\"\"\"\"\"         SHOES: %s",ShoesStats));

        return sb.toString();

    }
}
