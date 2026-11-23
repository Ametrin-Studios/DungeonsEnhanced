package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.structure.TerrainBox;
import com.ametrin.structures.structure.jigsaw.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DEDesertTomb {
    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var pools = new JigsawPools(context, DungeonsEnhanced.MOD_ID, "desert_tomb/");

        pools.defaultElementSettings(e -> e.terrainBox(TerrainBox.none()));

        pools.pool("root", p -> p
                .element("root_default", e -> e.terrainBox(TerrainBox.template()))
                .element("root_broken", e -> e.terrainBox(TerrainBox.template()))
        );

        pools.pool("down", p -> p.element("down"));
        pools.pool("trap", p -> p.element("trap"));
        pools.pool("cross", p -> p
//                .element("t-cross")
                .element("x-cross")
        );
        pools.pool("main", p -> p
                .element("tunnel", e -> e.weight(3))
                .element("t-cross", e -> e.weight(3))
                .element("x-cross", e -> e.weight(1))
                .element("room", e -> e.weight(3))
                .element("room_statue", e -> e.weight(2))
                .element("room_collapsed", e -> e.weight(1))
                .element("arrow_trap", e -> e.weight(2))
                .element("room_well", e -> e.weight(2))
                .element("dead_end", e -> e.weight(2))
                .element("tomb", e -> e.weight(2))
                .element("exit", e -> e.weight(1))
                .element("pot_corner", e -> e.weight(1))
                .fallback("fallback")
        );

        pools.pool("fallback", p -> p.element("fallback"));
    }
}