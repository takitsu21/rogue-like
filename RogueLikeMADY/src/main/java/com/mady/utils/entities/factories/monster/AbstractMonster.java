package com.mady.utils.entities.factories.monster;

import com.mady.utils.entities.AbstractEntities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;
import javafx.geometry.Pos;

public abstract class AbstractMonster  extends AbstractEntities implements Monster {

    private final int effectiveArea;

    public AbstractMonster(Position pos, int lifePoints, int damages, double movement, String repr, int effectiveArea) {
        super(pos, lifePoints, damages, movement, repr);

        this.effectiveArea = effectiveArea;
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

    public boolean isAreaClear(Player player) {
        return (getDistance(player) < effectiveArea);
    }

    @Override
    public void doTurn(Player player) {
        if (isAreaClear(player)) {
            double movement = getMovement();
            if (getPosition().getX() + movement > //dimension de salle) //idée: mettre la liste des entités
                                                                        // dans salle.
        }
    }
}
