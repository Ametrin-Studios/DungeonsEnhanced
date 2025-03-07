package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEJigsawTypes;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.registry.DETemplatePools;
import com.legacy.structure_gel.api.structure.jigsaw.*;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class DECastle {
    public static class Capability implements JigsawCapability {
        public static final Capability INSTANCE = new Capability();
        public static final MapCodec<Capability> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public JigsawCapabilityType<?> getType() {
            return DEJigsawTypes.CASTLE.get();
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

        public Piece(StructurePieceSerializationContext context, CompoundTag nbt) {
            super(context, nbt);
        }

        @Override
        public @NotNull StructurePieceType getType() {
            return Objects.requireNonNull(DEStructures.CASTLE.getPieceType().get());
        }

        @Override
        public void handleDataMarker(String key, BlockPos blockPos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) { }
    }

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "castle/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("top1", "top2").maintainWater(false)).register(DETemplatePools.CASTLE);

        var basicPool = registry.poolBuilder().maintainWater(false);
        registry.register("bottom1", basicPool.clone().names("bottom1"));
        registry.register("bottom2", basicPool.clone().names("bottom2"));
    }
}