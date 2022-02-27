package com.barion.dungeons_enhanced.world.structures.prefabs.utils;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEStructurePiece {
    public final ResourceLocation Resource;
    public BlockPos Offset;
    public int Weight;
    public DEStructurePiece(String resource, BlockPos offset, int weight){
        Resource = createRegistryName(resource);
        Offset = offset;
        Weight = weight;
    }

    public DEStructurePiece(String resource){this(resource, BlockPos.ZERO);}
    public DEStructurePiece(String resource, int weight){this(resource, BlockPos.ZERO, weight);}
    public DEStructurePiece(String resource, BlockPos offset) {this(resource, offset, 1);}
}