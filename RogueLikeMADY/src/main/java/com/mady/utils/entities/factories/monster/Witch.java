package com.mady.utils.entities.factories.monster;

import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class Witch extends AbstractMonster{

    public Witch(Position pos, Salle salle) {
        super("Sorcière", pos, 8, 3, 1, "w", 5, salle);
    }


    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }

    @Override
    public void skill(Entities target, Map map) {
        if (checkDistanceShoot(map)) {
            attack((Player) target);
        }
    }
}
