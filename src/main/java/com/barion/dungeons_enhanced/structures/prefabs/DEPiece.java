package com.barion.dungeons_enhanced.structures.prefabs;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEPiece {
    public final ResourceLocation Resource;
    public BlockPos Offset;
    public int Weight;
    public DEPiece(String resource, BlockPos offset, int weight){
        Resource = createRegistryName(resource);
        Offset = offset;
        Weight = weight;
    }

    public DEPiece(String resource){this(resource, BlockPos.ZERO);}

    public DEPiece(String resource, int weight){this(resource, BlockPos.ZERO, weight);}

    public DEPiece(String resource, BlockPos offset) {this(resource, offset, 1);}
}