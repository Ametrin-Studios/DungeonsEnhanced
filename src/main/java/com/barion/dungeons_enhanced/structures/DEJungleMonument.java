package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEJungleMonument extends DESimpleStructure {
    public DEJungleMonument(){
        super("jungle_monument", Offset(-12,-9,-12), DEConfig.COMMON.jungle_monument);
    }
}
