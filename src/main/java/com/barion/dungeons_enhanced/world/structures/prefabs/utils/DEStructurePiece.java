package com.barion.dungeons_enhanced.world.structures.prefabs.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEStructurePiece {
    public final ResourceLocation Resource;
    public BlockPos Offset;
    public int Weight;
    public DEStructurePiece(ResourceLocation resource, BlockPos offset, int weight){
        Resource = resource;
        Offset = offset;
        Weight = weight;
    }

    public static class Builder{
        private final List<DEStructurePiece> pieces;
        private BlockPos offset;
        private int weight;

        public Builder(){
            this.offset = BlockPos.ZERO;
            this.weight = 1;
            pieces = new ArrayList<>();
        }

        public Builder weight(int weight){
            this.weight = weight;
            return this;
        }
        public Builder offset(BlockPos offset){
            this.offset = offset;
            return this;
        }

        public Builder offset(int x, int y, int z){
            this.offset = new BlockPos(x, y, z);
            return this;
        }

        public Builder add(String rescource){
            return add(createRegistryName(rescource));
        }
        public Builder add(ResourceLocation rescource){
            pieces.add(new DEStructurePiece(rescource, offset, weight));
            return this;
        }

        public DEStructurePiece[] build(){
            return pieces.toArray(new DEStructurePiece[0]);
        }
    }
}