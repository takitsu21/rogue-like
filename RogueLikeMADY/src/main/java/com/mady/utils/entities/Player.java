package com.mady.utils.entities;

import com.mady.utils.Case;
import com.mady.utils.Map;

import com.mady.utils.Salle;
import com.mady.utils.entities.factories.items.Chest;
import com.mady.utils.entities.factories.items.Inventory;
import com.mady.utils.entities.factories.items.Item;
import com.mady.utils.entities.factories.monster.AbstractMonster;

import javax.swing.*;
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
    private double DEF = 1;
    private double AGI = 1;
    private double LUK = 2;
    private List<Double> stats = new ArrayList<>(Arrays.asList(maxMp, maxHp, expMax, HP, MP, ATK, DEF, AGI, LUK));
    private final Stuff stuff;
    private final Inventory inventory;


    public Player(Position pos, int hitPoints, int damages, int movement, String repr, Salle salle) {
        super(pos, hitPoints, damages, movement, repr, 3, salle);
        this.stuff = new Stuff();
        this.inventory = new Inventory(this.stuff);
    }


    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }

    /**
     * Ramasse un item et le met dans l'inventaire.
     * @param i AbstractStuffItem
     * @return boolean
     */
    public boolean pickItem(AbstractStuffItem i) {
        return inventory.addItem(i);
    }

    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Ramasse un item sur la case en ouvrant le coffre.
     *
     * @param c Case de la map.
     */
    public boolean pickItem(Case c) {
        AbstractStuffItem i = ((Chest)c.getItem()).openChest(this);
        i.setPosition(null);
        if (pickItem(i)) {
            c.setItem(null);
//            setEquipment(i);
            System.out.println("on équipe un équipement");
            return true;
        }
        return false;
    }

    /**
     * Equipe un item.
     * @param item item a équipé.
     * @return true si l'item a bien été équipé.
     */
    public boolean setEquipment(AbstractStuffItem item) {
        switch (item.getName()) {
            case "helmet":
                stuff.setHelmet(item);
                break;
            case "weapon":
                stuff.setWeapon(item);
                break;
            case "shoes":
                stuff.setShoes(item);
                break;
            case "pant":
                stuff.setPant(item);
                break;
            case "chest":
                stuff.setChest(item);
                break;
            case "amulet":
                stuff.setAmulet(item);
                break;
            case "gauntlet":
                stuff.setGauntlet(item);
                break;
            default:
                return false;
        }
        return true;
    }

    /**
     *
     * @param idx index de l'item a équipé.
     * @return true si l'item a bien été équipé.
     */
    public boolean equipItem(int idx) {
        AbstractStuffItem item = (AbstractStuffItem)inventory.getInventory().get(idx);
        setEquipment(item);
        if (setEquipment(item)) {
//            inventory.getInventory().set(idx, null);
//            inventory.get
            inventory.getInventory().remove(idx);
            return true;
        }
        return false;
    }

    public void useItem(Case c) {
        Item i = c.getItem();

        c.setItem(null);
        if (i.isDrinkable()) {
            i.act(this);
        } else if (i.isPickable()) {
            // pickItem(i);
        }
    }

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

    public boolean closeAttack(Entities monster, Map map){
        if (monster == null) {
            System.out.println("you can't attack now, no monsters around\n");
            return false;
        } else {
            System.out.println("you find a monster near you, you're attacking it\n");
            attackMonster(monster, map);
            return true;
        }
    }

    public boolean zoneAttack(List<Entities> monsters, Map map){
        if (monsters.isEmpty()) {
            System.out.println("you can't attack now, no monsters around\n");
            return false;
        } else {
            System.out.println("you find monsters around you, you're attacking them\n");
            for (Entities monster : monsters) {
                attackMonster(monster, map);
            }
            return true;
        }
    }

    private void attackMonster(Entities monster, Map map) {
        monster.takeDamages(getDamages());
        if (monster.isDead()) {
            //Case monsterCase = map.getcase(monster.getPosition());
            System.out.printf("player pos : %s\n", map.getPlayer().getPosition());
            System.out.printf("%s : %s\n", monster.getRepr(), monster.getPosition());
            Position mPos = monster.getPosition();
            map.clearCase(monster.getPosition());
            //map.getMap()[mPos.getX()][mPos.getY()].setEntity(null);
            System.out.println(map.getEntities());
            map.getEntities().remove(monster);
            System.out.println(map.getEntities());

            System.out.println("monster is dead\n");
        }
    }
}
