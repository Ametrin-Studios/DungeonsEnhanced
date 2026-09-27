package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.api.structure.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DEDruidCircle {

    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var pools = new JigsawPools(context, DungeonsEnhanced.MOD_ID, "druid_circle/");

        pools.pool("root", p -> p.element("top_big").element("small"));
        pools.pool("bottom_big", p -> p.element("bottom_big"));
    }
}