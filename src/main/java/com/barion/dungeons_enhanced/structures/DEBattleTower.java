package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEBattleTower extends DESimpleStructure {
    public DEBattleTower(){
        super(DEConfig.COMMON.battle_Tower);
        MainPiece = DEStructures.locate("battle_tower");
        Offset = Offset(-7,0,-7);
    }

    @Override
    public int getSeed() {
        return 2636541;
    }
}
