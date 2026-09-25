package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.data.DETags;
import com.ametrin.structures.api.structure.DeferredStructureRegister;
import com.ametrin.structures.api.structure.simple.HeightAnchor;
import com.ametrin.structures.api.structure.simple.HeightMode;
import com.ametrin.structures.api.structure.simple.SimpleStructure;
import com.ametrin.structures.impl.processor.RemoveFoamProcessor;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public final class DEStructures {
    public static final DeferredStructureRegister REGISTER = new DeferredStructureRegister(DungeonsEnhanced.MOD_ID);

    // Overworld
//    public static final StructureRegistrar<ExtendedJigsawStructure> CASTLE;
//    public static final StructureRegistrar<ExtendedJigsawStructure> DEEP_CRYPT;
//    public static final StructureRegistrar<DEDesertTemple> DESERT_TEMPLE;
//    public static final StructureRegistrar<ExtendedJigsawStructure> DESERT_TOMB;
//    public static final StructureRegistrar<ExtendedJigsawStructure> DRUID_CIRCLE;
    public static final SimpleStructure.Keys DUNGEON_VARIANT;
    //    public static final StructureRegistrar<DEEldersTemple> ELDERS_TEMPLE;
    public static final SimpleStructure.Keys FISHING_SHIP;
    public static final SimpleStructure.Keys FLYING_DUTCHMAN;
    public static final SimpleStructure.Keys HAY_STORAGE;
    //    public static final StructureRegistrar<DEIcePit> ICE_PIT;
    public static final SimpleStructure.Keys JUNGLE_MONUMENT;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> LARGE_DUNGEON;
    public static final SimpleStructure.Keys MINERS_HOUSE;
    //    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE_DARK;
//    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE_PALE;
//    public static final StructureRegistrar<DEGroundStructure> MUSHROOM_HOUSE;
//    public static final StructureRegistrar<ExtendedJigsawStructure> PILLAGER_CAMP;
    public static final SimpleStructure.Keys PIRATE_SHIP;
    public static final SimpleStructure.Keys RUINED_BUILDING;
    public static final SimpleStructure.Keys STABLES;
    public static final SimpleStructure.Keys SUNKEN_SHRINE;
    public static final SimpleStructure.Keys TALL_WITCH_HUT;
    public static final SimpleStructure.Keys TREE_HOUSE;
    public static final SimpleStructure.Keys TOWER_OF_THE_UNDEAD;
//    public static final StructureRegistrar<DEGroundStructure> WATCH_TOWER;
//    public static final StructureRegistrar<DEGroundStructure> WITCH_TOWER;

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
//        DESERT_TEMPLE = StructureRegistrar.builder(locate(DEStructureIDs.DESERT_TEMPLE), ()-> ()-> DEDesertTemple.CODEC)
//                .placement(()-> gridPlacement(39, 86).build(DEStructures.DESERT_TEMPLE))
//                .addPiece(()-> DEDesertTemple.Piece::new)
//                .pushStructure(DEDesertTemple::new)
//                        .biomes(DETags.Biomes.HAS_DESERT_TEMPLE)
//                .popStructure()
//                .build();
//
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
                .placement(19, 0.59f)
                .between(HeightAnchor.aboveBottom(8), HeightAnchor.oceanFloor(-32))
                .biomes(DETags.Biomes.HAS_DUNGEON_VARIANT)
                .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .build();

//        ELDERS_TEMPLE = StructureRegistrar.builder(locate(DEStructureIDs.ELDERS_TEMPLE), ()-> ()-> DEEldersTemple.CODEC)
//                .placement(()-> gridPlacement(24).build(DEStructures.ELDERS_TEMPLE))
//                .addPiece(()-> DEEldersTemple.Piece::new)
//                .pushStructure(DEEldersTemple::new)
//                        .biomes(DETags.Biomes.HAS_ELDERS_TEMPLE)
//                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
//                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, ()-> spawns(spawn(EntityType.GUARDIAN, 1,2,4)))
//                .popStructure()
//                .build();

        FISHING_SHIP = REGISTER.simple(DEStructureIDs.FISHING_SHIP)
                .single(b -> b.template("fishing_ship").yOffset(-3))
                .placement(48, 0.68f)
                .surface()
                .biomes(DETags.Biomes.HAS_FISHING_SHIP)
                .build();

        FLYING_DUTCHMAN = REGISTER.simple(DEStructureIDs.FLYING_DUTCHMAN)
                .single("flying_dutchman")
                .placement(b -> b.spacing(134).probability(0.63f).minChunksFromCenter(12))
                .between(HeightAnchor.surface(48), HeightAnchor.belowTop(24))
                .biomes(DETags.Biomes.HAS_FLYING_DUTCHMAN)
                .build();

        HAY_STORAGE = REGISTER.simple(DEStructureIDs.HAY_STORAGE)
                .weighted(b -> b
                        .add("hay_storage/small", 3)
                        .add("hay_storage/big", 2))
                .placement(23, 0.77f)
                .surface()
                .biomes(DETags.Biomes.HAS_HAY_STORAGE)
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

//        ICE_PIT = StructureRegistrar.builder(locate(DEStructureIDs.ICE_PIT), () -> () -> DEIcePit.CODEC)
//                .addPiece(() -> DEGroundStructure.Piece::new)
//                .placement(() -> gridPlacement(34, 77).build(DEStructures.ICE_PIT))
//                .pushStructure(DEIcePit::new)
//                .biomes(DETags.Biomes.HAS_ICE_PIT)
//                .popStructure()
//                .build();

        JUNGLE_MONUMENT = REGISTER.simple(DEStructureIDs.JUNGLE_MONUMENT)
                .single(b -> b.template("jungle_monument").processors(DEProcessorLists.JUNGLE_MONUMENT).yOffset(-9))
                .placement(46, 0.74f)
                .surface()
                .heightMode(HeightMode.MEAN)
//                .flatness(12)
                .biomes(DETags.Biomes.HAS_JUNGLE_MONUMENT)
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
                .placement(24, 0.8f)
                .surface()
                .biomes(DETags.Biomes.HAS_MINERS_HOUSE)
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

//        MUSHROOM_HOUSE = StructureRegistrar.builder(locate(DEStructureIDs.MUSHROOM_HOUSE), () -> () -> DEGroundStructure.CODEC_MUSHROOM_HOUSE)
//                .placement(() -> gridPlacement(19, 83).allowedNearSpawn(true).build(DEStructures.MUSHROOM_HOUSE))
//                .addPiece(() -> DEGroundStructure.Piece::new)
//                .pushStructure(DEGroundStructure::MushroomHouse)
//                .biomes(DETags.Biomes.HAS_MUSHROOM_HOUSE)
//                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
//                .popStructure()
//                .build();

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
                .list(lb -> lb.add("pirate_ship/front").add(b -> b.template("pirate_ship/back").offset(new BlockPos(25, 0, 0))))
                .placement(67, 0.49F)
                .surface()
                .biomes(DETags.Biomes.HAS_PIRATE_SHIP)
//                .filterMinWaterDepth(6)
                .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 3, 4), spawn(EntityType.VINDICATOR, 3, 1, 2))))
                .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .build();

        RUINED_BUILDING = REGISTER.simple(DEStructureIDs.RUINED_BUILDING)
                .weighted(b -> b
                        .add("ruined_building/house", 3)
                        .add("ruined_building/barn", 3)
                        .add("ruined_building/house_big", 2))
                .placement(27, 0.54f)
                .surface()
                .biomes(DETags.Biomes.HAS_RUINED_BUILDING)
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

        STABLES = REGISTER.simple(DEStructureIDs.STABLES)
                .single(b -> b.template("stables").yOffset(-4))
                .placement(53, 0.52f)
                .surface()
                .biomes(DETags.Biomes.HAS_STABLES)
                .build();

        SUNKEN_SHRINE = REGISTER.simple(DEStructureIDs.SUNKEN_SHRINE)
                .weighted(b -> b
                        .add(bt -> bt.template("sunken_shrine/small").processors(List.of(RemoveFoamProcessor.filledWith(Fluids.WATER))), 2)
                        .add(bt -> bt.template("sunken_shrine/big").yOffset(-1).processors(List.of(RemoveFoamProcessor.filledWith(Fluids.WATER))), 1))
                .placement(32, 0.55f)
                .oceanFloor()
                .filterUnderwater(5)
                .biomes(DETags.Biomes.HAS_SUNKEN_SHRINE)
                .build();

        TALL_WITCH_HUT = REGISTER.simple(DEStructureIDs.TALL_WITCH_HUT)
                .single(b -> b.template("tall_witch_hut").yOffset(-3))
                .placement(21, 0.61f)
                .surface()
                .filterMaxWaterDepth(4)
                .biomes(DETags.Biomes.HAS_TALL_WITCH_HUT)
                .build();


        TREE_HOUSE = REGISTER.simple(DEStructureIDs.TREE_HOUSE)
                .single("tree_house")
                .placement(29, 0.4f)
                .surface()
                .biomes(DETags.Biomes.HAS_TREE_HOUSE)
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

        TOWER_OF_THE_UNDEAD = REGISTER.simple(DEStructureIDs.TOWER_OF_THE_UNDEAD)
                .weighted(bw -> bw
                        .add("tower_of_the_undead/small", 3)
                        .add("tower_of_the_undead/big", 2))
                .placement(49, 0.65f)
                .surface()
//                .avoidWater()
                .biomes(DETags.Biomes.HAS_TOWER_OF_THE_UNDEAD)
                .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                .build();

//        WATCH_TOWER = REGISTER.simple(locate(DEStructureIDs.WATCH_TOWER), () -> () -> DEGroundStructure.CODEC_WATCH_TOWER)
//                .placement(() -> gridPlacement(27, 45).allowedNearSpawn(true).build(DEStructures.WATCH_TOWER))
//                .addPiece(() -> DEGroundStructure.Piece::new)
//                .pushStructure(DEGroundStructure::WatchTower)
//                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
//                .biomes(DETags.Biomes.HAS_WATCH_TOWER)
//                .popStructure()
//                .build();

//        WITCH_TOWER = StructureRegistrar.builder(locate(DEStructureIDs.WITCH_TOWER), () -> () -> DEGroundStructure.CODEC_WITCH_TOWER)
//                .placement(() -> gridPlacement(79, 54).allowedNearSpawn(true).build(DEStructures.WITCH_TOWER))
//                .addPiece(() -> DEGroundStructure.Piece::new)
//                .pushStructure(DEGroundStructure::WitchTower)
//                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
//                .biomes(DETags.Biomes.HAS_WITCH_TOWER)
//                .popStructure()
//                .build();

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

//    public static final StructureRegistrar<?>[] ALL_STRUCTURE_REGISTRARS = {
//            CASTLE,
//            DEEP_CRYPT,
//            DESERT_TEMPLE,
//            DESERT_TOMB,
//            DRUID_CIRCLE,
//            DUNGEON_VARIANT,
//            ELDERS_TEMPLE,
//            FISHING_SHIP,
//            FLYING_DUTCHMAN,
//            HAY_STORAGE,
//            ICE_PIT,
//            JUNGLE_MONUMENT,
//            LARGE_DUNGEON,
//            MINERS_HOUSE,
//            MONSTER_MAZE_DARK,
//            MONSTER_MAZE_PALE,
//            MUSHROOM_HOUSE,
//            PILLAGER_CAMP,
//            PIRATE_SHIP,
//            RUINED_BUILDING,
//            STABLES,
//            SUNKEN_SHRINE,
//            TALL_WITCH_HUT,
//            TOWER_OF_THE_UNDEAD,
//            TREE_HOUSE,
//            WATCH_TOWER,
//            WITCH_TOWER,
//
//            BLACK_CITADEL,
//    };

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
//
//    private static GridStructurePlacement.Builder gridPlacement(int spacing, int probability) {
//        return gridPlacement(spacing).probability(probability / 100f);
//    }
//
//    private static GridStructurePlacement.Builder gridPlacement(int spacing) {
//        return GridStructurePlacement.builder().spacing(spacing).offset((int) (spacing * 0.8));
//    }
//
//    private static ExtendedJigsawStructure.Builder extendedJigsawStructure(BootstrapContext<?> context, Structure.StructureSettings settings, JigsawCapability capability, ResourceKey<StructureTemplatePool> poolKey, int maxDepth, HeightProvider heightProvider) {
//        return ExtendedJigsawStructure.builder(settings, context.lookup(Registries.TEMPLATE_POOL).getOrThrow(poolKey)).maxDepth(maxDepth).startHeight(heightProvider).capability(capability);
//    }
}