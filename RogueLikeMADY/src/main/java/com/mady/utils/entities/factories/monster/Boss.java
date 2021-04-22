package com.mady.utils.entities.factories.monster;

import com.mady.utils.Salle;
import com.mady.utils.entities.Position;

public class Boss extends AbstractMonster{

    public Boss(Position pos, Salle salle) {
        super("Boss", pos, 50, 5, 1, "B", 5, salle);
    }


    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }
}
