package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEMinersHouse extends DESimpleStructure {
    public DEMinersHouse(){
        super(DEConfig.COMMON.miners_house);
        MainPiece = DEStructures.locate("miners_house");
        Offset = Offset(-5,0,-4);
    }

    @Override
    public int getSeed() {
        return 36516;
    }
}
