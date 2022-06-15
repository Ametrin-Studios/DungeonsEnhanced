package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.util.ConfigTemplates.StructureConfig;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.Dimension;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class DEConfig {
    public static final Common COMMON;
    protected static final ForgeConfigSpec COMMON_SPEC;
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
        public final StructureConfig DungeonVariant;
        public final StructureConfig EldersTemple;
        public final StructureConfig FishingShip;
        public final StructureConfig FlyingDutchman;
        public final StructureConfig HayStorage;
        public final StructureConfig IcePit;
        public final StructureConfig JungleMonument;
        public final StructureConfig LargeDungeon;
        public final StructureConfig MinersHouse;
        public final StructureConfig MonsterMaze;
        public final StructureConfig MushroomHouse;
        public final StructureConfig PillagerCamp;
        public final StructureConfig PirateShip;
        public final StructureConfig RuinedBuilding;
        public final StructureConfig SunkenShrine;
        public final StructureConfig Stables;
        public final StructureConfig TallWitchHut;
        public final StructureConfig TowerOfTheUndead;
        public final StructureConfig TreeHouse;
        public final StructureConfig WatchTower;
        public final StructureConfig WitchTower;

        protected Common(ForgeConfigSpec.Builder builder) {
            this.builder = builder;
            Castle = configs("Castle", 56, 0.42, Dimension.OVERWORLD, true, "#structure_gel:cold, #structure_gel:snowy, !#structure_gel:mountain, !#structure_gel:beach");
            DesertTemple = configs("Desert Temple", 32, 0.6, Dimension.OVERWORLD, true, "minecraft:desert");
            DesertTomb = configs("Desert Tomb", 29, 0.65, Dimension.OVERWORLD, true, "minecraft:desert");
            DruidCircle = configs("Druid Circle", 36, 0.4, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:wooded, !#structure_gel:mountain, !#structure_gel:beach");
            DungeonVariant = configs("Dungeon Variant", 16, 0.8, Dimension.OVERWORLD, false, "#forge:overworld");
            EldersTemple = configs("Elders Temple", 34, 82, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            FishingShip = configs("Fishing Ship", 48, 68, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            FlyingDutchman = configs("Flying Dutchman", 67, 40, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            HayStorage = configs("Hay Storage", 24, 0.75, Dimension.OVERWORLD, true, "#structure_gel:savanna");
            IcePit = configs("Ice Pit", 35, 0.70, Dimension.OVERWORLD, true, "#structure_gel:snowy, #structure_gel:frozen, !#structure_gel:mountain, !#structure_gel:river, !#structure_gel:beach");
            JungleMonument = configs("Jungle Monument", 30, 0.65, Dimension.OVERWORLD, true, "#structure_gel:jungle, !#structure_gel:bamboo_jungle");
            LargeDungeon = configs("Large Dungeon", 39, 0.35, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, #structure_gel:cold, !#structure_gel:mountain, !#structure_gel:beach");
            MinersHouse = configs("Miners House", 24, 0.8, Dimension.OVERWORLD, true, "minecraft:badlands, minecraft:badlands_plateau");
            MonsterMaze = configs("Monster Maze", 34, 0.5, Dimension.OVERWORLD, true, "#structure_gel:spooky, #structure_gel:pumpkin, !#structure_gel:sandy ,!#structure_gel:mountain");
            MushroomHouse = configs("Mushroom House", 15, 0.75, Dimension.OVERWORLD, true, "minecraft:mushroom_fields, minecraft:mushroom_field_shore");
            PillagerCamp = configs("Pillager Camp", 49, 0.35, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:mountain, !#structure_gel:wooded, !#structure_gel:river, !#structure_gel:beach");
            PirateShip = configs("Pirate Ship", 65, 49, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            RuinedBuilding = configs("Ruined Building", 27, 0.45, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:river, !#structure_gel:beach, !#structure_gel:mountain");
            SunkenShrine = configs("Sunken Shrine", 32, 55, Dimension.OVERWORLD, true, "#structure_gel:ocean");
            Stables = configs("Stables", 43, 0.57, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, !#structure_gel:mountain, !#structure_gel:wooded, !#structure_gel:beach");
            TallWitchHut = configs("Tall Witch Hut", 18, 0.6, Dimension.OVERWORLD, true, "#structure_gel:swamp");
            TowerOfTheUndead = configs("Tower of the Undead", 37, 0.35, Dimension.OVERWORLD, true, "#structure_gel:neutral_temp, #structure_gel:savanna, #structure_gel:swamp, !#structure_gel:mountain");
            TreeHouse = configs("Tree House", 31, 0.4, Dimension.OVERWORLD, true, "#forge:jungle");
            WatchTower = configs("Watch Tower", 33, 0.35, Dimension.OVERWORLD, true, "#structure_gel:cold, #structure_gel:snowy");
            WitchTower = configs("Witch Tower", 25, 0.5, Dimension.OVERWORLD, true, "#structure_gel:spruce_forest, #structure_gel:large_spruce_forest");
        }

        private StructureConfig configs(String name, int spacing, double prob, RegistryKey<Dimension> dimension, boolean isWhite, String biomes) {
            return new StructureConfig(this.builder, name).spacing(spacing).probability(prob).offset((int) (spacing/1.5f)).biomes(isWhite, biomes).validDimensions(dimension.location().toString());
        }
    }
}