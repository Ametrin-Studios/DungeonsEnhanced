package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEWatchTower extends DESimpleStructure {
    public DEWatchTower(){
        super("watch_tower", Offset(-4,0,-4), DEConfig.COMMON.watch_tower);
    }
}
