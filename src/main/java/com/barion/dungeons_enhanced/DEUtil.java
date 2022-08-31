package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEUnderwaterProcessor;
import com.legacy.structure_gel.api.registry.RegistryHelper;
import com.legacy.structure_gel.api.structure.processor.RandomBlockSwapProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;

public class DEUtil{
    public static ResourceLocation location(String key){ return new ResourceLocation(DungeonsEnhanced.ModID, key);}

    public static class Processors {
        public static final Holder<StructureProcessorList> AirToCobweb = register("air_to_cobweb", new RandomBlockSwapProcessor(Blocks.AIR, 0.02f, Blocks.COBWEB));
        public static final StructureProcessor BrainCoral = new RandomBlockSwapProcessor(Blocks.DEAD_BRAIN_CORAL_BLOCK, 1, Blocks.BRAIN_CORAL_BLOCK);
        public static final StructureProcessor FireCoral = new RandomBlockSwapProcessor(Blocks.DEAD_FIRE_CORAL_BLOCK, 1, Blocks.FIRE_CORAL_BLOCK);
        public static final StructureProcessor BubbleCoral = new RandomBlockSwapProcessor(Blocks.DEAD_BUBBLE_CORAL_BLOCK, 1, Blocks.BUBBLE_CORAL_BLOCK);
        public static final StructureProcessor HornCoral = new RandomBlockSwapProcessor(Blocks.DEAD_HORN_CORAL_BLOCK, 1, Blocks.HORN_CORAL_BLOCK);
        public static final StructureProcessor TubeCoral = new RandomBlockSwapProcessor(Blocks.DEAD_TUBE_CORAL_BLOCK, 1, Blocks.TUBE_CORAL_BLOCK);

        private static Holder<StructureProcessorList> register(String key, StructureProcessor processor){
            return RegistryHelper.registerProcessor(location(key), processor);
        }
        private static Holder<StructureProcessorList> register(String key, StructureProcessorList processorList){
            return RegistryHelper.registerProcessor(location(key), processorList);
        }

        public static class Types {
            public static final StructureProcessorType<DEUnderwaterProcessor> Underwater = () -> DEUnderwaterProcessor.CODEC;

            public static void register() {
                register("underwater", Underwater);
            }

            private static <P extends StructureProcessor> void register(String key, StructureProcessorType<P> processorType) {
                Registry.register(Registry.STRUCTURE_PROCESSOR, DEUtil.location(key), processorType);
            }
        }
    }

    public static int getRandomPiece(DEStructurePiece[] variants, int maxWeight, RandomSource rand){
        int piece = 0;
        if(variants.length > 1) {
            int i = rand.nextInt(maxWeight+1);
            for (int j = 0; j < variants.length; j++) {
                if (variants[j].Weight >= i) {
                    piece = j;
                    break;
                } else {
                    i -= variants[j].Weight;
                }
            }
        }
        return piece;
    }

    public static int getMaxWeight(DEStructurePiece[] variants){
        int i = 0;
        for (DEStructurePiece piece : variants){
            i += piece.Weight;
        }
        return i;
    }

    public static BlockPos ChunkPosToBlockPos(ChunkPos chunkPos){
        return ChunkPosToBlockPos(chunkPos, 63);
    }
    public static BlockPos ChunkPosToBlockPos(ChunkPos chunkPos, int y){
        return new BlockPos(chunkPos.getMiddleBlockX(), y, chunkPos.getMiddleBlockZ());
    }

    public static BlockPos ChunkPosToBlockPosFromHeightMap(ChunkPos chunkPos, ChunkGenerator chunkGenerator, Heightmap.Types heightmapType, LevelHeightAccessor heightAccessor, RandomState randomState){
        BlockPos pos = DEUtil.ChunkPosToBlockPos(chunkPos);
        return pos.atY(chunkGenerator.getBaseHeight(pos.getX(), pos.getY(), heightmapType, heightAccessor, randomState));
    }

    public static DEStructurePiece.Builder pieceBuilder() {return new DEStructurePiece.Builder();}
}