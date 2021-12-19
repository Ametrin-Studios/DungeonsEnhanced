package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEWitchTower extends DESimpleStructure {
    public DEWitchTower() {
        super(DEConfig.COMMON.witch_tower);
        MainPiece = DEStructures.locate("witch_tower");
        Offset = Offset(-5,0,-5);
    }

    @Override
    public int getSeed() {
        return 354641078;
    }
}