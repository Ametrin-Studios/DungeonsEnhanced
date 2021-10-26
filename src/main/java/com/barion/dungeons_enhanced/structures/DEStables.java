package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEStables extends DESimpleStructure {
    public DEStables() {
        super(DEConfig.COMMON.stables);
        MainPiece = DEStructures.locate("stables");
        Offset = Offset(-8,-6,-13);
    }

    @Override
    public int getSeed() {
        return 986646130;
    }

}
