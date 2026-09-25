package com.ametrin.dungeons_enhanced.world.structure;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DECastle {
//    public static class Capability implements JigsawCapability {
//        public static final Capability INSTANCE = new Capability();
//        public static final MapCodec<Capability> CODEC = MapCodec.unit(INSTANCE);
//
//        @Override
//        public JigsawCapabilityType<?> getType() {
//            return DEJigsawTypes.CASTLE.get();
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
//        public Piece(StructurePieceSerializationContext context, CompoundTag nbt) {
//            super(context, nbt);
//        }
//
//        @Override
//        public @NotNull StructurePieceType getType() {
//            return Objects.requireNonNull(DEStructures.CASTLE.getPieceType().get());
//        }
//
//        @Override
//        public void handleDataMarker(String key, BlockPos blockPos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) { }
//    }

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
//        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "castle/", context);
//        registry.register("root").add(JigsawRegistryHelper.PoolBuilder.of())
//        registry.registerBuilder().pools(registry.poolBuilder().names("top1", "top2").maintainWater(false)).register(DETemplatePools.CASTLE);
//
//        var basicPool = registry.poolBuilder().maintainWater(false);
//        registry.register("bottom1", basicPool.clone().names("bottom1"));
//        registry.register("bottom2", basicPool.clone().names("bottom2"));
    }
}