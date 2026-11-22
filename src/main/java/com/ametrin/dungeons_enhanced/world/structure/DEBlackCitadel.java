package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.registry.DEStructureIDs;
import com.ametrin.structures.structure.jigsaw.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DEBlackCitadel {
    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var builder = new JigsawPools(context, DungeonsEnhanced.MOD_ID, DEStructureIDs.BLACK_CITADEL + "/");

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
                .fallback("fallback")
        );

        builder.pool("short_bridge", p -> p
                .element("bridge/short")
        );

        builder.pool("pillar", p -> p
                .element("bridge_pillar/normal", e -> e.weight(3).foundation())
                .element("bridge_pillar/bones", e -> e.weight(3).foundation())
                .element("bridge_pillar/end", e -> e.weight(2).foundation())
                .element("bridge_pillar/end_cage", e -> e.weight(2).foundation())
                .element("bridge_tower/broken", e -> e.weight(3).foundation())
        );

        builder.pool("thick_pillar", p -> p
                .element("bridge_tower/normal", e -> e.foundation())
        );

        builder.pool("fallback", p -> p
                .element("bridge/broken")
        );
    }
}
