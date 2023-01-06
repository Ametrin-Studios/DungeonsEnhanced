package com.barion.dungeons_enhanced.world;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.structures.*;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class DEPools {
    public static final RegistrarHandler<StructureTemplatePool> Handler = RegistrarHandler.getOrCreate(Registries.TEMPLATE_POOL, DungeonsEnhanced.ModID).bootstrap(DEPools::init);

    public static final ResourceKey<StructureTemplatePool> Castle = Handler.key("castle/root");
    public static final ResourceKey<StructureTemplatePool> DeepCrypt = Handler.key("deep_crypt/root");
    public static final ResourceKey<StructureTemplatePool> DesertTomb = Handler.key("desert_tomb/root");
    public static final ResourceKey<StructureTemplatePool> DruidCircle = Handler.key("druid_circle/root");
    public static final ResourceKey<StructureTemplatePool> LargeDungeon = Handler.key("large_dungeon/root");
    public static final ResourceKey<StructureTemplatePool> MonsterMaze = Handler.key("monster_maze/root");
    public static final ResourceKey<StructureTemplatePool> PillagerCamp = Handler.key("pillager_camp/root");

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