package com.mady.utils.entities.factories.monster;

import com.mady.utils.entities.AbstractEntities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;


public abstract class AbstractMonster  extends AbstractEntities implements Monster {



    public AbstractMonster(Position pos,
                           int lifePoints,
                           int damages,
                           int movement,
                           String repr,
                           int effectiveArea) {
        super(pos, lifePoints, damages, movement, repr, effectiveArea);

    }

    private double getDistance (Player player) {
        Position monsterPos = getPosition();
        Position playerPos = player.getPosition();
        return monsterPos.getDistance(playerPos);
    }

    private void updatePos(Player player, double distance) {
        Position monsterPos = getPosition();
        Position playerPos = player.getPosition();
        setPos(monsterPos.moveTo(playerPos, distance));
    }

    public boolean engage(Player player) {
        boolean toMove = getDistance(player) > 1;
        if (toMove) {
            updatePos(player, getMovement());
        }
        return toMove;
    }

    public void attack(Player player) {
        int monsterDamages = getDamages();
        player.takeDamages(monsterDamages);
    }

//    public boolean isAreaClear(Player player) {
//        return (getDistance(player) < effectiveArea);
//    }


}
