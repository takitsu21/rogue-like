package com.mady.utils.entities.factories.monster;

import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class GoblinArcher extends AbstractMonster {


    public GoblinArcher(Position pos, Salle salle) {
        super("Gobelin", pos, 5, 3, 1, "g", 3, salle);
    }
  
    @Override
    public void skill(Entities target, Map map) {
        if (checkDistanceShoot(map)) {
            attack((Player) target);
        }
    }
}
