package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.registry.DEStructures;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.barion.dungeons_enhanced.DEUtil.locate;

public final class DEAdvancementProvider extends AdvancementProvider {
    public DEAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper){
        super(output, lookupProvider, existingFileHelper, List.of(new DEExplorerAdvancementSubProvider()));
    }

    public static final class DEExplorerAdvancementSubProvider implements AdvancementGenerator {
        @Override
        public void generate(@NotNull HolderLookup.Provider provider, @NotNull Consumer<AdvancementHolder> consumer, @NotNull ExistingFileHelper existingFileHelper) {
            var root = new AdvancementBuilder("root", Items.COMPASS)
                    .background("textures/block/mossy_cobblestone.png")
                    .hideToast().hideInChat()
                    .orCriterions()
                    .onEnterStructures(DEStructures.ALL_STRUCTURE_REGISTRARS)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("hidden_under_the_roots", Items.JACK_O_LANTERN)
                    .parent(root)
                    .onEnterStructure(DEStructures.MONSTER_MAZE)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("thats_a_dungeon", Items.SKELETON_SKULL)
                    .parent(root)
                    .onEnterStructure(DEStructures.LARGE_DUNGEON)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("traps_and_curses", Items.TNT)
                    .parent(root)
                    .onEnterStructure(DEStructures.DESERT_TEMPLE)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("ancient_civilizations", Items.BAMBOO)
                    .parent(root)
                    .onEnterStructure(DEStructures.JUNGLE_MONUMENT)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("wars_and_kingdoms", Items.STONE_BRICKS)
                    .parent(root)
                    .onEnterStructure(DEStructures.CASTLE)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("rarest_structure", Items.RED_MUSHROOM)
                    .parent(root)
                    .onEnterStructure(DEStructures.MUSHROOM_HOUSE)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("chilled_halls", Items.BONE)
                    .parent(root)
                    .onEnterStructure(DEStructures.ICE_PIT)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("ahoy", Items.WITHER_SKELETON_SKULL)
                    .parent(root)
                    .onEnterStructure(DEStructures.PIRATE_SHIP)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("in_the_air", Items.LANTERN)
                    .parent(root)
                    .onEnterStructure(DEStructures.FLYING_DUTCHMAN)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("sunken_deeps", Items.NAUTILUS_SHELL)
                    .parent(root)
                    .onEnterStructure(DEStructures.ELDERS_TEMPLE)
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("spooky_scary_citadel",
                    new BannerBuilder(Items.RED_BANNER)
                            .addPattern(BannerPatterns.BRICKS, DyeColor.BLACK)
                            .addPattern(BannerPatterns.GRADIENT_UP, DyeColor.RED)
                            .addPattern(BannerPatterns.SKULL, DyeColor.BLACK)
                            .addPattern(BannerPatterns.BORDER, DyeColor.BLACK)
                            .build())
                    .parent(root)
                    .onEnterStructure(DEStructures.BLACK_CITADEL)
                    .save(consumer, existingFileHelper);

            var sevenWorldWonders = new AdvancementBuilder("seven_world_wonders", Items.SPYGLASS)
                    .parent(root)
                    .type(AdvancementType.GOAL)
                    .onEnterStructures(
                            DEStructures.CASTLE,
                            DEStructures.DEEP_CRYPT,
                            DEStructures.DESERT_TEMPLE,
                            DEStructures.ICE_PIT,
                            DEStructures.JUNGLE_MONUMENT,
                            DEStructures.MONSTER_MAZE,
                            DEStructures.ELDERS_TEMPLE
                    )
                    .save(consumer, existingFileHelper);

            new AdvancementBuilder("ambitious_explorer", Items.FILLED_MAP)
                    .parent(sevenWorldWonders)
                    .type(AdvancementType.CHALLENGE)
                    .onEnterStructures(DEStructures.ALL_STRUCTURE_REGISTRARS)
                    .save(consumer, existingFileHelper);
        }
    }

    private static class AdvancementBuilder{
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

        private AdvancementBuilder parent(AdvancementHolder parent){
            _parent = parent;
            return this;
        }

        public AdvancementBuilder background(String background) {return background(new ResourceLocation(background));}
        public AdvancementBuilder background(ResourceLocation background){
            _background = background;
            return this;
        }

        public AdvancementBuilder type(AdvancementType type){
            _type = type;
            return this;
        }

        public AdvancementBuilder hideCompletely(){
            return hideToast().hideInChat().hide();
        }

        public AdvancementBuilder hideToast(){return showToast(false);}
        public AdvancementBuilder showToast(boolean show){
            _showToast = show;
            return this;
        }

        public AdvancementBuilder hideInChat(){return announceToChat(false);}
        public AdvancementBuilder announceToChat(boolean announce){
            _announceToChat = announce;
            return this;
        }

        public AdvancementBuilder hide(){return hidden(true);}
        public AdvancementBuilder hidden(boolean hidden){
            _hidden = hidden;
            return this;
        }

        public AdvancementBuilder orCriterions() {return criterionStrategy(AdvancementRequirements.Strategy.OR);}
        public AdvancementBuilder criterionStrategy(AdvancementRequirements.Strategy strategy){
            _criterionStrategy = strategy;
            return this;
        }

        public AdvancementBuilder onEnterStructures(StructureRegistrar<?>... structureRegistrars){
            for(StructureRegistrar<?> structure : structureRegistrars){
                onEnterStructure(structure);
            }
            return this;
        }

        public AdvancementBuilder onEnterStructure(@NotNull StructureRegistrar<?> structureRegistrar){return onEnterStructure(Objects.requireNonNull(structureRegistrar.getStructure()));}
        public AdvancementBuilder onEnterStructure(@NotNull Registrar.Pointer<Structure> structurePointer){
            _criterions.add(new Pair<>(
                    "entered_" + structurePointer.getKey().location().getPath(),
                    PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structurePointer.getKey()))));
            return this;
        }

        public AdvancementHolder save(@NotNull Consumer<AdvancementHolder> consumer, @NotNull ExistingFileHelper existingFileHelper){
            var builder =  new Advancement.Builder()
                    .display(_displayItem, component(_id), component(_id + ".desc"), _background, _type, _showToast, _announceToChat, _hidden)
                    .requirements(_criterionStrategy);
            for (var pair : _criterions){
                builder.addCriterion(pair.getFirst(), pair.getSecond());
            }
            if(_parent != null) builder.parent(_parent);
            return builder.save(consumer, locate(_id), existingFileHelper);
        }

        private static Component component(String key) {return Component.translatable("advancements.dungeons_enhanced." + key);}
    }

    private static class BannerBuilder{
        private static final String PATTERNS = "Patterns";
        private static final String PATTERN = "Pattern";
        private static final String COLOR = "Color";
        private final ItemStack _bannerStack;
        private final CompoundTag _blockTag = new CompoundTag();
        private final ListTag _patternsTag = _blockTag.getList(PATTERNS, 10);

        public BannerBuilder(Item item){
            _bannerStack = item.getDefaultInstance().copy();
        }

        public BannerBuilder addPattern(ResourceKey<BannerPattern> pattern, DyeColor color){
            var patternTag = new CompoundTag();
            patternTag.putString(PATTERN, Objects.requireNonNull(BuiltInRegistries.BANNER_PATTERN.get(pattern)).getHashname());
            patternTag.putInt(COLOR, color.getId());
            _patternsTag.add(patternTag);
            return this;
        }

        public ItemStack build(){
            _blockTag.put(PATTERNS, _patternsTag);
            BlockItem.setBlockEntityData(_bannerStack, BlockEntityType.BANNER, _blockTag);
            return _bannerStack;
        }
    }
}