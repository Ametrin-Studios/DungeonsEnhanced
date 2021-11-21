package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DECellarStructure;

public class DECastle extends DECellarStructure {
    public DECastle(){
        super("castle/top", Offset(-16, 0, -16), DEConfig.COMMON.castle);
    }
}