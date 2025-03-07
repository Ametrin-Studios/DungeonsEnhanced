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

import java.util.Map;
import java.util.Objects;

public final class DEDesertTomb {
    public static class Capability implements JigsawCapability {
        public static final Capability INSTANCE = new Capability();
        public static final MapCodec<Capability> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public JigsawCapabilityType<?> getType() {
            return DEJigsawTypes.DESERT_TOMB.get();
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
            return Objects.requireNonNull(DEStructures.DESERT_TOMB.getPieceType().get());
        }

        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) { }
    }

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "desert_tomb/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("root").maintainWater(false)).register(DETemplatePools.DESERT_TOMB);

        var basicPool = registry.poolBuilder().maintainWater(false);
        registry.register("down", basicPool.clone().names("down"));
        registry.register("trap", basicPool.clone().names("trap"));
        registry.register("cross", basicPool.clone().names("t-cross"));
        registry.register("main", basicPool.clone().names(Map.of("tunnel", 5, "t-cross", 4, "room", 4, "tomb", 3, "exit", 2)));
    }
}