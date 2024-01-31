package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.registry.DEStructures;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import net.minecraft.Util;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
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

        private Advancement.Builder enterAnyStructure(Advancement.Builder builder, StructureRegistrar<?>... structures){
            for(StructureRegistrar<?> structure : structures){
                builder = enterStructure(builder, structure);
            }

            return builder;
        }

        private Advancement.Builder enterStructure(Advancement.Builder builder, StructureRegistrar<?> structure){
            return builder.addCriterion(enterFeatureText(structure), PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inStructure(Objects.requireNonNull(structure.getStructure()).getKey())));
        }

        private String enterFeatureText(StructureRegistrar<?> structure){
            return "entered_" + Objects.requireNonNull(structure.getRegistryName()).getPath();
        }

        private Advancement.Builder advancement(ItemLike displayItem, String name, ResourceLocation background, AdvancementType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
            return new Advancement.Builder().display(displayItem, component(name), component(name + ".desc"), background, frameType, showToast, announceToChat, hidden);
        }

        private Advancement.Builder advancement(ItemLike displayItem, String name, AdvancementType frameType, boolean showToast, boolean announceToChat, boolean hidden){
            return advancement(displayItem, name, null, frameType, showToast, announceToChat, hidden);
        }

        private Component component(String key) {return Component.translatable("advancements.dungeons_enhanced." + key);}
    }
}