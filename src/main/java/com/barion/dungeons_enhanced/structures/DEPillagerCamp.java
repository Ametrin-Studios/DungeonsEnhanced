package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEPillagerCamp extends DESimpleStructure {
    public DEPillagerCamp(){
        super(DEConfig.COMMON.pillager_camp);
        MainPiece = DEStructures.locate("pillager_camp");
        Offset = Offset(-9,0,-14);
    }

    @Override
    public int getSeed() {
        return 946;
    }
}
