package com.mady.utils.entities.factories.monster;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.Util;
import com.mady.utils.entities.AbstractEntities;
import com.mady.utils.entities.Deplacement;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;


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

    }

    private double getDistance(Player player) {
        Position monsterPos = getPosition();
        Position playerPos = player.getPosition();
        return monsterPos.getDistance(playerPos);
    }

    private void updatePos(Map map, Player player) {
        Position playerPos = player.getPosition();
        Deplacement dep = direction(playerPos);
        map.move(this, dep.pos);
    }

    /**
     *
     * @param playerPos
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
            Util.currentAction.append(Ansi.colorize(String.format("%s<%d/%d HP> vous a infligé %d dégâts.\n",
                    getName(), getHitPoints(), getMaxHitPoints(), getDamages()), Attribute.RED_TEXT()));
        } else {
            updatePos(map, player);
            Util.currentAction.append(Ansi.colorize(String.format("%s<%d/%d HP> se rapproche.\n",
                    getName(), getHitPoints(), getMaxHitPoints()), Attribute.YELLOW_TEXT()));
        }
    }

    private void attack(Player player) {
        int monsterDamages = getDamages();
        player.takeDamages(monsterDamages);

    }

    @Override
    public String getRepr() {
        if (isAggro()) {
            return Ansi.colorize(super.getRepr(), Attribute.RED_TEXT());
        }
        return super.getRepr();
    }

    /**
     *
     * @param map
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

        if (map.getMap()[monsterPos.getX()][monsterPos.getY() + 1].getEntity() instanceof Player) {
            return true;
        }
        return false;
    }
}
