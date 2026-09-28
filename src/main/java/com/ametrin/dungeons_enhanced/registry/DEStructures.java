package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.data.DETags;
import com.ametrin.dungeons_enhanced.world.structure.DEIcePitPieces;
import com.ametrin.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.ametrin.structures.foam.RemoveFoamProcessor;
import com.ametrin.structures.structure.DeferredStructureHolder;
import com.ametrin.structures.structure.DeferredStructureRegister;
import com.ametrin.structures.structure.simple.HeightAnchor;
import com.ametrin.structures.structure.simple.HeightMode;
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
    public static final DeferredStructureHolder CASTLE;
    //    public static final DeferredStructureHolder<ExtendedJigsawStructure> DEEP_CRYPT;
    public static final DeferredStructureHolder DESERT_TEMPLE;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> DESERT_TOMB;
//    public static final StructureRegistrar<ExtendedJigsawStructure> DRUID_CIRCLE;
    public static final DeferredStructureHolder DUNGEON_VARIANT;
    public static final DeferredStructureHolder ELDERS_TEMPLE;
    public static final DeferredStructureHolder FISHING_SHIP;
    public static final DeferredStructureHolder FLYING_DUTCHMAN;
    public static final DeferredStructureHolder HAY_STORAGE;
    public static final DeferredStructureHolder ICE_PIT;
    public static final DeferredStructureHolder JUNGLE_MONUMENT;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> LARGE_DUNGEON;
    public static final DeferredStructureHolder MINERS_HOUSE;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE_DARK;
//    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE_PALE;
    public static final DeferredStructureHolder MUSHROOM_HOUSE;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> PILLAGER_CAMP;
    public static final DeferredStructureHolder PIRATE_SHIP;
    public static final DeferredStructureHolder RUINED_BUILDING;
    public static final DeferredStructureHolder STABLES;
    public static final DeferredStructureHolder SUNKEN_SHRINE;
    public static final DeferredStructureHolder TALL_WITCH_HUT;
    public static final DeferredStructureHolder TREE_HOUSE;
    public static final DeferredStructureHolder TOWER_OF_THE_UNDEAD;
    public static final DeferredStructureHolder WATCH_TOWER;
    public static final DeferredStructureHolder WITCH_TOWER;

    // Nether
//    public static final StructureRegistrar<ExtendedJigsawStructure> BLACK_CITADEL;

    private DEStructures() {
    }

    static {
        // Overworld
        CASTLE = REGISTER.set(DEStructureIDs.CASTLE)
                .scatteredGridPlacement(p -> p.spacing(69).probability(0.78f).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_CASTLE)
                        .surface()
                        .verticalPlacementMode(HeightMode.MEAN)
                        .filterFlatness(8)
                        .weighted(b -> b
                                .compound(c1 -> c1
                                        .single(t -> t.template("castle/top1").yOffset(-1))
                                        .single(t -> t.template("castle/bottom1").offset(-8, -5, -8)), 1)
                                .compound(c1 -> c1
                                        .single(t -> t.template("castle/top2").yOffset(-1))
                                        .single(t -> t.template("castle/bottom2").offset(-8, -5, -8)), 1)
                        )
                        .terrainAdaptation(TerrainAdjustment.BEARD_BOX)
                ).build();

//        DEEP_CRYPT = REGISTER.jigsaw(DEStructureIDs.DEEP_CRYPT)
//                .horizontalPlacement(b-> b.spacing(39).probability(0.67f))
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEDeepCrypt.Capability.INSTANCE, DETemplatePools.DEEP_CRYPT, 4, UniformHeight.of(VerticalAnchor.aboveBottom(16), VerticalAnchor.aboveBottom(48))).build())
//                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
//                        .biomes(DETags.Biomes.HAS_DEEP_CRYPT)
//                .popStructure()
//                .build();

        DESERT_TEMPLE = REGISTER.set(DEStructureIDs.DESERT_TEMPLE)
                .scatteredGridPlacement(b -> b.spacing(39).probability(0.86f).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_DESERT_TEMPLE)
                        .surface()
                        .compound(b -> b
                                .single(t -> t.template("desert_temple/main").yOffset(-6))
                                .single(t -> t.template("desert_temple/down").offset(15, -17, 2))
                                .single(t -> t.template("desert_temple/down").offset(25, -17, 16))
                                .single(t -> t.template("desert_temple/down").offset(13, -17, 14))
                        )
                ).build();

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

        DUNGEON_VARIANT = REGISTER.set(DEStructureIDs.DUNGEON_VARIANT)
                .scatteredGridPlacement(19, 0.59f)
                .simple(s -> s
                        .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .biomes(DETags.Biomes.HAS_DUNGEON_VARIANT)
                        .between(HeightAnchor.aboveBottom(8), HeightAnchor.oceanFloor(-24))
                        .weighted(builder -> builder
                                .single(b -> b.template("dungeon_variant/zombie").processors(DEProcessorLists.DUNGEON_VARIANT), 1)
                                .single(b -> b.template("dungeon_variant/skeleton").processors(DEProcessorLists.DUNGEON_VARIANT), 1)
                                .single(b -> b.template("dungeon_variant/spider").processors(DEProcessorLists.DUNGEON_VARIANT), 1)
                                .single(b -> b.template("dungeon_variant/special").processors(DEProcessorLists.DUNGEON_VARIANT), 1))
                ).build();

        ELDERS_TEMPLE = REGISTER.set(DEStructureIDs.ELDERS_TEMPLE)
                .scatteredGridPlacement(b -> b.spacing(24).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_ELDERS_TEMPLE)
                        .oceanFloor(-6)
                        .verticalPlacementMode(HeightMode.MEAN)
                        .filterSubmerged(1)
                        .compound(b -> b
                                .single(tb -> tb.template("elders_temple/ne").offset(0, 0, -29))
                                .single(tb -> tb.template("elders_temple/nw").offset(-30, 0, -29))
                                .single(tb -> tb.template("elders_temple/se").offset(0, 0, 0))
                                .single(tb -> tb.template("elders_temple/sw").offset(-30, 0, 0))
                        )
                        .processors(List.of(RemoveFoamProcessor.WATER, DESwapDeadCoralsProcessor.INSTANCE))
                        .foundation()
                        .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.GUARDIAN, 1, 2, 4))))
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                ).build();

        FISHING_SHIP = REGISTER.set(DEStructureIDs.FISHING_SHIP)
                .scatteredGridPlacement(48, 0.68f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_FISHING_SHIP)
                        .surface()
                        .single(b -> b.template("fishing_ship").yOffset(-3))
                ).build();

        FLYING_DUTCHMAN = REGISTER.set(DEStructureIDs.FLYING_DUTCHMAN)
                .scatteredGridPlacement(b -> b.spacing(134).probability(0.63f).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_FLYING_DUTCHMAN)
                        .between(HeightAnchor.surface(48), HeightAnchor.belowTop(24))
                        .single("flying_dutchman")
                ).build();

        HAY_STORAGE = REGISTER.set(DEStructureIDs.HAY_STORAGE)
                .scatteredGridPlacement(23, 0.77f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_HAY_STORAGE)
                        .surface()
                        .weighted(b -> b
                                .single("hay_storage/small", 3)
                                .single("hay_storage/big", 2))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                ).build();

        ICE_PIT = REGISTER.set(DEStructureIDs.ICE_PIT)
                .scatteredGridPlacement(b -> b.spacing(34).probability(0.77f).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_ICE_PIT)
                        .surface()
                        .pieces(_ -> DEIcePitPieces.INSTANCE)
                ).build();

        JUNGLE_MONUMENT = REGISTER.set(DEStructureIDs.JUNGLE_MONUMENT)
                .scatteredGridPlacement(46, 0.74f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_JUNGLE_MONUMENT)
                        .surface()
                        .verticalPlacementMode(HeightMode.MEAN)
//                        .filterFlatness(12)
                        .single(b -> b.template("jungle_monument").processors(DEProcessorLists.JUNGLE_MONUMENT).yOffset(-9))
                ).build();

//        LARGE_DUNGEON = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.LARGE_DUNGEON))
//                .placement(() -> gridPlacement(59, 56).allowedNearSpawn(true).build(DEStructures.LARGE_DUNGEON))
//                .addPiece(() -> DELargeDungeon.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DELargeDungeon.Capability.INSTANCE, DETemplatePools.LARGE_DUNGEON, 5, height(-16)).onSurface().build())
//                .biomes(DETags.Biomes.HAS_LARGE_DUNGEON)
//                .popStructure()
//                ).build();

        MINERS_HOUSE = REGISTER.set(DEStructureIDs.MINERS_HOUSE)
                .scatteredGridPlacement(24, 0.8f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_MINERS_HOUSE)
                        .surface()
                        .single(DEStructureIDs.MINERS_HOUSE)
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                ).build();

//        MONSTER_MAZE_DARK = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.MONSTER_MAZE_DARK))
//                .placement(() -> gridPlacement(28, 62).build(DEStructures.MONSTER_MAZE_DARK))
//                .addPiece(() -> DEMonsterMaze.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEMonsterMaze.Capability.INSTANCE, DETemplatePools.MONSTER_MAZE_DARK, 11, height(-26)).onSurface().build())
//                .biomes(DETags.Biomes.HAS_MONSTER_MAZE_DARK)
//                .popStructure()
//                ).build();

//        MONSTER_MAZE_PALE = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.MONSTER_MAZE_PALE))
//                .placement(() -> gridPlacement(18, 62).build(DEStructures.MONSTER_MAZE_DARK))
//                .addPiece(() -> DEMonsterMaze.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEMonsterMaze.Capability.INSTANCE, DETemplatePools.MONSTER_MAZE_PALE, 11, height(-26)).onSurface().build())
//                .biomes(DETags.Biomes.HAS_MONSTER_MAZE_PALE)
//                .popStructure()
//                ).build();

        MUSHROOM_HOUSE = REGISTER.set(DEStructureIDs.MUSHROOM_HOUSE)
                .scatteredGridPlacement(19, 0.83f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_MUSHROOM_HOUSE)
                        .surface()
                        .weighted(b -> b
                                .single("mushroom_house/red", 1)
                                .single("mushroom_house/brown", 1))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                ).build();

//        PILLAGER_CAMP = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.PILLAGER_CAMP))
//                .placement(() -> gridPlacement(56, 39).build(DEStructures.PILLAGER_CAMP))
//                .addPiece(() -> DEPillagerCamp.Piece::new)
//                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEPillagerCamp.Capability.INSTANCE, DETemplatePools.PILLAGER_CAMP, 4, ConstantHeight.ZERO).onSurface().build())
//                .biomes(DETags.Biomes.HAS_PILLAGER_CAMP)
//                .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, () -> spawns(spawn(EntityType.PILLAGER, 4, 2, 3), spawn(EntityType.VINDICATOR, 2, 1, 2)))
//                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
//                .popStructure()
//                ).build();

        PIRATE_SHIP = REGISTER.set(DEStructureIDs.PIRATE_SHIP)
                .scatteredGridPlacement(67, 0.49F)
                .simple(s -> s
                                .biomes(DETags.Biomes.HAS_PIRATE_SHIP)
                                .surface()
//                        .filterMinWaterDepth(6)
                                .compound(b -> b
                                        .single(t -> t.template("pirate_ship/front").offset(-25, 0, 0))
                                        .single(t -> t.template("pirate_ship/back")))
                                .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 3, 4), spawn(EntityType.VINDICATOR, 3, 1, 2))))
                                .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                ).build();

        RUINED_BUILDING = REGISTER.set(DEStructureIDs.RUINED_BUILDING)
                .scatteredGridPlacement(27, 0.54f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_RUINED_BUILDING)
                        .surface()
                        .weighted(b -> b
                                .single("ruined_building/house", 3)
                                .single("ruined_building/barn", 3)
                                .single("ruined_building/house_big", 2))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                ).build();

        STABLES = REGISTER.set(DEStructureIDs.STABLES)
                .scatteredGridPlacement(53, 0.52f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_STABLES)
                        .surface()
                        .single(b -> b.template("stables").yOffset(-4))
                ).build();

        SUNKEN_SHRINE = REGISTER.set(DEStructureIDs.SUNKEN_SHRINE)
                .scatteredGridPlacement(32, 0.55f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_SUNKEN_SHRINE)
                        .oceanFloor()
                        .weighted(b -> b
                                .single(bt -> bt.template("sunken_shrine/small"), 2)
                                .single(bt -> bt.template("sunken_shrine/big").yOffset(-1), 1))
                        .processors(List.of(RemoveFoamProcessor.WATER))
                        .filterSubmerged(5)
                ).build();

        TALL_WITCH_HUT = REGISTER.set(DEStructureIDs.TALL_WITCH_HUT)
                .scatteredGridPlacement(21, 0.61f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_TALL_WITCH_HUT)
                        .surface()
                        .single(b -> b.template("tall_witch_hut").yOffset(-3))
                        .filterMaxWaterDepth(4)
                ).build();


        TREE_HOUSE = REGISTER.set(DEStructureIDs.TREE_HOUSE)
                .scatteredGridPlacement(29, 0.4f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_TREE_HOUSE)
                        .surface()
                        .single("tree_house")
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                ).build();

        TOWER_OF_THE_UNDEAD = REGISTER.set(DEStructureIDs.TOWER_OF_THE_UNDEAD)
                .scatteredGridPlacement(49, 0.65f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_TOWER_OF_THE_UNDEAD)
                        .surface()
                        .weighted(bw -> bw
                                .single("tower_of_the_undead/small", 3)
                                .single("tower_of_the_undead/big", 2))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                ).build();

        WATCH_TOWER = REGISTER.set(DEStructureIDs.WATCH_TOWER)
                .scatteredGridPlacement(27, 0.45f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_WATCH_TOWER)
                        .surface()
                        .single("watch_tower")
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                ).build();

        WITCH_TOWER = REGISTER.set(DEStructureIDs.WITCH_TOWER)
                .scatteredGridPlacement(79, 0.54f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_WITCH_TOWER)
                        .surface()
                        .weighted(b -> b
                                .single("witch_tower/normal", 3)
                                .single("witch_tower/big", 2))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                ).build();

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
//                ).build();
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