package com.mady.utils.entities.factories.monster;

import com.mady.utils.Salle;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Position;

public class OrcWarrior extends AbstractMonster {


    public OrcWarrior(Position pos, Salle salle) {
        super("Orc", pos, 10, 2, 1, "o", 3, salle);
    }




    @Override
    public void skill(Entities target) {

    }
}
