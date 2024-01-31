package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEJigsawTypes;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.registry.DETemplatePools;
import com.legacy.structure_gel.api.structure.jigsaw.*;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class DEMonsterMaze{
    public static final String ID = "monster_maze";

    public static class Capability implements JigsawCapability{
        public static final Capability INSTANCE = new Capability();
        public static final Codec<Capability> CODEC = Codec.unit(INSTANCE);

        @Override
        public JigsawCapabilityType<?> getType(){return DEJigsawTypes.MONSTER_MAZE.get();}
        @Override
        public IPieceFactory getPieceFactory() {return Piece::new;}
    }
    public static class Piece extends ExtendedJigsawStructurePiece {
        public Piece(IPieceFactory.Context context) {super(context);}
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.MONSTER_MAZE.getPieceType().get();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) {}
    }

    public static void pool(BootstapContext<StructureTemplatePool> context){
        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "monster_maze/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("root").maintainWater(false)).register(DETemplatePools.MONSTER_MAZE);

        var basicPool = registry.poolBuilder().maintainWater(false);
        var CrossTunnels = basicPool.clone().names("tunnels/cross1", "tunnels/cross2");
        var EdgeTunnels = basicPool.clone().names("tunnels/edge1", "tunnels/edge2");
        var RoomTunnels = basicPool.clone().names("tunnels/room1", "tunnels/room2", "tunnels/room3");
        var ShortTunnels = basicPool.clone().names("tunnels/small1", "tunnels/small2", "tunnels/small3");
        var LongTunnels = basicPool.clone().names("tunnels/big1", "tunnels/big2", "tunnels/big3", "tunnels/big4", "tunnels/big5");
        var StartStairs = basicPool.clone().names("stairs/big1", "stairs/big2");
        var Stairs = basicPool.clone().names("stairs/big1", "stairs/big2", "stairs/big3");
        var Rooms = basicPool.clone().names("big_room", "church", "prison", "room1", "storage", "brewery");
        var Boss = basicPool.clone().names("boss");

        registry.register("tunnels/cross", CrossTunnels);
        registry.register("tunnels/edge", EdgeTunnels);
        registry.register("tunnels/room", RoomTunnels);
        registry.register("tunnels/small", ShortTunnels);
        registry.register("tunnels/big", LongTunnels);
        registry.register("start_stairs", StartStairs);
        registry.register("stairs", Stairs);
        registry.register("rooms", Rooms);
        registry.register("boss", Boss);

        registry.register("tree", basicPool.clone().names("tree"));
        registry.register("tunnels", JigsawPoolBuilder.collect(EdgeTunnels, RoomTunnels, ShortTunnels, LongTunnels));
        registry.register("main", JigsawPoolBuilder.collect(EdgeTunnels.weight(4), RoomTunnels.weight(3), ShortTunnels.weight(2), LongTunnels.weight(3), CrossTunnels.weight(5), Rooms.weight(2)));
    }
}