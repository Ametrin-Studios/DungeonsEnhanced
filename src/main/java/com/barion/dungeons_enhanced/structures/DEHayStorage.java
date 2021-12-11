package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEHayStorage extends DESimpleStructure {
    public DEHayStorage(){
        super(DEConfig.COMMON.hay_Storage, new DEPiece("hay_storage", Offset(-7,0,-7)));
    }
}
