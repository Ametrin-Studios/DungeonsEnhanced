package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DERuinedBarn extends DESimpleStructure {
    public DERuinedBarn(){
        super(DEConfig.COMMON.ruined_barn);
        MainPiece = DEStructures.locate("ruined_barn");
        Offset = Offset(-2,0,-4);
    }

    @Override
    public int getSeed() {
        return 345;
    }
}
