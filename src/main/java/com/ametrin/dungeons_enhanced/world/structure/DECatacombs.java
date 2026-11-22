package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.registry.DEStructureIDs;
import com.ametrin.structures.structure.filter.PlacementFilters;
import com.ametrin.structures.structure.jigsaw.JigsawPools;
import com.ametrin.structures.structure.jigsaw.Repeat;
import com.ametrin.structures.structure.modular.HeightAnchor;
import net.minecraft.core.Direction;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;
import java.util.Optional;

public final class DECatacombs {
    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var pools = new JigsawPools(context, DungeonsEnhanced.MOD_ID, DEStructureIDs.CATACOMBS + "/");

        pools.pool("root", p -> p.element("entry_ruin").element("entry_tower"));
        pools.pool("entry_down", p -> p.repeated("entry_stairs", Repeat.of(Direction.DOWN, 4).filter(new PlacementFilters.HeightRange(Optional.of(HeightAnchor.absolute(45)), Optional.empty(), PlacementFilters.HeightRange.Check.ORIGIN))));
        pools.pool("entry_bottom", p -> p.element("entry_bottom"));
        pools.pool("cross", p -> p.element("cross"));
        pools.pool("tunnel", p -> p
                .element("tunnel", e -> e.weight(5))
                .element("stairs", e -> e.weight(3))
                .elements(List.of("parkour", "corner_hole", "pot_corridor"), e -> e.weight(1))
                .fallback("fallback")
        );
        pools.pool("room", p -> p
                .element("cross", e -> e.weight(2).maxCount(8))
                .elements(List.of("room_small1", "room_small2", "room1", "room2", "room_big", "storage"), e -> e.weight(1))
                .element("laboratory", e -> e.weight(1).softMinCount(1).maxCount(1).minDepth(6))
                .fallback("fallback")
        );
        pools.pool("fallback", p -> p
                .element("fallback")
        );
    }
}