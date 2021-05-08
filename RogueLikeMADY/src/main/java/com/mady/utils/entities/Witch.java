package com.mady.utils.entities;

import com.mady.utils.Position;
import com.mady.utils.environment.Map;
import com.mady.utils.environment.Salle;

public class Witch extends AbstractMonster {

    public Witch(Position pos, Salle salle) {
        super("Sorcière", pos, 8, 2, 1, "w", 3, salle);
    }

    @Override
    public void skill(Map map) {
    }

}
