package com.mady.utils.entities;

import com.mady.utils.Position;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;

public class Troll extends AbstractMonster {

    public Troll(Position pos, Salle salle) {
        super("Troll", pos, 15, 2, 1, "T", 2, salle);
    }


    @Override
    public void skill(Map map) {

    }
}