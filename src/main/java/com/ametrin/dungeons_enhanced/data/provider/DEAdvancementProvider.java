package com.ametrin.dungeons_enhanced.data.provider;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.registry.DEStructures;
import com.ametrinstudios.ametrin.data.provider.ExtendedAdvancementSubProvider;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.*;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.PlayerTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.stream.Stream;

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
            var structureLookup = provider.lookupOrThrow(Registries.STRUCTURE);
            var bannerLookup = provider.lookupOrThrow(Registries.BANNER_PATTERN);

            var root = builder(provider, "root")
                    .background(Identifier.withDefaultNamespace("block/mossy_cobblestone"))
                    .hideToast().hideInChat()
                    .displayItem(Items.FILLED_MAP)
                    .orCriteria()
                    .onEnterStructures("entered_dungeon_enhanced_structure", DEStructures.REGISTER.getAllStructures())
                    .save(consumer);

//            new AdvancementBuilder("hidden_under_the_roots", Items.JACK_O_LANTERN)
//                    .parent(root)
//                    .orCriteria()
//                    .onEnterStructure(structureLookup, DEStructures.MONSTER_MAZE_DARK)
//                    .onEnterStructure(structureLookup, DEStructures.MONSTER_MAZE_PALE)
//                    .save(consumer);

//            new AdvancementBuilder("thats_a_dungeon", Items.SKELETON_SKULL)
//                    .parent(root)
//                    .onEnterStructure(structureLookup, DEStructures.LARGE_DUNGEON)
//                    .save(consumer);

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

            new AdvancementBuilderLegacy("chilled_halls", Items.BONE)
                    .parent(root)
                    .onEnterStructure(structureLookup.getOrThrow(DEStructures.ICE_PIT.structure()))
                    .save(consumer);

            new AdvancementBuilderLegacy("ahoy", Items.SKELETON_SKULL)
                    .parent(root)
                    .onEnterStructure(structureLookup.getOrThrow(DEStructures.PIRATE_SHIP.structure()))
                    .save(consumer);

            new AdvancementBuilderLegacy("in_the_air", Items.LANTERN)
                    .parent(root)
                    .onEnterStructure(structureLookup.getOrThrow(DEStructures.FLYING_DUTCHMAN.structure()))
                    .save(consumer);

            new AdvancementBuilderLegacy("sunken_depths", Items.NAUTILUS_SHELL)
                    .parent(root)
                    .onEnterStructure(structureLookup.getOrThrow(DEStructures.ELDERS_TEMPLE.structure()))
                    .save(consumer);

//            new AdvancementBuilder("spooky_scary_citadel",
//                    new BannerBuilder(Items.RED_BANNER)
//                            .addPattern(bannerLookup, BannerPatterns.BRICKS, DyeColor.BLACK)
//                            .addPattern(bannerLookup, BannerPatterns.GRADIENT_UP, DyeColor.RED)
//                            .addPattern(bannerLookup, BannerPatterns.SKULL, DyeColor.BLACK)
//                            .addPattern(bannerLookup, BannerPatterns.BORDER, DyeColor.BLACK)
//                            .build())
//                    .parent(root)
//                    .onEnterStructure(structureLookup, DEStructures.BLACK_CITADEL)
//                    .save(consumer);

            var sevenWorldWonders = new AdvancementBuilderLegacy("seven_world_wonders", Items.SPYGLASS)
                    .parent(root)
                    .type(AdvancementType.GOAL)
                    .onEnterStructures(structureLookup, Stream.of(
                            DEStructures.CASTLE.structure(),
                            DEStructures.DEEP_CRYPT.structure(),
                            DEStructures.DESERT_TEMPLE.structure(),
                            DEStructures.ICE_PIT.structure(),
                            DEStructures.JUNGLE_MONUMENT.structure(),
//                            DEStructures.MONSTER_MAZE_DARK,
                            DEStructures.ELDERS_TEMPLE.structure()
                    ))
                    .save(consumer);

            new AdvancementBuilderLegacy("ambitious_explorer", Items.FILLED_MAP)
                    .parent(sevenWorldWonders)
                    .type(AdvancementType.CHALLENGE)
                    .onEnterStructures(structureLookup, DEStructures.REGISTER.getAllStructures())
                    .save(consumer);
        }
    }

    private static class AdvancementBuilderLegacy {
        private final String _id;
        private final ItemStackTemplate _displayItem;
        @Nullable
        private AdvancementHolder _parent = null;
        @Nullable
        private Identifier _background = null;
        private AdvancementType _type = AdvancementType.TASK;
        private boolean _showToast = true;
        private boolean _announceToChat = true;
        private boolean _hidden = false;
        private AdvancementRequirements.Strategy _criterionStrategy = AdvancementRequirements.Strategy.AND;
        private final List<Pair<String, Criterion<?>>> _criteria = new ArrayList<>();

        private AdvancementBuilderLegacy(String id, ItemStackTemplate displayItem) {
            _id = id;
            _displayItem = displayItem;
        }

        private AdvancementBuilderLegacy(String id, ItemLike displayItem) {
            this(id, new ItemStackTemplate(displayItem.asItem()));
        }

        private AdvancementBuilderLegacy parent(AdvancementHolder parent) {
            _parent = parent;
            return this;
        }

        public AdvancementBuilderLegacy background(String background) {
            return background(Identifier.withDefaultNamespace(background));
        }

        public AdvancementBuilderLegacy background(Identifier background) {
            _background = background;
            return this;
        }

        public AdvancementBuilderLegacy type(AdvancementType type) {
            _type = type;
            return this;
        }

        public AdvancementBuilderLegacy hideCompletely() {
            return hideToast().hideInChat().hide();
        }

        public AdvancementBuilderLegacy hideToast() {
            return showToast(false);
        }

        public AdvancementBuilderLegacy showToast(boolean show) {
            _showToast = show;
            return this;
        }

        public AdvancementBuilderLegacy hideInChat() {
            return announceToChat(false);
        }

        public AdvancementBuilderLegacy announceToChat(boolean announce) {
            _announceToChat = announce;
            return this;
        }

        public AdvancementBuilderLegacy hide() {
            return hidden(true);
        }

        public AdvancementBuilderLegacy hidden(boolean hidden) {
            _hidden = hidden;
            return this;
        }

        public AdvancementBuilderLegacy orCriteria() {
            return criterionStrategy(AdvancementRequirements.Strategy.OR);
        }

        public AdvancementBuilderLegacy criterionStrategy(AdvancementRequirements.Strategy strategy) {
            _criterionStrategy = strategy;
            return this;
        }

        public AdvancementBuilderLegacy onEnterStructures(HolderLookup.RegistryLookup<Structure> lookup, Stream<ResourceKey<Structure>> structures) {
            structures.map(lookup::getOrThrow).forEach(this::onEnterStructure);
            return this;
        }

        public AdvancementBuilderLegacy onEnterStructure(Holder<Structure> structure) {
            return addCriterion(
                    "entered_" + Objects.requireNonNull(structure.getKey()).identifier().getPath(),
                    PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structure))
            );
        }

        public AdvancementBuilderLegacy addCriterion(String name, Criterion<?> criterion) {
            _criteria.add(Pair.of(name, criterion));
            return this;
        }

        public AdvancementHolder save(Consumer<AdvancementHolder> consumer) {
            var builder = new Advancement.Builder()
                    .display(_displayItem, component(_id + ".title"), component(_id + ".description"), _background, _type, _showToast, _announceToChat, _hidden)
                    .requirements(_criterionStrategy);
            for (var pair : _criteria) {
                builder.addCriterion(pair.getFirst(), pair.getSecond());
            }
            if (_parent != null) builder.parent(_parent);
            return builder.save(consumer, DungeonsEnhanced.locate(_id));
        }

        private static Component component(String key) {
            return Component.translatable("advancements.dungeons_enhanced." + key);
        }
    }
}