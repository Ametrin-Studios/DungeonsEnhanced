package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DETowerOfTheUndead extends DESimpleStructure {
    public DETowerOfTheUndead(){
        super("tower_of_the_undead", Offset(-7, 0, -7), DEConfig.COMMON.tower_of_the_undead);
    }
}