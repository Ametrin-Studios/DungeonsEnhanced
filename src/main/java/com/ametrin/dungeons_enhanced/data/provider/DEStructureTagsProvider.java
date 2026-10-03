package com.ametrin.dungeons_enhanced.data.provider;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.data.DETags;
import com.ametrin.dungeons_enhanced.registry.DEStructures;
import com.ametrin.structures.registry.ASTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.tags.StructureTags;

import java.util.concurrent.CompletableFuture;

public final class DEStructureTagsProvider extends StructureTagsProvider {
    public DEStructureTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, DungeonsEnhanced.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ASTags.Structures.LAKE_PROOF)
                .addAll(DEStructures.REGISTER.getAllStructures())
        ;
        tag(DETags.Structures.DUNGEON_VARIANT).addAll(DEStructures.DUNGEON_VARIANT.structures().values());
        tag(DETags.Structures.MONSTER_MAZE).addAll(DEStructures.MONSTER_MAZE.structures().values());
        tag(DETags.Structures.ON_CASTLE_EXPLORER_MAPS).add(DEStructures.CASTLE.structure());
        tag(DETags.Structures.ON_ELDER_EXPLORER_MAPS).add(DEStructures.ELDERS_TEMPLE.structure());
        tag(DETags.Structures.ON_DESERT_EXPLORER_MAPS).add(DEStructures.DESERT_TEMPLE.structure());
        tag(DETags.Structures.ON_MONSTER_MAZE_EXPLORER_MAPS).addTag(DETags.Structures.MONSTER_MAZE);

        tag(StructureTags.CATS_SPAWN_AS_BLACK)
                .add(DEStructures.TALL_WITCH_HUT.structure())
                .add(DEStructures.WITCH_TOWER.structure())
        ;
    }
}
