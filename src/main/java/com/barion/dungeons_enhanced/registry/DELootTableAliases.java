package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.legacy.structure_gel.api.registry.RegistrarHolder;
import com.legacy.structure_gel.api.registry.StructureGelRegistries;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.neoforge.registries.RegisterEvent.RegisterHelper;

import static com.barion.dungeons_enhanced.DungeonsEnhanced.locate;

@RegistrarHolder
public final class DELootTableAliases {
    public static final RegistrarHandler<ResourceKey<LootTable>> HANDLER = RegistrarHandler.getOrCreate(StructureGelRegistries.Keys.LOOT_TABLE_ALIAS, DungeonsEnhanced.MOD_ID).addListener(DELootTableAliases::register);

    private static void register(RegisterHelper<ResourceKey<LootTable>> event) {
        // Vanilla
        event.register(ResourceLocation.withDefaultNamespace("desert_pyramid"), BuiltInLootTables.DESERT_PYRAMID);
//        event.register(ResourceLocation.withDefaultNamespace("simple_dungeon"), BuiltInLootTables.SIMPLE_DUNGEON);
        event.register(ResourceLocation.withDefaultNamespace("village/house/desert"), BuiltInLootTables.VILLAGE_DESERT_HOUSE);
        event.register(ResourceLocation.withDefaultNamespace("village/house/plains"), BuiltInLootTables.VILLAGE_PLAINS_HOUSE);
        event.register(ResourceLocation.withDefaultNamespace("village/house/savanna"), BuiltInLootTables.VILLAGE_SAVANNA_HOUSE);
        event.register(ResourceLocation.withDefaultNamespace("village/house/snowy"), BuiltInLootTables.VILLAGE_SNOWY_HOUSE);
        event.register(ResourceLocation.withDefaultNamespace("village/house/taiga"), BuiltInLootTables.VILLAGE_TAIGA_HOUSE);

        // NOTE: might make sense to use the same name for the alias as the actual loot table
        // Overworld

        event.register(DELootTables.Castle.ARMORY.location(), DELootTables.Castle.ARMORY);
        event.register(DELootTables.Castle.BEDROOM.location(), DELootTables.Castle.BEDROOM);
        event.register(DELootTables.Castle.CELLAR.location(), DELootTables.Castle.CELLAR);
        event.register(DELootTables.Castle.COFFIN.location(), DELootTables.Castle.COFFIN);
        event.register(DELootTables.Castle.KITCHEN.location(), DELootTables.Castle.KITCHEN);
        event.register(DELootTables.Castle.LIBRARY.location(), DELootTables.Castle.LIBRARY);
        event.register(DELootTables.Castle.PRISON.location(), DELootTables.Castle.PRISON);
        event.register(DELootTables.Castle.QUARTERS.location(), DELootTables.Castle.QUARTERS);
        event.register(DELootTables.Castle.SPRING.location(), DELootTables.Castle.SPRING);
        event.register(DELootTables.Castle.THRONE.location(), DELootTables.Castle.THRONE);

        event.register(locate("desert_temple/coffin"), DELootTables.DesertTemple.COFFIN);
        event.register(locate("desert_temple/floor"), DELootTables.DesertTemple.FLOOR);
        event.register(locate("desert_temple/treasure"), DELootTables.DesertTemple.TREASURE);

        event.register(locate("desert_tomb"), DELootTables.DESERT_TOMB);
        event.register(locate("fishing_ship"), DELootTables.FISHING_SHIP);
        event.register(locate("hay_storage"), DELootTables.HAY_STORAGE);

        event.register(locate("ice_pit/armory"), DELootTables.IcePit.ARMORY);
        event.register(locate("ice_pit/bed"), DELootTables.IcePit.BED);
        event.register(locate("ice_pit/food"), DELootTables.IcePit.FOOD);
        event.register(locate("ice_pit/garden"), DELootTables.IcePit.GARDEN);
        event.register(locate("ice_pit/hall"), DELootTables.IcePit.HALL);

        event.register(locate("jungle_monument/floor"), DELootTables.JungleMonument.FLOOR);
        event.register(locate("jungle_monument/treasure"), DELootTables.JungleMonument.TREASURE);

        event.register(locate("miners_house"), DELootTables.MINERS_HOUSE);

        event.register(locate("monster_maze/brewery"), DELootTables.MonsterMaze.BREWERY);
        event.register(locate("monster_maze/church"), DELootTables.MonsterMaze.CHURCH);
        event.register(locate("monster_maze/prison"), DELootTables.MonsterMaze.PRISON);
        event.register(locate("monster_maze/treasure"), DELootTables.MonsterMaze.TREASURE);

        event.register(locate("mushroom_house"), DELootTables.MUSHROOM_HOUSE);

        event.register(locate("pillager_camp/general"), DELootTables.PillagerCamp.GENERAL);
        event.register(locate("pillager_camp/kitchen"), DELootTables.PillagerCamp.KITCHEN);

        event.register(locate("ruined/house"), DELootTables.Ruined.HOUSE);

        event.register(locate("stables"), DELootTables.STABLES);
        event.register(locate("sunken_shrine"), DELootTables.SUNKEN_SHRINE);

        // Nether
        event.register(locate("black_citadel/normal"), DELootTables.BlackCitadel.NORMAL);
        event.register(locate("black_citadel/normal_alt"), DELootTables.BlackCitadel.NORMAL_ALT);
        event.register(locate("black_citadel/kitchen"), DELootTables.BlackCitadel.KITCHEN);
        event.register(locate("black_citadel/treasure"), DELootTables.BlackCitadel.TREASURE);
        event.register(locate("black_citadel/treasure_alt"), DELootTables.BlackCitadel.TREASURE_ALT);
    }
}
