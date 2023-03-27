package com.barion.dungeons_enhanced.data;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structure.*;
import com.barion.dungeons_enhanced.world.structure.prefabs.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;

public class DETags {
    public interface Items {
        private static TagKey<Item> create(String name) {
            return TagKey.create(Registries.ITEM, DEUtil.location(name));
        }
    }

    public interface Biomes {
        TagKey<Biome> HAS_CASTLE = structure(DECastle.ID);
        TagKey<Biome> HAS_DEEP_CRYPT = structure(DEDeepCrypt.ID);
        TagKey<Biome> HAS_DESERT_TEMPLE = structure(DEDesertTemple.ID);
        TagKey<Biome> HAS_DESERT_TOMB = structure(DEDesertTomb.ID);
        TagKey<Biome> HAS_DRUID_CIRCLE = structure(DEDruidCircle.ID);
        TagKey<Biome> HAS_DUNGEON_VARIANT = structure(DEUndergroundStructure.ID_DUNGEON_VARIANT);
        TagKey<Biome> HAS_ELDERS_TEMPLE = structure(DEEldersTemple.ID);
        TagKey<Biome> HAS_FISHING_SHIP = structure(DESwimmingStructure.ID_FISHING_SHIP);
        TagKey<Biome> HAS_FLYING_DUTCHMAN = structure(DEFlyingStructure.ID_FLYING_DUTCHMAN);
        TagKey<Biome> HAS_HAY_STORAGE = structure(DEGroundStructure.ID_HAY_STORAGE);
        TagKey<Biome> HAS_ICE_PIT = structure(DEIcePit.ID);
        TagKey<Biome> HAS_JUNGLE_MONUMENT = structure(DEGroundStructure.ID_JUNGLE_MONUMENT);
        TagKey<Biome> HAS_LARGE_DUNGEON = structure(DELargeDungeon.ID);
        TagKey<Biome> HAS_MINERS_HOUSE = structure(DEGroundStructure.ID_MINERS_HOUSE);
        TagKey<Biome> HAS_MONSTER_MAZE = structure(DEMonsterMaze.ID);
        TagKey<Biome> HAS_MUSHROOM_HOUSE = structure(DEGroundStructure.ID_MUSHROOM_HOUSE);
        TagKey<Biome> HAS_PILLAGER_CAMP = structure(DEPillagerCamp.ID);
        TagKey<Biome> HAS_PIRATE_SHIP = structure(DEPirateShip.ID);
        TagKey<Biome> HAS_RUINED_BUILDING = structure(DEGroundStructure.ID_RUINED_BUILDING);
        TagKey<Biome> HAS_STABLES = structure(DEGroundStructure.ID_STABLES);
        TagKey<Biome> HAS_SUNKEN_SHRINE = structure(DEUnderwaterStructure.ID_SUNKEN_SHRINE);
        TagKey<Biome> HAS_TALL_WITCH_HUT = structure(DEGroundStructure.ID_TALL_WITCH_HUT);
        TagKey<Biome> HAS_TREE_HOUSE = structure(DEGroundStructure.ID_TREE_HOUSE);
        TagKey<Biome> HAS_TOWER_OF_THE_UNDEAD = structure(DEGroundStructure.ID_TOWER_OF_THE_UNDEAD);
        TagKey<Biome> HAS_WATCH_TOWER = structure(DEGroundStructure.ID_WATCH_TOWER);
        TagKey<Biome> HAS_WITCH_TOWER = structure(DEGroundStructure.ID_WITCH_TOWER);

        private static TagKey<Biome> structure(String name) {return create("has_structure/has_" + name);}
        private static TagKey<Biome> create(String name) {return TagKey.create(Registries.BIOME, DEUtil.location(name));}
    }
}
