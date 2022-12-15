package com.barion.dungeons_enhanced.world.structures.prefabs.utils;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;

public class DEStructurePieces {
    private final SimpleWeightedRandomList<Piece> pieces;

    public DEStructurePieces(final Builder builder) {this(builder.buildList());}
    public DEStructurePieces(final SimpleWeightedRandomList<Piece> pieces){
        if(pieces.isEmpty()) {
            throw new IllegalArgumentException("The given builder is empty");
        }
        this.pieces = pieces;
    }

    public Piece getRandomPiece(RandomSource random) {return pieces.getRandomValue(random).get();}

    public static class Piece{
        public final ResourceLocation Resource;
        public final BlockPos Offset;
        public Piece(final ResourceLocation resource, final BlockPos offset){
            Resource = resource;
            Offset = offset;
        }
    }

    public static class Builder{
        private final SimpleWeightedRandomList.Builder<Piece> pieces;
        private BlockPos offset;
        private int weight;

        public Builder(){
            this.offset = BlockPos.ZERO;
            this.weight = 1;
            pieces = SimpleWeightedRandomList.builder();
        }

        public Builder weight(int weight){
            this.weight = weight;
            return this;
        }
        public Builder offset(BlockPos offset){
            this.offset = offset;
            return this;
        }

        public Builder offset(int x, int y, int z) {return offset(new BlockPos(x, y, z));}

        public Builder add(String resource){
            pieces.add(new Piece(DEUtil.location(resource), offset), weight);
            return this;
        }

        private SimpleWeightedRandomList<Piece> buildList() {
            return pieces.build();
        }

        public DEStructurePieces build() {return new DEStructurePieces(this);}
    }
}