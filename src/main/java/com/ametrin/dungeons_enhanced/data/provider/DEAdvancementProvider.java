package com.ametrin.dungeons_enhanced.data.provider;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.data.DETags;
import com.ametrin.dungeons_enhanced.registry.DEStructures;
import com.ametrinstudios.ametrin.data.provider.ExtendedAdvancementSubProvider;
import com.ametrinstudios.ametrin.util.BannerBuilder;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BannerPatterns;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public final class DEAdvancementProvider extends AdvancementProvider {
    public DEAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, List.of(new DEExplorerAdvancementSubProvider()));
    }

    public static final class DEExplorerAdvancementSubProvider extends ExtendedAdvancementSubProvider {
        public DEExplorerAdvancementSubProvider() {
            super(DungeonsEnhanced.MOD_ID);
        }

        @Override
        public void generate(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
            var bannerLookup = provider.lookupOrThrow(Registries.BANNER_PATTERN);

            var root = builder(provider, "root")
                    .background(Identifier.withDefaultNamespace("block/mossy_cobblestone"))
                    .hideToast().hideInChat()
                    .displayItem(Items.MAP)
                    .orCriteria()
                    .onEnterAnyStructure("entered_dungeon_enhanced_structure", DEStructures.REGISTER.getAllStructures())
                    .save(consumer);

            builder(provider, "hidden_under_roots")
                    .parent(root)
                    .displayItem(Items.JACK_O_LANTERN)
                    .onEnterStructure(DETags.Structures.MONSTER_MAZE)
                    .save(consumer);

            builder(provider, "thats_a_dungeon")
                    .parent(root)
                    .displayItem(Items.MOSSY_COBBLESTONE)
                    .onEnterStructure(DEStructures.LARGE_DUNGEON.structure())
                    .save(consumer);

            builder(provider, "traps_and_curses")
                    .parent(root)
                    .displayItem(Items.TNT)
                    .onEnterStructure(DEStructures.DESERT_TEMPLE.structure())
                    .save(consumer);

            builder(provider, "ancient_civilizations")
                    .parent(root)
                    .displayItem(Items.BAMBOO)
                    .onEnterStructure(DEStructures.JUNGLE_MONUMENT.structure())
                    .save(consumer);

            builder(provider, "wars_and_kingdoms")
                    .parent(root)
                    .displayItem(Items.STONE_BRICKS)
                    .onEnterStructure(DEStructures.CASTLE.structure())
                    .save(consumer);

            builder(provider, "rarest_structure")
                    .parent(root)
                    .displayItem(Items.RED_MUSHROOM)
                    .onEnterStructure(DEStructures.MUSHROOM_HOUSE.structure())
                    .save(consumer);

            builder(provider, "chilled_halls")
                    .parent(root)
                    .displayItem(Items.BONE)
                    .onEnterStructure(DEStructures.ICE_PIT.structure())
                    .save(consumer);

            builder(provider, "ahoy")
                    .parent(root)
                    .displayItem(Items.SKELETON_SKULL)
                    .onEnterStructure(DEStructures.PIRATE_SHIP.structure())
                    .save(consumer);

            builder(provider, "flying_dutchman")
                    .parent(root)
                    .displayItem(Items.COPPER_LANTERN.oxidized())
                    .onEnterStructure(DEStructures.FLYING_DUTCHMAN.structure())
                    .save(consumer);

            builder(provider, "sunken_depths")
                    .parent(root)
                    .displayItem(Items.CONDUIT)
                    .onEnterStructure(DEStructures.ELDERS_TEMPLE.structure())
                    .save(consumer);

            builder(provider, "spooky_scary_citadel")
                    .parent(root)
                    .displayItem(new BannerBuilder(Items.RED_BANNER, bannerLookup)
                            .addPattern(BannerPatterns.BRICKS, DyeColor.BLACK)
                            .addPattern(BannerPatterns.GRADIENT_UP, DyeColor.RED)
                            .addPattern(BannerPatterns.SKULL, DyeColor.BLACK)
                            .addPattern(BannerPatterns.BORDER, DyeColor.BLACK)
                            .build())
                    .onEnterStructure(DEStructures.BLACK_CITADEL.structure())
                    .save(consumer);

            var sevenWorldWonders = builder(provider, "seven_world_wonders")
                    .parent(root)
                    .displayItem(Items.SPYGLASS)
                    .type(AdvancementType.GOAL)
                    .onEnterStructure(DEStructures.CASTLE.structure())
                    .onEnterStructure(DEStructures.DESERT_TEMPLE.structure())
                    .onEnterStructure(DEStructures.ICE_PIT.structure())
                    .onEnterStructure(DEStructures.JUNGLE_MONUMENT.structure())
                    .onEnterStructure(DETags.Structures.MONSTER_MAZE)
                    .onEnterStructure(DEStructures.ELDERS_TEMPLE.structure())
                    .onEnterStructure(DEStructures.BLACK_CITADEL.structure())
                    .save(consumer);

            builder(provider, "ambitious_explorer")
                    .parent(sevenWorldWonders)
                    .displayItem(Items.FILLED_MAP)
                    .type(AdvancementType.CHALLENGE)
                    .onEnterAllStructures(DEStructures.REGISTER.getAllStructures())
                    .save(consumer);
        }
    }
}