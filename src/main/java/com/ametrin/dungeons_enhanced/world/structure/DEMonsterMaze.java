package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.registry.DEProcessorLists;
import com.ametrin.structures.structure.jigsaw.JigsawPools;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.List;

public final class DEMonsterMaze {
    public static void pool(BootstrapContext<StructureTemplatePool> context) {
        var builder = new JigsawPools(context, DungeonsEnhanced.MOD_ID, "monster_maze/");

        builder.pool("dark_root", p -> p.element("dark_root"));
        builder.pool("pale_root", p -> p.element("pale_root"));

        builder.defaultElementSettings(e -> e.processors(DEProcessorLists.MONSTER_MAZE));

        builder.pool("dark_tree", p -> p.element("dark_tree"));
        builder.pool("pale_tree", p -> p.element("pale_tree"));

        var crossTunnels = List.of("tunnels/cross1", "tunnels/cross2");
        var edgeTunnels = List.of("tunnels/edge1", "tunnels/edge2");
        var roomTunnels = List.of("tunnels/room1", "tunnels/room2", "tunnels/room3");
        var smallTunnels = List.of("tunnels/small1", "tunnels/small2", "tunnels/small3");
        var bigTunnels = List.of("tunnels/big1", "tunnels/big2", "tunnels/big3", "tunnels/big4", "tunnels/big5");
        var startStairs = List.of("stairs/big1", "stairs/big2");
        var stairs = List.of("stairs/big1", "stairs/big2", "stairs/big3");
        var rooms = List.of("big_room", "church", "prison", "room1", "storage", "brewery");

        builder.pool("tunnels/cross", p -> p
                .elements(crossTunnels)
        );

        builder.pool("tunnels/edge", p -> p
                .elements(edgeTunnels)
        );

        builder.pool("tunnels/room", p -> p
                .elements(roomTunnels)
        );

        builder.pool("tunnels/small", p -> p
                .elements(smallTunnels)
        );

        builder.pool("tunnels/big", p -> p
                .elements(bigTunnels)
        );

        builder.pool("start_stairs", p -> p
                .elements(startStairs)
        );

        builder.pool("stairs", p -> p
                .elements(stairs)
        );

        builder.pool("rooms", p -> p
                .elements(rooms)
        );

        builder.pool("tunnels", p -> p
                .elements(edgeTunnels)
                .elements(roomTunnels)
                .elements(smallTunnels)
                .elements(bigTunnels)
        );

        builder.pool("main", p -> p
                .elements(edgeTunnels, e -> e.weight(4))
                .elements(roomTunnels, e -> e.weight(3))
                .elements(smallTunnels, e -> e.weight(2))
                .elements(bigTunnels, e -> e.weight(3))
                .elements(crossTunnels, e -> e.weight(5))
                .elements(rooms, e -> e.weight(2))
        );

        builder.pool("boss", p -> p.element("boss"));
    }
}