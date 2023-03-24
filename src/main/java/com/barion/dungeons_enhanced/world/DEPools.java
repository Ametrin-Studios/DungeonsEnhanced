package com.barion.dungeons_enhanced.world;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.structure.*;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class DEPools {
    public static final RegistrarHandler<StructureTemplatePool> HANDLER = RegistrarHandler.getOrCreate(Registries.TEMPLATE_POOL, DungeonsEnhanced.MOD_ID).bootstrap(DEPools::init);

    public static final ResourceKey<StructureTemplatePool> CASTLE = HANDLER.key("castle/root");
    public static final ResourceKey<StructureTemplatePool> DEEP_CRYPT = HANDLER.key("deep_crypt/root");
    public static final ResourceKey<StructureTemplatePool> DESERT_TOMB = HANDLER.key("desert_tomb/root");
    public static final ResourceKey<StructureTemplatePool> DRUID_CIRCLE = HANDLER.key("druid_circle/root");
    public static final ResourceKey<StructureTemplatePool> LARGE_DUNGEON = HANDLER.key("large_dungeon/root");
    public static final ResourceKey<StructureTemplatePool> MONSTER_MAZE = HANDLER.key("monster_maze/root");
    public static final ResourceKey<StructureTemplatePool> PILLAGER_CAMP = HANDLER.key("pillager_camp/root");

    private static void init(BootstapContext<StructureTemplatePool> context){
        DECastle.pool(context);
        DEDeepCrypt.pool(context);
        DEDesertTomb.pool(context);
        DEDruidCircle.pool(context);
        DELargeDungeon.pool(context);
        DEMonsterMaze.pool(context);
        DEPillagerCamp.pool(context);
    }
}