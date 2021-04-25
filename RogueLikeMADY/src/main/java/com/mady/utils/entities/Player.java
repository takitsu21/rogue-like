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

import java.util.HashMap;
import java.util.List;

public class Player extends AbstractEntities {

    private final Stuff stuff;
    private final Inventory inventory;
    private double maxMp = 50;
    //private double maxHp = getMaxHitPoints();
    private double exp = 0;
    private double expMax = 10;
    private double HP = getHitPoints();
    private double MP = maxMp;
    private double ATK = 3;
    private double DEF = 1;
    private double AGI = 1;
    private double LUK = 2;
    //private double multiplicateur =1.12;
    private final HashMap<String, Double> stats = new HashMap<>() {{
        put("LVL", (double) getLvl());
        put("MAX_HP", (double) getMaxHitPoints());
        put("HP", (double) getHitPoints());
        put("MAX_MP", maxMp);
        put("MP", MP);
        put("ATK", ATK);
        put("DEF", DEF);
        put("AGI", AGI);
        put("LUK", LUK);
    }};
    private double maxExpToWin = 3;
    private int coins = 1;
    private int manaAttack = 2;
//    private List<Double> stats = new ArrayList<>(Arrays.asList(maxMp, maxHp, expMax, HP, MP, ATK, DEF, AGI, LUK));


    public Player(Position pos, int hitPoints, int damages, int movement, String repr, Salle salle) {
        super("@", pos, hitPoints, damages, movement, repr, 3, salle);
        this.stuff = new Stuff();
        this.inventory = new Inventory(this.stuff);
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

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    /**
     * Ramasse un item sur la case en ouvrant le coffre.
     *
     * @param c Case de la map.
     */
    public void pickItem(Case c) {
        Item chest =c.getItem();
        AbstractStuffItem i = ((Chest) chest).openChest(this);
        i.setPosition(null);
        if (((Chest)chest).getPrice()>this.getCoins()) {
            //c.setItem(null);
            Util.currentAction.append(Ansi.colorize(String.format("Vous n'avez pas assez de MADY coins pour ouvrir le coffre.\n")));
        }else if (pickItem(i)) {
            setCoins(getCoins()-((Chest) chest).getPrice());
            c.setItem(null);
            Util.currentAction.append(Ansi.colorize(String.format("Vous avez récupérer <%s> : %s dans le coffre.\n",
                    i.getName().substring(0, 1).toUpperCase() + i.getName().substring(1), i), Attribute.MAGENTA_TEXT()));
        }

    }

    /**
     * Enlève les stats au joueur par rapport à l'item.
     *
     * @param item item qui va modiifer les statistiques du joueur
     */
    private void removeStats(AbstractStuffItem item) {
        if (item == null) {
            return; // Si aucun item n'est encore équipé
        }
        setLUK(getLUK() - item.getLUK());
        setAGI(getAGI() - item.getAGI());
        setDEF(getDEF() - item.getDEF());
        setMaxMp(getMaxMp() - item.getMP());
        setMP(getMP() - item.getMP());
        setMaxHitPoints((int) (getMaxHitPoints() - item.getHP()));
        setHitPoints((int) (getHitPoints() - item.getHP()));
        setATK(getATK() - item.getATK());
    }

    /**
     * Ajoute les stats au joueur par rapport à l'item.
     *
     * @param item item qui a va modifier les statistiques du joueur
     */
    private void addStats(AbstractStuffItem item) {
        setLUK(getLUK() + item.getLUK());
        setAGI(getAGI() + item.getAGI());
        setDEF(getDEF() + item.getDEF());
        setMaxMp(getMaxMp() + item.getMP());
        setMP(getMP() + item.getMP());
        setMaxHitPoints((int) (getMaxHitPoints() + item.getHP()));
        setHitPoints((int) (getHitPoints() + item.getHP()));
        setATK(getATK() + item.getATK());
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
                removeStats(stuff.getHelmet());
                stuff.setHelmet(item);
                break;
            case "weapon":
                removeStats(stuff.getWeapon());
                stuff.setWeapon(item);
                break;
            case "shoes":
                removeStats(stuff.getShoes());
                stuff.setShoes(item);
                break;
            case "pant":
                removeStats(stuff.getPant());
                stuff.setPant(item);
                break;
            case "chest":
                removeStats(stuff.getChest());
                stuff.setChest(item);
                break;
            case "amulet":
                removeStats(stuff.getAmulet());
                stuff.setAmulet(item);
                break;
            case "gauntlet":
                removeStats(stuff.getGauntlet());
                stuff.setGauntlet(item);
                break;
            default:
                return false;
        }
        addStats(item);
        return true;
    }

    /**
     * @param idx index de l'item a équipé.
     */
    public void equipItem(int idx) {
        AbstractStuffItem item = (AbstractStuffItem) inventory.getInventory().get(idx);
        if (setEquipment(item)) {
            inventory.getInventory().remove(idx);
        }
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


    @Override
    public void setLvl(int lvl) {
        super.setLvl(lvl);
        stats.put("LVL", (double) lvl);
    }


    public double getMaxMp() {
        return maxMp;
    }

    public void setMaxMp(double maxMp) {
        this.maxMp = maxMp;
        stats.put("MAX_MP", maxMp);
        stats.put("MP", maxMp);
    }


    @Override
    public void setMaxHitPoints(int maxHp) {
        super.setMaxHitPoints(maxHp);
        stats.put("MAX_HP", (double) maxHp);
    }

    @Override
    public void setHitPoints(int Hp) {
        super.setHitPoints(Hp);
        stats.put("HP", (double) Hp);
    }


    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
        stats.put("EXP", exp);
    }

    public double getExpMax() {
        return expMax;
    }

    public void setExpMax(double expMax) {
        this.expMax = expMax;
        stats.put("EXP_MAX", expMax);
    }


    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
        stats.put("HP", HP);
    }


    public double getMP() {
        return MP;
    }

    public boolean setMP(double MP) {
        if (MP < 0) {
            return false;
        }

        if (MP >= getMaxMp()) {
            MP = getMaxMp();
        }

        this.MP = MP;
        stats.put("MP", MP);
        return true;
    }

    public double getATK() {
        return ATK;
    }

    public void setATK(double ATK) {
        this.ATK = ATK;
        stats.put("ATK", ATK);
    }

    public double getDEF() {
        return DEF;
    }

    public void setDEF(double DEF) {
        this.DEF = DEF;
        stats.put("DEF", DEF);
    }

    public double getAGI() {
        return AGI;
    }

    public void setAGI(double AGI) {
        this.AGI = AGI;
        stats.put("AGI", AGI);
    }

    public double getLUK() {
        return LUK;
    }

    public void setLUK(double LUK) {
        this.LUK = LUK;
        stats.put("LUK", LUK);
    }

    public HashMap<String, Double> getStats() {
        return stats;
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

    public void updateStats() {
        setExp(0);
        setLvl(getLvl() + 1);
        setMaxHitPoints((int) (getMaxHitPoints() * getMultiplicateur()));
        setHitPoints(getMaxHitPoints());
        setMaxMp(getMaxMp() * getMultiplicateur());
        setMP(getMaxMp());
        setATK(getATK() * getMultiplicateur());
        setDamages((int) (getDamages() + getATK()));
        setDEF(getDEF() * getMultiplicateur());
        setAGI(getAGI() * getMultiplicateur());
        setLUK(getLUK() * getMultiplicateur());
        setExpMax(getExpMax() * getMultiplicateur() + getExpMax());
        setMaxExpToWin(getMaxExpToWin() * getMultiplicateur());
        manaAttack *= getMultiplicateur();
    }

    public boolean isDead() {
        return (getHitPoints() <= 0);
    }

    /**
     * @param monster monstre a attaquer
     * @param map     map sur laquelle ce trouve le joueur
     */

    public void closeAttack(Entities monster, Map map) {
        if (setMP(getMP() - manaAttack)) {
            if (monster == null) {
                Util.currentAction.append("Aucune cible atteinte...\n");
            } else {
                attackMonster(monster, map);
            }
        } else {
            Util.currentAction.append("Pas assez de mana...\n");
        }
    }

    /**
     * @param monsters liste des monstres a attaquer
     * @param map      map sur laquelle ce trouve le joueur
     */

    public void zoneAttack(List<Entities> monsters, Map map) {
        if (setMP(getMP() - manaAttack * 4)) {
            if (monsters.isEmpty()) {
                Util.currentAction.append("Aucune cible atteinte...\n");
            } else {
                for (Entities monster : monsters) {
                    attackMonster(monster, map);
                }
            }
        } else {
            Util.currentAction.append("Pas assez de mana...\n");
        }
    }

    /**
     * @param monster monstre a attaquer
     * @param map     map sur laquelle ce trouve le joueur
     *                the attack procedure. attack first and then looks if the monster's dead
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
