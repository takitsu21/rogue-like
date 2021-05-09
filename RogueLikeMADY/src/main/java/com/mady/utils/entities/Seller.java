package com.mady.utils.entities;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Position;
import com.mady.utils.environment.Map;

public class Seller extends AbstractMonster {
    public Seller(Position p) {
        super("Henry", p, 1, 0, 0, Ansi.colorize("!", Attribute.YELLOW_TEXT()), 0, null, 1);

    }

    @Override
    public void skill(Map map) {

    }
}
