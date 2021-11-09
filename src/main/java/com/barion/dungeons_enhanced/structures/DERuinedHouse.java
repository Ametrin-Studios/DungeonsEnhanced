package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DERuinedHouse extends DESimpleStructure {
    public DERuinedHouse(){
        super("ruined_house", Offset(-5, 0, -5), DEConfig.COMMON.ruined_house);
    }
}