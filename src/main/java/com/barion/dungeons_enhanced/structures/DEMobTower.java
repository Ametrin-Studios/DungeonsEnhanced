package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEMobTower extends DESimpleStructure {
    public DEMobTower(){
        super("mob_tower", Offset(-5, 0, -5), DEConfig.COMMON.mob_tower);
    }
}