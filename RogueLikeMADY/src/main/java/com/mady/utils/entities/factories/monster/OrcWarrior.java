package com.mady.utils.entities.factories.monster;

import com.mady.utils.Salle;
import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class OrcWarrior extends AbstractMonster{


    public OrcWarrior(Position pos, Salle salle) {
        super(pos, 10, 1, 1, "o", 3, salle);
    }


    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }
}
