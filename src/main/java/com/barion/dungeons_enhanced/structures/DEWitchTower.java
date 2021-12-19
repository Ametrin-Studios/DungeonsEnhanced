package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEWitchTower extends DESimpleStructure {
    public DEWitchTower() {
        super(DEConfig.COMMON.witch_tower, true, new DEPiece("witch_tower/normal", Offset(-5,0,-5)));
    }
}