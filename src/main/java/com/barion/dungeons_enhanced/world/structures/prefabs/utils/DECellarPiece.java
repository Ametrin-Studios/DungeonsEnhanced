package com.barion.dungeons_enhanced.world.structures.prefabs.utils;

import net.minecraft.core.BlockPos;

public class DECellarPiece extends DEStructurePiece {
    public String Cellar;

    public DECellarPiece(String piece, String cellar, BlockPos offset) {
        super(piece, offset);
        Cellar = cellar;
    }

    public DECellarPiece(String piece, String cellar) {this(piece, cellar, BlockPos.ZERO);}
}
