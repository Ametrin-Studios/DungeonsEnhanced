package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DECellarStructure;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DECastle extends DECellarStructure {
    public DECastle(){
        super(DEConfig.COMMON.castle, new DEPiece("castle/top", Offset(-16, 0, -16)));
    }
}