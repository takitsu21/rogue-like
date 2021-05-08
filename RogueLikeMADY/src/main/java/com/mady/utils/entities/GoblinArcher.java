package com.mady.utils.entities;

import com.mady.utils.Position;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;

public class GoblinArcher extends AbstractMonster {


    public GoblinArcher(Position pos, Salle salle) {
        super("Gobelin", pos, 5, 3, 1, "g", 3, salle);
    }

    @Override
    public void skill(Map map) {
    }
}
