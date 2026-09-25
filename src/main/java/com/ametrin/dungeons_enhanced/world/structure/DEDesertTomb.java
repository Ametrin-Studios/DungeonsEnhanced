package com.ametrin.dungeons_enhanced.world.structure;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DEDesertTomb {
//    public static class Capability implements JigsawCapability {
//        public static final Capability INSTANCE = new Capability();
//        public static final MapCodec<Capability> CODEC = MapCodec.unit(INSTANCE);
//
//        @Override
//        public JigsawCapabilityType<?> getType() {
//            return DEJigsawTypes.DESERT_TOMB.get();
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
//            return Objects.requireNonNull(DEStructures.DESERT_TOMB.getPieceType().get());
//        }
//
//        @Override
//        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) { }
//    }

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
//        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "desert_tomb/", context);
//        registry.registerBuilder().pools(registry.poolBuilder().names("root").maintainWater(false)).register(DETemplatePools.DESERT_TOMB);
//
//        var basicPool = registry.poolBuilder().maintainWater(false);
//        registry.register("down", basicPool.clone().names("down"));
//        registry.register("trap", basicPool.clone().names("trap"));
//        registry.register("cross", basicPool.clone().names("t-cross"));
//        registry.register("main", basicPool.clone().names(Map.of("tunnel", 5, "t-cross", 4, "room", 4, "tomb", 3, "exit", 2)));
    }
}