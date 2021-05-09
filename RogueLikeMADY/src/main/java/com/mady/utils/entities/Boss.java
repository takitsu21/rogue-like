package com.mady.utils.entities;

import com.mady.utils.Position;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;
import com.mady.utils.items.Chest;

public class Boss extends AbstractMonster {
    Map map;

    public Boss(Position pos, Salle salle, int playerLvl) {
        super("Boss", pos, 50, 5, 1, "B", 5, salle, playerLvl);
    }

    @Override
    public void skill(Map map) {
        this.map = map;
        Player player = map.getPlayer();
        Position posSalle = this.getSalle().getPos();
        for (int i = posSalle.getX(); i < posSalle.getX() + this.getSalle().getlignes(); i++) {
            map.getMap()[i][this.getPosition().getY()].setAttackBoss(this.getNbDeplacement() % 6 <= 2);
            if (map.getMap()[i][this.getPosition().getY()].isAttackBoss() && map.getMap()[i][this.getPosition().getY()].getEntity() != null && map.getMap()[i][this.getPosition().getY()].getEntity().equals(player)) {
                this.attack(player);
            }
        }
        for (int i = posSalle.getY(); i < posSalle.getY() + this.getSalle().getcolonnes(); i++) {
            map.getMap()[this.getPosition().getX()][i].setAttackBoss(this.getNbDeplacement() % 6 <= 2);
            if (map.getMap()[this.getPosition().getX()][i].isAttackBoss() && map.getMap()[this.getPosition().getX()][i].getEntity() != null && map.getMap()[this.getPosition().getX()][i].getEntity().equals(player)) {
                this.attack(player);
            }
        }
        map.getMap()[this.getPosition().getX()][this.getPosition().getY()].setAttackBoss(false);
    }

    @Override
    public boolean isDead(Map map) {
        if (map != null && getHitPoints() <= 0) {
            map.clearCase(map.getMap()[this.getPosition().getX()][this.getPosition().getY()]);
            map.getMap()[this.getPosition().getX()][this.getPosition().getY()].setItem(new Chest(this.getPosition(), getLvl(), getMultiplicateur()));
            System.out.println(map.getMap()[this.getPosition().getX()][this.getPosition().getY()]);
        }

        return (getHitPoints() <= 0);
    }
}
