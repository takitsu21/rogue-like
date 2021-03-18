package com.mady.utils.entities.factories.monster;

import com.mady.utils.entities.Player;
import com.mady.utils.entities.Position;

public class OrcWarrior extends AbstractMonster{


    public OrcWarrior(Position pos) {
        super(pos, 10, 3, 3, "o");
    }

    @Override
    public void act(Player player) {
        if (!engage(player)) {
            attack(player);
        }
    }

    @Override
    public int getMaxDammages() {
        return 0;
    }

    @Override
    public void setMaxDammages(int maxDammages) {

    }
}
