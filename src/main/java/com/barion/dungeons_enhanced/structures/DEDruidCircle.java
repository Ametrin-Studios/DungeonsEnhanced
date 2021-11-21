package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DECellarStructure;

public class DEDruidCircle extends DECellarStructure {
    public DEDruidCircle(){
        super("druid_circle/top", "druid_circle/bottom", Offset(-7, 0, -7),-1, DEConfig.COMMON.druid_circle);
    }
}
