package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.data.DETags;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.Tags;

import java.security.InvalidParameterException;

public final class DEConfig {
    public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;

    static {
        var builder = new ModConfigSpec.Builder();
        COMMON = new Common(builder);
        COMMON_SPEC = builder.build();
    }

    public static class Common {
        protected Common(ModConfigSpec.Builder builder) {
            // Overworld
            structureBiomes(DEStructures.CASTLE).whitelist(Tags.Biomes.IS_COLD_OVERWORLD, Tags.Biomes.IS_SNOWY).blacklist(Tags.Biomes.IS_WATER, Tags.Biomes.IS_MOUNTAIN, DETags.Biomes.IS_SHORE).popBiomes().build(builder);
            overworldExceptStructure(builder, DEStructures.DEEP_CRYPT);
            tagsStructure(builder, DEStructures.DESERT_TEMPLE, BiomeTags.HAS_DESERT_PYRAMID);
            tagsStructure(builder, DEStructures.DESERT_TOMB, BiomeTags.HAS_DESERT_PYRAMID);
            tagsStructure(builder, DEStructures.DRUID_CIRCLE, Tags.Biomes.IS_PLAINS);
            overworldExceptStructure(builder, DEStructures.DUNGEON_VARIANT);
            tagsStructure(builder, DEStructures.ELDERS_TEMPLE, BiomeTags.IS_DEEP_OCEAN);
            shipStructure(builder, DEStructures.FISHING_SHIP);
            tagsStructure(builder, DEStructures.FLYING_DUTCHMAN, Tags.Biomes.IS_WATER);
            structureBiomes(DEStructures.HAY_STORAGE).whitelist(BiomeTags.IS_SAVANNA).blacklist(DETags.Biomes.IS_BAD_FOR_STRUCTURE).popBiomes().build(builder);
            structureBiomes(DEStructures.ICE_PIT).whitelist(Tags.Biomes.IS_SNOWY).blacklist(Tags.Biomes.IS_WATER, Tags.Biomes.IS_MOUNTAIN, DETags.Biomes.IS_SHORE).popBiomes().build(builder);
            tagsStructure(builder, DEStructures.JUNGLE_MONUMENT, BiomeTags.HAS_JUNGLE_TEMPLE);
            structureBiomes(DEStructures.LARGE_DUNGEON).whitelist(Tags.Biomes.IS_PLAINS, BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA).blacklist(DETags.Biomes.IS_BAD_FOR_STRUCTURE, Tags.Biomes.IS_MOUNTAIN, Tags.Biomes.IS_WATER).popBiomes().build(builder);
            tagsStructure(builder, DEStructures.MINERS_HOUSE, BiomeTags.IS_BADLANDS);
            biomesStructure(builder, DEStructures.MONSTER_MAZE, Biomes.DARK_FOREST);
            biomesStructure(builder, DEStructures.MUSHROOM_HOUSE, Biomes.MUSHROOM_FIELDS);
            tagsStructure(builder, DEStructures.PILLAGER_CAMP, BiomeTags.HAS_PILLAGER_OUTPOST);
            shipStructure(builder, DEStructures.PIRATE_SHIP);
            structureBiomes(DEStructures.RUINED_BUILDING).whitelist(Tags.Biomes.IS_PLAINS, BiomeTags.IS_FOREST, BiomeTags.IS_TAIGA).blacklist(DETags.Biomes.IS_BAD_FOR_STRUCTURE, Tags.Biomes.IS_MOUNTAIN, Tags.Biomes.IS_WATER).popBiomes().build(builder);
            structureBiomes(DEStructures.STABLES).whitelist(Tags.Biomes.IS_PLAINS, BiomeTags.IS_FOREST).blacklist(DETags.Biomes.IS_BAD_FOR_STRUCTURE, Tags.Biomes.IS_MOUNTAIN, Tags.Biomes.IS_WATER).blacklist(Biomes.DARK_FOREST).popBiomes().build(builder);
            tagsStructure(builder, DEStructures.SUNKEN_SHRINE, BiomeTags.IS_OCEAN);
            tagsStructure(builder, DEStructures.TALL_WITCH_HUT, BiomeTags.HAS_SWAMP_HUT);
            overworldExceptStructure(builder, DEStructures.TOWER_OF_THE_UNDEAD, Tags.Biomes.IS_SANDY, Tags.Biomes.IS_WATER, BiomeTags.IS_JUNGLE, DETags.Biomes.IS_BAD_FOR_STRUCTURE, BiomeTags.IS_BADLANDS);
            tagsStructure(builder, DEStructures.TREE_HOUSE, BiomeTags.IS_JUNGLE);
            tagsNoWaterStructure(builder, DEStructures.WATCH_TOWER, Tags.Biomes.IS_COLD_OVERWORLD);
            tagsStructure(builder, DEStructures.WITCH_TOWER, BiomeTags.IS_TAIGA);

            // Nether
            structureConfigBuilder(DEStructures.BLACK_CITADEL).pushBiomes()
                    .whitelist(BiomeTags.IS_NETHER)
                    .blacklist(Biomes.BASALT_DELTAS)
                    .popBiomes().build(builder);
        }

        @SafeVarargs
        private static void tagsStructure(ModConfigSpec.Builder builder, StructureRegistrar<? extends Structure> structure, TagKey<Biome>... tagKeys) {
            structureBiomes(structure).whitelist(tagKeys).popBiomes().build(builder);
        }

        @SafeVarargs
        private static void tagsNoWaterStructure(ModConfigSpec.Builder builder, StructureRegistrar<? extends Structure> structure, TagKey<Biome>... tagKeys) {
            structureBiomes(structure).whitelist(tagKeys).blacklist(Tags.Biomes.IS_WATER).popBiomes().build(builder);
        }

        @SafeVarargs
        private static void overworldExceptStructure(ModConfigSpec.Builder builder, StructureRegistrar<? extends Structure> structure, TagKey<Biome>... tagKeys) {
            structureBiomes(structure).whitelist(BiomeTags.IS_OVERWORLD).blacklist(tagKeys).popBiomes().build(builder);
        }

        private static void shipStructure(ModConfigSpec.Builder builder, StructureRegistrar<? extends Structure> structure) {
            oceanExceptStructure(builder, structure, Tags.Biomes.IS_SNOWY);
        }

        @SafeVarargs
        private static void oceanExceptStructure(ModConfigSpec.Builder builder, StructureRegistrar<? extends Structure> structure, TagKey<Biome>... tagKeys) {
            structureBiomes(structure).whitelist(BiomeTags.IS_OCEAN).blacklist(tagKeys).popBiomes().build(builder);
        }

        @SafeVarargs
        private static void biomesStructure(ModConfigSpec.Builder builder, StructureRegistrar<? extends Structure> structure, ResourceKey<Biome>... biomes) {
            structureBiomes(structure).whitelist(biomes).popBiomes().build(builder);
        }

        private static StructureConfig.Builder.BiomeConfigBuilder structureBiomes(StructureRegistrar<? extends Structure> structure) {
            return structureConfigBuilder(structure).pushBiomes();
        }

        private static StructureConfig.Builder structureConfigBuilder(StructureRegistrar<? extends Structure> structure) {
            if (structure.getStructure() == null)
                throw new InvalidParameterException("Needs to be a single structure registrar");
            return StructureConfig.builder(structure.getStructure());
        }
    }
}