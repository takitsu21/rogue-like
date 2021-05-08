package com.mady.utils.entities;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.enums.DeplacementEnum;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;


public abstract class AbstractMonster extends AbstractEntities implements Monster {
    public AbstractMonster(String name,
                           Position pos,
                           int lifePoints,
                           int damages,
                           int movement,
                           String repr,
                           int effectiveArea,
                           Salle salle) {

        super(name, pos, lifePoints, damages, movement, repr, effectiveArea, salle);
        setMaxHitPoints((int) (lifePoints * getLvl() * getMultiplicateur()));
        setHitPoints((int) (lifePoints * getLvl() * getMultiplicateur()));
        setDamages((int) (damages * getLvl() * getMultiplicateur()));
    }


    private void updatePos(Map map, Player player) {
        Position playerPos = player.getPosition();
        DeplacementEnum dep = direction(playerPos);
        map.move(this, dep.pos);
    }

    /**
     * @param playerPos position du player
     * @return a direction
     * direction will determine where the monster needs to head to to find the player
     */

    private DeplacementEnum direction(Position playerPos) {
        if (getPosition().getX() < playerPos.getX()) {
            return DeplacementEnum.BAS;
        } else if (getPosition().getY() < playerPos.getY()) {
            return DeplacementEnum.DROITE;
        } else if (getPosition().getX() > playerPos.getX()) {
            return DeplacementEnum.HAUT;
        }
        return DeplacementEnum.GAUCHE;
    }

    /**
     * @param map the comportment of the monster.
     *            Check of special cases for special acting: witch, druid and distant shoot for goblin
     *            or either you can attack or move if the player can't be hit
     */

    public void act(Map map) {
        Player player = map.getPlayer();
        if (this instanceof Witch) {
            this.attack(player);
        } else if (this instanceof DarkDruide) {
            this.skill(map);
        } else if (this instanceof GoblinArcher && checkDistanceShoot(map)) {
            this.attack(player);
        } else if (this.nextTo(map)) {
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

    /**
     * @return special string based on target status
     */
    @Override
    public String getRepr() {
        if (isHealed()) {
            setHealed(false);
            return Ansi.colorize(super.getRepr(), Attribute.GREEN_BACK(), Attribute.BLACK_TEXT());
        }
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
    public abstract void skill(Map map);

    /**
     * @param map
     * @return either or not the monster can attack from a distance the player
     * the player must be precisely on one of the fourth directional case and this one msut be
     * at the exact distance that equals the effective area
     */
    public boolean checkDistanceShoot(Map map) {
        Position monsterPos = this.getPosition();

        if (map.getMap()[monsterPos.getX() - getEffectiveArea()][monsterPos.getY()].getEntity() instanceof Player
                && map.isInside(monsterPos.getX() - getEffectiveArea(), monsterPos.getY())) {
            return true;
        }

        if (map.getMap()[monsterPos.getX() + getEffectiveArea()][monsterPos.getY()].getEntity() instanceof Player
                && map.isInside(monsterPos.getX() + getEffectiveArea(), monsterPos.getY())) {
            return true;
        }

        if (map.getMap()[monsterPos.getX()][monsterPos.getY() - getEffectiveArea()].getEntity() instanceof Player
                && map.isInside(monsterPos.getX(), monsterPos.getY() - getEffectiveArea())) {
            return true;
        }

        if (map.getMap()[monsterPos.getX()][monsterPos.getY() + getEffectiveArea()].getEntity() instanceof Player
                && map.isInside(monsterPos.getX(), monsterPos.getY() + getEffectiveArea())) {
            return true;
        }
        return false;
    }

}
