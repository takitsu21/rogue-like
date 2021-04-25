package com.mady.utils.entities;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.Util;
import com.mady.utils.entities.factories.items.Chest;
import com.mady.utils.entities.factories.monster.AbstractMonster;
import com.mady.utils.entities.factories.monster.Boss;

public abstract class AbstractEntities implements Entities {
    private final int movement;
    private final int effectiveArea;
    private final String name;
    private final double multiplicateur = 1.12;
    private String repr;
    private Position pos;
    private int maxHitPoints;
    private int hitPoints;
    private int damages;
    private Salle salle;
    private boolean isAggro = false;
    private boolean isAttack = false;
    private int lvl = 1;
    private int nbDeplacement = 0;


    public AbstractEntities(String name,
                            Position pos,
                            int hitPoints,
                            int damages,
                            int movement,
                            String repr,
                            int effectiveArea,
                            Salle salle) {
        this.name = name;
        this.pos = pos;
        this.maxHitPoints = hitPoints;
        this.hitPoints = hitPoints;
        this.damages = damages;
        this.movement = movement;
        this.repr = repr;
        this.effectiveArea = effectiveArea;
        this.salle = salle;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getRepr() {
        if (this instanceof Player) {
            return Ansi.colorize(repr, Attribute.BLUE_TEXT());
        }
        return repr;
    }

    @Override
    public Position getPosition() {
        return pos;
    }

    public Position getPos() {
        return pos;
    }

    @Override
    public void setPos(Position position) {
        this.pos = position;
    }

    public int getEffectiveArea() {
        return effectiveArea;
    }

    public boolean isAggro() {
        return isAggro;
    }

    public boolean isAttack() {
        return isAttack;
    }

    public void setIsAttack(boolean attack) {
        isAttack = attack;
    }

    @Override
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    @Override
    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints = maxHitPoints;
    }

    @Override
    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    @Override
    public void takeDamages(int damages) {
        if (this instanceof Player) {
            this.setHitPoints(this.getHitPoints() - damages);
        } else {
            int new_HP = getHitPoints() - damages;
            setHitPoints(new_HP);
        }
        isAttack = true;
    }

    public boolean isDead() {
        return (getHitPoints() <= 0);
    }

    @Override
    public int getDamages() {
        return damages;
    }

    @Override
    public void setDamages(int damages) {
        this.damages = damages;
    }

    @Override
    public int getMovement() {
        return movement;
    }

    /**
     * @param map map sur laquelle ce trouve le monstre
     * @return boolean
     * this function allows the monster to detect the player if this one enters the effective area of the mob
     * if he enters the monster goes towards the player
     * the effective area of a monster moves with him
     */

    private boolean isInPerimeter(Map map) {
        for (int i = pos.getX() - effectiveArea; i <= pos.getX() + effectiveArea; i++) {
            for (int j = pos.getY() - effectiveArea; j <= pos.getY() + effectiveArea; j++) {
                if (map.isInside(i, j) && map.getMap()[i][j].isPlayer()) {
                    isAggro = true;
                    return true;
                }
            }
        }
        isAggro = false;
        return false;
    }


    public Position nextPos(Entities entitie) {
        int randomMove = Util.r.nextInt(entitie.getMovement() + 1);
        Deplacement d = Util.randomDirection();
        return d.pos.multiplyPos(randomMove);
    }

    /**
     * @param map map sur laquelle ce trouve le monstre
     * @return the map with monster's pose updated
     * either he moves randomly or towards the player if this one is in the effective area.
     */

    @Override
    public Map doTurn(Map map) {
        if (!this.isDead()) {
            if (isInPerimeter(map)) {
                ((AbstractMonster) this).act(map);
            } else {
                while (!map.move(this, nextPos(this))) ;
            }
            if (this instanceof Boss) {
                ((Boss) this).skill(map);
            }
        }
        return map;
    }

    public Salle getSalle() {
        return salle;
    }


    public void setSalle(Salle salle) {
        this.salle = salle;
    }


    public Double getMultiplicateur() {
        return multiplicateur;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;

    }

    public int getNbDeplacement() {
        return nbDeplacement;
    }

    public void setNbDeplacement(int nbDeplacement) {
        this.nbDeplacement = nbDeplacement;
        if (this instanceof Player && nbDeplacement % 5 == 0) {
            this.nbDeplacement = 0;
            ((Player) this).setMP(((Player) this).getMP() + 3);
        }
    }
}
