package com.barion.dungeons_enhanced;

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
import net.minecraft.world.level.levelgen.feature.StructureFeature;
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
        Advancement root = enterAnyStructure(builder(Blocks.MOSSY_STONE_BRICKS, "root", new ResourceLocation("textures/block/mossy_cobblestone.png"), FrameType.TASK, false, false, false), DEStructures.getAllStructures()).requirements(RequirementsStrategy.OR).save(consumer, location("root"));
        Advancement HiddenUnderTheRoots = enterStructure(builder(Blocks.JACK_O_LANTERN, "hidden_under_the_roots", FrameType.TASK, true, true, false), DEStructures.MonsterMaze.getStructure()).parent(root).save(consumer, location("hidden_under_the_roots"));
        Advancement ThatsADungeon = enterStructure(builder(Blocks.SKELETON_SKULL, "thats_a_dungeon", FrameType.TASK, true, true, false), DEStructures.LargeDungeon.getStructure()).parent(root).save(consumer, location("thats_a_dungeon"));
        Advancement TrapsAndCurses = enterStructure(builder(Blocks.TNT, "traps_and_curses", FrameType.TASK, true, true, false), DEStructures.DesertTemple.getStructure()).parent(root).save(consumer, location("traps_and_curses"));
        Advancement AncientCivilizations = enterStructure(builder(Blocks.BAMBOO, "ancient_civilizations", FrameType.TASK, true, true, false), DEStructures.JungleMonument.getStructure()).parent(root).save(consumer, location("ancient_civilizations"));
        Advancement WarsAndKingdoms = enterStructure(builder(Blocks.STONE_BRICKS, "wars_and_kingdoms", FrameType.TASK, true, true, false), DEStructures.Castle.getStructure()).parent(root).save(consumer, location("wars_and_kingdoms"));
        Advancement RarestStructure = enterStructure(builder(Items.RED_MUSHROOM, "rarest_structure", FrameType.TASK, true, true, false), DEStructures.MushroomHouse.getStructure()).parent(root).save(consumer, location("rarest_structure"));
        Advancement ChilledHalls = enterStructure(builder(Items.BONE, "chilled_halls", FrameType.TASK, true, true, false), DEStructures.IcePit.getStructure()).parent(root).save(consumer, location("chilled_halls"));
        Advancement SevenWorldWonders = enterAnyStructure(builder(Items.SPYGLASS, "seven_world_wonders", FrameType.GOAL, true, true, false),
                new StructureFeature<?>[] {
                        DEStructures.MonsterMaze.getStructure(),
                        DEStructures.LargeDungeon.getStructure(),
                        DEStructures.DesertTemple.getStructure(),
                        DEStructures.JungleMonument.getStructure(),
                        DEStructures.Castle.getStructure(),
                        DEStructures.MushroomHouse.getStructure(),
                        DEStructures.IcePit.getStructure()}
                ).requirements(RequirementsStrategy.AND).parent(root).save(consumer, location("seven_world_wonders"));
        Advancement AmbitiousExplorer = enterAnyStructure(builder(Items.FILLED_MAP, "ambitious_explorer", FrameType.CHALLENGE, true, true, false), DEStructures.getAllStructures()).requirements(RequirementsStrategy.AND).parent(root).save(consumer, location("ambitious_explorer"));
    }

    private Advancement.Builder enterAnyStructure(Advancement.Builder builder, StructureFeature<?>[] structures){
        for(StructureFeature<?> structure : structures){
            builder = enterStructure(builder, structure);
        }

        return builder;
    }

    private Advancement.Builder enterStructure(Advancement.Builder builder, StructureFeature<?> structure){
        return builder.addCriterion(enterFeatureText(structure), LocationTrigger.TriggerInstance.located(LocationPredicate.inFeature(structure)));
    }

    private String enterFeatureText(StructureFeature<?> structure){
        return "entered_" + Objects.requireNonNull(structure.getRegistryName()).getPath();
    }

    private Advancement.Builder builder(ItemLike displayItem, String name, ResourceLocation background, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().display(displayItem, translate(name), translate(name + ".desc"), background, frameType, showToast, announceToChat, hidden);
    }

    private Advancement.Builder builder(ItemLike displayItem, String name, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden){
        return builder(displayItem, name, null, frameType, showToast, announceToChat, hidden);
    }

    private TranslatableComponent translate(String key) {return new TranslatableComponent("advancements.dungeons_enhanced." + key);}

    private String location(String key) {return DungeonsEnhanced.Mod_ID + ":" + key;}
}