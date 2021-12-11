package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DETowerOfTheUndead extends DESimpleStructure {
    public DETowerOfTheUndead(){
        super(DEConfig.COMMON.tower_of_the_undead, new DEPiece("tower_of_the_undead", Offset(-7, 0, -7)));
    }
}