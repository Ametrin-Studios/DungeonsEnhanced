package com.barion.dungeons_enhanced;

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
        public final DEStructureConfig castle;
        public final DEStructureConfig ruined_building;
        public final DEStructureConfig druid_circle;
        //public final DEStructureConfig flying_dutchman;
        public final DEStructureConfig hay_Storage;
        public final DEStructureConfig ice_pit;
        public final DEStructureConfig jungle_monument;
        public final DEStructureConfig large_dungeon;
        public final DEStructureConfig desert_temple;
        public final DEStructureConfig desert_tomb;
        public final DEStructureConfig dungeon_variant;
        public final DEStructureConfig miners_house;
        public final DEStructureConfig monster_maze;
        public final DEStructureConfig mushroom_house;
        public final DEStructureConfig pillager_camp;
        public final DEStructureConfig stables;
        public final DEStructureConfig tall_witch_hut;
        public final DEStructureConfig tower_of_the_undead;
        public final DEStructureConfig tree_house;
        public final DEStructureConfig watch_tower;
        public final DEStructureConfig witch_tower;

        protected Common(ForgeConfigSpec.Builder builder) {
            this.builder = builder;
            castle = Configs("Castle", 57, 25, true, "#structure_gel:cold", "#structure_gel:snowy", "!#structure_gel:mountain", "!#structure_gel:beach");
            desert_temple = Configs("Desert Temple", 32, 60, true, "minecraft:desert");
            desert_tomb = Configs("Desert Tomb", 29, 65, true, "minecraft:desert");
            druid_circle = Configs("Druid Circle", 39, 40, true, "#structure_gel:neutral_temp", "!#structure_gel:wooded", "!#structure_gel:mountain", "!#structure_gel:beach" );
            dungeon_variant = Configs("Dungeon Variant", 12, 80, false, "");
            //flying_dutchman = Configs("Flying Dutchman", 23, 0, false, "");
            hay_Storage = Configs("Hay Storage", 24, 85, true, "#structure_gel:savanna");
            ice_pit = Configs("Ice Pit", 35, 70, true, "#structure_gel:snowy", "#structure_gel:frozen", "!#structure_gel:mountain", "!structure_gel:river", "!structure_gel:beach");
            jungle_monument = Configs("Jungle Monument", 20, 70, true, "#structure_gel:jungle", "!#structure_gel:bamboo_jungle");
            large_dungeon = Configs("Large Dungeon", 38, 35, true, "#structure_gel:neutral_temp", "#structure_gel:cold", "!#structure_gel:mountain","!structure_gel:beach");
            miners_house = Configs("Miners House", 24, 80, true, "minecraft:badlands", "minecraft:badlands_plateau");
            monster_maze = Configs("Monster Maze", 34, 50, true, "#structure_gel:spooky", "#structure_gel:pumpkin", "!#structure_gel:sandy" ,"!structure_gel:mountain");
            mushroom_house = Configs("Mushroom House", 15, 90, true, "minecraft:mushroom_fields", "minecraft:mushroom_field_shore");
            pillager_camp = Configs("Pillager Camp", 49, 35, true, "#structure_gel:neutral_temp", "!#structure_gel:mountain", "!#structure_gel:wooded", "!structure_gel:river", "!structure_gel:beach");
            ruined_building = Configs("Ruined Building", 27, 45, true, "#structure_gel:neutral_temp", "!structure_gel:river", "!structure_gel:beach", "!#structure_gel:mountain");
            stables = Configs("Stables", 46, 32, true, "#structure_gel:neutral_temp", "!#structure_gel:mountain", "!#structure_gel:wooded", "!#structure_gel:beach");
            tall_witch_hut = Configs("Tall Witch Hut", 18, 60, true, "#structure_gel:swamp");
            tower_of_the_undead = Configs("Tower of the Undead", 37, 35, true, "#structure_gel:neutral_temp", "#structure_gel:savanna", "#structure_gel:swamp", "!#structure_gel:mountain");
            tree_house = Configs("Tree House", 31, 40, true, "#forge:jungle");
            watch_tower = Configs("Watch Tower", 33, 35, true, "#structure_gel:cold", "#structure_gel:snowy");
            witch_tower = Configs("Witch Tower", 21, 50, true, "#structure_gel:spruce_forest", "#structure_gel:large_spruce_forest");
        }

        private DEStructureConfig Configs(String name, int spacing, int prob, boolean isWhite, String... biomes) {
            return (DEStructureConfig) new DEStructureConfig(this.builder, name).spacing(spacing).probability(prob).biomes(isWhite, biomes).validDimensions(Level.OVERWORLD.location().toString());
        }
    }
}