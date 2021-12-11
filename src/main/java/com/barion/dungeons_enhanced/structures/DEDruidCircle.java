package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DECellarStructure;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEDruidCircle extends DECellarStructure {
    public DEDruidCircle(){
        super(new DEPiece("druid_circle/top", Offset(-7, 0, -7)), new DEPiece("druid_circle/bottom", Offset(0, -1, 0)), DEConfig.COMMON.druid_circle);
    }
}
