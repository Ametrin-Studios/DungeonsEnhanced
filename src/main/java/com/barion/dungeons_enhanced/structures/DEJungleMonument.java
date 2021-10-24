package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEJungleMonument extends DESimpleStructure {
    public DEJungleMonument(){
        super(DEConfig.COMMON.jungle_monument);
        MainPiece = DEStructures.locate("jungle_monument");
        Offset = Offset(-12,-9,-12);
    }

    @Override
    public int getSeed() {
        return 8168;
    }
}
