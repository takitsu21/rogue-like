package com.mady.utils.entities;

import com.mady.utils.entities.factories.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStuffItem implements Item {
    private final String name;
    private double HP;
    private double MP;
    private double ATK;
    private double DEF;
    private double AGI;
    private double LUK;
    private final List<Double> stats = new ArrayList<>(Arrays.asList(HP, MP, ATK, DEF, AGI, LUK));


    public AbstractStuffItem(String name,
                             double HP,
                             double MP,
                             double ATK,
                             double DEF,
                             double AGI,
                             double LUK) {
        this.name = name;
        this.HP = HP;
        this.MP = MP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.AGI = AGI;
        this.LUK = LUK;
    }

    public AbstractStuffItem(String name) {
        this(name, 0, 0, 0, 0, 0, 0);
    }

    @Override
    public int getDamages() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void act(Player player) {
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

    public double getAGI() {
        return AGI;
    }

    public double getLUK() {
        return LUK;
    }

    public List<Double> getStats() {
        return stats;
    }

    @Override
    public String toString() {
        //une représentation du Stuff item sera différente pour une amulete vu que sa luk est le seul parametre interessant
        return (name.equals("amulet")) ? String.format("|LUK=%d|", (int) LUK) :
                String.format("|HP=%d|MP=%d|ATK=%d|DEF=%d|AGI=%d|", (int) HP, (int) MP, (int) ATK, (int) DEF, (int) AGI);
    }
}
