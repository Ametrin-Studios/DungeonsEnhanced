package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEStables extends DESimpleStructure {
    public DEStables() {
        super(DEConfig.COMMON.stables, false, new DEPiece("stables", Offset(-8,-6,-13)));
    }
}
