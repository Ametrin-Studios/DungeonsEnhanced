package com.barion.dungeons_enhanced;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.LocationPredicate;
import net.minecraft.advancements.critereon.LocationTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class DEAdvancementProvider extends AdvancementProvider{

    public DEAdvancementProvider(DataGenerator dataGenerator, ExistingFileHelper exFileHelper) {
        super(dataGenerator, exFileHelper);
    }

    @Override @ParametersAreNonnullByDefault
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) {
        Advancement root = builder(Blocks.MOSSY_STONE_BRICKS, "root", new ResourceLocation("textures/block/mossy_cobblestone.png"), FrameType.TASK, false, false, false).save(consumer, location("root"));
        Advancement HiddenUnderTheRoots = enterStructure(builder(Blocks.JACK_O_LANTERN, "hidden_under_the_roots", FrameType.TASK, true, true, false), DEStructures.MonsterMaze.getStructure()).parent(root).save(consumer, location("hidden_under_the_roots"));
        Advancement ThatsADungeon = enterStructure(builder(Blocks.SKELETON_SKULL, "thats_a_dungeon", FrameType.TASK, true, true, false), DEStructures.LargeDungeon.getStructure()).parent(root).save(consumer, location("thats_a_dungeon"));
    }

    private Advancement.Builder enterAnyStructure(Advancement.Builder builder, List<StructureFeature<?>> structures){
        structures.forEach((structure) -> enterStructure(builder, structure));
        return builder;
    }

    private Advancement.Builder enterStructure(Advancement.Builder builder, StructureFeature<?> structure){
        return builder.addCriterion("entered_" + Objects.requireNonNull(structure.getRegistryName()).getPath(), LocationTrigger.TriggerInstance.located(LocationPredicate.inFeature(structure)));
    }

    private String enterStructureText(StructureFeature<?> structure){
        return "entered_" + Objects.requireNonNull(structure.getRegistryName()).getPath();
    }

    private Advancement.Builder builder(ItemLike displayItem, String name, ResourceLocation background, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().display(displayItem, translate(name), translate(name + ".desc"), background, frameType, showToast, announceToChat, hidden);
    }

    private Advancement.Builder builder(ItemLike displayItem, String name, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
        return this.builder(displayItem, name, null, frameType, showToast, announceToChat, hidden);
    }

    private TranslatableComponent translate(String key) {return new TranslatableComponent("advancements.dungeons_enhanced." + key);}

    private String location(String key) {return DungeonsEnhanced.Mod_ID + ":" + key;}
}