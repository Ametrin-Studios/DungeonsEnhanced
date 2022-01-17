package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.api.config.StructureConfig;
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

    public static class Common {
        private final ForgeConfigSpec.Builder builder;
        public final StructureConfig castle;
        public final StructureConfig ruined_building;
        public final StructureConfig druid_circle;
        //public final StructureConfig flying_dutchman;
        public final StructureConfig hay_Storage;
        public final StructureConfig ice_pit;
        public final StructureConfig jungle_monument;
        public final StructureConfig large_dungeon;
        public final StructureConfig desert_temple;
        public final StructureConfig desert_tomb;
        public final StructureConfig dungeon_variant;
        public final StructureConfig miners_house;
        public final StructureConfig monster_maze;
        public final StructureConfig mushroom_house;
        public final StructureConfig pillager_camp;
        public final StructureConfig stables;
        public final StructureConfig tall_witch_hut;
        public final StructureConfig tower_of_the_undead;
        public final StructureConfig tree_house;
        public final StructureConfig watch_tower;
        public final StructureConfig witch_tower;

        protected Common(ForgeConfigSpec.Builder builder) {
            this.builder = builder;
            castle = Configs("Castle", 57, 25, true, "#structure_gel:cold", "#structure_gel:snowy", "!#structure_gel:mountain", "!#structure_gel:snowy_mountain", "!#structure_gel:beach", "!minecraft:stone_shore");
            desert_temple = Configs("Desert Temple", 32, 60, true, "minecraft:desert");
            desert_tomb = Configs("Desert Tomb", 29, 65, true, "minecraft:desert");
            druid_circle = Configs("Druid Circle", 39, 40, true, "#structure_gel:neutral_temp", "!#structure_gel:wooded", "!#structure_gel:mountain", "!#structure_gel:beach");
            dungeon_variant = Configs("Dungeon Variant", 12, 80, false, "");
            //flying_dutchman = Configs("Flying Dutchman", 23, 0, false, "");
            hay_Storage = Configs("Hay Storage", 24, 85, true, "#structure_gel:savanna");
            ice_pit = Configs("Ice Pit", 35, 70, true, "#structure_gel:snowy", "#structure_gel:frozen", "!#structure_gel:snowy_mountain", "!#structure_gel:mountain");
            jungle_monument = Configs("Jungle Monument", 20, 70, true, "#structure_gel:jungle", "!#structure_gel:bamboo_jungle");
            large_dungeon = Configs("Large Dungeon", 38, 35, true, "#structure_gel:neutral_temp", "#structure_gel:cold", "!#structure_gel:mountain");
            miners_house = Configs("Miners House", 24, 80, true, "minecraft:badlands", "minecraft:badlands_plateau");
            monster_maze = Configs("Monster Maze", 34, 50, true, "#structure_gel:spooky", "#structure_gel:pumpkin", "!#structure_gel:sandy", "!#forge:nether");
            mushroom_house = Configs("Mushroom House", 15, 90, true, "minecraft:mushroom_fields", "minecraft:mushroom_field_shore");
            pillager_camp = Configs("Pillager Camp", 49, 35, true, "#structure_gel:neutral_temp", "!#structure_gel:mountain", "!#structure_gel:wooded");
            ruined_building = Configs("Ruined Building", 27, 45, true, "#structure_gel:neutral_temp");
            stables = Configs("Stables", 46, 32, true, "#structure_gel:neutral_temp", "!#structure_gel:mountain", "!#structure_gel:wooded", "!#structure_gel:beach");
            tall_witch_hut = Configs("Tall Witch Hut", 18, 60, true, "#structure_gel:humid", "!#structure_gel:tropical", "!#structure_gel:jungle", "#structure_gel:pumpkin");
            tower_of_the_undead = Configs("Tower of the Undead", 37, 35, true, "#structure_gel:neutral_temp", "#structure_gel:savanna", "!#structure_gel:mountain", "#structure_gel:swamp");
            tree_house = Configs("Tree House", 31, 40, true, "#forge:jungle");
            watch_tower = Configs("Watch Tower", 33, 35, true, "#structure_gel:cold", "#structure_gel:snowy");
            witch_tower = Configs("Witch Tower", 21, 50, true, "#structure_gel:spruce_forest", "#structure_gel:large_spruce_forest");
        }

        private StructureConfig Configs(String name, int spacing, int prob, boolean isWhite, String... biomes){
            return new StructureConfig(this.builder, name).spacing(spacing).probability(prob).biomes(isWhite, biomes).validDimensions(Level.OVERWORLD.location().toString());
        }
    }
}