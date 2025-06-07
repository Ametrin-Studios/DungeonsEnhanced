package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.data.DETags;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.legacy.structure_gel.api.data.tags.SGTags;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public final class DEStructureTagsProvider extends StructureTagsProvider {
    public DEStructureTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, DungeonsEnhanced.MOD_ID);
    }

    @Override @SuppressWarnings("null")
    protected void addTags(@NotNull HolderLookup.Provider provider) {
        allStructures(SGTags.StructureTags.LAKE_PROOF);
        tag(DETags.Structures.ON_CASTLE_EXPLORER_MAPS).add(DEStructures.CASTLE.getStructure().getKey());
        tag(DETags.Structures.ON_ELDER_EXPLORER_MAPS).add(DEStructures.ELDERS_TEMPLE.getStructure().getKey());
        tag(DETags.Structures.ON_DESERT_EXPLORER_MAPS).add(DEStructures.DESERT_TEMPLE.getStructure().getKey());
        tag(DETags.Structures.ON_MONSTER_MAZE_EXPLORER_MAPS).add(DEStructures.MONSTER_MAZE_DARK.getStructure().getKey()).add(DEStructures.MONSTER_MAZE_PALE.getStructure().getKey());
    }

    private void allStructures(TagKey<Structure> tagKey) {
        var appender = tag(tagKey);
        for(var registrar : DEStructures.ALL_STRUCTURE_REGISTRARS){
            allStructures(appender, registrar);
        }
    }

    private void allStructures(TagKey<Structure> tagKey, StructureRegistrar<?> registrar) {
        allStructures(tag(tagKey), registrar);
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private void allStructures(TagAppender<Structure> appender, StructureRegistrar<?> registrar) {
        registrar.getStructures().values().stream().map(Registrar.Pointer::getKey).forEach(key -> appender.add((ResourceKey) key));
    }
}
