package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DETallWitchHut extends DESimpleStructure {
    public DETallWitchHut(){
        super(DEConfig.COMMON.tall_witch_hut, new DEPiece("tall_witch_hut", Offset(-3,-3,-4)));
    }
}
