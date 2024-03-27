package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.registry.DEStructures;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import net.minecraft.Util;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.block.entity.BannerPatterns;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEAdvancementProvider extends AdvancementProvider {
    public static final CompletableFuture<HolderLookup.Provider> registriesLookup = CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor());


    public DEAdvancementProvider(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, registriesLookup, existingFileHelper, List.of(new DEExplorerAdvancementSubProvider()));
    }

    public static class DEExplorerAdvancementSubProvider implements AdvancementGenerator{
        @Override
        public void generate(@NotNull HolderLookup.Provider provider, @NotNull Consumer<AdvancementHolder> consumer, @NotNull ExistingFileHelper existingFileHelper) {
            var root = enterAnyStructure(advancement(Blocks.MOSSY_STONE_BRICKS, "root", new ResourceLocation("textures/block/mossy_cobblestone.png"), AdvancementType.TASK, false, false, false), DEStructures.ALL_STRUCTURE_REGISTRARS).requirements(AdvancementRequirements.Strategy.OR).save(consumer, location("root"), existingFileHelper);
            enterStructure(advancement(Blocks.JACK_O_LANTERN, "hidden_under_the_roots", AdvancementType.TASK, true, true, false), DEStructures.MONSTER_MAZE).parent(root).save(consumer, location("hidden_under_the_roots"), existingFileHelper);
            enterStructure(advancement(Blocks.SKELETON_SKULL, "thats_a_dungeon", AdvancementType.TASK, true, true, false), DEStructures.LARGE_DUNGEON).parent(root).save(consumer, location("thats_a_dungeon"), existingFileHelper);
            enterStructure(advancement(Blocks.TNT, "traps_and_curses", AdvancementType.TASK, true, true, false), DEStructures.DESERT_TEMPLE).parent(root).save(consumer, location("traps_and_curses"), existingFileHelper);
            enterStructure(advancement(Blocks.BAMBOO, "ancient_civilizations", AdvancementType.TASK, true, true, false), DEStructures.JUNGLE_MONUMENT).parent(root).save(consumer, location("ancient_civilizations"), existingFileHelper);
            enterStructure(advancement(Blocks.STONE_BRICKS, "wars_and_kingdoms", AdvancementType.TASK, true, true, false), DEStructures.CASTLE).parent(root).save(consumer, location("wars_and_kingdoms"), existingFileHelper);
            enterStructure(advancement(Items.RED_MUSHROOM, "rarest_structure", AdvancementType.TASK, true, true, false), DEStructures.MUSHROOM_HOUSE).parent(root).save(consumer, location("rarest_structure"), existingFileHelper);
            enterStructure(advancement(Items.BONE, "chilled_halls", AdvancementType.TASK, true, true, false), DEStructures.ICE_PIT).parent(root).save(consumer, location("chilled_halls"), existingFileHelper);
            enterStructure(advancement(Items.WITHER_SKELETON_SKULL, "ahoy", AdvancementType.TASK, true, true, false), DEStructures.PIRATE_SHIP).parent(root).save(consumer, location("ahoy"), existingFileHelper);
            enterStructure(advancement(Items.LANTERN, "in_the_air", AdvancementType.TASK, true, true, false), DEStructures.FLYING_DUTCHMAN).parent(root).save(consumer, location("in_the_air"), existingFileHelper);
            enterStructure(advancement(Items.NAUTILUS_SHELL, "sunken_deeps", AdvancementType.TASK, true, true, false), DEStructures.ELDERS_TEMPLE).parent(root).save(consumer, location("sunken_deeps"), existingFileHelper);


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

            var SevenWorldWonders = enterAnyStructure(advancement(Items.SPYGLASS, "seven_world_wonders", AdvancementType.GOAL, true, true, false),
                    DEStructures.CASTLE,
                    DEStructures.DEEP_CRYPT,
                    DEStructures.DESERT_TEMPLE,
                    DEStructures.ICE_PIT,
                    DEStructures.JUNGLE_MONUMENT,
                    DEStructures.MONSTER_MAZE,
                    DEStructures.ELDERS_TEMPLE)
                    .requirements(AdvancementRequirements.Strategy.AND).parent(root).save(consumer, location("seven_world_wonders"), existingFileHelper);
            enterAnyStructure(advancement(Items.FILLED_MAP, "ambitious_explorer", AdvancementType.CHALLENGE, true, true, false), DEStructures.ALL_STRUCTURE_REGISTRARS).requirements(AdvancementRequirements.Strategy.AND).parent(SevenWorldWonders).save(consumer, location("ambitious_explorer"), existingFileHelper);
        }

        private static Advancement.Builder enterAnyStructure(Advancement.Builder builder, StructureRegistrar<?>... structures){
            for(StructureRegistrar<?> structure : structures){
                builder = enterStructure(builder, structure);
            }

            return builder;
        }

        private static Advancement.Builder enterStructure(Advancement.Builder builder, StructureRegistrar<?> structure){
            return builder.addCriterion(enterFeatureText(structure), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(Objects.requireNonNull(structure.getStructure()).getKey())));
        }

        private static String enterFeatureText(StructureRegistrar<?> structure){
            return "entered_" + Objects.requireNonNull(structure.getRegistryName()).getPath();
        }

        private static Advancement.Builder advancement(ItemLike displayItem, String name, ResourceLocation background, AdvancementType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
            return new Advancement.Builder().display(displayItem, component(name), component(name + ".desc"), background, frameType, showToast, announceToChat, hidden);
        }

        private static Advancement.Builder advancement(ItemLike displayItem, String name, AdvancementType frameType, boolean showToast, boolean announceToChat, boolean hidden){
            return advancement(displayItem, name, null, frameType, showToast, announceToChat, hidden);
        }

        private static Advancement.Builder advancement(ItemLike displayItem, String name){
            return advancement(displayItem, name, null, AdvancementType.TASK, true, true, false);
        }

        private static Component component(String key) {return Component.translatable("advancements.dungeons_enhanced." + key);}
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
        private final Map<String, Criterion<?>> _criterions = new HashMap<>();

        private AdvancementBuilder(String id, ItemStack displayItem) {
            _id = id;
            _displayItem = displayItem;
        }

        private AdvancementBuilder parent(AdvancementHolder parent){
            _parent = parent;
            return this;
        }

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

        public AdvancementBuilder onEnterStructure(@NotNull StructureRegistrar<?> structureRegistrar){return onEnterStructure(structureRegistrar.getStructure());}
        public AdvancementBuilder onEnterStructure(@NotNull Registrar.Pointer<Structure> structurePointer){
            _criterions.put(
                    "entered_" + structurePointer.getKey().location().getPath(),
                    PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(structurePointer.getKey())));
            return this;
        }

        public AdvancementHolder save(@NotNull Consumer<AdvancementHolder> consumer, @NotNull ExistingFileHelper existingFileHelper){
            var builder =  new Advancement.Builder()
                    .display(_displayItem, component(_id), component(_id + ".desc"), _background, _type, _showToast, _announceToChat, _hidden)
                    .requirements(_criterionStrategy);
            for (var pair : _criterions.entrySet()){
                builder.addCriterion(pair.getKey(), pair.getValue());
            }
            if(_parent != null) builder.parent(_parent);
            return builder.save(consumer, location(_id), existingFileHelper);
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
            patternTag.putString(PATTERN, BuiltInRegistries.BANNER_PATTERN.get(pattern).getHashname());
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