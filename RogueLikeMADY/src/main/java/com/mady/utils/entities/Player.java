package com.mady.utils.entities;

public class Player extends AbstractEntities{
    public Player(Position pos, int hitPoints, int damages, double movement) {
        super(pos, hitPoints, damages, movement);
    }


    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }
}
