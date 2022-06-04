package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
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

public class DEMonsterMaze extends GelConfigJigsawStructure {
    public DEMonsterMaze(){
        super(VillageConfig.CODEC, DEConfig.COMMON.MonsterMaze, -17, true, true);
        Pool.init();
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return false;}

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biome, ChunkPos chunkPos, VillageConfig config) {
        boolean canGenerate = super.isFeatureChunk(chunkGen, biomeProvider, seed, sharedSeedRand, chunkPosX, chunkPosZ, biome, chunkPos, config);
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
        public IStructurePieceType getType() {return DEStructures.MonsterMaze.getPieceType();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random random, MutableBoundingBox box){}
    }

    public static class Pool{
        public static JigsawPattern Root;
        public static void init(){}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID, "monster_maze/");
            Root = registry.register("root", registry.builder().names("root").maintainWater(false).build());

            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            JigsawPoolBuilder CrossTunnels = poolBuilder.clone().names("tunnels/cross1", "tunnels/cross2");
            JigsawPoolBuilder EdgeTunnels = poolBuilder.clone().names("tunnels/edge1", "tunnels/edge2");
            JigsawPoolBuilder RoomTunnels = poolBuilder.clone().names("tunnels/room1", "tunnels/room2", "tunnels/room3");
            JigsawPoolBuilder ShortTunnels = poolBuilder.clone().names("tunnels/small1", "tunnels/small2", "tunnels/small3");
            JigsawPoolBuilder LongTunnels = poolBuilder.clone().names("tunnels/big1", "tunnels/big2", "tunnels/big3", "tunnels/big4", "tunnels/big5");
            JigsawPoolBuilder StartStairs = poolBuilder.clone().names("stairs/big1", "stairs/big2");
            JigsawPoolBuilder Stairs = poolBuilder.clone().names("stairs/big1", "stairs/big2", "stairs/big3");
            JigsawPoolBuilder Rooms = poolBuilder.clone().names("big_room", "church", "prison", "room1", "storage", "brewery");
            JigsawPoolBuilder Boss = poolBuilder.clone().names("boss");

            registry.register("tunnels/cross", CrossTunnels.build());
            registry.register("tunnels/edge", EdgeTunnels.build());
            registry.register("tunnels/room", RoomTunnels.build());
            registry.register("tunnels/small", ShortTunnels.build());
            registry.register("tunnels/big", LongTunnels.build());
            registry.register("start_stairs", StartStairs.build());
            registry.register("stairs", Stairs.build());
            registry.register("rooms", Rooms.build());
            registry.register("boss", Boss.build());

            registry.register("tree", poolBuilder.clone().names("tree").build());
            registry.register("tunnels", JigsawPoolBuilder.collect(EdgeTunnels, RoomTunnels, ShortTunnels, LongTunnels));
            registry.register("main", JigsawPoolBuilder.collect(EdgeTunnels.weight(4), RoomTunnels.weight(3), ShortTunnels.weight(2), LongTunnels.weight(3), CrossTunnels.weight(5), Rooms.weight(2)));
        }
    }
}