package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DECellarStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DECastle extends DECellarStructure {
    public DECastle(){
        super(DEConfig.COMMON.castle);
        Cellar = null;
        MainPiece = DEStructures.locate("castle/top");
        Offset = Offset(-16, 0, -16);
    }

    @Override
    public int getSeed() {
        return 1194;
    }
}