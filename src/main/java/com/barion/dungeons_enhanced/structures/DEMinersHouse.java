package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEMinersHouse extends DESimpleStructure {
    public DEMinersHouse(){
        super(DEConfig.COMMON.miners_house, true, new DEPiece("miners_house", Offset(-5, 0, -5)));
    }
}