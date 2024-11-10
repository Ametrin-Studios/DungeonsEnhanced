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
        tag(DETags.Biomes.IS_SHORE)
                .addTag(BiomeTags.IS_BEACH)
                .add(Biomes.STONY_SHORE)
        ;
        tag(DETags.Biomes.IS_BAD_FOR_STRUCTURE)
                .addTag(DETags.Biomes.IS_SHORE)
                .addTag(Tags.Biomes.IS_CAVE)
                .addTag(Tags.Biomes.IS_MOUNTAIN)
                .add(
                        Biomes.WINDSWEPT_FOREST,
                        Biomes.WINDSWEPT_SAVANNA,
                        Biomes.WINDSWEPT_HILLS,
                        Biomes.WINDSWEPT_GRAVELLY_HILLS
                )
        ;
    }
}
