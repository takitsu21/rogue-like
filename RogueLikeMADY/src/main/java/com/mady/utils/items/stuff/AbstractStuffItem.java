package com.mady.utils.items.stuff;

import com.mady.utils.entities.Player;
import com.mady.utils.items.Item;
import com.mady.utils.items.Price;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStuffItem implements Item {
    private final String name;
    private int HP;
    private int MP;
    private int ATK;
    private int DEF;
    private int AGI;
    private int LUK;
    private final List<Integer> stats = new ArrayList<>(Arrays.asList(HP, MP, ATK, DEF, AGI, LUK));
    private Price PRI;


    public AbstractStuffItem(String name,
                             int HP,
                             int MP,
                             int ATK,
                             int DEF,
                             int AGI,
                             int LUK) {
        this.name = name;
        this.HP = HP;
        this.MP = MP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.AGI = AGI;
        this.LUK = LUK;
        this.PRI = new Price(this);
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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getATK() {
        return ATK;
    }

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public int getDEF() {
        return DEF;
    }

    public int getAGI() {
        return AGI;
    }

    public int getLUK() {
        return LUK;
    }

    public List<Integer> getStats() {
        return stats;
    }

    public int getPRIX() {
        return PRI.getPrice();
    }

    @Override
    public String toString() {
        //une représentation du Stuff item sera différente pour une amulete vu que sa luk est le seul parametre interessant
        return (name.equals("amulet")) ? String.format("|LUK=%d|", (int) LUK) :
                String.format("|HP=%d|MP=%d|ATK=%d|DEF=%d|AGI=%d|", (int) HP, (int) MP, (int) ATK, (int) DEF, (int) AGI);
    }

}
