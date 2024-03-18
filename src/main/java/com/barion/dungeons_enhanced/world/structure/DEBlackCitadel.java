package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.*;
import com.google.common.collect.ImmutableMap;
import com.legacy.structure_gel.api.structure.jigsaw.*;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class DEBlackCitadel {
    public static class Capability implements JigsawCapability {
        public static final Capability INSTANCE = new Capability();
        public static final Codec<Capability> CODEC = Codec.unit(INSTANCE);
        @Override
        public JigsawCapabilityType<?> getType() {return DEJigsawTypes.BLACK_CITADEL.get();}
        @Override
        public IPieceFactory getPieceFactory() {return DECastle.Piece::new;}
    }

    public static class Piece extends ExtendedJigsawStructurePiece {
        public Piece(IPieceFactory.Context context) {super(context);}
        public Piece(StructurePieceSerializationContext context, CompoundTag nbt) {super(context, nbt);}
        @Override
        public StructurePieceType getType() {return DEStructures.BLACK_CITADEL.getPieceType().get();}
        @Override
        public void handleDataMarker(String key, BlockPos blockPos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) {}
    }

    public static void pool(BootstapContext<StructureTemplatePool> context){
        var registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, DEStructureIDs.BLACK_CITADEL + "/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("main").processors(DEProcessorLists.BLACK_CITADEL_ROOT.getKey())).register(DETemplatePools.BLACK_CITADEL);

        var basicPool = registry.poolBuilder().processors(DEProcessorLists.BLACK_CITADEL_DEFAULT.getKey());
        var bridges = basicPool.clone().names(ImmutableMap.<String, Integer>builder().put("bridge/normal", 2).put("bridge/bones", 1).put("bridge/broken", 1).build());
        var pillars = basicPool.clone().names(ImmutableMap.<String, Integer>builder().put("bridge_pillar/normal", 2).put("bridge_pillar/end", 1).put("bridge_pillar/end_cage", 1).put("bridge_tower/middle_1", 2).put("bridge_tower/middle_2", 2).build());
        var tower_top = basicPool.clone().names("bridge_tower/top_1");
        var mainExtensions = basicPool.clone().names("main_bridge_extension");

        registry.register("tower_top", tower_top);
        registry.register("bridge", bridges);
        registry.register("pillar", pillars);
        registry.register("main_extension", mainExtensions);
    }
}
