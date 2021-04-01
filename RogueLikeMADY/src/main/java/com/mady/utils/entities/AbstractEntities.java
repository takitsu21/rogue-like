package com.mady.utils.entities;

import com.mady.utils.Map;
import com.mady.utils.Util;
import com.mady.utils.entities.factories.monster.Monster;

public abstract class AbstractEntities implements Entities {
    private Position pos;
    private int maxHitPoints;
    private int hitPoints;
    private int damages;
    private final double movement;
    private final String repr;
    private final int effectiveArea;

    public AbstractEntities(Position pos,
                            int hitPoints,
                            int damages,
                            double movement,
                            String repr,
                            int effectiveArea) {
        this.pos = pos;
        maxHitPoints = hitPoints;
        this.hitPoints = hitPoints;
        this.damages = damages;
        this.movement = movement;
        this.repr = repr;
        this.effectiveArea = effectiveArea;
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

        /* TODO : gérer la mort */
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
    public double getMovement() {
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



    public Map move(Map map) {
        int randomMove = Util.r.nextInt((int)movement);
        int randomDirection = Util.r.nextInt(4);
        switch (randomDirection) {
            case 0:
                map.getMap()[pos.getX()][pos.getY()].setRepr(" ");
                map.getMap()[pos.getX()-randomMove][pos.getY()].setEntity(this);
                break;
            case 1:
                map.getMap()[pos.getX()][pos.getY()].setRepr(" ");
                map.getMap()[pos.getX()][pos.getY()-randomMove].setEntity(this);
                break;
            case 2:
                map.getMap()[pos.getX()][pos.getY()].setRepr(" ");
                map.getMap()[pos.getX()+randomMove][pos.getY()].setEntity(this);
                break;
            default:
                map.getMap()[pos.getX()][pos.getY()].setRepr(" ");
                map.getMap()[pos.getX()][pos.getY()+randomMove].setEntity(this);
                break;



        }

        return map;
    }

    @Override
    public Map doTurn(Map map) {
        if (isInPerimeter(map)) {
//            attack
            System.out.println("Le monstre attack");
        } else {
            System.out.println("on bouge");
            map = move(map);
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


}
