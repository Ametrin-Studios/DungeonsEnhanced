package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEMushroomHouse extends DESimpleStructure {
    public DEMushroomHouse(){
        super("mushroom_house", Offset(-7,0,-7), DEConfig.COMMON.mushroom_house);
    }
}