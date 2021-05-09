package com.mady.utils.entities;

import com.mady.utils.Position;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;

public class GoblinArcher extends AbstractMonster {


    public GoblinArcher(Position pos, Salle salle, int playerLvl) {
        super("Gobelin", pos, 4, 3, 1, "g", 3, salle, playerLvl);
    }

    @Override
    public void skill(Map map) {
    }
}
