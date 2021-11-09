package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEHayStorage extends DESimpleStructure {
    public DEHayStorage(){
        super("hay_storage", Offset(-7,0,-7), DEConfig.COMMON.hay_Storage);
    }
}
