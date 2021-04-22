package com.mady.utils.entities.factories.monster;

import com.mady.utils.Salle;
import com.mady.utils.entities.Position;

public class DarkDruide extends AbstractMonster{

    public DarkDruide(Position pos, Salle salle) {
        super("Druide noir", pos, 20, 2, 1, "D", 3, salle);
    }

    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }
}
