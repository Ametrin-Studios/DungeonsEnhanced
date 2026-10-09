package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.structure.jigsaw.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;

public final class DEPillagerCamp {
    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var builder = new JigsawPools(context, DungeonsEnhanced.MOD_ID, "pillager_camp/");

        builder.pool("root", p -> p.element("tent/general"));
        builder.pool("feature_plates", p -> p.element("plate/var1").element("plate/var2").terrainMatching());

        builder.pool("features", p -> p
                .elements(List.of("tent/sleep1", "tent/sleep2"), e -> e.weight(2))
                .elements(List.of("tent/kitchen"), e -> e.weight(2))
                .elementsI(List.of(mcPiece("logs"), mcPiece("targets"), mcPiece("tent1"), mcPiece("tent2")), e -> e.weight(2))
                .elements(List.of("decoration/campfire", "decoration/cage1"), e -> e.weight(3))
                .elements(List.of("decoration/bell", "decoration/pillar"), e -> e.weight(1))
        );
    }

    private static Identifier mcPiece(String key) {
        return Identifier.withDefaultNamespace("pillager_outpost/feature_" + key);
    }
}