package com.mady.utils.entities;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.GameStatus;
import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.Util;
import com.mady.utils.entities.factories.monster.AbstractMonster;

public abstract class AbstractEntities implements Entities {
    private Position pos;
    private int maxHitPoints;
    private int hitPoints;
    private int damages;
    private final int movement;
    private final String repr;
    private final int effectiveArea;
    private Salle salle;
    private boolean isAggro = false;

    public AbstractEntities(Position pos,
                            int hitPoints,
                            int damages,
                            int movement,
                            String repr,
                            int effectiveArea,
                            Salle salle) {
        this.pos = pos;
        maxHitPoints = hitPoints;
        this.hitPoints = hitPoints;
        this.damages = damages;
        this.movement = movement;
        this.repr = repr;
        this.effectiveArea = effectiveArea;
        this.salle=salle;
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

    public int getEffectiveArea() {
        return effectiveArea;
    }

    public boolean isAggro() {
        return isAggro;
    }

    public void setAggro(boolean aggro) {
        isAggro = aggro;
    }

    @Override
    public void setPos(Position position) {
        this.pos = position;
    }

    @Override
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    @Override
    public void setMaxHitPoints(int maxHitPoints) {
        this.maxHitPoints=maxHitPoints;}

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
        if (this instanceof Player){
            ((Player) this).setHP(((Player) this).getHP() - damages);
        } else {
            int new_HP = getHitPoints() - damages;
            setHitPoints(new_HP);
        }
        if (this.isDead()) {
            Util.currentAction.append(Ansi.colorize(String.format("Vous avez tué %s.\n", getRepr()),
                    Attribute.RED_TEXT()));
        }
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
        this.damages=damages;
    }

    @Override
    public int getMovement() {
        return movement;
    }


    private boolean isInPerimeter(Map map) {
        for (int i = pos.getX() - effectiveArea; i < pos.getX() + effectiveArea; i++) {
            for (int j = pos.getY() - effectiveArea; j < pos.getY() + effectiveArea; j++) {
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


    @Override
    public Map doTurn(Map map) {
        if (!this.isDead()) {
            if (isInPerimeter(map)) {
                ((AbstractMonster) this).act(map);
            } else {
                while (!map.move(this, nextPos(this))) ;
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
}
