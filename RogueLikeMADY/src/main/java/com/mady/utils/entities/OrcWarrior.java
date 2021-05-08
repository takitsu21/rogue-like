package com.mady.utils.entities;

import com.mady.utils.Position;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;

public class OrcWarrior extends AbstractMonster {


    public OrcWarrior(Position pos, Salle salle) {
        super("Orc", pos, 10, 2, 1, "o", 3, salle);
    }


    @Override
    public void skill(Map map) {

    }
}
