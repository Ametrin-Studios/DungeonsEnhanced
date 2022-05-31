package com.barion.dungeons_enhanced;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.LocationPredicate;
import net.minecraft.advancements.criterion.PositionTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.function.Consumer;

public class DEAdvancementProvider extends AdvancementProvider {
    public DEAdvancementProvider(DataGenerator dataGenerator, ExistingFileHelper exFileHelper){
        super(dataGenerator, exFileHelper);
    }

    @Override @ParametersAreNonnullByDefault
    protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper exFileHelper) {
        Advancement root = enterAnyStructure(builder(Blocks.MOSSY_STONE_BRICKS.asItem(), "root", new ResourceLocation("textures/block/mossy_cobblestone.png"), FrameType.TASK, false, false, false), DEStructures.getAllStructures()).requirements(IRequirementsStrategy.OR).save(consumer, location("root"));
        Advancement HiddenUnderTheRoots = enterStructure(builder(Blocks.JACK_O_LANTERN.asItem(), "hidden_under_the_roots", FrameType.TASK, true, true, false), DEStructures.MonsterMaze.getStructure()).parent(root).save(consumer, location("hidden_under_the_roots"));
        Advancement ThatsADungeon = enterStructure(builder(Blocks.SKELETON_SKULL.asItem(), "thats_a_dungeon", FrameType.TASK, true, true, false), DEStructures.LargeDungeon.getStructure()).parent(root).save(consumer, location("thats_a_dungeon"));
        Advancement TrapsAndCurses = enterStructure(builder(Blocks.TNT.asItem(), "traps_and_curses", FrameType.TASK, true, true, false), DEStructures.DesertTemple.getStructure()).parent(root).save(consumer, location("traps_and_curses"));
        Advancement AncientCivilizations = enterStructure(builder(Blocks.BAMBOO.asItem(), "ancient_civilizations", FrameType.TASK, true, true, false), DEStructures.JungleMonument.getStructure()).parent(root).save(consumer, location("ancient_civilizations"));
        Advancement WarsAndKingdoms = enterStructure(builder(Blocks.STONE_BRICKS.asItem(), "wars_and_kingdoms", FrameType.TASK, true, true, false), DEStructures.Castle.getStructure()).parent(root).save(consumer, location("wars_and_kingdoms"));
        Advancement RarestStructure = enterStructure(builder(Items.RED_MUSHROOM, "rarest_structure", FrameType.TASK, true, true, false), DEStructures.MushroomHouse.getStructure()).parent(root).save(consumer, location("rarest_structure"));
        Advancement ChilledHalls = enterStructure(builder(Items.BONE, "chilled_halls", FrameType.TASK, true, true, false), DEStructures.IcePit.getStructure()).parent(root).save(consumer, location("chilled_halls"));
        Advancement SevenWorldWonders = enterAnyStructure(builder(Items.COMPASS, "seven_world_wonders", FrameType.GOAL, true, true, false),
                new Structure<?>[] {
                        DEStructures.MonsterMaze.getStructure(),
                        DEStructures.LargeDungeon.getStructure(),
                        DEStructures.DesertTemple.getStructure(),
                        DEStructures.JungleMonument.getStructure(),
                        DEStructures.Castle.getStructure(),
                        DEStructures.MushroomHouse.getStructure(),
                        DEStructures.IcePit.getStructure()}
                ).requirements(IRequirementsStrategy.AND).parent(root).save(consumer, location("seven_world_wonders"));
        Advancement AmbitiousExplorer = enterAnyStructure(builder(Items.FILLED_MAP, "ambitious_explorer", FrameType.CHALLENGE, true, true, false), DEStructures.getAllStructures()).requirements(IRequirementsStrategy.AND).parent(root).save(consumer, location("ambitious_explorer"));
    }

    private Advancement.Builder enterAnyStructure(Advancement.Builder builder, Structure<?>[] structures){
        for(Structure<?> structure : structures){
            builder = enterStructure(builder, structure);
        }

        return builder;
    }

    private Advancement.Builder enterStructure(Advancement.Builder builder, Structure<?> structure){
        return builder.addCriterion(enterFeatureText(structure), PositionTrigger.Instance.located(LocationPredicate.inFeature(structure)));
    }

    private String enterFeatureText(Structure<?> structure){
        return "entered_" + Objects.requireNonNull(structure.getRegistryName()).getPath();
    }

    private Advancement.Builder builder(Item displayItem, String name, ResourceLocation background, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden) {
        return Advancement.Builder.advancement().display(displayItem, translate(name), translate(name + ".desc"), background, frameType, showToast, announceToChat, hidden);
    }

    private Advancement.Builder builder(Item displayItem, String name, FrameType frameType, boolean showToast, boolean announceToChat, boolean hidden){
        return builder(displayItem, name, null, frameType, showToast, announceToChat, hidden);
    }

    private TranslationTextComponent translate(String key) {return new TranslationTextComponent("advancements.dungeons_enhanced." + key);}

    private String location(String key) {return DungeonsEnhanced.ModID + ":" + key;}
}