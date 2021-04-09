package com.mady.utils.entities.factories.monster;

import com.mady.utils.Salle;
import com.mady.utils.entities.AbstractEntities;
import com.mady.utils.entities.Deplacement;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;


public abstract class AbstractMonster extends AbstractEntities implements Monster {


    public AbstractMonster(Position pos,
                           int lifePoints,
                           int damages,
                           int movement,
                           String repr,
                           int effectiveArea,
                           Salle salle) {
        super(pos, lifePoints, damages, movement, repr, effectiveArea, salle);

    }

    private double getDistance(Player player) {
        Position monsterPos = getPosition();
        Position playerPos = player.getPosition();
        return monsterPos.getDistance(playerPos);
    }

    private void updatePos(Player player) {
        Position monsterPos = getPosition();
        Position playerPos = player.getPosition();
        setPos(monsterPos.incrementPos(direction(playerPos).pos));
    }

    private Deplacement direction(Position playerPos) {
        if (getPosition().getX() < playerPos.getX()) {
           return Deplacement.BAS;
        } else if (getPosition().getY() < playerPos.getY()) {
            return Deplacement.DROITE;
        } else if (getPosition().getX() > playerPos.getX()){
            return Deplacement.HAUT;
        }
        return Deplacement.GAUCHE;
    }

    @Override
    public void act(Player player) {
        System.out.println("acting");
        double distance_from_player = getDistance(player);
        if (distance_from_player > 1) {
            updatePos(player);
            System.out.println("le monstre se rapproche");
        } else {
            attack(player);
            System.out.println("le monstre vous attaque");
        }
    }

    private void attack(Player player) {
        int monsterDamages = getDamages();
        player.takeDamages(monsterDamages);
    }

//    public boolean isAreaClear(Player player) {
//        return (getDistance(player) < effectiveArea);
//    }


}
