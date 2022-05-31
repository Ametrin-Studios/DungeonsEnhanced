package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.DEBaseStructure;
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

public class DELargeDungeon extends GelConfigJigsawStructure {
    public DELargeDungeon(){
        super(VillageConfig.CODEC, DEConfig.COMMON.large_dungeon, -16, true, true);
        Pool.init();
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return true;}

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, VillageConfig config) {
        boolean canGenerate = super.isFeatureChunk(chunkGen, biomeProvider, seed, sharedSeedRand, chunkPosX, chunkPosZ, biomeIn, chunkPos, config);
        if(!canGenerate) {return false;}

        return DETerrainAnalyzer.isPositionSuitable(chunkPos, chunkGen, DEBaseStructure.GenerationType.onGround);
    }

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos pos, int groundLevelDelta, Rotation rotation, MutableBoundingBox bounds) {
            super(templateManager, jigsawPiece, pos, groundLevelDelta, rotation, bounds);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(templateManager, nbt);
        }

        @Override
        public void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random random, MutableBoundingBox box) {}

        @Override
        public IStructurePieceType getType() {return DEStructures.LargeDungeon.getPieceType();}
    }

    public static class Pool{
        public static JigsawPattern Root;
        public static void init(){}
        static{
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID, "large_dungeon/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false).processors(DEUtil.Processors.AirToCobweb);
            Root = registry.register("root", poolBuilder.clone().names("root").build());

            JigsawPoolBuilder Cross = poolBuilder.clone().names("cross");
            JigsawPoolBuilder Rooms = poolBuilder.clone().names("room_small1", "room_small2", "room1", "room2", "room_big", "parkour", "storage");
            JigsawPoolBuilder Tunnels = poolBuilder.clone().names("tunnel");
            JigsawPoolBuilder Stairs = poolBuilder.clone().names("stairs");

            registry.register("cross", Cross.build());
            registry.register("main", JigsawPoolBuilder.collect(Tunnels.weight(4), Stairs.weight(2), Cross.weight(2), Rooms.weight(1)));
        }
    }
}