package com.mady.utils.entities;

import com.mady.utils.Case;

import com.mady.utils.Salle;

import com.mady.utils.entities.factories.items.Item;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends AbstractEntities {

    private int lvl = 1;
    private double maxMp = 50;
    private double maxHp = 100;

    private double exp = 0;
    private double expMax = 1000;
    private double HP = maxHp;
    private double MP = maxMp;
    private double ATK = 3;
    private  double DEF = 1;
    private  double AGI = 1;
    private  double LUK = 1;
    private  List<Double> stats = new ArrayList<>(Arrays.asList(maxMp, maxHp, expMax, HP, MP, ATK, DEF, AGI, LUK));
    private final Stuff stuff;


    public Player(Position pos, int hitPoints, int damages, int movement, String repr, Salle salle) {
        super(pos, hitPoints, damages, movement, repr, 3, salle);
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
        Item i = c.getItem();

        c.setItem(null);
        if (i.isDrinkable()) {
            i.act(this);
        } else if (i.isPickable()) {
           // pickItem(i);
        }}

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public double getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(double maxMp) {
        this.maxMp = maxMp;
        this.MP = maxMp;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
        this.HP = maxHp;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }

    public double getExpMax() {
        return expMax;
    }

    public void setExpMax(double expMax) {
        this.expMax = expMax;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public double getMP() {
        return MP;
    }

    public void setMP(double MP) {
        this.MP = MP;
    }

    public double getATK() {
        return ATK;
    }

    public void setATK(double ATK) {
        this.ATK = ATK;
    }

    public double getDEF() {
        return DEF;
    }

    public void setDEF(double DEF) {
        this.DEF = DEF;
    }

    public double getAGI() {
        return AGI;
    }

    public void setAGI(double AGI) {
        this.AGI = AGI;
    }

    public double getLUK() {
        return LUK;
    }

    public void setLUK(double LUK) {
        this.LUK = LUK;
    }

    public List<Double> getStats() {
        return stats;
    }

    public void setStats(List<Double> stats) {
        this.stats = stats;
    }

    public Stuff getStuff() {
        return stuff;
    }




    public void updateStats() {
        double multiplicateur = 1.16;
        setLvl(getLvl() + 1);
        setMaxHp(getMaxHp() * multiplicateur);
        setMaxMp(getMP() * multiplicateur);
        setATK(getATK() * multiplicateur);
        setDEF(getDEF() * multiplicateur);
        setAGI(getAGI() * multiplicateur);
        setLUK(getLUK() * multiplicateur);
        setExpMax(getExpMax() * multiplicateur);
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
