package com.barion.dungeons_enhanced.structures.prefabs;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

import static com.barion.dungeons_enhanced.DEUtil.createResourceKey;

public class DEPiece {
    public final ResourceLocation resource;
    public BlockPos offset;
    public final int weight;

    public DEPiece(String resource, BlockPos offset, int weight){
        this.resource = createResourceKey(resource);
        this.offset = offset;
        this.weight = weight;
    }

    public DEPiece(String resource){this(resource, BlockPos.ZERO);}
    public DEPiece(String resource, BlockPos offset) {this(resource, offset, 1);}
    public DEPiece(String resource, int weight) {this(resource, BlockPos.ZERO, weight);}
}