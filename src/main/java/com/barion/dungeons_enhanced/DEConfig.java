package com.barion.dungeons_enhanced;

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

    public static class Common {
        private final ForgeConfigSpec.Builder builder;
        public final DEStructureConfig castle;
        public final DEStructureConfig ruined_house;
        public final DEStructureConfig ruined_barn;
        public final DEStructureConfig tower_of_the_undead;
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
        public final DEStructureConfig mob_tower;
        public final DEStructureConfig monster_maze;
        public final DEStructureConfig mushroom_house;
        public final DEStructureConfig pillager_camp;
        public final DEStructureConfig stables;
        public final DEStructureConfig tall_witch_hut;
        public final DEStructureConfig tree_house;
        public final DEStructureConfig watch_tower;
        public final DEStructureConfig witch_tower;

        protected Common(ForgeConfigSpec.Builder builder){
            this.builder = builder;
            castle = Configs("Castle", 57, 0.25, true, "#structure_gel:cold, #structure_gel:snowy, !#structure_gel:mountain, !#structure_gel:snowy_mountain", Dimension.OVERWORLD);
            desert_temple = Configs("Desert Temple", 32, 0.6, true, "minecraft:desert", Dimension.OVERWORLD);
            desert_tomb = Configs("Desert Tomb", 17, 0.65, true, "minecraft:desert", Dimension.OVERWORLD);
            druid_circle = Configs("Druid Circle", 39, 0.4, true, "#structure_gel:neutral_temp, !#structure_gel:wooded", Dimension.OVERWORLD);
            dungeon_variant = Configs("Dungeon Variant", 12, 0.8, false, "#structure_gel:ocean", Dimension.OVERWORLD);
            //flying_dutchman = Configs("Flying Dutchman", 23, 0, false, "", Dimension.OVERWORLD);
            hay_Storage = Configs("Hay Storage", 24, 0.85, true, "#structure_gel:savanna", Dimension.OVERWORLD);
            ice_pit = Configs("Ice Pit", 35, 0.7, true, "#structure_gel:snowy, #structure_gel:frozen, !#structure_gel:snowy_mountain, !#structure_gel:mountain", Dimension.OVERWORLD);
            jungle_monument = Configs("Jungle Monument", 20, 0.7, true, "#structure_gel:jungle, !#structure_gel:bamboo_jungle", Dimension.OVERWORLD);
            large_dungeon = Configs("Large Dungeon", 34, 0.35, true, "#structure_gel:neutral_temp, #structure_gel:cold, !#structure_gel:mountain", Dimension.OVERWORLD);
            miners_house = Configs("Miners House", 24, 0.8, true, "minecraft:badlands, minecraft:badlands_plateau", Dimension.OVERWORLD);
            mob_tower = Configs("Mob Tower", 31, 0.45, true, "#structure_gel:neutral_temp, #structure_gel:swamp", Dimension.OVERWORLD);
            monster_maze = Configs("Monster Maze", 39, 0.35, true, "#structure_gel:spooky, #structure_gel:pumpkin, !#structure_gel:sandy", Dimension.OVERWORLD);
            mushroom_house = Configs("Mushroom House", 15, 0.9, true, "minecraft:mushroom_fields, minecraft:mushroom_field_shore", Dimension.OVERWORLD);
            pillager_camp = Configs("Pillager Camp", 49, 0.35, true, "#structure_gel:neutral_temp, !#structure_gel:mountain, !#structure_gel:wooded", Dimension.OVERWORLD);
            ruined_barn = Configs("Ruined Barn", 25, 0.6, true, "#structure_gel:wooded, #structure_gel:birch_forest", Dimension.OVERWORLD);
            ruined_house = Configs("Ruined House", 22, 0.5, true, "#structure_gel:neutral_temp", Dimension.OVERWORLD);
            stables = Configs("Stables", 46, 0.32, true, "#structure_gel:neutral_temp, !#structure_gel:mountain, !#structure_gel:wooded", Dimension.OVERWORLD);
            tall_witch_hut = Configs("Tall Witch Hut", 18, 0.6, true, "#structure_gel:humid, !#structure_gel:tropical, !#structure_gel:jungle, #structure_gel:pumpkin", Dimension.OVERWORLD);
            tower_of_the_undead = Configs("Tower of the Undead", 43, 0.35, true, "#structure_gel:neutral_temp, #structure_gel:savanna, !#structure_gel:mountain", Dimension.OVERWORLD);
            tree_house = Configs("Tree House", 25, 0.4, true, "#forge:jungle", Dimension.OVERWORLD);
            watch_tower = Configs("Watch Tower", 33, 0.35, true, "#structure_gel:cold, #structure_gel:snowy", Dimension.OVERWORLD);
            witch_tower = Configs("Witch Tower", 21, 0.5, true, "#structure_gel:spruce_forest, #structure_gel:large_spruce_forest", Dimension.OVERWORLD);
        }

        private DEStructureConfig Configs(String name, int spacing, double prob, boolean isWhite, String biomes, RegistryKey<Dimension> dim){
            return (DEStructureConfig) new DEStructureConfig(this.builder, name).probability(prob).spacing(spacing).offset(spacing/4).biomes(isWhite, biomes).validDimensions(dim.location().toString());
        }
    }
}