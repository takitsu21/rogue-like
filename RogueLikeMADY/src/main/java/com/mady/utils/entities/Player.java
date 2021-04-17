package com.mady.utils.entities;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Case;
import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.Util;
import com.mady.utils.entities.factories.items.Chest;
import com.mady.utils.entities.factories.items.Inventory;
import com.mady.utils.entities.factories.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player extends AbstractEntities {

    private final Stuff stuff;
    private final Inventory inventory;
    private int lvl = 1;
    private double maxMp = 50;
    private double maxHp = 100;
    private double exp = 0;
    private double expMax = 10;
    private double HP = maxHp;
    private double MP = maxMp;
    private double ATK = 3;
    private double DEF = 1;
    private double AGI = 1;
    private double LUK = 2;
    private double maxExpToWin = 3;
    private double multiplicateur =1.12;
    private List<Double> stats = new ArrayList<>(Arrays.asList(maxMp, maxHp, expMax, HP, MP, ATK, DEF, AGI, LUK));


    public Player(Position pos, int hitPoints, int damages, int movement, String repr, Salle salle) {
        super("@", pos, hitPoints, damages, movement, repr, 3, salle);
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
     *
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
        AbstractStuffItem i = ((Chest) c.getItem()).openChest(this);
        i.setPosition(null);
        if (pickItem(i)) {
            c.setItem(null);
            Util.currentAction.append(Ansi.colorize(String.format("Vous avez récupérer <%s> : %s dans le coffre.\n",
                    i.getName().substring(0, 1).toUpperCase() + i.getName().substring(1), i), Attribute.MAGENTA_TEXT()));
            return true;
        }
        return false;
    }

    /**
     * Equipe un item.
     *
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
     * @param idx index de l'item a équipé.
     * @return true si l'item a bien été équipé.
     */
    public boolean equipItem(int idx) {
        AbstractStuffItem item = (AbstractStuffItem) inventory.getInventory().get(idx);
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

    public double getMaxExpToWin() {
        return maxExpToWin;
    }

    public void setMaxExpToWin(double maxExpToWin) {
        this.maxExpToWin = maxExpToWin;
    }

    public double getMultiplicateur() {
        return multiplicateur;
    }

    public void setMultiplicateur(double multiplicateur) {
        this.multiplicateur = multiplicateur;
    }

    public void updateStats() {
        //double multiplicateur = 1.16;
        setExp(0);
        setLvl(getLvl() + 1);
        setMaxHp(getMaxHp() * multiplicateur);
        setMaxMp(getMaxMp() * multiplicateur);
        setATK(getATK() * multiplicateur);
        setDamages((int) (getDamages() + getATK()));
        setDEF(getDEF() * multiplicateur);
        setAGI(getAGI() * multiplicateur);
        setLUK(getLUK() * multiplicateur);
        setExpMax(getExpMax() * multiplicateur + getExpMax());
        setMaxExpToWin(getMaxExpToWin() * multiplicateur);
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

    /**
     *
     * @param monster
     * @param map
     * @return a bool
     * attack the first monster you will find around you
     */

    public boolean closeAttack(Entities monster, Map map) {
        if (monster == null) {
            Util.currentAction.append("Aucune cible atteinte...\n");
            return false;
        } else {
            attackMonster(monster, map);
            return true;
        }
    }

    /**
     *
     * @param monsters
     * @param map
     * @return a bool
     * attack all the monster around you even the diagonals
     */

    public boolean zoneAttack(List<Entities> monsters, Map map) {
        if (monsters.isEmpty()) {
            Util.currentAction.append("Aucune cible atteinte...\n");
            return false;
        } else {
            for (Entities monster : monsters) {
                attackMonster(monster, map);
            }
            return true;
        }
    }

    /**
     *
     * @param monster
     * @param map
     * the attack procedure. attack first and then looks if the monster's dead
     */

    private void attackMonster(Entities monster, Map map) {
        monster.takeDamages(getDamages());
        Util.currentAction.append(Ansi.colorize(String.format("Vous attaquez %s<%d/%d HP> et lui infligez %d points de dégâts.\n",
                monster.getName(), monster.getHitPoints(), monster.getMaxHitPoints(), getDamages()), Attribute.BLUE_TEXT()));
        if (monster.isDead()) {
            Util.currentAction.append(Ansi.colorize(String.format("Vous avez tué %s.\n", monster.getName()),
                    Attribute.RED_TEXT()));
            winExp();
            map.clearCase(monster.getPosition());
            map.getEntities().remove(monster);
        }
    }

    private int randomExp() {
        return (int) (Math.random() * maxExpToWin) + 1;
    }


    private void winExp() {
        exp += randomExp();
        if (exp >= expMax) {
            updateStats();
            Util.currentAction.append(Ansi.colorize(String.format("Vous avez atteint le niveau %d, félicitation!\n",
                    getLvl()), Attribute.YELLOW_TEXT()));
        }
    }
}
