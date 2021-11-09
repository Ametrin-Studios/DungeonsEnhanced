package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEDesertTomb extends DESimpleStructure {
    public DEDesertTomb(){
        super("desert_tomb", Offset(-10, -6, -10), DEConfig.COMMON.desert_tomb);
    }
}
