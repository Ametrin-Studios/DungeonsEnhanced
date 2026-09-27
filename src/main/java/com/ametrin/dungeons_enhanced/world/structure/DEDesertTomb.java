package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.api.structure.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DEDesertTomb {
    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var pools = new JigsawPools(context, DungeonsEnhanced.MOD_ID, "desert_tomb/");

        pools.pool("root", p -> p.element("root"));

        pools.pool("down", p -> p.element("down"));
        pools.pool("trap", p -> p.element("trap"));
        pools.pool("cross", p -> p.element("t-cross"));
        pools.pool("main", p -> p
                .element("tunnel", e -> e.weight(5))
                .element("t-cross", e -> e.weight(4))
                .element("room", e -> e.weight(4))
                .element("tomb", e -> e.weight(3))
                .element("exit", e -> e.weight(2))
        );
    }
}