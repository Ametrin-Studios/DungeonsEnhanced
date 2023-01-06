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
            Castle = config("castle", 56, 42, Level.OVERWORLD, "#forge:is_cold/overworld", "#forge:is_snowy", "!#forge:is_water", "!#forge:is_mountain", "!#minecraft:is_beach");
            DeepCrypt = config("deep_crypt", 35, 75, Level.OVERWORLD, "#minecraft:is_overworld");
            DesertTemple = config("desert_temple", 31, 60, Level.OVERWORLD, "minecraft:desert");
            DesertTomb = config("desert_tomb", 25, 75, Level.OVERWORLD, "minecraft:desert");
            DruidCircle = config("druid_circle", 39, 40, Level.OVERWORLD, "#minecraft:is_overworld", "!#forge:is_cold/overworld", "!#forge:is_hot/overworld", "!#minecraft:is_forest", "!#forge:is_water", "!#forge:is_mountain", "!#minecraft:is_beach", "!#forge:is_underground");
            DungeonVariant = config("dungeon_variant", 16, 80, Level.OVERWORLD, "#minecraft:is_overworld");
            EldersTemple = config("elders_temple", 29, 100, Level.OVERWORLD, "#minecraft:is_ocean");
            FishingShip = config("fishing_ship", 48, 68, Level.OVERWORLD, "#minecraft:is_ocean", "!minecraft:frozen_ocean", "!minecraft:deep_frozen_ocean");
            FlyingDutchman = config("flying_dutchman", 67, 40, Level.OVERWORLD, "#minecraft:is_ocean");
            HayStorage = config("hay_storage", 24, 75, Level.OVERWORLD, "#minecraft:is_savanna");
            IcePit = config("ice_pit", 35, 70, Level.OVERWORLD, "#forge:is_snowy", "!#forge:is_water", "!#forge:is_mountain", "!#minecraft:is_beach");
            JungleMonument = config("jungle_monument", 41, 75, Level.OVERWORLD, "#minecraft:is_jungle");
            LargeDungeon = config("large_dungeon", 39, 35, Level.OVERWORLD, "#minecraft:is_overworld", "!#forge:is_hot/overworld", "!#forge:is_water", "!#forge:is_mountain", "!#minecraft:is_beach", "!#forge:is_underground");
            MinersHouse = config("miners_mouse", 24, 80, Level.OVERWORLD, "#minecraft:is_badlands");
            MonsterMaze = config("monster_maze", 34, 50, Level.OVERWORLD, "minecraft:dark_forest");
            MushroomHouse = config("mushroom_house", 15, 75, Level.OVERWORLD, "minecraft:mushroom_fields", "minecraft:mushroom_field_shore");
            PillagerCamp = config("pillager_camp", 49, 35, Level.OVERWORLD, "#minecraft:is_overworld", "!#forge:is_cold/overworld", "!#forge:is_hot/overworld", "!#minecraft:is_forest", "!#forge:is_water", "!#forge:is_mountain", "!#minecraft:is_beach", "!#forge:is_underground");
            PirateShip = config("pirate_ship", 65, 49, Level.OVERWORLD, "#minecraft:is_ocean", "!minecraft:frozen_ocean", "!minecraft:deep_frozen_ocean");
            RuinedBuilding = config("ruined_building", 27, 45, Level.OVERWORLD, "#minecraft:is_overworld", "!#forge:is_hot/overworld", "!#forge:is_water", "!#forge:is_mountain", "!#forge:is_underground");
            Stables = config("stables", 46, 32, Level.OVERWORLD, "#minecraft:is_overworld", "!#forge:is_cold/overworld", "!#forge:is_hot/overworld", "!#minecraft:is_forest", "!#forge:is_water", "!#forge:is_mountain", "!#minecraft:is_beach", "!#forge:is_underground");
            SunkenShrine = config("sunken_shrine", 32, 55, Level.OVERWORLD, "#minecraft:is_ocean");
            TallWitchHut = config("tall_witch_hut", 18, 60, Level.OVERWORLD, "#forge:is_swamp");
            TowerOfTheUndead = config("tower_of_the_undead", 37, 35, Level.OVERWORLD, "#minecraft:is_overworld", "!#forge:is_sandy", "!#forge:is_water", "!#minecraft:is_beach", "!#forge:is_underground");
            TreeHouse = config("tree_house", 29, 40, Level.OVERWORLD, "#minecraft:is_jungle");
            WatchTower = config("watch_tower", 33, 45, Level.OVERWORLD, "#forge:is_cold/overworld", "!#forge:is_water");
            WitchTower = config("witch_tower", 29, 45, Level.OVERWORLD, "#minecraft:is_taiga");
        }

        private StructureConfig config(String name, int spacing, int prob, ResourceKey<Level> dimension, String... biomes) {
            return StructureConfig.builder(this.builder, name)
                    .pushPlacement()
                        .spacing(spacing)
                        .offset((int) (spacing/1.4f))
                        .probability(prob)
                    .popPlacement()
                    .pushStructure()
                        .biomes(biomes)
                        .dimensions(dimension)
                    .popStructure().build();
        }
    }
}
