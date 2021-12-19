package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEDesertTomb extends DESimpleStructure {
    public DEDesertTomb(){
        super( DEConfig.COMMON.desert_tomb, true, new DEPiece("desert_tomb", Offset(-10, -6, -10)));
    }
}
