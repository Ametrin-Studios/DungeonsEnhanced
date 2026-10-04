package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.data.DETags;
import com.ametrin.dungeons_enhanced.world.structure.DEIcePitPieces;
import com.ametrin.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.ametrin.structures.foam.RemoveFoamProcessor;
import com.ametrin.structures.processor.RetainExistingProcessor;
import com.ametrin.structures.structure.DeferredStructureHolder;
import com.ametrin.structures.structure.DeferredStructureRegister;
import com.ametrin.structures.structure.simple.HeightAnchor;
import com.ametrin.structures.structure.simple.HeightMode;
import com.ametrin.structures.structure.simple.TerrainBox;
import net.minecraft.tags.StructureTags;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

import java.util.List;

public final class DEStructures {
    public static final DeferredStructureRegister REGISTER = new DeferredStructureRegister(DungeonsEnhanced.MOD_ID);

    // Overworld
    public static final DeferredStructureHolder CASTLE;
    public static final DeferredStructureHolder DEEP_CRYPT;
    public static final DeferredStructureHolder DESERT_TEMPLE;
    public static final DeferredStructureHolder DESERT_TOMB;
    public static final DeferredStructureHolder DRUID_CIRCLE;
    public static final DeferredStructureHolder DUNGEON_VARIANT;
    public static final DeferredStructureHolder ELDERS_TEMPLE;
    public static final DeferredStructureHolder FISHING_SHIP;
    public static final DeferredStructureHolder FLYING_DUTCHMAN;
    public static final DeferredStructureHolder HAY_STORAGE;
    public static final DeferredStructureHolder ICE_PIT;
    public static final DeferredStructureHolder JUNGLE_MONUMENT;
    public static final DeferredStructureHolder LARGE_DUNGEON;
    public static final DeferredStructureHolder MINERS_HOUSE;
    public static final DeferredStructureHolder MONSTER_MAZE;
    public static final DeferredStructureHolder MUSHROOM_HOUSE;
    public static final DeferredStructureHolder PILLAGER_CAMP;
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
    public static final DeferredStructureHolder BLACK_CITADEL;

    private DEStructures() {}

    static {
        // Overworld
        CASTLE = REGISTER.set(DEStructureIDs.CASTLE)
                .scatteredGridPlacement(p -> p.spacing(72).probability(0.74f).minChunksFromCenter(14))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_CASTLE)
                        .surface()
                        .verticalPlacementMode(HeightMode.MEAN)
                        .filterFlatness(8)
                        .weighted(b -> b
                                .single(t -> t.template("castle/blue").offset(0, -5, 0), 1)
                                .single(t -> t.template("castle/red").offset(0, -5, 0), 1)
                        )
                        .terrainAdaptation(TerrainAdjustment.BEARD_BOX)
                )
                .build();

        DEEP_CRYPT = REGISTER.set(DEStructureIDs.DEEP_CRYPT)
                .scatteredGridPlacement(b -> b.spacing(41).probability(0.64f))
                .jigsaw(DETemplatePools.DEEP_CRYPT, j -> j
                                .startHeight(UniformHeight.of(VerticalAnchor.aboveBottom(16), VerticalAnchor.aboveBottom(52)))
                                .size(4)
                                .build(),
                        s -> s
                                .biomes(DETags.Biomes.HAS_DEEP_CRYPT)
                                .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                )
                .build();

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
                )
                .build();

        DESERT_TOMB = REGISTER.set(DEStructureIDs.DESERT_TOMB)
                .scatteredGridPlacement(p -> p.spacing(29).probability(0.65f))
                .jigsaw(DETemplatePools.DESERT_TOMB, j -> j
                                .onSurface()
                                .size(5),
                        s -> s
                                .biomes(DETags.Biomes.HAS_DESERT_TOMB)
                )
                .build();
//
        DRUID_CIRCLE = REGISTER.set(DEStructureIDs.DRUID_CIRCLE)
                .scatteredGridPlacement(41, 0.68f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_DRUID_CIRCLE)
                        .surface()
                        .weighted(p -> p
                                .single(t -> t.template(DEStructureIDs.DRUID_CIRCLE + "/small").yOffset(-1), 3)
                                .single(t -> t.template(DEStructureIDs.DRUID_CIRCLE + "/big").yOffset(-4), 2)
                        )
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .build();

        DUNGEON_VARIANT = REGISTER.set(DEStructureIDs.DUNGEON_VARIANT)
                .scatteredGridPlacement(24, 0.53f)
                .simple("stone", s -> s
                        .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .biomes(DETags.Biomes.HAS_DUNGEON_VARIANT)
                        .between(HeightAnchor.absolute(0), HeightAnchor.oceanFloor(-24))
                        .weighted(builder -> builder
                                .single(b -> b.template("dungeon_variant/zombie"), 1)
                                .single(b -> b.template("dungeon_variant/skeleton"), 1)
                                .single(b -> b.template("dungeon_variant/spider"), 1)
                                .single(b -> b.template("dungeon_variant/special"), 1)
                        )
                        .processors(DEProcessorLists.DUNGEON_VARIANT_STONE)
                        .weight(3)
                )
                .simple("deepslate", s -> s
                        .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .biomes(DETags.Biomes.HAS_DUNGEON_VARIANT)
                        .between(HeightAnchor.aboveBottom(8), HeightAnchor.absolute(-6))
                        .weighted(builder -> builder
                                .single(b -> b.template("dungeon_variant/copper_shrine"), 1)
                        )
                        .processors(DEProcessorLists.DUNGEON_VARIANT_DEEPSLATE)
                )
                .build();

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
                )
                .build();

        FISHING_SHIP = REGISTER.set(DEStructureIDs.FISHING_SHIP)
                .scatteredGridPlacement(49, 0.62f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_FISHING_SHIP)
                        .surface()
                        .single(b -> b.template("fishing_ship").yOffset(-3))
                )
                .build();

        FLYING_DUTCHMAN = REGISTER.set(DEStructureIDs.FLYING_DUTCHMAN)
                .scatteredGridPlacement(b -> b.spacing(134).probability(0.63f).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_FLYING_DUTCHMAN)
                        .between(HeightAnchor.surface(48), HeightAnchor.belowTop(24))
                        .single("flying_dutchman")
                )
                .build();

        HAY_STORAGE = REGISTER.set(DEStructureIDs.HAY_STORAGE)
                .scatteredGridPlacement(23, 0.77f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_HAY_STORAGE)
                        .surface()
                        .weighted(b -> b
                                .single("hay_storage/small", 3)
                                .single("hay_storage/big", 2))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .build();

        ICE_PIT = REGISTER.set(DEStructureIDs.ICE_PIT)
                .scatteredGridPlacement(b -> b.spacing(34).probability(0.77f).minChunksFromCenter(12))
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_ICE_PIT)
                        .surface()
                        .pieces(_ -> DEIcePitPieces.INSTANCE)
                )
                .build();

        JUNGLE_MONUMENT = REGISTER.set(DEStructureIDs.JUNGLE_MONUMENT)
                .scatteredGridPlacement(46, 0.74f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_JUNGLE_MONUMENT)
                        .surface()
                        .verticalPlacementMode(HeightMode.MEAN)
                        .single(b -> b.template("jungle_monument").processors(DEProcessorLists.JUNGLE_MONUMENT).yOffset(-9))
                        .filterWithinBiome(12)
                )
                .build();

        LARGE_DUNGEON = REGISTER.set(DEStructureIDs.LARGE_DUNGEON)
                .scatteredGridPlacement(p -> p.spacing(59).probability(0.56f))
                .jigsaw(DETemplatePools.LARGE_DUNGEON, j -> j
                                .size(5)
                                .startHeight(-16)
                                .onSurface()
                        , s -> s
                                .biomes(DETags.Biomes.HAS_LARGE_DUNGEON)
                )
                .build();

        MINERS_HOUSE = REGISTER.set(DEStructureIDs.MINERS_HOUSE)
                .scatteredGridPlacement(24, 0.8f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_MINERS_HOUSE)
                        .surface()
                        .single(DEStructureIDs.MINERS_HOUSE)
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .build();

        MONSTER_MAZE = REGISTER.set("monster_maze")
                .scatteredGridPlacement(p -> p.spacing(28).probability(0.62f).minChunksFromCenter(12))
                .jigsaw("dark", DETemplatePools.MONSTER_MAZE_DARK, j -> j
                                .size(11)
                                .onSurface()
                                .startHeight(-26)
                        , s -> s
                                .biomes(DETags.Biomes.HAS_MONSTER_MAZE_DARK)
                ).jigsaw("pale", DETemplatePools.MONSTER_MAZE_PALE, j -> j
                                .size(11)
                                .onSurface()
                                .startHeight(-26)
                        , s -> s
                                .biomes(DETags.Biomes.HAS_MONSTER_MAZE_PALE)
                )
                .build();

        MUSHROOM_HOUSE = REGISTER.set(DEStructureIDs.MUSHROOM_HOUSE)
                .scatteredGridPlacement(19, 0.83f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_MUSHROOM_HOUSE)
                        .surface()
                        .weighted(b -> b
                                .single(t -> t.template("mushroom_house/red").terrainBox(TerrainBox.footprint()), 1)
                                .single(t -> t.template("mushroom_house/brown").terrainBox(TerrainBox.footprint()), 1))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .build();

        PILLAGER_CAMP = REGISTER.set(DEStructureIDs.PILLAGER_CAMP)
                .scatteredGridPlacement(p -> p.spacing(56).probability(0.39f).minChunksFromCenter(8).exclusionZone(StructureTags.VILLAGE, 5))
                .jigsaw(DETemplatePools.PILLAGER_CAMP, j -> j
                                .onSurface()
                                .size(4)
                                .build(),
                        builder -> builder
                                .biomes(DETags.Biomes.HAS_PILLAGER_CAMP)
                                .terrainAdaptation(TerrainAdjustment.BEARD_BOX)
                                .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 2, 3), spawn(EntityType.VINDICATOR, 2, 1, 2))))
                )
                .build();

        PIRATE_SHIP = REGISTER.set(DEStructureIDs.PIRATE_SHIP)
                .scatteredGridPlacement(68, 0.42F)
                .simple(s -> s
                                .biomes(DETags.Biomes.HAS_PIRATE_SHIP)
                                .surface()
//                        .filterMinWaterDepth(6)
                                .compound(b -> b
                                        .single(t -> t.template("pirate_ship/front").offset(-25, -3, 0))
                                        .single(t -> t.template("pirate_ship/back").offset(0, -3, 0)))
                                .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 3, 4), spawn(EntityType.VINDICATOR, 3, 1, 2))))
                                .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                )
                .build();

        RUINED_BUILDING = REGISTER.set(DEStructureIDs.RUINED_BUILDING)
                .scatteredGridPlacement(27, 0.54f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_RUINED_BUILDING)
                        .surface()
                        .weighted(b -> b
                                .single(t -> t.template("ruined_building/house").yOffset(-1), 3)
                                .single(t -> t.template("ruined_building/barn").yOffset(-1), 3)
                                .single(t -> t.template("ruined_building/house_big").yOffset(-1), 2))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .build();

        STABLES = REGISTER.set(DEStructureIDs.STABLES)
                .scatteredGridPlacement(53, 0.52f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_STABLES)
                        .surface()
                        .single(b -> b.template("stables").yOffset(-4))
                )
                .build();

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
                )
                .build();

        TALL_WITCH_HUT = REGISTER.set(DEStructureIDs.TALL_WITCH_HUT)
                .scatteredGridPlacement(23, 0.51f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_TALL_WITCH_HUT)
                        .surface()
                        .single(b -> b.template("tall_witch_hut").yOffset(-1))
                        .processors(List.of(RetainExistingProcessor.REPLACEABLE_ONLY))
                        .foundation()
                )
                .build();


        TREE_HOUSE = REGISTER.set(DEStructureIDs.TREE_HOUSE)
                .scatteredGridPlacement(29, 0.4f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_TREE_HOUSE)
                        .surface()
                        .single("tree_house")
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .build();

        TOWER_OF_THE_UNDEAD = REGISTER.set(DEStructureIDs.TOWER_OF_THE_UNDEAD)
                .scatteredGridPlacement(49, 0.65f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_TOWER_OF_THE_UNDEAD)
                        .surface()
                        .weighted(bw -> bw
                                .single("tower_of_the_undead/small", 3)
                                .single("tower_of_the_undead/big", 2))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .build();

        WATCH_TOWER = REGISTER.set(DEStructureIDs.WATCH_TOWER)
                .scatteredGridPlacement(27, 0.45f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_WATCH_TOWER)
                        .surface()
                        .single("watch_tower")
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .build();

        WITCH_TOWER = REGISTER.set(DEStructureIDs.WITCH_TOWER)
                .scatteredGridPlacement(54, 0.59f)
                .simple(s -> s
                        .biomes(DETags.Biomes.HAS_WITCH_TOWER)
                        .surface()
                        .weighted(b -> b
                                .single("witch_tower/normal", 3)
                                .single(t -> t.template("witch_tower/big").terrainBox(TerrainBox.footprint()), 2))
                        .terrainAdaptation(TerrainAdjustment.BEARD_THIN)
                )
                .build();

        // Nether
        BLACK_CITADEL = REGISTER.set(DEStructureIDs.BLACK_CITADEL)
                .scatteredGridPlacement(p -> p.spacing(69).probability(0.72f).exclusionZone(DETags.Structures.BLACK_CITADEL_EXCLUSION_ZONE, 8))
                .jigsaw(DETemplatePools.BLACK_CITADEL, j -> j
                                .size(6)
                                .startHeight(28)
                                .maxDistanceFromCenter(116)
                        , s -> s
                                .biomes(DETags.Biomes.HAS_BLACK_CITADEL)
                                .spawnOverride(MobCategory.MONSTER, new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.PIECE, spawns(spawn(EntityType.WITHER_SKELETON, 4, 2, 5))))
                                .step(GenerationStep.Decoration.UNDERGROUND_STRUCTURES) // needs to generate after the basalt
                                .terrainAdaptation(TerrainAdjustment.BEARD_BOX)
                )
                .build();
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