package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEMobTower extends DESimpleStructure {
    public DEMobTower(){
        super(DEConfig.COMMON.mob_tower);
        MainPiece = DEStructures.locate("mob_tower");
        Offset = Offset(-5,0,-5);
    }

    @Override
    public int getSeed() {
        return 98234;
    }
}
