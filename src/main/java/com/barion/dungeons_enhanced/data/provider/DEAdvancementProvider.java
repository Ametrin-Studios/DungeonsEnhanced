package com.barion.dungeons_enhanced.data.provider;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import net.minecraft.Util;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
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
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class DEAdvancementProvider extends ForgeAdvancementProvider {
    public static final CompletableFuture<HolderLookup.Provider> registriesLookup = CompletableFuture.supplyAsync(VanillaRegistries::createLookup, Util.backgroundExecutor());


    public DEAdvancementProvider(PackOutput output, ExistingFileHelper existingFileHelper){
        super(output, registriesLookup, existingFileHelper, List.of(new DEExplorerAdvancementSubProvider()));
    }

    public static class DEExplorerAdvancementSubProvider implements AdvancementGenerator{
        @Override
        public void generate(@Nonnull HolderLookup.Provider registries, @Nonnull Consumer<Advancement> consumer, @Nonnull ExistingFileHelper existingFileHelper) {
            var root = enterAnyStructure(advancement(Blocks.MOSSY_STONE_BRICKS, "root", new ResourceLocation("textures/block/mossy_cobblestone.png"), FrameType.TASK, false, false, false), DEStructures.ALL_STRUCTURE_REGISTRARS).requirements(RequirementsStrategy.OR).save(consumer, location("root"), existingFileHelper);
            var HiddenUnderTheRoots = enterStructure(advancement(Blocks.JACK_O_LANTERN, "hidden_under_the_roots", FrameType.TASK, true, true, false), DEStructures.MONSTER_MAZE).parent(root).save(consumer, location("hidden_under_the_roots"), existingFileHelper);
            var ThatsADungeon = enterStructure(advancement(Blocks.SKELETON_SKULL, "thats_a_dungeon", FrameType.TASK, true, true, false), DEStructures.LARGE_DUNGEON).parent(root).save(consumer, location("thats_a_dungeon"), existingFileHelper);
            var TrapsAndCurses = enterStructure(advancement(Blocks.TNT, "traps_and_curses", FrameType.TASK, true, true, false), DEStructures.DESERT_TEMPLE).parent(root).save(consumer, location("traps_and_curses"), existingFileHelper);
            var AncientCivilizations = enterStructure(advancement(Blocks.BAMBOO, "ancient_civilizations", FrameType.TASK, true, true, false), DEStructures.JUNGLE_MONUMENT).parent(root).save(consumer, location("ancient_civilizations"), existingFileHelper);
            var WarsAndKingdoms = enterStructure(advancement(Blocks.STONE_BRICKS, "wars_and_kingdoms", FrameType.TASK, true, true, false), DEStructures.CASTLE).parent(root).save(consumer, location("wars_and_kingdoms"), existingFileHelper);
            var RarestStructure = enterStructure(advancement(Items.RED_MUSHROOM, "rarest_structure", FrameType.TASK, true, true, false), DEStructures.MUSHROOM_HOUSE).parent(root).save(consumer, location("rarest_structure"), existingFileHelper);
            var ChilledHalls = enterStructure(advancement(Items.BONE, "chilled_halls", FrameType.TASK, true, true, false), DEStructures.ICE_PIT).parent(root).save(consumer, location("chilled_halls"), existingFileHelper);
            var Ahoy = enterStructure(advancement(Items.WITHER_SKELETON_SKULL, "ahoy", FrameType.TASK, true, true, false), DEStructures.PIRATE_SHIP).parent(root).save(consumer, location("ahoy"), existingFileHelper);
            var InTheAir = enterStructure(advancement(Items.LANTERN, "in_the_air", FrameType.TASK, true, true, false), DEStructures.FLYING_DUTCHMAN).parent(root).save(consumer, location("in_the_air"), existingFileHelper);
            var SunkenDepths = enterStructure(advancement(Items.NAUTILUS_SHELL, "sunken_deeps", FrameType.TASK, true, true, false), DEStructures.ELDERS_TEMPLE).parent(root).save(consumer, location("sunken_deeps"), existingFileHelper);
            var SevenWorldWonders = enterAnyStructure(advancement(Items.SPYGLASS, "seven_world_wonders", FrameType.GOAL, true, true, false),
                    DEStructures.CASTLE,
                    DEStructures.DEEP_CRYPT,
                    DEStructures.DESERT_TEMPLE,
                    DEStructures.ICE_PIT,
                    DEStructures.JUNGLE_MONUMENT,
                    DEStructures.MONSTER_MAZE,
                    DEStructures.ELDERS_TEMPLE)
                    .requirements(RequirementsStrategy.AND).parent(root).save(consumer, location("seven_world_wonders"), existingFileHelper);
            var AmbitiousExplorer = enterAnyStructure(advancement(Items.FILLED_MAP, "ambitious_explorer", FrameType.CHALLENGE, true, true, false), DEStructures.ALL_STRUCTURE_REGISTRARS).requirements(RequirementsStrategy.AND).parent(SevenWorldWonders).save(consumer, location("ambitious_explorer"), existingFileHelper);
        }

        private Advancement.Builder enterAnyStructure(Advancement.Builder builder, StructureRegistrar<?>... structures){
            for(StructureRegistrar<?> structure : structures){
                builder = enterStructure(builder, structure);
            }

            return builder;
        }

        private Advancement.Builder enterStructure(Advancement.Builder builder, StructureRegistrar<?> structure){
            return builder.addCriterion(enterFeatureText(structure), PlayerTrigger.TriggerInstance.located(LocationPredicate.inStructure(Objects.requireNonNull(structure.getStructure()).getKey())));
        }

        private String enterFeatureText(StructureRegistrar<?> structure){
            return "entered_" + Objects.requireNonNull(structure.getRegistryName()).getPath();
        }

        private Advancement.Builder advancement(ItemLike displayItem, String name, ResourceLocation background, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
            //only difference between recipeAdvancement and advancement is that it does not send telemetry events
            return Advancement.Builder.recipeAdvancement().display(displayItem, translate(name), translate(name + ".desc"), background, frameType, showToast, announceToChat, hidden);
        }

        private Advancement.Builder advancement(ItemLike displayItem, String name, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden){
            return advancement(displayItem, name, null, frameType, showToast, announceToChat, hidden);
        }

        private Component translate(String key) {return Component.translatable("advancements.dungeons_enhanced." + key);}
        private ResourceLocation location(String key) {return DEUtil.location(key);}
    }
}