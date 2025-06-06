package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

public interface DELootTables {
    // Overworld
    interface DesertTemple {
        ResourceKey<LootTable> COFFIN = chest("desert_temple/coffin");
        ResourceKey<LootTable> FLOOR = chest("desert_temple/floor");
        ResourceKey<LootTable> TREASURE = chest("desert_temple/treasure");
    }

    ResourceKey<LootTable> DESERT_TOMB = chest("desert_tomb");
    ResourceKey<LootTable> FISHING_SHIP = chest("fishing_ship");
    ResourceKey<LootTable> HAY_STORAGE = chest("hay_storage");

    interface IcePit {
        ResourceKey<LootTable> ARMORY = chest("ice_pit/armory");
        ResourceKey<LootTable> BED = chest("ice_pit/bed");
        ResourceKey<LootTable> FOOD = chest("ice_pit/food");
        ResourceKey<LootTable> GARDEN = chest("ice_pit/garden");
        ResourceKey<LootTable> HALL = chest("ice_pit/hall");
    }

    interface JungleMonument {
        ResourceKey<LootTable> FLOOR = chest("jungle_monument/floor");
        ResourceKey<LootTable> TREASURE = chest("jungle_monument/treasure");
    }

    ResourceKey<LootTable> MINERS_HOUSE = chest("miners_house");

    interface MonsterMaze {
        ResourceKey<LootTable> BREWERY = chest("monster_maze/brewery");
        ResourceKey<LootTable> CHURCH = chest("monster_maze/church");
        ResourceKey<LootTable> TREASURE = chest("monster_maze/treasure");
        ResourceKey<LootTable> PRISON = chest("monster_maze/prison");
        ResourceKey<LootTable> EQUIPMENT_PRISON_ZOMBIE = create("equipment/monster_maze/prison_zombie");
    }

    ResourceKey<LootTable> MUSHROOM_HOUSE = chest("mushroom_house");

    interface PillagerCamp {
        ResourceKey<LootTable> GENERAL = chest("pillager_camp/general");
        ResourceKey<LootTable> KITCHEN = chest("pillager_camp/kitchen");
    }

    interface Ruined {
        ResourceKey<LootTable> HOUSE = chest("ruined/house");
    }

    ResourceKey<LootTable> STABLES = chest("stables");
    ResourceKey<LootTable> SUNKEN_SHRINE = chest("sunken_shrine");

    // Nether
    interface BlackCitadel {
        ResourceKey<LootTable> NORMAL = chest("black_citadel/normal");
        ResourceKey<LootTable> NORMAL_ALT = chest("black_citadel/normal_alt");
        ResourceKey<LootTable> KITCHEN = chest("black_citadel/kitchen");
        ResourceKey<LootTable> TREASURE = chest("black_citadel/treasure");
        ResourceKey<LootTable> TREASURE_ALT = chest("black_citadel/treasure_alt");
    }

    private static ResourceKey<LootTable> chest(String key) {
        return create("chests/" + key);
    }

    private static ResourceKey<LootTable> create(String key) {
        return ResourceKey.create(Registries.LOOT_TABLE, DungeonsEnhanced.locate(key));
    }
}
