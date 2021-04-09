package com.mady.utils.entities;

import com.mady.utils.Util;
import com.mady.utils.entities.factories.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractStuffItem implements Item {
    private Position pos;
    private static double HP = 1;
    private static double MP = 1;
    private static double ATK = 1;
    private static double DEF = 1;
    private static double AGI = 1;
    private static double LUK = 1;
    private final List<Double> stats = new ArrayList<>(Arrays.asList(HP, MP, ATK, DEF, AGI, LUK));

    public AbstractStuffItem(Position pos) {
        this.pos = pos;
    }

    @Override
    public Position getPosition() {
        return pos;
    }

    @Override
    public void setPosition(Position position) {
        pos = position;
    }

    @Override
    public double getMovement() {
        return 0;
    }

    @Override
    public int getDamages() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void act(Player player) {
    }

    public void setStats(int lvl) {
        for (double stat : stats) {
            stat = (double)Util.r.nextInt(lvl + 1) * 1.2;
        }
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public static double getHP() {
        return HP;
    }

    public static void setHP(double HP) {
        AbstractStuffItem.HP = HP;
    }

    public static double getMP() {
        return MP;
    }

    public static void setMP(double MP) {
        AbstractStuffItem.MP = MP;
    }

    public static double getATK() {
        return ATK;
    }

    public static void setATK(double ATK) {
        AbstractStuffItem.ATK = ATK;
    }

    public static double getDEF() {
        return DEF;
    }

    public static void setDEF(double DEF) {
        AbstractStuffItem.DEF = DEF;
    }

    public static double getAGI() {
        return AGI;
    }

    public static void setAGI(double AGI) {
        AbstractStuffItem.AGI = AGI;
    }

    public static double getLUK() {
        return LUK;
    }

    public static void setLUK(double LUK) {
        AbstractStuffItem.LUK = LUK;
    }

    public List<Double> getStats() {
        return stats;
    }

    @Override
    public String toString() {
       return String.format("HP=%.2f/MP=%.2f/ATK=%.2f/DEF=%.2f/AGI=%.2f/LUK=%.2f",HP, MP, ATK, DEF, AGI, LUK);
    }
}
