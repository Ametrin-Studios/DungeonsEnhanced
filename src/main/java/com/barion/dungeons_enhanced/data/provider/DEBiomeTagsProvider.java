package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.data.DETags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class DEBiomeTagsProvider extends BiomeTagsProvider {
    public DEBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookup, DungeonsEnhanced.MOD_ID, existingFileHelper);
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
                .addTag(BiomeTags.IS_OVERWORLD)
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
                .addTag(BiomeTags.IS_OVERWORLD)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_UNDERGROUND)
        ;
        tag(DETags.Biomes.HAS_ELDERS_TEMPLE)
                .addTag(BiomeTags.IS_DEEP_OCEAN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_FLOOR)
        ;
        tag(DETags.Biomes.HAS_FISHING_SHIP)
                .addTag(BiomeTags.IS_OCEAN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_SURFACE)
        ;
        tag(DETags.Biomes.HAS_FLYING_DUTCHMAN)
                .addTag(Tags.Biomes.IS_WATER)
                .remove(DETags.Biomes.NO_STRUCTURES)
        ;
        tag(DETags.Biomes.HAS_HAY_STORAGE)
                .addTag(BiomeTags.IS_SAVANNA)
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
                .addTag(BiomeTags.IS_FOREST)
                .addTag(BiomeTags.IS_TAIGA)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_MINERS_HOUSE)
                .addTag(BiomeTags.IS_BADLANDS)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_MONSTER_MAZE)
                .add(Biomes.DARK_FOREST)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
                .addOptional(BOPBiomes.CONIFEROUS_FOREST.location())
                .addOptional(BOPBiomes.REDWOOD_FOREST.location())
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
                .addTag(BiomeTags.IS_OCEAN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_SURFACE)
        ;
        tag(DETags.Biomes.HAS_RUINED_BUILDING)
                .addTag(Tags.Biomes.IS_PLAINS)
                .addTag(BiomeTags.IS_FOREST)
                .addTag(BiomeTags.IS_TAIGA)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_STABLES)
                .addTag(Tags.Biomes.IS_PLAINS)
                .addTag(BiomeTags.IS_FOREST)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_SUNKEN_SHRINE)
                .addTag(BiomeTags.IS_OCEAN)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_FLOOR)
        ;
        tag(DETags.Biomes.HAS_TALL_WITCH_HUT)
                .addTag(BiomeTags.HAS_SWAMP_HUT)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_TREE_HOUSE)
                .addTag(BiomeTags.IS_JUNGLE)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;
        tag(DETags.Biomes.HAS_TOWER_OF_THE_UNDEAD)
                .addTag(BiomeTags.IS_OVERWORLD)
                .remove(Biomes.CHERRY_GROVE)
                .remove(BiomeTags.IS_JUNGLE)
                .remove(BiomeTags.IS_BADLANDS)
                .remove(Tags.Biomes.IS_MUSHROOM)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
//                .remove(BOPBiomes.LUSH_DESERT)
//                .remove(BOPBiomes.BAYOU)
        ;
        tag(DETags.Biomes.HAS_WATCH_TOWER)
                .addTag(Tags.Biomes.IS_COLD_OVERWORLD)
                .remove(DETags.Biomes.NO_STRUCTURES)
                .remove(Tags.Biomes.IS_WATER)
                .remove(Tags.Biomes.IS_CAVE)
                .remove(BiomeTags.IS_NETHER)
                .remove(BiomeTags.IS_END)
                .addOptional(BOPBiomes.CRAG.location())
                .addOptional(BOPBiomes.JADE_CLIFFS.location())
        ;
        tag(DETags.Biomes.HAS_WITCH_TOWER)
                .addTag(BiomeTags.IS_TAIGA)
                .remove(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
        ;

        // Nether
        tag(DETags.Biomes.HAS_BLACK_CITADEL)
                .addTag(BiomeTags.IS_NETHER)
                .remove(DETags.Biomes.NO_STRUCTURES_NETHER)
        ;


        // Other
        tag(DETags.Biomes.IS_SHORE)
                .addTag(BiomeTags.IS_BEACH)
                .add(Biomes.STONY_SHORE)
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE_EXTENDED)
                .addTag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE)
                .addTag(Tags.Biomes.IS_MOUNTAIN)
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_SURFACE)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(DETags.Biomes.IS_SHORE)
                .addTag(Tags.Biomes.IS_CAVE)
                .add(Biomes.WINDSWEPT_FOREST)
                .add(Biomes.WINDSWEPT_HILLS)
                .add(Biomes.WINDSWEPT_SAVANNA)
                .add(Biomes.WINDSWEPT_GRAVELLY_HILLS)
                .addTag(Tags.Biomes.IS_WATER)
                .addTag(BiomeTags.IS_NETHER)
                .addTag(BiomeTags.IS_END)
                .addOptional(BOPBiomes.WASTELAND.location())
                .addOptional(BOPBiomes.WASTELAND_STEPPE.location())
                .addOptional(BOPBiomes.VOLCANO.location())
                .addOptional(BOPBiomes.OMINOUS_WOODS.location())
                .addOptional(BOPBiomes.MYSTIC_GROVE.location())
                .addOptional(BOPBiomes.DRYLAND.location())
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_UNDERGROUND)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(BiomeTags.IS_NETHER)
                .addTag(BiomeTags.IS_END)
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_SURFACE)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(BiomeTags.IS_NETHER)
                .addTag(BiomeTags.IS_END)
                .add(Biomes.FROZEN_OCEAN)
                .add(Biomes.DEEP_FROZEN_OCEAN)
                .add(Biomes.FROZEN_RIVER)
        ;

        tag(DETags.Biomes.NO_STRUCTURES_OVERWORLD_OCEAN_FLOOR)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(BiomeTags.IS_NETHER)
                .addTag(BiomeTags.IS_END)
        ;

        tag(DETags.Biomes.NO_STRUCTURES_NETHER)
                .addTag(DETags.Biomes.NO_STRUCTURES)
                .addTag(BiomeTags.IS_OVERWORLD)
                .addTag(BiomeTags.IS_END)
                .add(Biomes.BASALT_DELTAS)
        ;

        tag(DETags.Biomes.NO_STRUCTURES);
    }
}
