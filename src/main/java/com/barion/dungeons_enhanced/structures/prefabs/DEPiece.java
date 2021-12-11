package com.barion.dungeons_enhanced.structures.prefabs;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import static com.barion.dungeons_enhanced.DEUtil.locate;

public class DEPiece {
    public final ResourceLocation Resource;
    public BlockPos Offset;
    public DEPiece(String resource, BlockPos offset){
        Resource = locate(resource);
        Offset = offset;
    }
    public DEPiece(String resource){
        this(resource, BlockPos.ZERO);
    }
}