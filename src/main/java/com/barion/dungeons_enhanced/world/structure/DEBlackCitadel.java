package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEJigsawTypes;
import com.barion.dungeons_enhanced.registry.DEProcessorLists;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.registry.DETemplatePools;
import com.google.common.collect.ImmutableMap;
import com.legacy.structure_gel.api.structure.jigsaw.ExtendedJigsawStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.IPieceFactory;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawCapability;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import javax.annotation.ParametersAreNonnullByDefault;

public final class DEBlackCitadel {
    public static final String ID = "black_citadel";

    public static class Capability implements JigsawCapability.IJigsawCapability {
        public static final Capability INSTANCE = new Capability();
        public static final Codec<Capability> CODEC = Codec.unit(INSTANCE);

        @Override
        public JigsawCapability.JigsawType<?> getType() {
            return DEJigsawTypes.BLACK_CITADEL;
        }

        @Override
        public IPieceFactory getPieceFactory() {
            return DEBlackCitadel.Piece::new;
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
        public StructurePieceType getType() {
            return DEStructures.BLACK_CITADEL.getPieceType().get();
        }

        @Override
        @ParametersAreNonnullByDefault
        public void place(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox bounds, BlockPos pos, boolean keepJigsaws) {
            super.place(level, structureManager, generator, random, bounds, pos, keepJigsaws);
            if (getLocation().getPath().contains("pillar") || getLocation().getPath().contains("tower")) {
                this.extendDown(level, Blocks.POLISHED_BLACKSTONE_BRICKS.defaultBlockState(), bounds, rotation, random);
            }
        }

        @Override
        public void handleDataMarker(String key, BlockPos blockPos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) {
        }
    }

    public static void pool(BootstapContext<StructureTemplatePool> context) {
        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "black_citadel/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("main").processors(DEProcessorLists.BLACK_CITADEL.getKey())).register(DETemplatePools.BLACK_CITADEL);

        var basicPool = registry.poolBuilder().processors(DEProcessorLists.BLACK_CITADEL.getKey());
        var tower = basicPool.clone().names("tower/broken", "tower/normal");
        var bridge = basicPool.clone().names(ImmutableMap.<String, Integer>builder().put("bridge/normal", 2).put("bridge/bones", 1).put("bridge/broken", 1).put("bridge/short", 2).put("bridge/shorter", 1).build());
        var shortBridge = basicPool.clone().names("bridge/short");
        var pillar = basicPool.clone().names(ImmutableMap.<String, Integer>builder().put("bridge_pillar/normal", 3).put("bridge_pillar/bones", 3).put("bridge_pillar/end", 2).put("bridge_pillar/end_cage", 2).put("bridge_tower/broken", 3).build());
        var thickPillar = basicPool.clone().names("bridge_tower/normal");
        var mainExtensions = basicPool.clone().names("main_bridge_extension");

        registry.register("tower", tower);
        registry.register("bridge", bridge);
        registry.register("short_bridge", shortBridge);
        registry.register("pillar", pillar);
        registry.register("thick_pillar", thickPillar);
        registry.register("main_extension", mainExtensions);
    }
}
