package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.data.DETags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;

public class DEBiomeTagsProvider extends BiomeTagsProvider {
    public DEBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookup, DungeonsEnhanced.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider provider) {
        tag(DETags.Biomes.HAS_CASTLE);
        tag(DETags.Biomes.HAS_DEEP_CRYPT).addTag(BiomeTags.IS_OVERWORLD);
        tag(DETags.Biomes.HAS_DESERT_TEMPLE).addTag(BiomeTags.HAS_DESERT_PYRAMID);
        tag(DETags.Biomes.HAS_DESERT_TOMB).addTag(DETags.Biomes.HAS_DESERT_TEMPLE);
        tag(DETags.Biomes.HAS_DRUID_CIRCLE).addTag(BiomeTags.IS_OVERWORLD).remove(BiomeTags.IS_OCEAN);
        tag(DETags.Biomes.HAS_DUNGEON_VARIANT).addTag(BiomeTags.IS_OVERWORLD);
        tag(DETags.Biomes.HAS_ELDERS_TEMPLE).addTag(BiomeTags.IS_DEEP_OCEAN);
        tag(DETags.Biomes.HAS_FISHING_SHIP).addTag(BiomeTags.IS_OCEAN);
        tag(DETags.Biomes.HAS_FLYING_DUTCHMAN).addTag(BiomeTags.IS_OCEAN);
        tag(DETags.Biomes.HAS_HAY_STORAGE).addTag(BiomeTags.IS_SAVANNA);
        tag(DETags.Biomes.HAS_ICE_PIT);
        tag(DETags.Biomes.HAS_JUNGLE_MONUMENT).addTag(BiomeTags.HAS_JUNGLE_TEMPLE);
        tag(DETags.Biomes.HAS_LARGE_DUNGEON);
        tag(DETags.Biomes.HAS_MINERS_HOUSE).addTag(BiomeTags.IS_BADLANDS);
        tag(DETags.Biomes.HAS_MONSTER_MAZE).add(Biomes.DARK_FOREST);
        tag(DETags.Biomes.HAS_MUSHROOM_HOUSE).add(Biomes.MUSHROOM_FIELDS);
        tag(DETags.Biomes.HAS_PILLAGER_CAMP).addTag(BiomeTags.HAS_PILLAGER_OUTPOST);
        tag(DETags.Biomes.HAS_PIRATE_SHIP).addTag(BiomeTags.IS_OCEAN);
        tag(DETags.Biomes.HAS_RUINED_BUILDING);
        tag(DETags.Biomes.HAS_STABLES);
        tag(DETags.Biomes.HAS_SUNKEN_SHRINE).addTag(BiomeTags.IS_OCEAN);
        tag(DETags.Biomes.HAS_TALL_WITCH_HUT).addTag(BiomeTags.HAS_SWAMP_HUT);
        tag(DETags.Biomes.HAS_TREE_HOUSE).addTag(BiomeTags.IS_JUNGLE);
        tag(DETags.Biomes.HAS_TOWER_OF_THE_UNDEAD);
        tag(DETags.Biomes.HAS_WATCH_TOWER);
        tag(DETags.Biomes.HAS_WITCH_TOWER).addTag(BiomeTags.IS_TAIGA);

        /*        var registry = provider.lookup(Registries.BIOME).get();
//        provider.asGetterLookup().lookup(Registries.BIOME).filter()
        var appender = tag(BiomeTags.HAS_DESERT_PYRAMID).getInternalBuilder();
//        appender.getInternalBuilder()
        var tagO = provider.asGetterLookup().lookup(Registries.BIOME).get().get(BiomeTags.HAS_DESERT_PYRAMID);
        var tag = tagO.get();
        var results = registry.filterElements((biome)-> tag.contains(Holder.direct(biome)));
//        BiomeTags.IS_OCEAN.ge
//        tag(provider, DETags.Biomes.HAS_RUINED_BUILDING, (biome)-> !tag.contains(biome.));*/
    }

    private void tag(HolderLookup.Provider provider, TagKey<Biome> key, Predicate<Biome> predicate){
        var results = provider.lookup(Registries.BIOME).get().filterElements(predicate).listElementIds();
        var appender = tag(key);
        results.forEach(appender::add);
    }
}
