package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEMinersHouse extends DESimpleStructure {
    public DEMinersHouse(){
        super("miners_house", Offset(-5, 0, -5), DEConfig.COMMON.miners_house);
    }
}