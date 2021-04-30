package com.mady.utils.entities.factories.monster;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Case;
import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.Util;
import com.mady.utils.entities.AbstractEntities;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Position;
import javafx.geometry.Pos;

import java.util.List;

public class DarkDruide extends AbstractMonster {

    public DarkDruide(Position pos, Salle salle) {
        super("Druide noir", pos, 12, 3, 1, "d", 4, salle);
    }


    @Override
    public void skill(Map map) {
        Position pos = getPosition();
        int area = getEffectiveArea();
        if (checkSalle(map)) {
            for (int i = pos.getX() - area; i <= pos.getX() + area; i++) {
                for (int j = pos.getY() - area; j <= pos.getY() + area; j++) {
                    if (map.isInside(i, j) && map.getMap()[i][j].isOccupied() && !map.getMap()[i][j].isPlayer()) {
                        Entities target = map.getMap()[i][j].getEntity();
                        if (target != null) {
                            if (target.getHitPoints() == target.getMaxHitPoints()) {
                            } else {
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
        int HpHealed = target.getHitPoints() + getDamages();
        target.setHitPoints(HpHealed);
        if (target.getHitPoints() > target.getMaxHitPoints()) {
            target.setHitPoints(target.getMaxHitPoints());
        }
        Util.currentAction.append(Ansi.colorize(String.format("%s<%d/%d HP> est soigné de %d HP.\n",
                target.getName(), target.getHitPoints(), target.getMaxHitPoints(), getDamages()), Attribute.GREEN_TEXT()));
    }

//    private boolean checkMonsterInSalle(Map map) {
//        boolean inSalle = false;
//        Salle druidSalle = getSalle();
//        int lignes = druidSalle.getlignes();
//        int colonnes = druidSalle.getcolonnes();
//        Position druidPos = getPosition();
//        for (int i = 0; i <= lignes; i++) {
//            for (int j = 0; j <= colonnes; j++) {
//                if (map.getMap()[i][j].getEntity() instanceof AbstractMonster && !map.getMap()[i][j].isPlayer()) {
//                    Position entityPos = map.getMap()[i][j].getEntity().getPosition();
//                    if (entityPos != druidPos) {
//                        inSalle = true;
//                        return inSalle;
//                    }
//                }
//            }
//        }
//        return inSalle;
//    }

    private boolean checkSalle(Map map) {
        boolean inSalle = false;
        Salle current = getSalle();
        int origineSalleX = current.getPos().getX();
        int origineSalleY = current.getPos().getY();
        for (int i = origineSalleX; i <= origineSalleX + current.getlignes(); i++) {
            for (int j = origineSalleY; j <= origineSalleY + current.getcolonnes(); j++) {
                if (map.getMap()[i][j].isOccupied() && !map.getMap()[i][j].isPlayer()) {
                    Case location = map.getMap()[i][j];
                    if (location.getEntity() instanceof AbstractMonster) {
                        if (location.getEntity().getPosition() != this.getPosition()) {
                            inSalle = true;
                            return inSalle;
                        }
                    }
                }
            }
        }
        return false;
    }
}
