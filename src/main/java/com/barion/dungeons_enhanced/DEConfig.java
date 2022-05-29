package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
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
        public final StructureConfig DeepCrypt;
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
        public final StructureConfig Stables;
        public final StructureConfig SunkenShrine;
        public final StructureConfig TallWitchHut;
        public final StructureConfig TowerOfTheUndead;
        public final StructureConfig TreeHouse;
        public final StructureConfig WatchTower;
        public final StructureConfig WitchTower;

        protected Common(ForgeConfigSpec.Builder builder) {
            this.builder = builder;
            Castle = configs("Castle", 56, 42, Level.OVERWORLD, "##structure_gel:cold", "##structure_gel:snowy", "!##structure_gel:mountain", "!##structure_gel:beach");
            DeepCrypt = configs("Deep Crypt", 35, 75, Level.OVERWORLD, "##structure_gel:overworld");
            DesertTemple = configs("Desert Temple", 32, 60, Level.OVERWORLD, "minecraft:desert");
            DesertTomb = configs("Desert Tomb", 29, 65, Level.OVERWORLD, "minecraft:desert");
            DruidCircle = configs("Druid Circle", 39, 40, Level.OVERWORLD, "##structure_gel:neutral_temp", "!##structure_gel:wooded", "!##structure_gel:mountain", "!##structure_gel:beach" );
            DungeonVariant = configs("Dungeon Variant", 16, 80, Level.OVERWORLD, "##structure_gel:overworld");
            EldersTemple = configs("Elders Temple", 34, 82, Level.OVERWORLD, "##structure_gel:ocean");
            FishingShip = configs("Fishing Ship", 48, 68, Level.OVERWORLD, "##structure_gel:ocean");
            FlyingDutchman = configs("Flying Dutchman", 67, 40, Level.OVERWORLD, "##structure_gel:ocean");
            HayStorage = configs("Hay Storage", 24, 75, Level.OVERWORLD, "##structure_gel:savanna");
            IcePit = configs("Ice Pit", 35, 70, Level.OVERWORLD, "##structure_gel:snowy", "##structure_gel:frozen", "!##structure_gel:mountain", "!##structure_gel:river", "!##structure_gel:beach");
            JungleMonument = configs("Jungle Monument", 41, 75, Level.OVERWORLD, "#minecraft:is_jungle");
            LargeDungeon = configs("Large Dungeon", 39, 35, Level.OVERWORLD, "##structure_gel:neutral_temp", "##structure_gel:cold", "!##structure_gel:mountain","!##structure_gel:beach");
            MinersHouse = configs("Miners House", 24, 80, Level.OVERWORLD, "minecraft:badlands", "minecraft:badlands_plateau");
            MonsterMaze = configs("Monster Maze", 34, 50, Level.OVERWORLD, "##structure_gel:spooky", "##structure_gel:pumpkin", "!##structure_gel:sandy" ,"!##structure_gel:mountain");
            MushroomHouse = configs("Mushroom House", 15, 75, Level.OVERWORLD, "minecraft:mushroom_fields", "minecraft:mushroom_field_shore");
            PillagerCamp = configs("Pillager Camp", 49, 35, Level.OVERWORLD, "##structure_gel:neutral_temp", "!##structure_gel:mountain", "!##structure_gel:wooded", "!##structure_gel:river", "!##structure_gel:beach");
            PirateShip = configs("Pirate Ship", 59, 49, Level.OVERWORLD, "##structure_gel:ocean");
            RuinedBuilding = configs("Ruined Building", 27, 45, Level.OVERWORLD, "##structure_gel:neutral_temp", "!##structure_gel:river", "!##structure_gel:beach", "!##structure_gel:mountain");
            Stables = configs("Stables", 46, 32, Level.OVERWORLD, "##structure_gel:neutral_temp", "!##structure_gel:mountain", "!##structure_gel:wooded", "!##structure_gel:beach");
            SunkenShrine = configs("Sunken Shrine", 29, 55, Level.OVERWORLD, "##structure_gel:ocean");
            TallWitchHut = configs("Tall Witch Hut", 18, 60, Level.OVERWORLD, "##structure_gel:swamp");
            TowerOfTheUndead = configs("Tower of the Undead", 37, 35, Level.OVERWORLD, "##structure_gel:neutral_temp", "##structure_gel:savanna", "##structure_gel:swamp", "!##structure_gel:mountain");
            TreeHouse = configs("Tree House", 29, 40, Level.OVERWORLD, "#minecraft:is_jungle");
            WatchTower = configs("Watch Tower", 33, 45, Level.OVERWORLD, "##structure_gel:cold", "##structure_gel:snowy", "##structure_gel:mountain");
            WitchTower = configs("Witch Tower", 29, 45, Level.OVERWORLD, "##structure_gel:spruce_forest", "##structure_gel:large_spruce_forest");
        }

        private StructureConfig configs(String name, int spacing, int prob, ResourceKey<Level> dimension, String... biomes) {
            return StructureConfig.builder(this.builder, name)
                    .pushPlacement()
                        .spacing(spacing)
                        .offset((int) (spacing/1.5f))
                        .probability(prob)
                    .popPlacement()
                    .pushConfigured()
                        .biomes(biomes)
                        .dimensions(dimension)
                    .popConfigured().build();
        }
    }
}
