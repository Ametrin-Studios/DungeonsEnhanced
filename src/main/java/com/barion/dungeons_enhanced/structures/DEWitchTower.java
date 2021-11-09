package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEWitchTower extends DESimpleStructure {
    public DEWitchTower() {
        super("witch_tower", Offset(-5,0,-5), DEConfig.COMMON.witch_tower);
    }
}