package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEPillagerCamp extends DESimpleStructure {
    public DEPillagerCamp(){
        super("pillager_camp", Offset(-9,0,-14), DEConfig.COMMON.pillager_camp);
    }
}
