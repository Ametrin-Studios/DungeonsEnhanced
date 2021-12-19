package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DECellarStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEDruidCircle extends DECellarStructure {
    public DEDruidCircle(){
        super(DEConfig.COMMON.druid_circle);
        MainPiece = DEStructures.locate("druid_circle/top");
        Cellar = DEStructures.locate("druid_circle/bottom");
        Offset = Offset(-7, 0, -7);
        CellarOffset = -1;
    }

    @Override
    public int getSeed() {
        return 11194;
    }
}
