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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class DEPillagerCamp {
    public static class Capability implements JigsawCapability {
        public static final Capability INSTANCE = new Capability();
        public static final MapCodec<Capability> CODEC = MapCodec.unit(INSTANCE);

        @Override
        public JigsawCapabilityType<?> getType() {
            return DEJigsawTypes.PILLAGER_CAMP.get();
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
            return Objects.requireNonNull(DEStructures.PILLAGER_CAMP.getPieceType().get());
        }

        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) { }
    }

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "pillager_camp/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("tent/general").maintainWater(false)).register(DETemplatePools.PILLAGER_CAMP);

        var basicPool = registry.poolBuilder().maintainWater(false);
        var SleepingTents = basicPool.clone().names("tent/sleep1", "tent/sleep2");
        var Kitchen = basicPool.clone().names("tent/kitchen");
        var Decoration = basicPool.clone().names("decoration/campfire", "decoration/cage1");
        var Pillars = basicPool.clone().names("decoration/bell", "decoration/pillar");
        var VanillaDecoration = basicPool.clone().namesR(mcPiece("logs"), mcPiece("targets"), mcPiece("tent1"), mcPiece("tent2"));

        registry.registerBuilder().pools(basicPool.clone().names("plate/var1", "plate/var2")).projection(StructureTemplatePool.Projection.TERRAIN_MATCHING).register("feature_plates");
        registry.register("features", JigsawPoolBuilder.collect(SleepingTents.weight(2), Kitchen.weight(2), VanillaDecoration.weight(2), Decoration.weight(3), Pillars.weight(1)));
    }

    private static ResourceLocation mcPiece(String key) {
        return ResourceLocation.withDefaultNamespace("pillager_outpost/feature_" + key);
    }
}