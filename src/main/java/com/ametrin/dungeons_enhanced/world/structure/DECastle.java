package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.api.structure.JigsawPools;
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
        var helper = new JigsawPools(context, DungeonsEnhanced.MOD_ID, "castle/");

        helper.pool("root", b -> b
                .element("top1")
                .element("top2")
        );

        helper.pool("bottom1", b -> b.element("bottom1"));
        helper.pool("bottom2", b -> b.element("bottom2"));
    }
}