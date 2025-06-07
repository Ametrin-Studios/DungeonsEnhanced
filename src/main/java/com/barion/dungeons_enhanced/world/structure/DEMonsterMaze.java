package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEJigsawTypes;
import com.barion.dungeons_enhanced.registry.DEProcessorLists;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.registry.DETemplatePools;
import com.legacy.structure_gel.api.structure.ExtendedJigsawStructure;
import com.legacy.structure_gel.api.structure.jigsaw.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class DEMonsterMaze {
    public static class Capability implements JigsawCapability {
        public static final Capability INSTANCE = new Capability();
        public static final MapCodec<Capability> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public JigsawCapabilityType<?> getType() {
            return DEJigsawTypes.MONSTER_MAZE.get();
        }

        @Override
        public boolean canPlace(Structure.GenerationContext generationContext, BlockPos placementPos, ExtendedJigsawStructure.PlaceContext placeContext) {
            return placementPos.getY() < 90;
        }

        @Override
        public IPieceFactory getPieceFactory() {
            return Piece::new;
        }
    }

    public static class Piece extends ExtendedJigsawStructurePiece {
        public Piece(IPieceFactory.Context context) {
            super(context);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(serializationContext, nbt);
        }

        @Override
        public @NotNull StructurePieceType getType() {
            return Objects.requireNonNull(DEStructures.MONSTER_MAZE_DARK.getPieceType().get());
        }

        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) { }
    }

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "monster_maze/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("dark_root").maintainWater(false)).register(DETemplatePools.MONSTER_MAZE_DARK);
        registry.registerBuilder().pools(registry.poolBuilder().names("pale_root").maintainWater(false)).register(DETemplatePools.MONSTER_MAZE_PALE);

        var basicPool = registry.poolBuilder().maintainWater(false).processors(DEProcessorLists.MONSTER_MAZE.getKey());
        var crossTunnels = basicPool.clone().names("tunnels/cross1", "tunnels/cross2");
        var edgeTunnels = basicPool.clone().names("tunnels/edge1", "tunnels/edge2");
        var roomTunnels = basicPool.clone().names("tunnels/room1", "tunnels/room2", "tunnels/room3");
        var shortTunnels = basicPool.clone().names("tunnels/small1", "tunnels/small2", "tunnels/small3");
        var longTunnels = basicPool.clone().names("tunnels/big1", "tunnels/big2", "tunnels/big3", "tunnels/big4", "tunnels/big5");
        var startStairs = basicPool.clone().names("stairs/big1", "stairs/big2");
        var stairs = basicPool.clone().names("stairs/big1", "stairs/big2", "stairs/big3");
        var rooms = basicPool.clone().names("big_room", "church", "prison", "room1", "storage", "brewery");
        var boss = basicPool.clone().names("boss");

        registry.register("tunnels/cross", crossTunnels);
        registry.register("tunnels/edge", edgeTunnels);
        registry.register("tunnels/room", roomTunnels);
        registry.register("tunnels/small", shortTunnels);
        registry.register("tunnels/big", longTunnels);
        registry.register("start_stairs", startStairs);
        registry.register("stairs", stairs);
        registry.register("rooms", rooms);
        registry.register("boss", boss);

        registry.register("dark_tree", basicPool.clone().names("dark_tree"));
        registry.register("pale_tree", basicPool.clone().names("pale_tree"));
        registry.register("tunnels", JigsawPoolBuilder.collect(edgeTunnels, roomTunnels, shortTunnels, longTunnels));
        registry.register("main", JigsawPoolBuilder.collect(edgeTunnels.weight(4), roomTunnels.weight(3), shortTunnels.weight(2), longTunnels.weight(3), crossTunnels.weight(5), rooms.weight(2)));
    }
}