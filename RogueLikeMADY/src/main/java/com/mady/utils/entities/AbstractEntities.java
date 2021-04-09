package com.mady.utils.entities;

import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.Util;

public abstract class AbstractEntities implements Entities {
    private Position pos;
    private int maxHitPoints;
    private int hitPoints;
    private int damages;
    private final int movement;
    private final String repr;
    private final int effectiveArea;
    private Salle salle;

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
        return repr;
    }

    @Override
    public Position getPosition() {
        return pos;
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
        this.hitPoints=hitPoints;
    }

    @Override
    public void takeDamages(int damages) {

        setHitPoints(getHitPoints()-damages);

        /* TODO : gérer la mort player et monstre */
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
        for (int i = pos.getX() - effectiveArea; i < effectiveArea; i++) {
            for (int j = pos.getY() - effectiveArea; j < effectiveArea; j++) {
                if (map.getMap()[i][j].isPlayer()) {
                    return true;
                }
            }
        }
        return false;
    }



    public Position nextPos(Entities entitie) {
        int randomMove = Util.r.nextInt(entitie.getMovement() + 1);
        Deplacement d = Util.randomDirection();
        return d.pos.multiplyPos(randomMove);
    }

    @Override
    public Map doTurn(Map map) {
        if (isInPerimeter(map)) {
//            attack
            System.out.println("Le monstre attaque");
        } else {
//            System.out.println("on bouge");
            if (!map.move(this,nextPos(this))){
                map.move(this,nextPos(this));
            }
        }
        return map;
//        if (isAreaClear(player)) {
//            double movement = getMovement();
//            if (getPosition().getX() + movement > //dimension de salle) //idée: mettre la liste des entités
//            // dans salle.
//        }
    }
//
//    @Override
//    public void doTurn() {
//
//    }

    public Salle getSalle() {
        return salle;
    }


    public void setSalle(Salle salle) {
        this.salle = salle;
    }
}
