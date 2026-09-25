package com.ametrin.dungeons_enhanced.world.structure;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DELargeDungeon {
//    public static class Capability implements JigsawCapability {
//        public static final Capability INSTANCE = new Capability();
//        public static final MapCodec<Capability> CODEC = MapCodec.unit(INSTANCE);
//
//        @Override
//        public JigsawCapabilityType<?> getType() {
//            return DEJigsawTypes.LARGE_DUNGEON.get();
//        }
//
//        @Override
//        public IPieceFactory getPieceFactory() {
//            return Piece::new;
//        }
//    }
//
//    public static class Piece extends ExtendedJigsawStructurePiece {
//        public Piece(IPieceFactory.Context context) {
//            super(context);
//        }
//
//        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
//            super(serializationContext, nbt);
//        }
//
//        @Override
//        public @NotNull StructurePieceType getType() {
//            return Objects.requireNonNull(DEStructures.LARGE_DUNGEON.getPieceType().get());
//        }
//
//        @Override
//        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) { }
//    }

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
//        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "large_dungeon/", context);
//        registry.registerBuilder().pools(registry.poolBuilder().names("root").maintainWater(false)).register(DETemplatePools.LARGE_DUNGEON);
//
//        var basePool = registry.poolBuilder().maintainWater(false);
//        var Cross = basePool.clone().names("cross");
//        var Rooms = basePool.clone().names("room_small1", "room_small2", "room1", "room2", "room_big", "parkour", "storage");
//        var Tunnels = basePool.clone().names("tunnel");
//        var Stairs = basePool.clone().names("stairs");
//
//        registry.register("cross", Cross.build());
//        registry.register("main", JigsawPoolBuilder.collect(Tunnels.weight(4), Stairs.weight(2), Cross.weight(2), Rooms.weight(1)));
    }
}