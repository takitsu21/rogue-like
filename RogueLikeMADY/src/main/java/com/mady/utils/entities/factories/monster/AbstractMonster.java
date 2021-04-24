package com.mady.utils.entities.factories.monster;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.Util;
import com.mady.utils.entities.*;


public abstract class AbstractMonster extends AbstractEntities implements Monster {
    public AbstractMonster(String name,
                           Position pos,
                           int lifePoints,
                           int damages,
                           int movement,
                           String repr,
                           int effectiveArea,
                           Salle salle) {

        super(name,pos, lifePoints, damages, movement, repr, effectiveArea, salle);
        if(getLvl() != 1){
            setMaxHitPoints((int) (lifePoints * (getLvl() - 1) * getMultiplicateur()));
            setHitPoints((int) (lifePoints * (getLvl() - 1) * getMultiplicateur()));
            setDamages((int) (damages * (getLvl() - 1) * getMultiplicateur()));
        }
    }


    private void updatePos(Map map, Player player) {
        Position playerPos = player.getPosition();
        Deplacement dep = direction(playerPos);
        map.move(this, dep.pos);
    }

    /**
     *
     * @param playerPos position du player
     * @return a direction
     * direction will determine where the monster needs to head to to find the player
     */

    private Deplacement direction(Position playerPos) {
        if (getPosition().getX() < playerPos.getX()) {
            return Deplacement.BAS;
        } else if (getPosition().getY() < playerPos.getY()) {
            return Deplacement.DROITE;
        } else if (getPosition().getX() > playerPos.getX()) {
            return Deplacement.HAUT;
        }
        return Deplacement.GAUCHE;
    }

    /**
     *
     * @param map
     * the comportment of the monster.
     * Two possibilities, either he's next to the player and then attacks him, or the mob is away from the player and
     * moves randomly.
     */

    public void act(Map map) {
        Player player = map.getPlayer();
        if (this.nextTo(map)) {
            this.attack(player);
        } else {
            updatePos(map, player);
            Util.currentAction.append(Ansi.colorize(String.format("%s<%d/%d HP> se rapproche.\n",
                    getName(), getHitPoints(), getMaxHitPoints()), Attribute.YELLOW_TEXT()));
        }
    }

    public void attack(Player player) {
        int monsterDamages = getDamages();
        player.takeDamages(monsterDamages);
        Util.currentAction.append(Ansi.colorize(String.format("%s<%d/%d HP> vous a infligé %d dégâts.\n",
                getName(), getHitPoints(), getMaxHitPoints(), getDamages()), Attribute.RED_TEXT()));
    }

    @Override
    public String getRepr() {
        if (isAttack()) {
            setIsAttack(false);
            return Ansi.colorize(super.getRepr(), Attribute.RED_BACK());
        }
        if (isAggro()) {
            return Ansi.colorize(super.getRepr(), Attribute.RED_TEXT());
        }

        return super.getRepr();
    }

    /**
     *
     * @param map map sur laquelle ce trouve le monstre
     * @return a bool if the player is on one of the four cases around us
     */

    private boolean nextTo(Map map) {
        Position monsterPos = this.getPosition();

        if (map.getMap()[monsterPos.getX() - 1][monsterPos.getY()].getEntity() instanceof Player) {
            return true;
        }

        if (map.getMap()[monsterPos.getX() + 1][monsterPos.getY()].getEntity() instanceof Player) {
            return true;
        }

        if (map.getMap()[monsterPos.getX()][monsterPos.getY() - 1].getEntity() instanceof Player) {
            return true;
        }

        return map.getMap()[monsterPos.getX()][monsterPos.getY() + 1].getEntity() instanceof Player;
    }

    @Override
    public abstract void skill(Entities target, Map map);

    public boolean checkDistanceShoot(Map map) {
        Position monsterPos = this.getPosition();

        if (map.getMap()[monsterPos.getX() - getEffectiveArea()][monsterPos.getY()].getEntity() instanceof Player) {
            return true;
        }

        if (map.getMap()[monsterPos.getX() + getEffectiveArea()][monsterPos.getY()].getEntity() instanceof Player) {
            return true;
        }

        if (map.getMap()[monsterPos.getX()][monsterPos.getY() - getEffectiveArea()].getEntity() instanceof Player) {
            return true;
        }

        if (map.getMap()[monsterPos.getX()][monsterPos.getY() + getEffectiveArea()].getEntity() instanceof Player) {
            return true;
        }
        return false;
    }

}
