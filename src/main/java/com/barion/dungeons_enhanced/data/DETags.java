package com.barion.dungeons_enhanced.data;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class DETags {
    public interface Items {
        private static TagKey<Item> create(String name) {
            return TagKey.create(Registries.ITEM, DEUtil.locate(name));
        }
    }

    public interface Biomes {
        TagKey<Biome> IS_SHORE = create("is_shore");
        TagKey<Biome> IS_BAD_FOR_STRUCTURE = create("is_bad_for_structure");

        private static TagKey<Biome> structure(String name) {
            return create("has_structure/has_" + name);
        }

        private static TagKey<Biome> create(String name) {
            return TagKey.create(Registries.BIOME, DEUtil.locate(name));
        }
    }

    public interface Structures {
        TagKey<Structure> ON_CASTLE_EXPLORER_MAPS = create("on_castle_explorer_maps");
        TagKey<Structure> ON_ELDER_EXPLORER_MAPS = create("on_elder_explorer_maps");
        TagKey<Structure> ON_DESERT_EXPLORER_MAPS = create("on_desert_explorer_maps");
        TagKey<Structure> ON_MONSTER_MAZE_EXPLORER_MAPS = create("on_monster_maze_explorer_maps");

        private static TagKey<Structure> create(String name) {
            return TagKey.create(Registries.STRUCTURE, DEUtil.locate(name));
        }
    }
}
