package com.mady.utils.entities;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.Util;
import com.mady.utils.environment.Case;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;


public class DarkDruide extends AbstractMonster {

    public DarkDruide(Position pos, Salle salle, int playerLvl) {
        super("Druide noir", pos, 10, 2, 1, "d", 3, salle, playerLvl);
    }


    /**
     * @param map druid has a special skill
     *            he can heal another unit if a unit is attack around him
     *            he can't heal himself
     */
    @Override
    public void skill(Map map) {
        Position pos = getPosition();
        int area = getEffectiveArea();
        if (checkSalle(map)) {
            for (int i = pos.getX() - area; i <= pos.getX() + area; i++) {
                for (int j = pos.getY() - area; j <= pos.getY() + area; j++) {
                    if (map.isInside(i, j) && map.getMap()[i][j].isOccupied() && !map.getMap()[i][j].isPlayer()) {
                        Entities target = map.getMap()[i][j].getEntity();
                        if (target != null && target != this) {
                            if (target.getHitPoints() != target.getMaxHitPoints()) {
                                heal(target);
                            }
                        }
                    }
                }
            }
        } else {
            attack(map.getPlayer());
        }
    }

    private void heal(Entities target) {
        ((AbstractEntities) target).setHealed(true);
        int heal = (int) Math.floor(getDamages() * 0.75);
        int HpHealed = target.getHitPoints() + heal;
        target.setHitPoints(HpHealed);
        if (target.getHitPoints() > target.getMaxHitPoints()) {
            target.setHitPoints(target.getMaxHitPoints());
            setHealed(false);
        }
        Util.currentAction.append(Ansi.colorize(String.format("%s<%d/%d HP> est soigné de %d HP.\n",
                target.getName(), target.getHitPoints(), target.getMaxHitPoints(), heal), Attribute.GREEN_TEXT()));
    }

    /**
     * @param map
     * @return a bool. Method that check if druid is surrender by other monsters
     */
    private boolean checkSalle(Map map) {
        Salle current = getSalle();
        int origineSalleX = current.getPos().getX();
        int origineSalleY = current.getPos().getY();
        for (int i = origineSalleX; i <= origineSalleX + current.getlignes(); i++) {
            for (int j = origineSalleY; j <= origineSalleY + current.getcolonnes(); j++) {
                if (map.getMap()[i][j].isOccupied() && !map.getMap()[i][j].isPlayer()) {
                    Case location = map.getMap()[i][j];
                    if (location.getEntity() instanceof AbstractMonster) {
                        if (location.getEntity().getPosition() != this.getPosition()) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
