package com.mady.utils.entities.factories.monster;

import com.mady.utils.Salle;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Position;

public class Troll extends AbstractMonster{

    public Troll(Position pos, Salle salle) {
        super("Troll", pos, 15, 2, 1, "T", 2, salle);
    }



    @Override
    public void skill(Entities target) {

    }
}
