package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DETreeHouse extends DESimpleStructure {
    public DETreeHouse(){
        super(DEConfig.COMMON.tree_house, true, new DEPiece("tree_house", Offset(-9,0,-10)));
    }
}
