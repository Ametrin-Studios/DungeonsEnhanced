package com.ametrin.dungeons_enhanced.world.structure;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DEDruidCircle {

//    public static class Capability implements JigsawCapability {
//        public static final Capability INSTANCE = new Capability();
//        public static final MapCodec<Capability> CODEC = MapCodec.unit(INSTANCE);
//
//        @Override
//        public JigsawCapabilityType<?> getType() {
//            return DEJigsawTypes.DRUID_CIRCLE.get();
//        }
//
//        @Override
//        public IPieceFactory getPieceFactory() {
//            return Piece::new;
//        }
//
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
//            return Objects.requireNonNull(DEStructures.DRUID_CIRCLE.getPieceType().get());
//        }
//
//        @Override
//        public void handleDataMarker(String key, BlockPos blockPos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) { }
//    }

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
//        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "druid_circle/", context);
//        registry.registerBuilder().pools(registry.poolBuilder().names("top_big", "small").maintainWater(false)).register(DETemplatePools.DRUID_CIRCLE);
//
//        registry.register("bottom_big", registry.poolBuilder().maintainWater(false).names("bottom_big").build());
    }
}