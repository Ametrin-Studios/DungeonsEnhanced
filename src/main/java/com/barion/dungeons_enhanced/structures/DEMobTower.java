package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEMobTower extends DESimpleStructure {
    public DEMobTower(){
        super(DEConfig.COMMON.mob_tower, new DEPiece("mob_tower", Offset(-5, 0, -5)));
    }
}