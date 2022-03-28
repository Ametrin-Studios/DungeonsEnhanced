package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.DEBaseStructure;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Random;

public class DEMonsterMaze extends GelConfigJigsawStructure{
    public DEMonsterMaze(){
        super(JigsawConfiguration.CODEC, DEConfig.COMMON.monster_maze, -17, true, true, (context) -> checkLocation(context, DETerrainAnalyzer.defaultCheckSettings));
        Pool.init();
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return false;}

    private static boolean checkLocation(PieceGeneratorSupplier.Context<JigsawConfiguration> context, DETerrainAnalyzer.TerrainCheckSettings checkSettings){
        if(context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG)){
            return DETerrainAnalyzer.isPositionSuitable(context.chunkPos(), context.chunkGenerator(), DEBaseStructure.GenerationType.onGround, checkSettings, context.heightAccessor());
        }

        return false;
    }

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(StructureManager structureManager, StructurePoolElement poolElement, BlockPos pos, int groundLevelDelta, Rotation rotation, BoundingBox bounds) {
            super(structureManager, poolElement, pos, groundLevelDelta, rotation, bounds);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.MonsterMaze.getPieceType();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, Random random, BoundingBox box) {}
    }

    public static class Pool{
        public static StructureTemplatePool Root;
        public static void init(){}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.Mod_ID, "monster_maze/");
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