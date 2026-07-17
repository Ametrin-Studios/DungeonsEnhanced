package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.structure.*;
import com.legacy.structure_gel.api.registry.RegistrarHolder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

@RegistrarHolder
public final class DETemplatePools {
    // Overworld
    public static final ResourceKey<StructureTemplatePool> CASTLE = resourceKey("castle/root");
    public static final ResourceKey<StructureTemplatePool> DEEP_CRYPT = resourceKey("deep_crypt/root");
    public static final ResourceKey<StructureTemplatePool> DESERT_TOMB = resourceKey("desert_tomb/root");
    public static final ResourceKey<StructureTemplatePool> DRUID_CIRCLE = resourceKey("druid_circle/root");
    public static final ResourceKey<StructureTemplatePool> LARGE_DUNGEON = resourceKey("large_dungeon/root");
    public static final ResourceKey<StructureTemplatePool> MONSTER_MAZE_DARK = resourceKey("monster_maze/dark_root");
    public static final ResourceKey<StructureTemplatePool> MONSTER_MAZE_PALE = resourceKey("monster_maze/pale_root");
    public static final ResourceKey<StructureTemplatePool> PILLAGER_CAMP = resourceKey("pillager_camp/root");

    // Nether
    public static final ResourceKey<StructureTemplatePool> BLACK_CITADEL = resourceKey("black_citadel/root");

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        // Overworld
        DECastle.pool(context);
        DEDeepCrypt.pool(context);
        DEDesertTomb.pool(context);
        DEDruidCircle.pool(context);
        DELargeDungeon.pool(context);
        DEMonsterMaze.pool(context);
        DEPillagerCamp.pool(context);

        // Nether
        DEBlackCitadel.pool(context);
    }

    static ResourceKey<StructureTemplatePool> resourceKey(String path) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, DungeonsEnhanced.locate(path));
    }
}