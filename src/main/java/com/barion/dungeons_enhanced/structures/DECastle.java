package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DECellarStructure;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DECastle extends DECellarStructure {
    public DECastle(){
        super(DEConfig.COMMON.castle, false, new DEPiece("castle/top1", Offset(-16, 0, -16)), new DEPiece("castle/top2", Offset(-15, 0, -21)));
    }
}