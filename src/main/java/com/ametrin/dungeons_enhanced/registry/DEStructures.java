package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.data.DETags;
import com.ametrin.dungeons_enhanced.world.structure.DEIcePitPieces;
import com.ametrin.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.ametrin.structures.foam.RemoveFoamProcessor;
import com.ametrin.structures.processor.RetainExistingProcessor;
import com.ametrin.structures.structure.StructureBootstrap;
import com.ametrin.structures.structure.StructureSetKeys;
import com.ametrin.structures.structure.simple.HeightAnchor;
import com.ametrin.structures.structure.simple.HeightMode;
import com.ametrin.structures.structure.simple.TerrainBox;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

import java.util.List;

public final class DEStructures {
    public static final StructureBootstrap BOOTSTRAP = new StructureBootstrap(DungeonsEnhanced.MOD_ID);

    // update ambitious explorer advancement separately

    // Overworld
    public static final StructureSetKeys CASTLE;
    public static final StructureSetKeys DEEP_CRYPT;
    public static final StructureSetKeys DESERT_TEMPLE;
    public static final StructureSetKeys DESERT_TOMB;
    public static final StructureSetKeys DRUID_CIRCLE;
    public static final StructureSetKeys DUNGEON_VARIANT;
    public static final StructureSetKeys ELDERS_TEMPLE;
    public static final StructureSetKeys FISHING_SHIP;
    public static final StructureSetKeys FLYING_DUTCHMAN;
    public static final StructureSetKeys ICE_PIT;
    public static final StructureSetKeys JUNGLE_MONUMENT;
    public static final StructureSetKeys LARGE_DUNGEON;
    public static final StructureSetKeys MONSTER_MAZE;
    public static final StructureSetKeys MUSHROOM_HOUSE;
    public static final StructureSetKeys PILLAGER_CAMP;
    public static final StructureSetKeys PIRATE_SHIP;
    public static final StructureSetKeys RUINED_BUILDING;
    public static final StructureSetKeys STABLES;
    public static final StructureSetKeys SUNKEN_SHRINE;
    public static final StructureSetKeys TALL_WITCH_HUT;
    public static final StructureSetKeys TREE_HOUSE;
    public static final StructureSetKeys TOWER_OF_THE_UNDEAD;
    public static final StructureSetKeys WATCH_TOWER;
    public static final StructureSetKeys WITCH_TOWER;

    // Nether
    public static final StructureSetKeys BLACK_CITADEL;

    private DEStructures() {}

    static {
        // Overworld
        CASTLE = BOOTSTRAP.registerSet(DEStructureIDs.CASTLE, set -> set
                .evenSpreadPlacement(p -> p.minDistance(39).probability(0.74f).minChunksFromCenter(14))
                .simple("blue", 1, s -> s
                        .biomes(DETags.Biomes.HAS_CASTLE)
                        .surface(0, HeightMode.CORNERS_MEAN)
                        .onlyWhenPickedFirst()
                        .filterFlatness(8)
                        .single(t -> t.template("castle/blue").offset(0, -5, 0))
                        .terrainAdaptation(TerrainAdjustment.BEARD_BOX)
                )
                .simple("red", 1, s -> s
                        .biomes(DETags.Biomes.HAS_CASTLE)
                        .surface(0, HeightMode.CORNERS_MEAN)
                        .onlyWhenPickedFirst()
                        .filterFlatness(8)
                        .single(t -> t.template("castle/red").offset(0, -5, 0))
                        .terrainAdaptation(TerrainAdjustment.BEARD_BOX)
                )
        );

        DEEP_CRYPT = BOOTSTRAP.registerSet(DEStructureIDs.DEEP_CRYPT, set -> set
                .scatteredGridPlacement(p -> p.spacing(41).probability(0.64f))
                .jigsaw(DETemplatePools.DEEP_CRYPT, j -> j
                        .verticalPlacement(UniformHeight.of(VerticalAnchor.aboveBottom(16), VerticalAnchor.aboveBottom(52)))
                        .size(4)
                        .biomes(DETags.Biomes.HAS_DEEP_CRYPT)
                        .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                )
        );

        DESERT_TEMPLE = BOOTSTRAP.registerSet(DEStructureIDs.DESERT_TEMPLE, set -> set
                .evenSpreadPlacement(p -> p.minDistance(30).probability(0.86f).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_DESERT_TEMPLE)
                        .surface()
//                        .processors(List.of(DEBadlandsFromDesertProcessor.INSTANCE))
                        .compound(b -> b
                                .single(t -> t.template("desert_temple/main").yOffset(-6))
                                .single(t -> t.template("desert_temple/down").offset(15, -17, 2))
                                .single(t -> t.template("desert_temple/down").offset(25, -17, 16))
                                .single(t -> t.template("desert_temple/down").offset(13, -17, 14))
                        )
                        .foundation()
                )
        );

        DESERT_TOMB = BOOTSTRAP.registerSet(DEStructureIDs.DESERT_TOMB, set -> set
                .scatteredGridPlacement(p -> p.spacing(31).probability(0.71f))
                .jigsaw(DETemplatePools.DESERT_TOMB, j -> j
                        .surface()
                        .size(5)
//                        .processors(List.of(DEBadlandsFromDesertProcessor.INSTANCE))
                        .biomes(DETags.Biomes.HAS_DESERT_TOMB)
                )
        );

        DRUID_CIRCLE = BOOTSTRAP.registerSet(DEStructureIDs.DRUID_CIRCLE, set -> set
                .scatteredGridPlacement(41, 0.68f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_DRUID_CIRCLE)
                        .surface()
                        .weighted(p -> p
                                .single(3, t -> t.template(DEStructureIDs.DRUID_CIRCLE + "/small").yOffset(-1))
                                .single(2, t -> t.template(DEStructureIDs.DRUID_CIRCLE + "/big").yOffset(-4))
                        )
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
        );

        DUNGEON_VARIANT = BOOTSTRAP.registerSet(DEStructureIDs.DUNGEON_VARIANT, set -> set
                .scatteredGridPlacement(24, 0.53f)
                .simple("stone", 3, s -> s
                        .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .biomes(DETags.Biomes.HAS_DUNGEON_VARIANT)
                        .between(HeightAnchor.absolute(0), HeightAnchor.oceanFloor(-24))
                        .weighted(builder -> builder
                                .single(1, b -> b.template("dungeon_variant/zombie"))
                                .single(1, b -> b.template("dungeon_variant/skeleton"))
                                .single(1, b -> b.template("dungeon_variant/spider"))
                                .single(1, b -> b.template("dungeon_variant/special"))
                        )
                        .processors(DEProcessorLists.DUNGEON_VARIANT_STONE)
                )
                .simple("deepslate", 1, s -> s
                        .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .biomes(DETags.Biomes.HAS_DUNGEON_VARIANT)
                        .between(HeightAnchor.aboveBottom(8), HeightAnchor.absolute(-6))
                        .weighted(builder -> builder
                                .single(1, b -> b.template("dungeon_variant/copper_shrine"))
                        )
                        .processors(DEProcessorLists.DUNGEON_VARIANT_DEEPSLATE)
                )
        );

        ELDERS_TEMPLE = BOOTSTRAP.registerSet(DEStructureIDs.ELDERS_TEMPLE, set -> set
                .scatteredGridPlacement(p -> p.spacing(28).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_ELDERS_TEMPLE)
                        .oceanFloor(0, HeightMode.CORNERS_MEAN)
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
                )
        );

        FISHING_SHIP = BOOTSTRAP.registerSet(DEStructureIDs.FISHING_SHIP, set -> set
                .scatteredGridPlacement(p -> p.spacing(49).probability(0.62f).exclusionZone(DETags.Structures.FISHING_SHIP_EXCLUSION_ZONE, 10))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_FISHING_SHIP)
                        .surface()
                        .single(b -> b.template("fishing_ship").yOffset(-3))
                )
        );

        FLYING_DUTCHMAN = BOOTSTRAP.registerSet(DEStructureIDs.FLYING_DUTCHMAN, set -> set
                .scatteredGridPlacement(p -> p.spacing(137).probability(0.61f).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_FLYING_DUTCHMAN)
                        .between(HeightAnchor.surface(64), HeightAnchor.belowTop(24))
                        .single("flying_dutchman")
                )
        );

        ICE_PIT = BOOTSTRAP.registerSet(DEStructureIDs.ICE_PIT, set -> set
                .evenSpreadPlacement(b -> b.minDistance(36).probability(0.77f).minChunksFromCenter(14))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_ICE_PIT)
                        .surface()
                        .pieces(_ -> DEIcePitPieces.INSTANCE)
                        .filterHeightRange(0, 110)
                )
        );

        JUNGLE_MONUMENT = BOOTSTRAP.registerSet(DEStructureIDs.JUNGLE_MONUMENT, set -> set
                .scatteredGridPlacement(46, 0.74f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_JUNGLE_MONUMENT)
                        .surface(0, HeightMode.CORNERS_MEAN)
                        .single(b -> b.template("jungle_monument").yOffset(-9))
                        .processors(DEProcessorLists.JUNGLE_MONUMENT)
                        .filterWithinBiome(12)
                )
        );

        LARGE_DUNGEON = BOOTSTRAP.registerSet(DEStructureIDs.LARGE_DUNGEON, set -> set
                .scatteredGridPlacement(p -> p.spacing(59).probability(0.56f))
                .jigsaw(DETemplatePools.LARGE_DUNGEON, j -> j
                                .size(5)
                                .surface(-16)
                                .biomes(DETags.Biomes.HAS_LARGE_DUNGEON)
                )
        );

        MONSTER_MAZE = BOOTSTRAP.registerSet("monster_maze", set -> set
                .scatteredGridPlacement(p -> p.spacing(28).probability(0.62f).minChunksFromCenter(12))
                .jigsaw("dark", 1, DETemplatePools.MONSTER_MAZE_DARK, j -> j
                                .size(11)
                                .surface(-26)
                                .biomes(DETags.Biomes.HAS_MONSTER_MAZE_DARK)
                                .processors(DEProcessorLists.MONSTER_MAZE)
                ).jigsaw("pale", 1, DETemplatePools.MONSTER_MAZE_PALE, j -> j
                                .size(11)
                                .surface(-32)
                                .biomes(DETags.Biomes.HAS_MONSTER_MAZE_PALE)
                                .processors(DEProcessorLists.MONSTER_MAZE_PALE)
                )
        );

        MUSHROOM_HOUSE = BOOTSTRAP.registerSet(DEStructureIDs.MUSHROOM_HOUSE, set -> set
                .scatteredGridPlacement(19, 0.83f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_MUSHROOM_HOUSE)
                        .surface()
                        .weighted(b -> b
                                .single(1, t -> t.template("mushroom_house/red").terrainBox(TerrainBox.footprint()))
                                .single(1, t -> t.template("mushroom_house/brown").terrainBox(TerrainBox.footprint())))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
        );

        PILLAGER_CAMP = BOOTSTRAP.registerSet(DEStructureIDs.PILLAGER_CAMP, set -> set
                .scatteredGridPlacement(p -> p.spacing(56).probability(0.39f).minChunksFromCenter(8).exclusionZone(DETags.Structures.PILLAGER_CAMP_EXCLUSION_ZONE, 10))
                .jigsaw(DETemplatePools.PILLAGER_CAMP, j -> j
                                .surface()
                                .size(4)
                                .biomes(DETags.Biomes.HAS_PILLAGER_CAMP)
                                .terrainAdaptation(TerrainAdjustment.BEARD_BOX)
                                .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 2, 3), spawn(EntityType.VINDICATOR, 2, 1, 2))))
                )
        );

        PIRATE_SHIP = BOOTSTRAP.registerSet(DEStructureIDs.PIRATE_SHIP, set -> set
                .evenSpreadPlacement(53, 0.42F)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_PIRATE_SHIP)
                        .surface()
                        .compound(b -> b
                                .single(t -> t.template("pirate_ship/front").offset(0, -3, 0))
                                .single(t -> t.template("pirate_ship/back").offset(0, -3, -26))
                        )
                        .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 3, 4), spawn(EntityType.VINDICATOR, 3, 1, 2))))
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                )
        );

        RUINED_BUILDING = BOOTSTRAP.registerSet(DEStructureIDs.RUINED_BUILDING, set -> set
                .evenSpreadPlacement(21, 0.73f)
                .simple("house", 3, s -> s
                        .biomes(DETags.Biomes.HAS_RUINED_BUILDING)
                        .surface()
                        .weighted(b -> b
                                .single(3, t -> t.template("ruined_building/house").yOffset(-1))
                                .single(2, t -> t.template("ruined_building/house_big").yOffset(-1)))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .simple("barn", 2, s -> s
                        .biomes(DETags.Biomes.HAS_RUINED_BUILDING)
                        .surface()
                        .single(t -> t.template("ruined_building/barn").yOffset(-1))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .simple(DEStructureIDs.HAY_STORAGE, 1, s -> s
                        .biomes(DETags.Biomes.HAS_HAY_STORAGE)
                        .surface()
                        .weighted(b -> b
                                .single(3, "hay_storage/small")
                                .single(2, "hay_storage/big"))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .simple(DEStructureIDs.MINERS_HOUSE, 1, s -> s
                        .biomes(DETags.Biomes.HAS_MINERS_HOUSE)
                        .surface()
                        .single("ruined_building/miners_house")
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
        );

        STABLES = BOOTSTRAP.registerSet(DEStructureIDs.STABLES, set -> set
                .evenSpreadPlacement(b -> b.minDistance(50).probability(0.66f))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_STABLES)
                        .surface(0, HeightMode.CORNERS_MEAN)
                        .single(b -> b.template("stables").yOffset(-5))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                        .filterFlatness(8)
                )
        );

        SUNKEN_SHRINE = BOOTSTRAP.registerSet(DEStructureIDs.SUNKEN_SHRINE, set -> set
                .evenSpreadPlacement(29, 0.55f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_SUNKEN_SHRINE)
                        .oceanFloor()
                        .weighted(b -> b
                                .single(2, bt -> bt.template("sunken_shrine/small"))
                                .single(1, bt -> bt.template("sunken_shrine/big").yOffset(-1)))
                        .processors(List.of(RemoveFoamProcessor.WATER))
                        .filterSubmerged(5)
                )
        );

        TALL_WITCH_HUT = BOOTSTRAP.registerSet(DEStructureIDs.TALL_WITCH_HUT, set -> set
                .scatteredGridPlacement(25, 0.51f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_TALL_WITCH_HUT)
                        .surface()
                        .single(b -> b.template("tall_witch_hut").yOffset(-1))
                        .processors(List.of(RetainExistingProcessor.REPLACEABLE_ONLY))
                        .foundation()
                )
        );


        TREE_HOUSE = BOOTSTRAP.registerSet(DEStructureIDs.TREE_HOUSE, set -> set
                .scatteredGridPlacement(31, 0.4f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_TREE_HOUSE)
                        .surface()
                        .single(t -> t.template("tree_house").terrainBox(TerrainBox.footprint()))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
        );

        TOWER_OF_THE_UNDEAD = BOOTSTRAP.registerSet(DEStructureIDs.TOWER_OF_THE_UNDEAD, set -> set
                .evenSpreadPlacement(b -> b.minDistance(55).probability(0.65f))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_TOWER_OF_THE_UNDEAD)
                        .surface()
                        .weighted(bw -> bw
                                .single(3, "tower_of_the_undead/small")
                                .single(2, "tower_of_the_undead/big"))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
        );

        WATCH_TOWER = BOOTSTRAP.registerSet(DEStructureIDs.WATCH_TOWER, set -> set
                .evenSpreadPlacement(40, 0.65f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_WATCH_TOWER)
                        .surface()
                        .single("watch_tower")
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
        );

        WITCH_TOWER = BOOTSTRAP.registerSet(DEStructureIDs.WITCH_TOWER, set -> set
                .scatteredGridPlacement(54, 0.59f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_WITCH_TOWER)
                        .surface()
                        .weighted(b -> b
                                .single(3, "witch_tower/normal")
                                .single(2, t -> t.template("witch_tower/big").terrainBox(TerrainBox.footprint())))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
        );

        // Nether
        BLACK_CITADEL = BOOTSTRAP.registerSet(DEStructureIDs.BLACK_CITADEL, set -> set
                .evenSpreadPlacement(p -> p.minDistance(65).probability(0.72f).exclusionZone(DETags.Structures.BLACK_CITADEL_EXCLUSION_ZONE, 8))
                .jigsaw(DETemplatePools.BLACK_CITADEL, j -> j
                                .size(6)
                                .verticalPlacement(28)
                                .maxDistanceFromCenter(116)
                                .biomes(DETags.Biomes.HAS_BLACK_CITADEL)
                                .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, spawns(spawn(EntityType.WITHER_SKELETON, 4, 2, 5))))
                                .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES) // needs to generate after the basalt
                                .terrainAdaptation(TerrainAdjustment.BEARD_BOX)
                )
        );
    }

    @SafeVarargs
    private static WeightedList<MobSpawnSettings.SpawnerData> spawns(Weighted<MobSpawnSettings.SpawnerData>... spawns) {
        return WeightedList.of(spawns);
    }

    private static Weighted<MobSpawnSettings.SpawnerData> spawn(EntityType<?> entity, int weight, int min, int max) {
        return new Weighted<>(new MobSpawnSettings.SpawnerData(entity, min, max), weight);
    }
}