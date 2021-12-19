package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEWatchTower extends DESimpleStructure {
    public DEWatchTower(){
        super(DEConfig.COMMON.watch_tower, true, new DEPiece("watch_tower", Offset(-4,0,-4)));
    }
}
