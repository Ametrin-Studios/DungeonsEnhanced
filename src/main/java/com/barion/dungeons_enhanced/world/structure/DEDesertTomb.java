package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEProcessors;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.google.common.collect.ImmutableMap;
import com.legacy.structure_gel.worldgen.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.worldgen.jigsaw.GelConfigJigsawStructure;
import com.legacy.structure_gel.worldgen.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.worldgen.jigsaw.JigsawRegistryHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class DEDesertTomb extends GelConfigJigsawStructure {
    public DEDesertTomb(){
        super(VillageConfig.CODEC, DEConfig.COMMON.DesertTomb, 1, true, true);
        Pool.init();
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return true;}

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, VillageConfig config) {
        boolean canGenerate = super.isFeatureChunk(chunkGen, biomeProvider, seed, sharedSeedRand, chunkPosX, chunkPosZ, biomeIn, chunkPos, config);
        if(!canGenerate) {return false;}

        return DETerrainAnalyzer.isFlatEnough(chunkPos, chunkGen);
    }

    public static class Piece extends AbstractGelStructurePiece {

        public Piece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos pos, int groundLevelDelta, Rotation rotation, MutableBoundingBox bounds) {
            super(templateManager, jigsawPiece, pos, groundLevelDelta, rotation, bounds);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(templateManager, nbt);
        }

        @Override
        public IStructurePieceType getType() {return DEStructures.DesertTomb.getPieceType();}

        @Override
        public void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random random, MutableBoundingBox box) {}
    }

    public static class Pool{
        public static JigsawPattern Root;
        public static void init(){}
        static{
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "desert_tomb/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false).processors(DEProcessors.AIR_TO_COBWEB);
            Root = registry.register("root", poolBuilder.clone().names("root").build());

            registry.register("down", poolBuilder.clone().names("down").build());
            registry.register("trap", poolBuilder.clone().names("trap").build());
            registry.register("cross", poolBuilder.clone().names("t-cross").build());
            registry.register("main", poolBuilder.clone().names(ImmutableMap.of("tunnel", 5, "t-cross", 4, "room", 4, "tomb", 3, "exit", 2)).build());
        }
    }
}