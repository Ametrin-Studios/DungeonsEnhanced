package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEMushroomHouse extends DESimpleStructure {
    public DEMushroomHouse(){
        super(DEConfig.COMMON.mushroom_house, new DEPiece("mushroom_house", Offset(-7,0,-7)));
    }
}