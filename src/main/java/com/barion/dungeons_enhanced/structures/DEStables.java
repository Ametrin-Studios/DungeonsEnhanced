package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEStables extends DESimpleStructure {
    public DEStables() {
        super("stables", Offset(-8,-6,-13), DEConfig.COMMON.stables);
    }
}
