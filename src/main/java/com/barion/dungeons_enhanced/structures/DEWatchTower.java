package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEWatchTower extends DESimpleStructure {
    public DEWatchTower(){
        super(DEConfig.COMMON.watch_tower);
        MainPiece = DEStructures.locate("watch_tower");
        Offset = Offset(-4,0,-4);
    }

    @Override
    public int getSeed() {
        return 687871;
    }
}
