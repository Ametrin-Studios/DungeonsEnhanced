package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.structure.jigsaw.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public final class DECastle {
    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var helper = new JigsawPools(context, DungeonsEnhanced.MOD_ID, "castle/");

        helper.pool("root", b -> b
                .element("top1")
                .element("top2")
        );

        helper.pool("bottom1", b -> b.element("bottom1"));
        helper.pool("bottom2", b -> b.element("bottom2"));
    }
}