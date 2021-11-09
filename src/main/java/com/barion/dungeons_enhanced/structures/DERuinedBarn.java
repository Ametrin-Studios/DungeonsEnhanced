package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DERuinedBarn extends DESimpleStructure {
    public DERuinedBarn(){
        super("ruined_barn", Offset(-2,0,-4), DEConfig.COMMON.ruined_barn);
    }
}