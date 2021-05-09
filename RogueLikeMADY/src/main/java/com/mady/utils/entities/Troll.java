package com.mady.utils.entities;

import com.mady.utils.Position;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;

public class Troll extends AbstractMonster {

    public Troll(Position pos, Salle salle, int playerLvl) {
        super("Troll", pos, 12, 2, 1, "T", 2, salle, playerLvl);
    }


    @Override
    public void skill(Map map) {

    }
}
