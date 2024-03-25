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

    public static class Common{
        private final ForgeConfigSpec.Builder builder;

        public final StructureConfig Castle;
        public final StructureConfig DesertTemple;
        public final StructureConfig DesertTomb;
        public final StructureConfig DruidCircle;
        public final StructureConfig DUNGEON_VARIANT;
        public final StructureConfig EldersTemple;
        public final StructureConfig FishingShip;
        public final StructureConfig FLYING_DUTCHMAN;
        public final StructureConfig HayStorage;
        public final StructureConfig IcePit;
        public final StructureConfig JungleMonument;
        public final StructureConfig LargeDungeon;
        public final StructureConfig MinersHouse;
        public final StructureConfig MonsterMaze;
        public final StructureConfig MushroomHouse;
        public final StructureConfig PillagerCamp;
        public final StructureConfig PirateShip;
        public final StructureConfig RUINED_BUILDING;
        public final StructureConfig SunkenShrine;
        public final StructureConfig Stables;
        public final StructureConfig TallWitchHut;
        public final StructureConfig TowerOfTheUndead;
        public final StructureConfig TreeHouse;
        public final StructureConfig WatchTower;
        public final StructureConfig WitchTower;

        protected Common(ForgeConfigSpec.Builder builder) {
            this.builder = builder;
            Castle = config("Castle", 56, 0.42, Dimension.OVERWORLD, true, "#structure_gel:cold, #structure_gel:snowy, !#structure_gel:mountain, !#structure_gel:beach");
            DesertTemple = config("Desert Temple", 32, 0.6, Dimension.OVERWORLD, true, "minecraft:desert");
            DesertTomb = config("Desert Tomb", 29, 0.65, Dimension.OVERWORLD, true, "minecraft:desert");
            DruidCircle = config("Druid Circle", 36, 0.4, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:wooded, !#structure_gel:mountain, !#structure_gel:beach");
            DUNGEON_VARIANT = config("Dungeon Variant", 16, 0.8, Dimension.OVERWORLD, false, "");
            EldersTemple = config("Elders Temple", 29, 1, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            FishingShip = config("Fishing Ship", 48, 0.68, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            FLYING_DUTCHMAN = config("Flying Dutchman", 67, 0.4, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            HayStorage = config("Hay Storage", 24, 0.75, Dimension.OVERWORLD, true, "#structure_gel:savanna");
            IcePit = config("Ice Pit", 35, 0.70, Dimension.OVERWORLD, true, "#structure_gel:snowy, #structure_gel:frozen, !#structure_gel:mountain, !#structure_gel:river, !#structure_gel:beach");
            JungleMonument = config("Jungle Monument", 30, 0.65, Dimension.OVERWORLD, true, "#structure_gel:jungle, !#structure_gel:bamboo_jungle");
            LargeDungeon = config("Large Dungeon", 39, 0.35, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, #structure_gel:cold, !#structure_gel:mountain, !#structure_gel:beach");
            MinersHouse = config("Miners House", 24, 0.8, Dimension.OVERWORLD, true, "minecraft:badlands, minecraft:badlands_plateau");
            MonsterMaze = config("Monster Maze", 34, 0.5, Dimension.OVERWORLD, true, "#structure_gel:spooky, #structure_gel:pumpkin, !#structure_gel:sandy ,!#structure_gel:mountain");
            MushroomHouse = config("Mushroom House", 15, 0.75, Dimension.OVERWORLD, true, "minecraft:mushroom_fields, minecraft:mushroom_field_shore");
            PillagerCamp = config("Pillager Camp", 49, 0.35, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:mountain, !#structure_gel:wooded, !#structure_gel:river, !#structure_gel:beach");
            PirateShip = config("Pirate Ship", 65, 0.49, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            RUINED_BUILDING = config("Ruined Building", 27, 0.45, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:river, !#structure_gel:beach, !#structure_gel:mountain");
            SunkenShrine = config("Sunken Shrine", 32, 0.55, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            Stables = config("Stables", 43, 0.57, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:mountain, !#structure_gel:wooded, !#structure_gel:beach");
            TallWitchHut = config("Tall Witch Hut", 18, 0.6, Dimension.OVERWORLD, true, "#structure_gel:swamp");
            TowerOfTheUndead = config("Tower of the Undead", 37, 0.35, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, #structure_gel:savanna, #structure_gel:swamp, !#structure_gel:mountain");
            TreeHouse = config("Tree House", 31, 0.4, Dimension.OVERWORLD, true, "#forge:jungle");
            WatchTower = config("Watch Tower", 33, 0.35, Dimension.OVERWORLD, true, "#structure_gel:cold, #structure_gel:snowy");
            WitchTower = config("Witch Tower", 25, 0.5, Dimension.OVERWORLD, true, "#structure_gel:spruce_forest, #structure_gel:large_spruce_forest");
        }

        private StructureConfig config(String name, int spacing, double probability, RegistryKey<Dimension> dimension, boolean isWhitelist, String biomes) {
            return new StructureConfig(this.builder, name).spacing(spacing).probability(probability).offset((int) (spacing/1.5f)).biomes(isWhitelist, biomes).validDimensions(dimension.location().toString());
        }
    }
}