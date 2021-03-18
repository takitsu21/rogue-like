package com.mady.utils.entities;

import com.mady.utils.Case;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends AbstractEntities {

    private static int lvl;
    private double exp;
    private static double expMax;
    private static double HP = 10;
    private static double MP = 5;
    private static double ATK = 3;
    private static double DEF = 1;
    private static double AGI = 1;
    private static double LUK = 1;
    private List<Double> stats = new ArrayList<>(Arrays.asList(expMax, HP, MP, ATK, DEF, AGI, LUK));
    private final Stuff stuff;

    public Player(Position pos, int hitPoints, int damages, double movement, String repr) {
        super(pos, hitPoints, damages, movement, repr);
        this.stuff = new Stuff();
    }


    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }

    public void pickItem( AbstractStuffItem i) {
        this.stuff.getItems().add(i);
    }
    public void pickItem(Case c) {
        AbstractStuffItem i = (AbstractStuffItem) c.getItem();
        i.setPos(null);
        this.stuff.getItems().add(i);
        c.setItem(null);
    }

    public void equipItem(Case c) {
        AbstractStuffItem i = (AbstractStuffItem) c.getItem();
        i.setPos(null);
        c.setItem(null);
        String n = i.getName();
        switch (n) {
            case "helmet":
                this.stuff.setHelmet(i);
            case "weapon":
                this.stuff.setWeapon(i);
            case "shoes":
                this.stuff.setShoes(i);
            case "pant":
                this.stuff.setPant(i);
            case "chest":
                this.stuff.setChest(i);
                break;
        }
    }

    public void useItem(Case c) {
        AbstractStuffItem i = (AbstractStuffItem) c.getItem();
        i.setPos(null);
        c.setItem(null);
        if (i.isDrinkable()) {
            i.act(this);
        } else if (i.isPickable()) {
            pickItem(i);
        }

    }





    public Stuff getStuff() {
        return stuff;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public static int getLvl() {
        return lvl;
    }

    public static void setLvl(int lvl) {
        Player.lvl = lvl;
    }

    public static double getExpMax() {
        return expMax;
    }

    public static void setExpMax(double expMax) {
        Player.expMax = expMax;
    }

    public static double getHP() {
        return HP;
    }

    public static void setHP(double HP) {
        Player.HP = HP;
    }

    public static double getMP() {
        return MP;
    }

    public static void setMP(double MP) {
        Player.MP = MP;
    }

    public static double getATK() {
        return ATK;
    }

    public static void setATK(double ATK) {
        Player.ATK = ATK;
    }

    public static double getDEF() {
        return DEF;
    }

    public static void setDEF(double DEF) {
        Player.DEF = DEF;
    }

    public static double getAGI() {
        return AGI;
    }

    public static void setAGI(double AGI) {
        Player.AGI = AGI;
    }

    public static double getLUK() {
        return LUK;
    }

    public static void setLUK(double LUK) {
        Player.LUK = LUK;
    }

    public List<Double> getStats() {
        return stats;
    }

    public void setStats(List<Double> stats) {
        this.stats = stats;
    }

    public void updateStats() {
        lvl += 1;
        for (double stat : stats) {
            stat = stat * 1.36;
        }
    }


    public boolean isLevelUp(int expGain) {
        double newExp = (exp + expGain) % expMax;
        if (newExp < exp) {
            exp = newExp;
            return true;
        }
        return false;
    }

    public boolean isDead() {
        return (getHP() <= 0);
    }

}
