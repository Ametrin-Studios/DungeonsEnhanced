package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEJungleMonument extends DESimpleStructure {
    public DEJungleMonument(){
        super(DEConfig.COMMON.jungle_monument, new DEPiece("jungle_monument", Offset(-12,-9,-12)));
    }
}
