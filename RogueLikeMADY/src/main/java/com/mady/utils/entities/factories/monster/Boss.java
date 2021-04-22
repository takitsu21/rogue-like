package com.mady.utils.entities.factories.monster;

import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class Boss extends AbstractMonster{

    public Boss(Position pos, Salle salle) {
        super("Boss", pos, 50, 5, 1, "B", 5, salle);
    }


    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }


    public void skill(Map map) {
        Player player=map.getPlayer();
        Position posSalle=this.getSalle().getPos();
        for (int i=posSalle.getX();i<posSalle.getX() + this.getSalle().getlignes();i++) {
            if (map.getMap()[i][this.getPosition().getY()].getEntity()!=null && map.getMap()[i][this.getPosition().getY()].getEntity().equals(player)) {
                this.attack(player);
            }
            map.getMap()[i][this.getPosition().getY()].setAttackBoss(true);
        }
        for (int i=posSalle.getY();i<posSalle.getY() + this.getSalle().getcolonnes();i++) {
            if (map.getMap()[this.getPosition().getX()][i].getEntity()!=null && map.getMap()[this.getPosition().getX()][i].getEntity().equals(player)) {
                this.attack(player);
            }
            map.getMap()[this.getPosition().getX()][i].setAttackBoss(true);
        }
        map.getMap()[this.getPosition().getX()][this.getPosition().getY()].setAttackBoss(false);
    }

    @Override
    public void skill(Entities target) {

    }
}
