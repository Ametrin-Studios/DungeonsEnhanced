package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEDesertTomb extends DESimpleStructure {
    public DEDesertTomb(){
        super(DEConfig.COMMON.desert_tomb);
        MainPiece = DEStructures.locate("desert_tomb");
        Offset = Offset(-10,-6,-5);
    }

    @Override
    public int getSeed() {
        return 2450273;
    }
}
