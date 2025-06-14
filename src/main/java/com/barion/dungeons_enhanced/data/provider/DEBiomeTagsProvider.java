package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.data.DETags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class DEBiomeTagsProvider extends BiomeTagsProvider {
    public DEBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, DungeonsEnhanced.MOD_ID);
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider provider) {
        // Overworld
        tag(DETags.Biomes.HAS_CASTLE)
                .addTag(Tags.Biomes.IS_COLD_OVERWORLD)
                .addTag(Tags.Biomes.IS_SNOWY)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
                .addOptional(BOPBiomes.MEDITERRANEAN_FOREST.location())
        ;
        tag(DETags.Biomes.HAS_DEEP_CRYPT)
                .addTag(Tags.Biomes.IS_OVERWORLD)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_UNDERGROUND)
        ;
        tag(DETags.Biomes.HAS_DESERT_TEMPLE)
                .addTag(BiomeTags.HAS_DESERT_PYRAMID)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_DESERT_TOMB)
                .addTag(BiomeTags.HAS_DESERT_PYRAMID)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_DRUID_CIRCLE)
                .add(Biomes.MEADOW)
                .add(Biomes.CHERRY_GROVE)
                .addTag(Tags.Biomes.IS_PLAINS)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
                .addOptional(BOPBiomes.HIGHLAND.location())
        ;
        tag(DETags.Biomes.HAS_DUNGEON_VARIANT)
                .addTag(Tags.Biomes.IS_OVERWORLD)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_UNDERGROUND)
        ;
        tag(DETags.Biomes.HAS_ELDERS_TEMPLE)
                .addTag(Tags.Biomes.IS_DEEP_OCEAN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_FLOOR)
        ;
        tag(DETags.Biomes.HAS_FISHING_SHIP)
                .addTag(Tags.Biomes.IS_OCEAN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_SURFACE)
        ;
        tag(DETags.Biomes.HAS_FLYING_DUTCHMAN)
                .addTag(Tags.Biomes.IS_AQUATIC)
                .remove(DETags.Biomes.NO_STRUCTURES)
        ;
        tag(DETags.Biomes.HAS_HAY_STORAGE)
                .addTag(Tags.Biomes.IS_SAVANNA)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
                .addOptional(BOPBiomes.PASTURE.location())
                .addOptional(BOPBiomes.PRAIRIE.location())
        ;
        tag(DETags.Biomes.HAS_ICE_PIT)
                .addTag(Tags.Biomes.IS_SNOWY)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_JUNGLE_MONUMENT)
                .addTag(BiomeTags.HAS_JUNGLE_TEMPLE)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_LARGE_DUNGEON)
                .addTag(Tags.Biomes.IS_PLAINS)
                .addTag(Tags.Biomes.IS_FOREST)
                .addTag(Tags.Biomes.IS_TAIGA)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_MINERS_HOUSE)
                .addTag(Tags.Biomes.IS_BADLANDS)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_MONSTER_MAZE_DARK)
                .addTag(Tags.Biomes.IS_DARK_FOREST)
                .remove(Biomes.PALE_GARDEN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
                .addOptional(BOPBiomes.CONIFEROUS_FOREST.location())
                .addOptional(BOPBiomes.REDWOOD_FOREST.location())
        ;
        tag(DETags.Biomes.HAS_MONSTER_MAZE_PALE)
                .add(Biomes.PALE_GARDEN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_MUSHROOM_HOUSE)
                .add(Biomes.MUSHROOM_FIELDS)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_PILLAGER_CAMP)
                .addTag(BiomeTags.HAS_PILLAGER_OUTPOST)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_PIRATE_SHIP)
                .addTag(Tags.Biomes.IS_OCEAN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_SURFACE)
        ;
        tag(DETags.Biomes.HAS_RUINED_BUILDING)
                .addTag(Tags.Biomes.IS_PLAINS)
                .addTag(Tags.Biomes.IS_FOREST)
                .addTag(Tags.Biomes.IS_TAIGA)
                .remove(Biomes.PALE_GARDEN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_STABLES)
                .addTag(Tags.Biomes.IS_PLAINS)
                .addTag(Tags.Biomes.IS_FOREST)
                .remove(Biomes.PALE_GARDEN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_SUNKEN_SHRINE)
                .addTag(Tags.Biomes.IS_OCEAN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_FLOOR)
        ;
        tag(DETags.Biomes.HAS_TALL_WITCH_HUT)
                .addTag(BiomeTags.HAS_SWAMP_HUT)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_TREE_HOUSE)
                .addTag(Tags.Biomes.IS_JUNGLE)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_TOWER_OF_THE_UNDEAD)
                .addTag(Tags.Biomes.IS_OVERWORLD)
                .remove(Biomes.CHERRY_GROVE)
                .remove(Tags.Biomes.IS_JUNGLE)
                .remove(Tags.Biomes.IS_BADLANDS)
                .remove(Tags.Biomes.IS_MUSHROOM)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
                .getInternalBuilder()
                .remove(TagEntry.optionalElement(BOPBiomes.LUSH_DESERT.location()))
                .remove(TagEntry.optionalElement(BOPBiomes.BAYOU.location()))
                .remove(TagEntry.optionalElement(BOPBiomes.REDWOOD_FOREST.location()))
        ;

        tag(DETags.Biomes.HAS_WATCH_TOWER)
                .addTag(Tags.Biomes.IS_COLD_OVERWORLD)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE)
                .addOptional(BOPBiomes.CRAG.location())
                .addOptional(BOPBiomes.JADE_CLIFFS.location())
        ;
        tag(DETags.Biomes.HAS_WITCH_TOWER)
                .addTag(Tags.Biomes.IS_TAIGA)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;

        // Nether
        tag(DETags.Biomes.HAS_BLACK_CITADEL)
                .addTag(Tags.Biomes.IS_NETHER)
                .remove(DETags.Biomes.NO_STRUCTURES_NETHER)
        ;


        // Other
        tag(DETags.Biomes.IS_SHORE)
                .addTag(Tags.Biomes.IS_STONY_SHORES)
                .addTag(Tags.Biomes.IS_BEACH)
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
                .addTag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE)
                .addTag(Tags.Biomes.IS_MOUNTAIN) //contains peaks and slopes
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(DETags.Biomes.IS_SHORE)
                .addTag(Tags.Biomes.IS_CAVE)
                .addTag(Tags.Biomes.IS_WINDSWEPT)
                .addTag(Tags.Biomes.IS_AQUATIC)
                .addTag(Tags.Biomes.IS_NETHER)
                .addTag(Tags.Biomes.IS_END)
                .addOptional(BOPBiomes.WASTELAND.location())
                .addOptional(BOPBiomes.WASTELAND_STEPPE.location())
                .addOptional(BOPBiomes.VOLCANO.location())
                .addOptional(BOPBiomes.OMINOUS_WOODS.location())
                .addOptional(BOPBiomes.MYSTIC_GROVE.location())
                .addOptional(BOPBiomes.DRYLAND.location())
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_UNDERGROUND)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(Tags.Biomes.IS_NETHER)
                .addTag(Tags.Biomes.IS_END)
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_SURFACE)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(Tags.Biomes.IS_AQUATIC_ICY)
                .addTag(Tags.Biomes.IS_NETHER)
                .addTag(Tags.Biomes.IS_END)
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_FLOOR)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(Tags.Biomes.IS_NETHER)
                .addTag(Tags.Biomes.IS_END)
        ;

        tag(DETags.Biomes.NO_STRUCTURES_NETHER)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(Tags.Biomes.IS_OVERWORLD)
                .addTag(Tags.Biomes.IS_END)
                .add(Biomes.BASALT_DELTAS)
        ;

        tag(DETags.Biomes.NO_STRUCTURES);
    }
}
