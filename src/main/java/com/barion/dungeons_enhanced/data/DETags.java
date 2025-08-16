package com.barion.dungeons_enhanced.data;

import com.barion.dungeons_enhanced.registry.DEStructureIDs;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;

import static com.barion.dungeons_enhanced.DungeonsEnhanced.locate;

public final class DETags {
    public interface Items {
        private static TagKey<Item> create(String name) {
            return TagKey.create(Registries.ITEM, locate(name));
        }
    }

    public interface Biomes {
        // Overworld
        TagKey<Biome> HAS_CASTLE = structure(DEStructureIDs.CASTLE);
        TagKey<Biome> HAS_DEEP_CRYPT = structure(DEStructureIDs.DEEP_CRYPT);
        TagKey<Biome> HAS_DESERT_TEMPLE = structure(DEStructureIDs.DESERT_TEMPLE);
        TagKey<Biome> HAS_DESERT_TOMB = structure(DEStructureIDs.DESERT_TOMB);
        TagKey<Biome> HAS_DRUID_CIRCLE = structure(DEStructureIDs.DRUID_CIRCLE);
        TagKey<Biome> HAS_DUNGEON_VARIANT = structure(DEStructureIDs.DUNGEON_VARIANT);
        TagKey<Biome> HAS_ELDERS_TEMPLE = structure(DEStructureIDs.ELDERS_TEMPLE);
        TagKey<Biome> HAS_FISHING_SHIP = structure(DEStructureIDs.FISHING_SHIP);
        TagKey<Biome> HAS_FLYING_DUTCHMAN = structure(DEStructureIDs.FLYING_DUTCHMAN);
        TagKey<Biome> HAS_HAY_STORAGE = structure(DEStructureIDs.HAY_STORAGE);
        TagKey<Biome> HAS_ICE_PIT = structure(DEStructureIDs.ICE_PIT);
        TagKey<Biome> HAS_JUNGLE_MONUMENT = structure(DEStructureIDs.JUNGLE_MONUMENT);
        TagKey<Biome> HAS_LARGE_DUNGEON = structure(DEStructureIDs.LARGE_DUNGEON);
        TagKey<Biome> HAS_MINERS_HOUSE = structure(DEStructureIDs.MINERS_HOUSE);
        TagKey<Biome> HAS_MONSTER_MAZE_DARK = structure("monster_maze/dark");
        TagKey<Biome> HAS_MONSTER_MAZE_PALE = structure("monster_maze/pale");
        TagKey<Biome> HAS_MUSHROOM_HOUSE = structure(DEStructureIDs.MUSHROOM_HOUSE);
        TagKey<Biome> HAS_PILLAGER_CAMP = structure(DEStructureIDs.PILLAGER_CAMP);
        TagKey<Biome> HAS_PIRATE_SHIP = structure(DEStructureIDs.PIRATE_SHIP);
        TagKey<Biome> HAS_RUINED_BUILDING = structure(DEStructureIDs.RUINED_BUILDING);
        TagKey<Biome> HAS_STABLES = structure(DEStructureIDs.STABLES);
        TagKey<Biome> HAS_SUNKEN_SHRINE = structure(DEStructureIDs.SUNKEN_SHRINE);
        TagKey<Biome> HAS_TALL_WITCH_HUT = structure(DEStructureIDs.TALL_WITCH_HUT);
        TagKey<Biome> HAS_TREE_HOUSE = structure(DEStructureIDs.TREE_HOUSE);
        TagKey<Biome> HAS_TOWER_OF_THE_UNDEAD = structure(DEStructureIDs.TOWER_OF_THE_UNDEAD);
        TagKey<Biome> HAS_WATCH_TOWER = structure(DEStructureIDs.WATCH_TOWER);
        TagKey<Biome> HAS_WITCH_TOWER = structure(DEStructureIDs.WITCH_TOWER);

        // Nether
        TagKey<Biome> HAS_BLACK_CITADEL = structure(DEStructureIDs.BLACK_CITADEL);

        // Other
        TagKey<Biome> IS_SHORE = create("is_shore");
        TagKey<Biome> NO_STRUCTURES = create("no_structures");
        TagKey<Biome> NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED = create("no_structures/overworld/surface_extended");
        TagKey<Biome> NO_STRUCTURES_OVERWORLD_SURFACE = create("no_structures/overworld/surface");
        TagKey<Biome> NO_STRUCTURES_OVERWORLD_UNDERGROUND = create("no_structures/overworld/underground");
        TagKey<Biome> NO_STRUCTURES_OVERWORLD_OCEAN_SURFACE = create("no_structures/overworld/ocean_surface");
        TagKey<Biome> NO_STRUCTURES_OVERWORLD_OCEAN_FLOOR = create("no_structures/overworld/ocean_floor");
        TagKey<Biome> NO_STRUCTURES_NETHER = create("no_structures/nether");

        private static TagKey<Biome> structure(String name) {
            return create("has_structure/" + name);
        }

        private static TagKey<Biome> create(String name) {
            return TagKey.create(Registries.BIOME, locate(name));
        }
    }

    public interface Structures {
        TagKey<Structure> MONSTER_MAZE = create("monster_maze");
        TagKey<Structure> ON_CASTLE_EXPLORER_MAPS = create("on_castle_explorer_maps");
        TagKey<Structure> ON_ELDER_EXPLORER_MAPS = create("on_elder_explorer_maps");
        TagKey<Structure> ON_DESERT_EXPLORER_MAPS = create("on_desert_explorer_maps");
        TagKey<Structure> ON_MONSTER_MAZE_EXPLORER_MAPS = create("on_monster_maze_explorer_maps");

        private static TagKey<Structure> create(String name) {
            return TagKey.create(Registries.STRUCTURE, locate(name));
        }
    }
}
