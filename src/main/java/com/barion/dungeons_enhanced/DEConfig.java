package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.api.config.StructureConfig;
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

        public final StructureConfig castle;
        public final StructureConfig deep_crypt;
        public final StructureConfig desert_temple;
        public final StructureConfig desert_tomb;
        public final StructureConfig druid_circle;
        public final StructureConfig dungeon_variant;
        public final StructureConfig flying_dutchman;
        public final StructureConfig hay_Storage;
        public final StructureConfig ice_pit;
        public final StructureConfig jungle_monument;
        public final StructureConfig large_dungeon;
        public final StructureConfig miners_house;
        public final StructureConfig monster_maze;
        public final StructureConfig mushroom_house;
        public final StructureConfig pillager_camp;
        public final StructureConfig ruined_building;
        public final StructureConfig stables;
        public final StructureConfig tall_witch_hut;
        public final StructureConfig tower_of_the_undead;
        public final StructureConfig tree_house;
        public final StructureConfig watch_tower;
        public final StructureConfig witch_tower;

        protected Common(ForgeConfigSpec.Builder builder) {
            this.builder = builder;
            castle = configs("Castle", 57, 25, "##structure_gel:cold", "##structure_gel:snowy", "!##structure_gel:mountain", "!##structure_gel:beach");
            deep_crypt = configs("Deep Crypt", 35, 75, "##structure_gel:overworld");
            desert_temple = configs("Desert Temple", 32, 60, "minecraft:desert");
            desert_tomb = configs("Desert Tomb", 29, 65, "minecraft:desert");
            druid_circle = configs("Druid Circle", 39, 40, "##structure_gel:neutral_temp", "!##structure_gel:wooded", "!##structure_gel:mountain", "!##structure_gel:beach" );
            dungeon_variant = configs("Dungeon Variant", 16, 80, "##structure_gel:overworld");
            flying_dutchman = configs("Flying Dutchman", 67, 40, "##structure_gel:ocean");
            hay_Storage = configs("Hay Storage", 24, 75, "##structure_gel:savanna");
            ice_pit = configs("Ice Pit", 35, 70, "##structure_gel:snowy", "##structure_gel:frozen", "!##structure_gel:mountain", "!##structure_gel:river", "!##structure_gel:beach");
            jungle_monument = configs("Jungle Monument", 41, 75, "#minecraft:is_jungle");
            large_dungeon = configs("Large Dungeon", 39, 35, "##structure_gel:neutral_temp", "##structure_gel:cold", "!##structure_gel:mountain","!##structure_gel:beach");
            miners_house = configs("Miners House", 24, 80, "minecraft:badlands", "minecraft:badlands_plateau");
            monster_maze = configs("Monster Maze", 34, 50, "##structure_gel:spooky", "##structure_gel:pumpkin", "!##structure_gel:sandy" ,"!##structure_gel:mountain");
            mushroom_house = configs("Mushroom House", 15, 75, "minecraft:mushroom_fields", "minecraft:mushroom_field_shore");
            pillager_camp = configs("Pillager Camp", 49, 35, "##structure_gel:neutral_temp", "!##structure_gel:mountain", "!##structure_gel:wooded", "!##structure_gel:river", "!##structure_gel:beach");
            ruined_building = configs("Ruined Building", 27, 45, "##structure_gel:neutral_temp", "!##structure_gel:river", "!##structure_gel:beach", "!##structure_gel:mountain");
            stables = configs("Stables", 46, 32, "##structure_gel:neutral_temp", "!##structure_gel:mountain", "!##structure_gel:wooded", "!##structure_gel:beach");
            tall_witch_hut = configs("Tall Witch Hut", 18, 60, "##structure_gel:swamp");
            tower_of_the_undead = configs("Tower of the Undead", 37, 35, "##structure_gel:neutral_temp", "##structure_gel:savanna", "##structure_gel:swamp", "!##structure_gel:mountain");
            tree_house = configs("Tree House", 29, 40, "#minecraft:is_jungle");
            watch_tower = configs("Watch Tower", 33, 45, "##structure_gel:cold", "##structure_gel:snowy", "##structure_gel:mountain");
            witch_tower = configs("Witch Tower", 29, 45, "##structure_gel:spruce_forest", "##structure_gel:large_spruce_forest");
        }

        private StructureConfig configs(String name, int spacing, int prob, String... biomes) {
            return StructureConfig.builder(this.builder, name)
                    .pushPlacement()
                        .spacing(spacing)
                        .offset((int)(spacing/1.5f))
                        .probability(prob)
                    .popPlacement()
                    .pushConfigured()
                        .biomes(biomes)
                    .popConfigured().build();
        }
    }
}
