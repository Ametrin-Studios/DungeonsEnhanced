package com.ametrin.dungeons_enhanced.data.provider;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.data.DETags;
import com.ametrin.dungeons_enhanced.registry.DEStructures;
import com.ametrin.structures.registry.ASTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.StructureTagsProvider;
import net.minecraft.tags.StructureTags;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;

import java.util.concurrent.CompletableFuture;

public final class DEStructureTagsProvider extends StructureTagsProvider {
    public DEStructureTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup, DungeonsEnhanced.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ASTags.Structures.LAKE_PROOF)
                .addAll(DEStructures.BOOTSTRAP.getStructures())
        ;
        tag(DETags.Structures.CASTLE).addAll(DEStructures.CASTLE.structures());
        tag(DETags.Structures.DUNGEON_VARIANT).addAll(DEStructures.DUNGEON_VARIANT.structures());
        tag(DETags.Structures.MONSTER_MAZE).addAll(DEStructures.MONSTER_MAZE.structures());
        tag(DETags.Structures.RUINED_BUILDING).addAll(DEStructures.RUINED_BUILDING.structures());

        tag(DETags.Structures.ON_CASTLE_EXPLORER_MAPS).addTag(DETags.Structures.CASTLE);
        tag(DETags.Structures.ON_DEEP_CRYPT_EXPLORER_MAPS).addAll(DEStructures.DEEP_CRYPT.structures());
        tag(DETags.Structures.ON_DESERT_TEMPLE_EXPLORER_MAPS).addAll(DEStructures.DESERT_TEMPLE.structures());
        tag(DETags.Structures.ON_ELDERS_TEMPLE_EXPLORER_MAPS).addAll(DEStructures.ELDERS_TEMPLE.structures());
        tag(DETags.Structures.ON_JUNGLE_MONUMENT_EXPLORER_MAPS).addAll(DEStructures.JUNGLE_MONUMENT.structures());
        tag(DETags.Structures.ON_MONSTER_MAZE_EXPLORER_MAPS).addTag(DETags.Structures.MONSTER_MAZE);
        tag(DETags.Structures.ON_MUSHROOM_HOUSE_EXPLORER_MAPS).addAll(DEStructures.MUSHROOM_HOUSE.structures());

        tag(StructureTags.CATS_SPAWN_AS_BLACK)
                .add(DEStructures.TALL_WITCH_HUT.structure())
                .add(DEStructures.WITCH_TOWER.structure())
        ;

        tag(DETags.Structures.FISHING_SHIP_EXCLUSION_ZONE)
                .add(DEStructures.PIRATE_SHIP.structure())
                .add(DEStructures.ELDERS_TEMPLE.structure())
                .add(BuiltinStructures.OCEAN_MONUMENT)
        ;

        tag(DETags.Structures.PILLAGER_CAMP_EXCLUSION_ZONE)
                .addAll(DEStructures.CASTLE.structures())
                .addTag(StructureTags.VILLAGE)
        ;

        tag(DETags.Structures.BLACK_CITADEL_EXCLUSION_ZONE)
                .add(BuiltinStructures.FORTRESS)
        ;
    }
}
