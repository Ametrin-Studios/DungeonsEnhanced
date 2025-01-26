package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.registry.DEStructures;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatternLayers;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.levelgen.structure.Structure;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.barion.dungeons_enhanced.DEUtil.locate;

public final class DEAdvancementProvider extends AdvancementProvider {
    public DEAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, List.of(new DEExplorerAdvancementSubProvider()));
    }

    public static final class DEExplorerAdvancementSubProvider implements AdvancementSubProvider {
        @Override
        public void generate(@NotNull HolderLookup.Provider provider, @NotNull Consumer<AdvancementHolder> consumer) {
            var structureLookup = provider.lookupOrThrow(Registries.STRUCTURE);
            var bannerLookup = provider.lookupOrThrow(Registries.BANNER_PATTERN);

            var root = new AdvancementBuilder("root", Items.COMPASS)
                    .background("textures/block/mossy_cobblestone.png")
                    .hideToast().hideInChat()
                    .orCriterions()
                    .onEnterStructures(structureLookup, DEStructures.ALL_STRUCTURE_REGISTRARS)
                    .save(consumer);

            new AdvancementBuilder("hidden_under_the_roots", Items.JACK_O_LANTERN)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.MONSTER_MAZE)
                    .save(consumer);

            new AdvancementBuilder("thats_a_dungeon", Items.SKELETON_SKULL)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.LARGE_DUNGEON)
                    .save(consumer);

            new AdvancementBuilder("traps_and_curses", Items.TNT)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.DESERT_TEMPLE)
                    .save(consumer);

            new AdvancementBuilder("ancient_civilizations", Items.BAMBOO)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.JUNGLE_MONUMENT)
                    .save(consumer);

            new AdvancementBuilder("wars_and_kingdoms", Items.STONE_BRICKS)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.CASTLE)
                    .save(consumer);

            new AdvancementBuilder("rarest_structure", Items.RED_MUSHROOM)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.MUSHROOM_HOUSE)
                    .save(consumer);

            new AdvancementBuilder("chilled_halls", Items.BONE)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.ICE_PIT)
                    .save(consumer);

            new AdvancementBuilder("ahoy", Items.WITHER_SKELETON_SKULL)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.PIRATE_SHIP)
                    .save(consumer);

            new AdvancementBuilder("in_the_air", Items.LANTERN)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.FLYING_DUTCHMAN)
                    .save(consumer);

            new AdvancementBuilder("sunken_deeps", Items.NAUTILUS_SHELL)
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.ELDERS_TEMPLE)
                    .save(consumer);

            new AdvancementBuilder("spooky_scary_citadel",
                    new BannerBuilder(Items.RED_BANNER)
                            .addPattern( bannerLookup, BannerPatterns.BRICKS, DyeColor.BLACK)
                            .addPattern( bannerLookup, BannerPatterns.GRADIENT_UP, DyeColor.RED)
                            .addPattern( bannerLookup, BannerPatterns.SKULL, DyeColor.BLACK)
                            .addPattern( bannerLookup, BannerPatterns.BORDER, DyeColor.BLACK)
                            .build())
                    .parent(root)
                    .onEnterStructure(structureLookup, DEStructures.BLACK_CITADEL)
                    .save(consumer);

            var sevenWorldWonders = new AdvancementBuilder("seven_world_wonders", Items.SPYGLASS)
                    .parent(root)
                    .type(AdvancementType.GOAL)
                    .onEnterStructures(structureLookup,
                            DEStructures.CASTLE,
                            DEStructures.DEEP_CRYPT,
                            DEStructures.DESERT_TEMPLE,
                            DEStructures.ICE_PIT,
                            DEStructures.JUNGLE_MONUMENT,
                            DEStructures.MONSTER_MAZE,
                            DEStructures.ELDERS_TEMPLE
                    )
                    .save(consumer);

            new AdvancementBuilder("ambitious_explorer", Items.FILLED_MAP)
                    .parent(sevenWorldWonders)
                    .type(AdvancementType.CHALLENGE)
                    .onEnterStructures(structureLookup, DEStructures.ALL_STRUCTURE_REGISTRARS)
                    .save(consumer);
        }
    }

    private static class AdvancementBuilder {
        private final String _id;
        private final ItemStack _displayItem;
        private AdvancementHolder _parent = null;
        private ResourceLocation _background = null;
        private AdvancementType _type = AdvancementType.TASK;
        private boolean _showToast = true;
        private boolean _announceToChat = true;
        private boolean _hidden = false;
        private AdvancementRequirements.Strategy _criterionStrategy = AdvancementRequirements.Strategy.AND;
        private final List<Pair<String, Criterion<?>>> _criterions = new ArrayList<>();

        private AdvancementBuilder(String id, ItemStack displayItem) {
            _id = id;
            _displayItem = displayItem;
        }

        private AdvancementBuilder(String id, ItemLike displayItem) {
            this(id, displayItem.asItem().getDefaultInstance());
        }

        private AdvancementBuilder parent(AdvancementHolder parent) {
            _parent = parent;
            return this;
        }

        public AdvancementBuilder background(String background) {
            return background(ResourceLocation.withDefaultNamespace(background));
        }

        public AdvancementBuilder background(ResourceLocation background) {
            _background = background;
            return this;
        }

        public AdvancementBuilder type(AdvancementType type) {
            _type = type;
            return this;
        }

        public AdvancementBuilder hideCompletely() {
            return hideToast().hideInChat().hide();
        }

        public AdvancementBuilder hideToast() {
            return showToast(false);
        }

        public AdvancementBuilder showToast(boolean show) {
            _showToast = show;
            return this;
        }

        public AdvancementBuilder hideInChat() {
            return announceToChat(false);
        }

        public AdvancementBuilder announceToChat(boolean announce) {
            _announceToChat = announce;
            return this;
        }

        public AdvancementBuilder hide() {
            return hidden(true);
        }

        public AdvancementBuilder hidden(boolean hidden) {
            _hidden = hidden;
            return this;
        }

        public AdvancementBuilder orCriterions() {
            return criterionStrategy(AdvancementRequirements.Strategy.OR);
        }

        public AdvancementBuilder criterionStrategy(AdvancementRequirements.Strategy strategy) {
            _criterionStrategy = strategy;
            return this;
        }

        public AdvancementBuilder onEnterStructures(HolderLookup.RegistryLookup<Structure> lookup, StructureRegistrar<?>... structureRegistrars) {
            for (StructureRegistrar<?> structure : structureRegistrars) {
                onEnterStructure(lookup, structure);
            }
            return this;
        }

        public AdvancementBuilder onEnterStructure(HolderLookup.RegistryLookup<Structure> lookup, @NotNull StructureRegistrar<?> structureRegistrar) {
            return onEnterStructure(Objects.requireNonNull(structureRegistrar.getStructure()).getHolder(lookup).orElseThrow());
        }

        public AdvancementBuilder onEnterStructure(@NotNull Holder<Structure> structureHolder) {
            _criterions.add(new Pair<>(
                    "entered_" + Objects.requireNonNull(structureHolder.getKey()).location().getPath(),
                    PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structureHolder))));
            return this;
        }

        public AdvancementHolder save(@NotNull Consumer<AdvancementHolder> consumer) {
            var builder = new Advancement.Builder()
                    .display(_displayItem, component(_id), component(_id + ".desc"), _background, _type, _showToast, _announceToChat, _hidden)
                    .requirements(_criterionStrategy);
            for (var pair : _criterions) {
                builder.addCriterion(pair.getFirst(), pair.getSecond());
            }
            if (_parent != null) builder.parent(_parent);
            return builder.save(consumer, locate(_id));
        }

        private static Component component(String key) {
            return Component.translatable("advancements.dungeons_enhanced." + key);
        }
    }

    private static class BannerBuilder {
        private final Item _bannerItem;
        private final BannerPatternLayers.Builder _layers = new BannerPatternLayers.Builder();

        public BannerBuilder(Item item) {
            _bannerItem = item;
        }

        public BannerBuilder addPattern(HolderGetter<BannerPattern> lookup, ResourceKey<BannerPattern> pattern, DyeColor color) {
            _layers.add(lookup.get(pattern).orElseThrow(), color);
            return this;
        }

        public ItemStack build() {
            var stack = new ItemStack(_bannerItem);
            stack.set(DataComponents.BANNER_PATTERNS, _layers.build());
            return stack;
        }
    }
}