package com.mady.utils.entities.factories.monster;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class GoblinArcher extends AbstractMonster{

    public GoblinArcher(Position pos) {
        super(pos, 5, 1, 5);
    }

    @Override
    public void act(Player player) {

    }

    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }
}
