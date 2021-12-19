package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DETallWitchHut extends DESimpleStructure {
    public DETallWitchHut(){
        super(DEConfig.COMMON.tall_witch_hut);
        MainPiece = DEStructures.locate("tall_witch_hut");
        Offset = Offset(-3,-3,-4);
    }

    @Override
    public int getSeed() {
        return 6664;
    }
}
