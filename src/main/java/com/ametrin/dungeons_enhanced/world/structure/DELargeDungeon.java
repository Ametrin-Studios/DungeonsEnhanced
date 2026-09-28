package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.structure.jigsaw.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;

public final class DELargeDungeon {
    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var pools = new JigsawPools(context, DungeonsEnhanced.MOD_ID, "large_dungeon/");

        pools.pool("root", p -> p.element("root"));
        pools.pool("cross", p -> p.element("cross"));
        pools.pool("main", p -> p
                .element("tunnel", e -> e.weight(4))
                .element("stairs", e -> e.weight(2))
                .element("cross", e -> e.weight(2))
                .elements(List.of("room_small1", "room_small2", "room1", "room2", "room_big", "parkour", "storage"), e -> e.weight(1))
        );
    }
}