package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.data.DETags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.neoforged.neoforge.common.Tags;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

public final class DEBiomeTagsProvider extends BiomeTagsProvider {
    public DEBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, DungeonsEnhanced.MOD_ID);
    }

    @Override
    protected void addTags(@Nonnull HolderLookup.Provider provider) {
        tag(DETags.Biomes.IS_SHORE)
                .addTag(Tags.Biomes.IS_STONY_SHORES)
                .addTag(Tags.Biomes.IS_BEACH)
        ;

        tag(DETags.Biomes.IS_BAD_FOR_STRUCTURE)
                .addTag(DETags.Biomes.IS_SHORE)
                .addTag(Tags.Biomes.IS_CAVE)
                .addTag(Tags.Biomes.IS_MOUNTAIN)
                .addTag(Tags.Biomes.IS_WINDSWEPT)
        ;
    }
}
