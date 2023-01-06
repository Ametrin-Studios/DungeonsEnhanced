package com.barion.dungeons_enhanced;

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
            Advancement root = enterAnyStructure(advancement(Blocks.MOSSY_STONE_BRICKS, "root", new ResourceLocation("textures/block/mossy_cobblestone.png"), FrameType.TASK, false, false, false), DEStructures.getAllStructureRegistrars()).requirements(RequirementsStrategy.OR).save(consumer, location("root"));
            Advancement HiddenUnderTheRoots = enterStructure(advancement(Blocks.JACK_O_LANTERN, "hidden_under_the_roots", FrameType.TASK, true, true, false), DEStructures.MonsterMaze).parent(root).save(consumer, location("hidden_under_the_roots"));
            Advancement ThatsADungeon = enterStructure(advancement(Blocks.SKELETON_SKULL, "thats_a_dungeon", FrameType.TASK, true, true, false), DEStructures.LargeDungeon).parent(root).save(consumer, location("thats_a_dungeon"));
            Advancement TrapsAndCurses = enterStructure(advancement(Blocks.TNT, "traps_and_curses", FrameType.TASK, true, true, false), DEStructures.DesertTemple).parent(root).save(consumer, location("traps_and_curses"));
            Advancement AncientCivilizations = enterStructure(advancement(Blocks.BAMBOO, "ancient_civilizations", FrameType.TASK, true, true, false), DEStructures.JungleMonument).parent(root).save(consumer, location("ancient_civilizations"));
            Advancement WarsAndKingdoms = enterStructure(advancement(Blocks.STONE_BRICKS, "wars_and_kingdoms", FrameType.TASK, true, true, false), DEStructures.Castle).parent(root).save(consumer, location("wars_and_kingdoms"));
            Advancement RarestStructure = enterStructure(advancement(Items.RED_MUSHROOM, "rarest_structure", FrameType.TASK, true, true, false), DEStructures.MushroomHouse).parent(root).save(consumer, location("rarest_structure"));
            Advancement ChilledHalls = enterStructure(advancement(Items.BONE, "chilled_halls", FrameType.TASK, true, true, false), DEStructures.IcePit).parent(root).save(consumer, location("chilled_halls"));
            Advancement Ahoy = enterStructure(advancement(Items.WITHER_SKELETON_SKULL, "ahoy", FrameType.TASK, true, true, false), DEStructures.PirateShip).parent(root).save(consumer, location("ahoy"));
            Advancement InTheAir = enterStructure(advancement(Items.LANTERN, "in_the_air", FrameType.TASK, true, true, false), DEStructures.FlyingDutchman).parent(root).save(consumer, location("in_the_air"));
            Advancement SunkenDepths = enterStructure(advancement(Items.NAUTILUS_SHELL, "sunken_deeps", FrameType.TASK, true, true, false), DEStructures.EldersTemple).parent(root).save(consumer, location("sunken_deeps"));
            Advancement SevenWorldWonders = enterAnyStructure(advancement(Items.SPYGLASS, "seven_world_wonders", FrameType.GOAL, true, true, false),
                    new StructureRegistrar<?>[] {
                            DEStructures.Castle,
                            DEStructures.DeepCrypt,
                            DEStructures.DesertTemple,
                            DEStructures.IcePit,
                            DEStructures.JungleMonument,
                            DEStructures.MonsterMaze,
                            DEStructures.EldersTemple
                    }
            ).requirements(RequirementsStrategy.AND).parent(root).save(consumer, location("seven_world_wonders"));
            Advancement AmbitiousExplorer = enterAnyStructure(advancement(Items.FILLED_MAP, "ambitious_explorer", FrameType.CHALLENGE, true, true, false), DEStructures.getAllStructureRegistrars()).requirements(RequirementsStrategy.AND).parent(SevenWorldWonders).save(consumer, location("ambitious_explorer"));
        }

        private Advancement.Builder enterAnyStructure(Advancement.Builder builder, StructureRegistrar<?>[] structures){
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
            return Advancement.Builder.advancement().display(displayItem, translate(name), translate(name + ".desc"), background, frameType, showToast, announceToChat, hidden);
        }

        private Advancement.Builder advancement(ItemLike displayItem, String name, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden){
            return advancement(displayItem, name, null, frameType, showToast, announceToChat, hidden);
        }

        private Component translate(String key) {return Component.translatable("advancements.dungeons_enhanced." + key);}
        private String location(String key) {return new ResourceLocation(DungeonsEnhanced.ModID, key).toString();}
    }
}