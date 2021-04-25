package com.mady.utils.entities.factories.monster;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import com.mady.utils.Map;
import com.mady.utils.Salle;
import com.mady.utils.entities.Entities;
import com.mady.utils.entities.Position;

public class Seller extends AbstractMonster{
    public Seller(Position p) {
        super("Seller",p,1,0,0, Ansi.colorize("!", Attribute.YELLOW_TEXT()),0,null);

    }

    @Override
    public void skill(Entities target, Map map) {

    }
}
