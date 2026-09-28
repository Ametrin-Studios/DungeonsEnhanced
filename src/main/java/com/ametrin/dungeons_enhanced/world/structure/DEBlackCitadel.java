package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.registry.DEProcessorLists;
import com.ametrin.dungeons_enhanced.registry.DEStructureIDs;
import com.ametrin.structures.structure.jigsaw.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DEBlackCitadel {
//        @Override
//        @ParametersAreNonnullByDefault
//        public void place(WorldGenLevel level, StructureManager structureManager, ChunkGenerator generator, RandomSource random, BoundingBox bounds, BlockPos pos, boolean keepJigsaws) {
//            super.place(level, structureManager, generator, random, bounds, pos, keepJigsaws);
//            if (getLocation().getPath().contains("pillar") || getLocation().getPath().contains("tower")) {
//                this.extendDown(level, Blocks.POLISHED_BLACKSTONE_BRICKS.defaultBlockState(), bounds, rotation, random);
//            }
//        }
//
//        @Override
//        public void handleDataMarker(String key, BlockPos blockPos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) { }
//    }

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var builder = new JigsawPools(context, DungeonsEnhanced.MOD_ID, DEStructureIDs.BLACK_CITADEL + "/");
        builder.defaultElementSettings(e -> e.processors(DEProcessorLists.BLACK_CITADEL));

        builder.pool("root", p -> p.element("main"));
        builder.pool("main_extension", p -> p.element("main_bridge_extension"));

        builder.pool("tower", p -> p
                .element("tower/broken")
                .element("tower/normal")
        );

        builder.pool("bridge", p -> p
                .element("bridge/normal", e -> e.weight(2))
                .element("bridge/bones", e -> e.weight(1))
                .element("bridge/broken", e -> e.weight(1))
                .element("bridge/short", e -> e.weight(2))
                .element("bridge/shorter", e -> e.weight(1))
        );

        builder.pool("short_bridge", p -> p
                .element("bridge/short")
        );

        //
//        var shortBridge = basicPool.clone().names("bridge/short");
//        var pillar = basicPool.clone().names(ImmutableMap.<String, Integer>builder().put("bridge_pillar/normal", 3).put("bridge_pillar/bones", 3).put("bridge_pillar/end", 2).put("bridge_pillar/end_cage", 2).put("bridge_tower/broken", 3).build());
//        var thickPillar = basicPool.clone().names("bridge_tower/normal");
//        var mainExtensions = basicPool.clone().names("main_bridge_extension");
//
//        registry.register("tower", tower);
//        registry.register("bridge", bridge);
//        registry.register("short_bridge", shortBridge);
//        registry.register("pillar", pillar);
//        registry.register("thick_pillar", thickPillar);
//        registry.register("main_extension", mainExtensions);
    }
}
