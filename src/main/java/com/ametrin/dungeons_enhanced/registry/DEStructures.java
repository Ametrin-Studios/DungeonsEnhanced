package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.data.DETags;
import com.ametrin.dungeons_enhanced.world.structure.DEIcePitPieces;
import com.ametrin.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.ametrin.structures.api.structure.DeferredStructureRegister;
import com.ametrin.structures.api.structure.simple.HeightAnchor;
import com.ametrin.structures.api.structure.simple.HeightMode;
import com.ametrin.structures.api.structure.simple.SimpleStructure;
import com.ametrin.structures.impl.processor.RemoveFoamProcessor;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

import java.util.List;

public final class DEStructures {
    public static final DeferredStructureRegister REGISTER = new DeferredStructureRegister(DungeonsEnhanced.MOD_ID);

    // Overworld
//    public static final StructureRegistrar<ExtendedJigsawStructure> CASTLE;
//    public static final StructureRegistrar<ExtendedJigsawStructure> DEEP_CRYPT;
    public static final SimpleStructure.Keys DESERT_TEMPLE;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> DESERT_TOMB;
//    public static final StructureRegistrar<ExtendedJigsawStructure> DRUID_CIRCLE;
    public static final SimpleStructure.Keys DUNGEON_VARIANT;
    public static final SimpleStructure.Keys ELDERS_TEMPLE;
    public static final SimpleStructure.Keys FISHING_SHIP;
    public static final SimpleStructure.Keys FLYING_DUTCHMAN;
    public static final SimpleStructure.Keys HAY_STORAGE;
    public static final SimpleStructure.Keys ICE_PIT;
    public static final SimpleStructure.Keys JUNGLE_MONUMENT;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> LARGE_DUNGEON;
    public static final SimpleStructure.Keys MINERS_HOUSE;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE_DARK;
//    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE_PALE;
    public static final SimpleStructure.Keys MUSHROOM_HOUSE;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> PILLAGER_CAMP;
    public static final SimpleStructure.Keys PIRATE_SHIP;
    public static final SimpleStructure.Keys RUINED_BUILDING;
    public static final SimpleStructure.Keys STABLES;
    public static final SimpleStructure.Keys SUNKEN_SHRINE;
    public static final SimpleStructure.Keys TALL_WITCH_HUT;
    public static final SimpleStructure.Keys TREE_HOUSE;
    public static final SimpleStructure.Keys TOWER_OF_THE_UNDEAD;
    public static final SimpleStructure.Keys WATCH_TOWER;
    public static final SimpleStructure.Keys WITCH_TOWER;

    // Nether
//    public static final StructureRegistrar<ExtendedJigsawStructure> BLACK_CITADEL;

    private DEStructures() {
    }

    static {
        //Overworld
//        CASTLE = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.CASTLE))
//                .placement(()-> gridPlacement(69, 78).build(DEStructures.CASTLE))
//                .addPiece(()-> DECastle.Piece::new)
//                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DECastle.Capability.INSTANCE, DETemplatePools.CASTLE, 1, ConstantHeight.ZERO).onSurface().build())
//                        .terrainAdjustment(TerrainAdjustment.BEARD_BOX)
//                        .biomes(DETags.Biomes.HAS_CASTLE)
//                .popStructure()
//                .build();
//
//        DEEP_CRYPT = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.DEEP_CRYPT))
//                .placement(()-> gridPlacement(39, 67).build(DEStructures.DEEP_CRYPT))
//                .addPiece(()-> DEDeepCrypt.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEDeepCrypt.Capability.INSTANCE, DETemplatePools.DEEP_CRYPT, 4, UniformHeight.of(VerticalAnchor.aboveBottom(16), VerticalAnchor.aboveBottom(48))).build())
//                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
//                        .biomes(DETags.Biomes.HAS_DEEP_CRYPT)
//                .popStructure()
//                .build();
//
        DESERT_TEMPLE = REGISTER.simple(DEStructureIDs.DESERT_TEMPLE)
                .compound(b -> b
                        .add(t -> t.template("desert_temple/main").yOffset(-6))
                        .add(t -> t.template("desert_temple/down").offset(15, -17, 2))
                        .add(t -> t.template("desert_temple/down").offset(25, -17, 16))
                        .add(t -> t.template("desert_temple/down").offset(13, -17, 14))
                )
                .horizontalPlacement(b -> b.spacing(39).probability(0.86f).minChunksFromCenter(12))
                .biomes(DETags.Biomes.HAS_DESERT_TEMPLE)
                .surface()
                .build();

//        DESERT_TOMB = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.DESERT_TOMB))
//                .placement(()-> gridPlacement(29, 65).allowedNearSpawn(true).build(DEStructures.DESERT_TOMB))
//                .addPiece(()-> DEDesertTomb.Piece::new)
//                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDesertTomb.Capability.INSTANCE, DETemplatePools.DESERT_TOMB, 5, ConstantHeight.ZERO).onSurface().build())
//                        .biomes(DETags.Biomes.HAS_DESERT_TOMB)
//                .popStructure()
//                .build();
//
//        DRUID_CIRCLE = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.DRUID_CIRCLE))
//                .placement(()-> gridPlacement(41, 68).allowedNearSpawn(true).build(DEStructures.DRUID_CIRCLE))
//                .addPiece(()-> DEDruidCircle.Piece::new)
//                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDruidCircle.Capability.INSTANCE, DETemplatePools.DRUID_CIRCLE, 1, ConstantHeight.ZERO).onSurface().build())//TODO: make own tag
//                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
//                        .biomes(DETags.Biomes.HAS_DRUID_CIRCLE)
//                .popStructure()
//                .build();

        DUNGEON_VARIANT = REGISTER.simple(DEStructureIDs.DUNGEON_VARIANT)
                .weighted(builder -> builder
                        .add(b -> b.template("dungeon_variant/zombie").processors(DEProcessorLists.DUNGEON_VARIANT), 1)
                        .add(b -> b.template("dungeon_variant/skeleton").processors(DEProcessorLists.DUNGEON_VARIANT), 1)
                        .add(b -> b.template("dungeon_variant/spider").processors(DEProcessorLists.DUNGEON_VARIANT), 1)
                        .add(b -> b.template("dungeon_variant/special").processors(DEProcessorLists.DUNGEON_VARIANT), 1))
                .horizontalPlacement(19, 0.59f)
                .biomes(DETags.Biomes.HAS_DUNGEON_VARIANT)
                .between(HeightAnchor.aboveBottom(8), HeightAnchor.oceanFloor(-32))
                .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .build();

        ELDERS_TEMPLE = REGISTER.simple(DEStructureIDs.ELDERS_TEMPLE)
                .compound(b -> b
                        .add(tb -> tb.template("elders_temple/ne").offset(0, 0, -29))
                        .add(tb -> tb.template("elders_temple/nw").offset(-30, 0, -29))
                        .add(tb -> tb.template("elders_temple/se").offset(0, 0, 0))
                        .add(tb -> tb.template("elders_temple/sw").offset(-30, 0, 0))
                )
                .processors(List.of(RemoveFoamProcessor.WATER, DESwapDeadCoralsProcessor.INSTANCE))
                .biomes(DETags.Biomes.HAS_ELDERS_TEMPLE)
                .horizontalPlacement(b -> b.spacing(24).minChunksFromCenter(12))
                .verticalPlacementMode(HeightMode.MEAN)
                .oceanFloor(-8)
                .filterSubmerged(1)
                .foundation()
                .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.GUARDIAN, 1, 2, 4))))
                .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .build();

        FISHING_SHIP = REGISTER.simple(DEStructureIDs.FISHING_SHIP)
                .single(b -> b.template("fishing_ship").yOffset(-3))
                .horizontalPlacement(48, 0.68f)
                .biomes(DETags.Biomes.HAS_FISHING_SHIP)
                .surface()
                .build();

        FLYING_DUTCHMAN = REGISTER.simple(DEStructureIDs.FLYING_DUTCHMAN)
                .single("flying_dutchman")
                .horizontalPlacement(b -> b.spacing(134).probability(0.63f).minChunksFromCenter(12))
                .biomes(DETags.Biomes.HAS_FLYING_DUTCHMAN)
                .between(HeightAnchor.surface(48), HeightAnchor.belowTop(24))
                .build();

        HAY_STORAGE = REGISTER.simple(DEStructureIDs.HAY_STORAGE)
                .weighted(b -> b
                        .add("hay_storage/small", 3)
                        .add("hay_storage/big", 2))
                .horizontalPlacement(23, 0.77f)
                .biomes(DETags.Biomes.HAS_HAY_STORAGE)
                .surface()
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

        ICE_PIT = REGISTER.simple(DEStructureIDs.ICE_PIT)
                .pieces(_ -> DEIcePitPieces.INSTANCE)
                .horizontalPlacement(b -> b.spacing(34).probability(0.77f).minChunksFromCenter(12))
                .biomes(DETags.Biomes.HAS_ICE_PIT)
                .surface()
                .build();

        JUNGLE_MONUMENT = REGISTER.simple(DEStructureIDs.JUNGLE_MONUMENT)
                .single(b -> b.template("jungle_monument").processors(DEProcessorLists.JUNGLE_MONUMENT).yOffset(-9))
                .horizontalPlacement(46, 0.74f)
                .biomes(DETags.Biomes.HAS_JUNGLE_MONUMENT)
                .verticalPlacementMode(HeightMode.MEAN)
                .surface()
//                .flatness(12)
                .build();

//        LARGE_DUNGEON = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.LARGE_DUNGEON))
//                .placement(() -> gridPlacement(59, 56).allowedNearSpawn(true).build(DEStructures.LARGE_DUNGEON))
//                .addPiece(() -> DELargeDungeon.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DELargeDungeon.Capability.INSTANCE, DETemplatePools.LARGE_DUNGEON, 5, height(-16)).onSurface().build())
//                .biomes(DETags.Biomes.HAS_LARGE_DUNGEON)
//                .popStructure()
//                .build();

        MINERS_HOUSE = REGISTER.simple(DEStructureIDs.MINERS_HOUSE)
                .single(DEStructureIDs.MINERS_HOUSE)
                .horizontalPlacement(24, 0.8f)
                .biomes(DETags.Biomes.HAS_MINERS_HOUSE)
                .surface()
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

//        MONSTER_MAZE_DARK = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.MONSTER_MAZE_DARK))
//                .placement(() -> gridPlacement(28, 62).build(DEStructures.MONSTER_MAZE_DARK))
//                .addPiece(() -> DEMonsterMaze.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEMonsterMaze.Capability.INSTANCE, DETemplatePools.MONSTER_MAZE_DARK, 11, height(-26)).onSurface().build())
//                .biomes(DETags.Biomes.HAS_MONSTER_MAZE_DARK)
//                .popStructure()
//                .build();

//        MONSTER_MAZE_PALE = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.MONSTER_MAZE_PALE))
//                .placement(() -> gridPlacement(18, 62).build(DEStructures.MONSTER_MAZE_DARK))
//                .addPiece(() -> DEMonsterMaze.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEMonsterMaze.Capability.INSTANCE, DETemplatePools.MONSTER_MAZE_PALE, 11, height(-26)).onSurface().build())
//                .biomes(DETags.Biomes.HAS_MONSTER_MAZE_PALE)
//                .popStructure()
//                .build();

        MUSHROOM_HOUSE = REGISTER.simple(DEStructureIDs.MUSHROOM_HOUSE)
                .weighted(b -> b
                        .add("mushroom_house/red", 1)
                        .add("mushroom_house/brown", 1))
                .horizontalPlacement(19, 0.83f)
                .biomes(DETags.Biomes.HAS_MUSHROOM_HOUSE)
                .surface()
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

//        PILLAGER_CAMP = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.PILLAGER_CAMP))
//                .placement(() -> gridPlacement(56, 39).build(DEStructures.PILLAGER_CAMP))
//                .addPiece(() -> DEPillagerCamp.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEPillagerCamp.Capability.INSTANCE, DETemplatePools.PILLAGER_CAMP, 4, ConstantHeight.ZERO).onSurface().build())
//                .biomes(DETags.Biomes.HAS_PILLAGER_CAMP)
//                .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, () -> spawns(spawn(EntityType.PILLAGER, 4, 2, 3), spawn(EntityType.VINDICATOR, 2, 1, 2)))
//                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
//                .popStructure()
//                .build();

        PIRATE_SHIP = REGISTER.simple(DEStructureIDs.PIRATE_SHIP)
                .compound(b -> b
                        .add(t -> t.template("pirate_ship/front").offset(-25, 0, 0))
                        .add(t -> t.template("pirate_ship/back")))
                .horizontalPlacement(67, 0.49F)
                .biomes(DETags.Biomes.HAS_PIRATE_SHIP)
                .surface()
//                .filterMinWaterDepth(6)
                .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 3, 4), spawn(EntityType.VINDICATOR, 3, 1, 2))))
                .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .build();

        RUINED_BUILDING = REGISTER.simple(DEStructureIDs.RUINED_BUILDING)
                .weighted(b -> b
                        .add("ruined_building/house", 3)
                        .add("ruined_building/barn", 3)
                        .add("ruined_building/house_big", 2))
                .horizontalPlacement(27, 0.54f)
                .biomes(DETags.Biomes.HAS_RUINED_BUILDING)
                .surface()
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

        STABLES = REGISTER.simple(DEStructureIDs.STABLES)
                .single(b -> b.template("stables").yOffset(-4))
                .horizontalPlacement(53, 0.52f)
                .biomes(DETags.Biomes.HAS_STABLES)
                .surface()
                .build();

        SUNKEN_SHRINE = REGISTER.simple(DEStructureIDs.SUNKEN_SHRINE)
                .weighted(b -> b
                        .add(bt -> bt.template("sunken_shrine/small"), 2)
                        .add(bt -> bt.template("sunken_shrine/big").yOffset(-1), 1))
                .processors(List.of(RemoveFoamProcessor.WATER))
                .horizontalPlacement(32, 0.55f)
                .biomes(DETags.Biomes.HAS_SUNKEN_SHRINE)
                .oceanFloor()
                .filterSubmerged(5)
                .build();

        TALL_WITCH_HUT = REGISTER.simple(DEStructureIDs.TALL_WITCH_HUT)
                .single(b -> b.template("tall_witch_hut").yOffset(-3))
                .horizontalPlacement(21, 0.61f)
                .biomes(DETags.Biomes.HAS_TALL_WITCH_HUT)
                .surface()
                .filterMaxWaterDepth(4)
                .build();


        TREE_HOUSE = REGISTER.simple(DEStructureIDs.TREE_HOUSE)
                .single("tree_house")
                .horizontalPlacement(29, 0.4f)
                .biomes(DETags.Biomes.HAS_TREE_HOUSE)
                .surface()
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

        TOWER_OF_THE_UNDEAD = REGISTER.simple(DEStructureIDs.TOWER_OF_THE_UNDEAD)
                .weighted(bw -> bw
                        .add("tower_of_the_undead/small", 3)
                        .add("tower_of_the_undead/big", 2))
                .horizontalPlacement(49, 0.65f)
                .biomes(DETags.Biomes.HAS_TOWER_OF_THE_UNDEAD)
                .surface()
//                .avoidWater()
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

        WATCH_TOWER = REGISTER.simple(DEStructureIDs.WATCH_TOWER)
                .single("watch_tower")
                .horizontalPlacement(27, 0.45f)
                .biomes(DETags.Biomes.HAS_WATCH_TOWER)
                .surface()
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

        WITCH_TOWER = REGISTER.simple(DEStructureIDs.WITCH_TOWER)
                .weighted(b -> b
                        .add("witch_tower/normal", 3)
                        .add("witch_tower/big", 2))
                .horizontalPlacement(79, 0.54f)
                .biomes(DETags.Biomes.HAS_WITCH_TOWER)
                .surface()
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

        // Nether
//        BLACK_CITADEL = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.BLACK_CITADEL))
//                .placement(() -> gridPlacement(67, 75).build(DEStructures.BLACK_CITADEL))
//                .addPiece(() -> DEBlackCitadel.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEBlackCitadel.Capability.INSTANCE, DETemplatePools.BLACK_CITADEL, 6, height(28)).maxDistanceFromCenter(116).build())
//                .biomes(DETags.Biomes.HAS_BLACK_CITADEL)
//                .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.PIECE, () -> spawns(spawn(EntityType.WITHER_SKELETON, 4, 2, 5), spawn(EntityType.SKELETON, 1, 1, 3)))
//                .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES) //needs to generate after the basalt
//                .terrainAdjustment(TerrainAdjustment.BEARD_BOX)
//                .popStructure()
//                .build();
    }

    private static ConstantHeight height(int y) {
        return ConstantHeight.of(new VerticalAnchor.Absolute(y));
    }

    @SafeVarargs
    private static WeightedList<MobSpawnSettings.SpawnerData> spawns(Weighted<MobSpawnSettings.SpawnerData>... spawns) {
        return WeightedList.of(spawns);
    }

    private static Weighted<MobSpawnSettings.SpawnerData> spawn(EntityType<?> entity, int weight, int min, int max) {
        return new Weighted<>(new MobSpawnSettings.SpawnerData(entity, min, max), weight);
    }
//    private static ExtendedJigsawStructure.Builder extendedJigsawStructure(BootstrapContext<?> context, Structure.StructureSettings settings, JigsawCapability capability, ResourceKey<StructureTemplatePool> poolKey, int maxDepth, HeightProvider heightProvider) {
//        return ExtendedJigsawStructure.builder(settings, context.lookup(Registries.TEMPLATE_POOL).getOrThrow(poolKey)).maxDepth(maxDepth).startHeight(heightProvider).capability(capability);
//    }
}