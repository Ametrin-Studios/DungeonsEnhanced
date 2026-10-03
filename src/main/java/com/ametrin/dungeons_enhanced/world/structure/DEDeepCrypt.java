package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.registry.DEProcessorLists;
import com.ametrin.structures.structure.jigsaw.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.List;

public final class DEDeepCrypt {
    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var pools = new JigsawPools(context, DungeonsEnhanced.MOD_ID, "deep_crypt/");
        pools.defaultElementSettings(e -> e.liquidSettings(LiquidSettings.IGNORE_WATERLOGGING));

        pools.pool("root", b -> b
                .element("root")
        );

        pools.pool("main", b -> b
                .elements(List.of("tunnel", "cross"), e -> e
                        .weight(6)
                        .processors(DEProcessorLists.AIR_TO_COBWEB)
                )
                .elements(List.of("big_tunnel", "large_tomb", "prison", "tomb", "tombs", "root"), e -> e.weight(2))
                .element("treasure")
        );
    }
}