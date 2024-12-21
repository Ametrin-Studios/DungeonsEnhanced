package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.util.ConfigTemplates.StructureConfig;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.Dimension;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class DEConfig {
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {
        private final ForgeConfigSpec.Builder builder;

        public final StructureConfig CASTLE;
        public final StructureConfig DESERT_TEMPLE;
        public final StructureConfig DESERT_TOMB;
        public final StructureConfig DRUID_CIRCLE;
        public final StructureConfig DUNGEON_VARIANT;
        public final StructureConfig ELDERS_TEMPLE;
        public final StructureConfig FISHING_SHIP;
        public final StructureConfig FLYING_DUTCHMAN;
        public final StructureConfig HAY_STORAGE;
        public final StructureConfig ICE_PIT;
        public final StructureConfig JUNGLE_MONUMENT;
        public final StructureConfig LARGE_DUNGEON;
        public final StructureConfig MINERS_HOUSE;
        public final StructureConfig MONSTER_MAZE;
        public final StructureConfig MUSHROOM_HOUSE;
        public final StructureConfig PILLAGER_CAMP;
        public final StructureConfig PIRATE_SHIP;
        public final StructureConfig RUINED_BUILDING;
        public final StructureConfig SUNKEN_SHRINE;
        public final StructureConfig STABLES;
        public final StructureConfig TALL_WITCH_HUT;
        public final StructureConfig TOWER_OF_THE_UNDEAD;
        public final StructureConfig TREE_HOUSE;
        public final StructureConfig WATCH_TOWER;
        public final StructureConfig WITCH_TOWER;

        protected Common(ForgeConfigSpec.Builder builder) {
            this.builder = builder;
            CASTLE = config("Castle", 56, 0.42, Dimension.OVERWORLD, true, "#structure_gel:cold, #structure_gel:snowy, !#structure_gel:mountain, !#structure_gel:beach");
            DESERT_TEMPLE = config("Desert Temple", 32, 0.6, Dimension.OVERWORLD, true, "minecraft:desert");
            DESERT_TOMB = config("Desert Tomb", 29, 0.65, Dimension.OVERWORLD, true, "minecraft:desert");
            DRUID_CIRCLE = config("Druid Circle", 36, 0.4, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:wooded, !#structure_gel:mountain, !#structure_gel:beach");
            DUNGEON_VARIANT = config("Dungeon Variant", 16, 0.8, Dimension.OVERWORLD, false, "");
            ELDERS_TEMPLE = config("Elders Temple", 29, 1, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            FISHING_SHIP = config("Fishing Ship", 48, 0.68, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            FLYING_DUTCHMAN = config("Flying Dutchman", 67, 0.4, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            HAY_STORAGE = config("Hay Storage", 24, 0.75, Dimension.OVERWORLD, true, "#structure_gel:savanna");
            ICE_PIT = config("Ice Pit", 35, 0.70, Dimension.OVERWORLD, true, "#structure_gel:snowy, #structure_gel:frozen, !#structure_gel:mountain, !#structure_gel:river, !#structure_gel:beach");
            JUNGLE_MONUMENT = config("Jungle Monument", 30, 0.65, Dimension.OVERWORLD, true, "#structure_gel:jungle, !#structure_gel:bamboo_jungle");
            LARGE_DUNGEON = config("Large Dungeon", 39, 0.35, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, #structure_gel:cold, !#structure_gel:mountain, !#structure_gel:beach");
            MINERS_HOUSE = config("Miners House", 24, 0.8, Dimension.OVERWORLD, true, "minecraft:badlands, minecraft:badlands_plateau");
            MONSTER_MAZE = config("Monster Maze", 34, 0.5, Dimension.OVERWORLD, true, "#structure_gel:spooky, #structure_gel:pumpkin, !#structure_gel:sandy ,!#structure_gel:mountain");
            MUSHROOM_HOUSE = config("Mushroom House", 15, 0.75, Dimension.OVERWORLD, true, "minecraft:mushroom_fields, minecraft:mushroom_field_shore");
            PILLAGER_CAMP = config("Pillager Camp", 49, 0.35, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:mountain, !#structure_gel:wooded, !#structure_gel:river, !#structure_gel:beach");
            PIRATE_SHIP = config("Pirate Ship", 65, 0.49, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            RUINED_BUILDING = config("Ruined Building", 27, 0.45, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:river, !#structure_gel:beach, !#structure_gel:mountain");
            SUNKEN_SHRINE = config("Sunken Shrine", 32, 0.55, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            STABLES = config("Stables", 43, 0.57, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:mountain, !#structure_gel:wooded, !#structure_gel:beach");
            TALL_WITCH_HUT = config("Tall Witch Hut", 18, 0.6, Dimension.OVERWORLD, true, "#structure_gel:swamp");
            TOWER_OF_THE_UNDEAD = config("Tower of the Undead", 37, 0.35, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, #structure_gel:savanna, #structure_gel:swamp, !#structure_gel:mountain");
            TREE_HOUSE = config("Tree House", 31, 0.4, Dimension.OVERWORLD, true, "#forge:jungle");
            WATCH_TOWER = config("Watch Tower", 33, 0.35, Dimension.OVERWORLD, true, "#structure_gel:cold, #structure_gel:snowy");
            WITCH_TOWER = config("Witch Tower", 25, 0.5, Dimension.OVERWORLD, true, "#structure_gel:spruce_forest, #structure_gel:large_spruce_forest");
        }

        private StructureConfig config(String name, int spacing, double probability, RegistryKey<Dimension> dimension, boolean isWhitelist, String biomes) {
            return new StructureConfig(this.builder, name).spacing(spacing).probability(probability).offset((int) (spacing / 1.5f)).biomes(isWhitelist, biomes).validDimensions(dimension.location().toString());
        }
    }
}