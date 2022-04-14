package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.LocationTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.function.Consumer;

public class DEAdvancementProvider extends AdvancementProvider{
    public DEAdvancementProvider(DataGenerator dataGenerator, ExistingFileHelper exFileHelper){
        super(dataGenerator, exFileHelper);
    }

    @Override @ParametersAreNonnullByDefault
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper exFileHelper) {
        Advancement root = enterAnyStructure(builder(Blocks.MOSSY_STONE_BRICKS, "root", new ResourceLocation("textures/block/mossy_cobblestone.png"), FrameType.TASK, false, false, false), DEStructures.getAllStructureRegistrars()).requirements(RequirementsStrategy.OR).save(consumer, location("root"));
        Advancement HiddenUnderTheRoots = enterStructure(builder(Blocks.JACK_O_LANTERN, "hidden_under_the_roots", FrameType.TASK, true, true, false), DEStructures.MonsterMaze).parent(root).save(consumer, location("hidden_under_the_roots"));
        Advancement ThatsADungeon = enterStructure(builder(Blocks.SKELETON_SKULL, "thats_a_dungeon", FrameType.TASK, true, true, false), DEStructures.LargeDungeon).parent(root).save(consumer, location("thats_a_dungeon"));
        Advancement TrapsAndCurses = enterStructure(builder(Blocks.TNT, "traps_and_curses", FrameType.TASK, true, true, false), DEStructures.DesertTemple).parent(root).save(consumer, location("traps_and_curses"));
        Advancement AncientCivilizations = enterStructure(builder(Blocks.BAMBOO, "ancient_civilizations", FrameType.TASK, true, true, false), DEStructures.JungleMonument).parent(root).save(consumer, location("ancient_civilizations"));
        Advancement WarsAndKingdoms = enterStructure(builder(Blocks.STONE_BRICKS, "wars_and_kingdoms", FrameType.TASK, true, true, false), DEStructures.Castle).parent(root).save(consumer, location("wars_and_kingdoms"));
        Advancement RarestStructure = enterStructure(builder(Items.RED_MUSHROOM, "rarest_structure", FrameType.TASK, true, true, false), DEStructures.MushroomHouse).parent(root).save(consumer, location("rarest_structure"));
        Advancement ChilledHalls = enterStructure(builder(Items.BONE, "chilled_halls", FrameType.TASK, true, true, false), DEStructures.IcePit).parent(root).save(consumer, location("chilled_halls"));
        Advancement SevenWorldWonders = enterAnyStructure(builder(Items.SPYGLASS, "seven_world_wonders", FrameType.GOAL, true, true, false),
                new StructureRegistrar<?, ?>[] {
                        DEStructures.MonsterMaze,
                        DEStructures.DesertTemple,
                        DEStructures.JungleMonument,
                        DEStructures.Castle,
                        DEStructures.MushroomHouse,
                        DEStructures.IcePit
        }
                ).requirements(RequirementsStrategy.AND).parent(root).save(consumer, location("seven_world_wonders"));
        Advancement AmbitiousExplorer = enterAnyStructure(builder(Items.FILLED_MAP, "ambitious_explorer", FrameType.CHALLENGE, true, true, false), DEStructures.getAllStructureRegistrars()).requirements(RequirementsStrategy.AND).parent(root).save(consumer, location("ambitious_explorer"));
    }

    private Advancement.Builder enterAnyStructure(Advancement.Builder builder, StructureRegistrar<?, ?>[] structures){
        for(StructureRegistrar<?, ?> structure : structures){
            builder = enterStructure(builder, structure);
        }

        return builder;
    }

    private Advancement.Builder enterStructure(Advancement.Builder builder, StructureRegistrar<?, ?> structure){
        return builder.addCriterion(enterFeatureText(structure), LocationTrigger.TriggerInstance.located(LocationPredicate.inFeature(Objects.requireNonNull(structure.getConfigured()).unwrapKey().orElseThrow())));
    }

    private String enterFeatureText(StructureRegistrar<?, ?> structure){
        return "entered_" + Objects.requireNonNull(structure.getRegistryName()).getPath();
    }

    private Advancement.Builder builder(ItemLike displayItem, String name, ResourceLocation background, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().display(displayItem, translate(name), translate(name + ".desc"), background, frameType, showToast, announceToChat, hidden);
    }

    private Advancement.Builder builder(ItemLike displayItem, String name, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden){
        return builder(displayItem, name, null, frameType, showToast, announceToChat, hidden);
    }

    private TranslatableComponent translate(String key) {return new TranslatableComponent("advancements.dungeons_enhanced." + key);}

    private String location(String key) {return DungeonsEnhanced.ModID + ":" + key;}
}