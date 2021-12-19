package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEMushroomHouse extends DESimpleStructure {
    public DEMushroomHouse(){
        super(DEConfig.COMMON.mushroom_house);
        MainPiece = DEStructures.locate("mushroom_house");
        Offset = Offset(-7,0,-7);
    }

    @Override
    public int getSeed() {
        return 32108;
    }
}
