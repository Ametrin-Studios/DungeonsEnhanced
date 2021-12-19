package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEPillagerCamp extends DESimpleStructure {
    public DEPillagerCamp(){
        super(DEConfig.COMMON.pillager_camp, true, new DEPiece("pillager_camp", Offset(-9,0,-14)));
    }
}