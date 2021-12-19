package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DETreeHouse extends DESimpleStructure {
    public DETreeHouse(){
        super(DEConfig.COMMON.tree_house);
        MainPiece = DEStructures.locate("tree_house");
        Offset = Offset(-9,0,-10);
    }

    @Override
    public int getSeed() {
        return 723648;
    }
}
