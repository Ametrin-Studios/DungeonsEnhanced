package com.barion.dungeons_enhanced.world.structures.prefabs.utils;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.core.BlockPos;

public class DECellarPiece extends DEStructurePiece {
    public String Cellar;

    public DECellarPiece(String piece, String cellar, BlockPos offset) {
        super(DEUtil.createRegistryName(piece), offset, 1);
        Cellar = cellar;
    }

    public DECellarPiece(String piece, String cellar) {this(piece, cellar, BlockPos.ZERO);}
}
