package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DETreeHouse extends DESimpleStructure {
    public DETreeHouse(){
        super("tree_house", Offset(-9,0,-10), DEConfig.COMMON.tree_house);
    }
}
