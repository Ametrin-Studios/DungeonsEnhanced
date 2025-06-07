package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.data.DETags;
import com.barion.dungeons_enhanced.world.structure.*;
import com.barion.dungeons_enhanced.world.structure.builder.DEModularRegistrarBuilder;
import com.barion.dungeons_enhanced.world.structure.builder.DEPlacement;
import com.barion.dungeons_enhanced.world.structure.builder.DEPlacementFilter;
import com.barion.dungeons_enhanced.world.structure.builder.DEStructureTemplate;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEGroundStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEModularStructure;
import com.barion.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;
import com.legacy.structure_gel.api.registry.RegistrarHolder;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.ExtendedJigsawStructure;
import com.legacy.structure_gel.api.structure.GridStructurePlacement;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawCapability;
import com.legacy.structure_gel.api.structure.processor.RemoveGelStructureProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static com.barion.dungeons_enhanced.DungeonsEnhanced.locate;

@RegistrarHolder
public final class DEStructures {
    // Overworld
    public static final StructureRegistrar<ExtendedJigsawStructure> CASTLE;
    public static final StructureRegistrar<ExtendedJigsawStructure> DEEP_CRYPT;
    public static final StructureRegistrar<DEDesertTemple> DESERT_TEMPLE;
    public static final StructureRegistrar<ExtendedJigsawStructure> DESERT_TOMB;
    public static final StructureRegistrar<ExtendedJigsawStructure> DRUID_CIRCLE;
    public static final StructureRegistrar<DEModularStructure> DUNGEON_VARIANT;
    public static final StructureRegistrar<DEEldersTemple> ELDERS_TEMPLE;
    public static final StructureRegistrar<DEModularStructure> FISHING_SHIP;
    public static final StructureRegistrar<DEModularStructure> FLYING_DUTCHMAN;
    public static final StructureRegistrar<DEModularStructure> HAY_STORAGE;
    public static final StructureRegistrar<DEIcePit> ICE_PIT;
    public static final StructureRegistrar<DEGroundStructure> JUNGLE_MONUMENT;
    public static final StructureRegistrar<ExtendedJigsawStructure> LARGE_DUNGEON;
    public static final StructureRegistrar<DEModularStructure> MINERS_HOUSE;
    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE_DARK;
    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE_PALE;
    public static final StructureRegistrar<DEGroundStructure> MUSHROOM_HOUSE;
    public static final StructureRegistrar<ExtendedJigsawStructure> PILLAGER_CAMP;
    public static final StructureRegistrar<DEPirateShip> PIRATE_SHIP;
    public static final StructureRegistrar<DEModularStructure> RUINED_BUILDING;
    public static final StructureRegistrar<DEModularStructure> STABLES;
    public static final StructureRegistrar<DEModularStructure> SUNKEN_SHRINE;
    public static final StructureRegistrar<DEModularStructure> TALL_WITCH_HUT;
    public static final StructureRegistrar<DEModularStructure> TREE_HOUSE;
    public static final StructureRegistrar<DEModularStructure> TOWER_OF_THE_UNDEAD;
    public static final StructureRegistrar<DEGroundStructure> WATCH_TOWER;
    public static final StructureRegistrar<DEGroundStructure> WITCH_TOWER;

    // Nether
    public static final StructureRegistrar<ExtendedJigsawStructure> BLACK_CITADEL;

    private DEStructures() { }

    static {
        //Overworld
        CASTLE = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.CASTLE))
                .placement(()-> gridPlacement(69, 78).build(DEStructures.CASTLE))
                .addPiece(()-> DECastle.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DECastle.Capability.INSTANCE, DETemplatePools.CASTLE, 1, ConstantHeight.ZERO).onSurface().build())
                        .terrainAdjustment(TerrainAdjustment.BEARD_BOX)
                        .biomes(DETags.Biomes.HAS_CASTLE)
                .popStructure()
                .build();

        DEEP_CRYPT = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.DEEP_CRYPT))
                .placement(()-> gridPlacement(39, 67).build(DEStructures.DEEP_CRYPT))
                .addPiece(()-> DEDeepCrypt.Piece::new)
                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEDeepCrypt.Capability.INSTANCE, DETemplatePools.DEEP_CRYPT, 4, UniformHeight.of(VerticalAnchor.aboveBottom(16), VerticalAnchor.aboveBottom(48))).build())
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                        .biomes(DETags.Biomes.HAS_DEEP_CRYPT)
                .popStructure()
                .build();

        DESERT_TEMPLE = StructureRegistrar.builder(locate(DEStructureIDs.DESERT_TEMPLE), ()-> ()-> DEDesertTemple.CODEC)
                .placement(()-> gridPlacement(39, 86).build(DEStructures.DESERT_TEMPLE))
                .addPiece(()-> DEDesertTemple.Piece::new)
                .pushStructure(DEDesertTemple::new)
                        .biomes(DETags.Biomes.HAS_DESERT_TEMPLE)
                .popStructure()
                .build();

        DESERT_TOMB = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.DESERT_TOMB))
                .placement(()-> gridPlacement(29, 65).allowedNearSpawn(true).build(DEStructures.DESERT_TOMB))
                .addPiece(()-> DEDesertTomb.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDesertTomb.Capability.INSTANCE, DETemplatePools.DESERT_TOMB, 5, ConstantHeight.ZERO).onSurface().build())
                        .biomes(DETags.Biomes.HAS_DESERT_TOMB)
                .popStructure()
                .build();

        DRUID_CIRCLE = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.DRUID_CIRCLE))
                .placement(()-> gridPlacement(41, 68).allowedNearSpawn(true).build(DEStructures.DRUID_CIRCLE))
                .addPiece(()-> DEDruidCircle.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDruidCircle.Capability.INSTANCE, DETemplatePools.DRUID_CIRCLE, 1, ConstantHeight.ZERO).onSurface().build())//TODO: make own tag
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                        .biomes(DETags.Biomes.HAS_DRUID_CIRCLE)
                .popStructure()
                .build();

        DUNGEON_VARIANT = DEModularRegistrarBuilder.create(() -> DEStructures.DUNGEON_VARIANT, DEStructureIDs.DUNGEON_VARIANT)
                .addStructure(pieceFactory -> pieceFactory
                                .add("dungeon_variant/zombie")
                                .add("dungeon_variant/skeleton")
                                .add("dungeon_variant/spider")
                                .add("dungeon_variant/special")
                                .settings(settings -> settings
                                        .addProcessor(DEProcessors.MOSSY_COBBLESTONE_40)
                                        .addProcessor(DEProcessors.MOSSY_COBBLESTONE_STAIRS_40)
                                        .addProcessor(DEProcessors.MOSSY_COBBLESTONE_SLAB_40)
                                        .addProcessor(DEProcessors.MOSSY_STONE_BRICKS_30)
                                        .addProcessor(DEProcessors.CRACK_STONE_BRICKS_20)
                                ),
                        structure -> structure
                                .placement(DEPlacement.UNDERGROUND),
                        config -> config
                                .biomes(DETags.Biomes.HAS_DUNGEON_VARIANT)
                                .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES))
                .placement(19, 0.59f).allowNearSpawn()
                .build();

        ELDERS_TEMPLE = StructureRegistrar.builder(locate(DEStructureIDs.ELDERS_TEMPLE), ()-> ()-> DEEldersTemple.CODEC)
                .placement(()-> gridPlacement(24).build(DEStructures.ELDERS_TEMPLE))
                .addPiece(()-> DEEldersTemple.Piece::new)
                .pushStructure(DEEldersTemple::new)
                        .biomes(DETags.Biomes.HAS_ELDERS_TEMPLE)
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.GUARDIAN, 1,2,4)))
                .popStructure()
                .build();

        FISHING_SHIP = DEModularRegistrarBuilder.create(() -> DEStructures.FISHING_SHIP, DEStructureIDs.FISHING_SHIP)
                .addStructure(DEStructureTemplate.of("fishing_ship", -3),
                        structure -> structure
                                .placement(DEPlacement.WORLD_SURFACE),
                        config -> config
                                .biomes(DETags.Biomes.HAS_FISHING_SHIP)
                )
                .placement(48, 0.68f).allowNearSpawn()
                .build();

        FLYING_DUTCHMAN = DEModularRegistrarBuilder.create(() -> DEStructures.FLYING_DUTCHMAN, DEStructureIDs.FLYING_DUTCHMAN)
                .addStructure(locate("flying_dutchman"),
                        structure -> structure
                                .placement(DEPlacement.ABOVE_GROUND),
                        config -> config
                                .biomes(DETags.Biomes.HAS_FLYING_DUTCHMAN)
                )
                .placement(134, 0.63f)
                .build();

        HAY_STORAGE = DEModularRegistrarBuilder.create(() -> DEStructures.HAY_STORAGE, DEStructureIDs.HAY_STORAGE)
                .addStructure(pieceFactory -> pieceFactory
                                .add("hay_storage/small")
                                .add("hay_storage/big"),
                        structure -> structure
                                .placement(DEPlacement.OCEAN_FLOOR),
                        config -> config
                                .biomes(DETags.Biomes.HAS_HAY_STORAGE)
                                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                )
                .placement(23, 0.77f).allowNearSpawn()
                .build();

        ICE_PIT = StructureRegistrar.builder(locate(DEStructureIDs.ICE_PIT), ()-> ()-> DEIcePit.CODEC)
                .addPiece(()-> DEGroundStructure.Piece::new)
                .placement(()-> gridPlacement(34, 77).build(DEStructures.ICE_PIT))
                .pushStructure(DEIcePit::new)
                        .biomes(DETags.Biomes.HAS_ICE_PIT)
                .popStructure()
                .build();

        JUNGLE_MONUMENT = StructureRegistrar.builder(locate(DEStructureIDs.JUNGLE_MONUMENT), ()-> ()-> DEGroundStructure.CODEC_JUNGLE_MONUMENT)
                .placement(()-> gridPlacement(46, 74).build(DEStructures.JUNGLE_MONUMENT))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::JungleMonument)
                        .biomes(DETags.Biomes.HAS_JUNGLE_MONUMENT)
                .popStructure()
                .build();

        LARGE_DUNGEON = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.LARGE_DUNGEON))
                .placement(()-> gridPlacement(59, 56).allowedNearSpawn(true).build(DEStructures.LARGE_DUNGEON))
                .addPiece(()-> DELargeDungeon.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DELargeDungeon.Capability.INSTANCE, DETemplatePools.LARGE_DUNGEON, 5, height(-16)).onSurface().build())
                        .biomes(DETags.Biomes.HAS_LARGE_DUNGEON)
                .popStructure()
                .build();

        MINERS_HOUSE = DEModularRegistrarBuilder.create(()-> DEStructures.MINERS_HOUSE, DEStructureIDs.MINERS_HOUSE)
                .addStructure(locate(DEStructureIDs.MINERS_HOUSE),
                        structure-> structure
                                .placement(DEPlacement.WORLD_SURFACE_FLAT),
                        config-> config
                                .biomes(DETags.Biomes.HAS_MINERS_HOUSE)
                                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                )
                .placement(24, 0.8f).allowNearSpawn()
                .build();

        // TODO: update id (1.22)
        MONSTER_MAZE_DARK = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.MONSTER_MAZE_DARK))
                .placement(()-> gridPlacement(32, 52).build(DEStructures.MONSTER_MAZE_DARK))
                .addPiece(()-> DEMonsterMaze.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEMonsterMaze.Capability.INSTANCE, DETemplatePools.MONSTER_MAZE_DARK, 12, height(-26)).onSurface().build())
                        .biomes(DETags.Biomes.HAS_MONSTER_MAZE_DARK)
                .popStructure()
                .build();

        MONSTER_MAZE_PALE = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.MONSTER_MAZE_PALE))
                .placement(()-> gridPlacement(18, 52).build(DEStructures.MONSTER_MAZE_DARK))
                .addPiece(()-> DEMonsterMaze.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEMonsterMaze.Capability.INSTANCE, DETemplatePools.MONSTER_MAZE_PALE, 12, height(-26)).onSurface().build())
                        .biomes(DETags.Biomes.HAS_MONSTER_MAZE_PALE)
                .popStructure()
                .build();

        MUSHROOM_HOUSE = StructureRegistrar.builder(locate(DEStructureIDs.MUSHROOM_HOUSE), ()-> ()-> DEGroundStructure.CODEC_MUSHROOM_HOUSE)
                .placement(()-> gridPlacement(19, 83).allowedNearSpawn(true).build(DEStructures.MUSHROOM_HOUSE))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::MushroomHouse)
                        .biomes(DETags.Biomes.HAS_MUSHROOM_HOUSE)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        PILLAGER_CAMP = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.PILLAGER_CAMP))
                .placement(()-> gridPlacement(56, 39).build(DEStructures.PILLAGER_CAMP))
                .addPiece(()-> DEPillagerCamp.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEPillagerCamp.Capability.INSTANCE, DETemplatePools.PILLAGER_CAMP, 4, ConstantHeight.ZERO).onSurface().build())
                        .biomes(DETags.Biomes.HAS_PILLAGER_CAMP)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 2, 3), spawn(EntityType.VINDICATOR, 2, 1, 2)))
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        PIRATE_SHIP = StructureRegistrar.builder(locate(DEStructureIDs.PIRATE_SHIP), ()-> ()-> DEPirateShip.CODEC)
                .placement(()-> gridPlacement(67, 49).build(DEStructures.PIRATE_SHIP))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEPirateShip::new)
                        .biomes(DETags.Biomes.HAS_PIRATE_SHIP)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 3, 4), spawn(EntityType.VINDICATOR, 3, 1, 2)))
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .popStructure()
                .build();

        RUINED_BUILDING = DEModularRegistrarBuilder.create(() -> DEStructures.RUINED_BUILDING, DEStructureIDs.RUINED_BUILDING)
                .addStructure(pieceFactory -> pieceFactory
                                .add(3, "ruined_building/house")
                                .add(3, "ruined_building/barn")
                                .add(2, "ruined_building/house_big"),
                        structure -> structure
                                .placement(DEPlacement.OCEAN_FLOOR),
                        config -> config
                                .biomes(DETags.Biomes.HAS_RUINED_BUILDING)
                                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                )
                .placement(27, 0.54f).allowNearSpawn()
                .build();

        STABLES = DEModularRegistrarBuilder.create(()-> DEStructures.STABLES, DEStructureIDs.STABLES)
                .addStructure(DEStructureTemplate.of("stables", -5),
                        structure -> structure
                                .placement(DEPlacement.OCEAN_FLOOR),
                        config -> config
                                .biomes(DETags.Biomes.HAS_STABLES)
                )
                .placement(53, 0.52f).allowNearSpawn()
                .build();

        SUNKEN_SHRINE = DEModularRegistrarBuilder.create(() -> DEStructures.SUNKEN_SHRINE, DEStructureIDs.SUNKEN_SHRINE)
                .addStructure(
                        pieces -> pieces
                                .settings(settings -> settings
                                        .setLiquidSettings(LiquidSettings.APPLY_WATERLOGGING)
                                        .popProcessor(RemoveGelStructureProcessor.INSTANCE)
                                        .addProcessor(DEUnderwaterProcessor.INSTANCE)
                                )
                                .add(2, "sunken_shrine/small")
                                .add(1, "sunken_shrine/big", -1),
                        structure -> structure
                                .placement(DEPlacement.OCEAN_FLOOR)
                                .filter(DEPlacementFilter.MinWaterHeight(12)),
                        config -> config
                                .biomes(DETags.Biomes.HAS_SUNKEN_SHRINE)
                )
                .placement(32, 0.55f).allowNearSpawn()
                .build();

        TALL_WITCH_HUT = DEModularRegistrarBuilder.create(()-> DEStructures.TALL_WITCH_HUT, DEStructureIDs.TALL_WITCH_HUT)
                .addStructure(DEStructureTemplate.of("tall_witch_hut", -3),
                        structure -> structure
                                .placement(DEPlacement.WORLD_SURFACE_FLAT)
                                .filter(DEPlacementFilter.MaxWaterDepth(4)),
                        config -> config
                                .biomes(DETags.Biomes.HAS_TALL_WITCH_HUT)
                )
                .placement(21, 0.61f).allowNearSpawn()
                .build();


        TREE_HOUSE = DEModularRegistrarBuilder.create(() -> DEStructures.TREE_HOUSE, DEStructureIDs.TREE_HOUSE)
                .addStructure(DungeonsEnhanced.locate("tree_house"),
                        structure -> structure
                                .placement(DEPlacement.WORLD_SURFACE_FLAT),
                        config -> config
                                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                                .biomes(DETags.Biomes.HAS_TREE_HOUSE)
                )
                .placement(29, 0.4f).allowNearSpawn()
                .build();

        TOWER_OF_THE_UNDEAD = DEModularRegistrarBuilder.create(()-> DEStructures.TOWER_OF_THE_UNDEAD, DEStructureIDs.TOWER_OF_THE_UNDEAD)
                .addStructure(
                        pieces -> pieces
                                .add(3, "tower_of_the_undead/small")
                                .add(2, "tower_of_the_undead/big"),
                        structure -> structure
                                .placement(DEPlacement.WORLD_SURFACE_FLAT)
                                .filter(DEPlacementFilter.NO_WATER),
                        config -> config
                                .biomes(DETags.Biomes.HAS_TOWER_OF_THE_UNDEAD)
                                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                )
                .placement(49, 0.65f).allowNearSpawn()
                .build();

        WATCH_TOWER = StructureRegistrar.builder(locate(DEStructureIDs.WATCH_TOWER), ()-> ()-> DEGroundStructure.CODEC_WATCH_TOWER)
                .placement(()-> gridPlacement(27, 45).allowedNearSpawn(true).build(DEStructures.WATCH_TOWER))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::WatchTower)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                        .biomes(DETags.Biomes.HAS_WATCH_TOWER)
                .popStructure()
                .build();

        WITCH_TOWER = StructureRegistrar.builder(locate(DEStructureIDs.WITCH_TOWER), ()-> ()-> DEGroundStructure.CODEC_WITCH_TOWER)
                .placement(()-> gridPlacement(79, 54).allowedNearSpawn(true).build(DEStructures.WITCH_TOWER))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::WitchTower)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                        .biomes(DETags.Biomes.HAS_WITCH_TOWER)
                .popStructure()
                .build();

        // Nether
        BLACK_CITADEL = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.BLACK_CITADEL))
                .placement(()-> gridPlacement(67, 75).build(DEStructures.BLACK_CITADEL))
                .addPiece(()-> DEBlackCitadel.Piece::new)
                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEBlackCitadel.Capability.INSTANCE, DETemplatePools.BLACK_CITADEL, 6, height(28)).maxDistanceFromCenter(116).build())
                        .biomes(DETags.Biomes.HAS_BLACK_CITADEL)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.PIECE, spawns(spawn(EntityType.WITHER_SKELETON, 4, 2, 5), spawn(EntityType.SKELETON, 1, 1, 3)))
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES) //needs to generate after the basalt
                        .terrainAdjustment(TerrainAdjustment.BEARD_BOX)
                .popStructure()
                .build();
    }

    public static final StructureRegistrar<?>[] ALL_STRUCTURE_REGISTRARS = {
            CASTLE,
            DEEP_CRYPT,
            DESERT_TEMPLE,
            DESERT_TOMB,
            DRUID_CIRCLE,
            DUNGEON_VARIANT,
            ELDERS_TEMPLE,
            FISHING_SHIP,
            FLYING_DUTCHMAN,
            HAY_STORAGE,
            ICE_PIT,
            JUNGLE_MONUMENT,
            LARGE_DUNGEON,
            MINERS_HOUSE,
            MONSTER_MAZE_DARK,
            MONSTER_MAZE_PALE,
            MUSHROOM_HOUSE,
            PILLAGER_CAMP,
            PIRATE_SHIP,
            RUINED_BUILDING,
            STABLES,
            SUNKEN_SHRINE,
            TALL_WITCH_HUT,
            TOWER_OF_THE_UNDEAD,
            TREE_HOUSE,
            WATCH_TOWER,
            WITCH_TOWER,

            BLACK_CITADEL,
    };

    private static ConstantHeight height(int y) {
        return ConstantHeight.of(new VerticalAnchor.Absolute(y));
    }

    private static Supplier<List<MobSpawnSettings.SpawnerData>> spawns(MobSpawnSettings.SpawnerData... spawns) {
        return () -> Arrays.stream(spawns).toList();
    }

    private static MobSpawnSettings.SpawnerData spawn(EntityType<?> entity, int weight, int min, int max) {
        return new MobSpawnSettings.SpawnerData(entity, weight, min, max);
    }

    private static GridStructurePlacement.Builder gridPlacement(int spacing, int probability) {
        return gridPlacement(spacing).probability(probability / 100f);
    }

    private static GridStructurePlacement.Builder gridPlacement(int spacing) {
        return GridStructurePlacement.builder().spacing(spacing).offset((int) (spacing * 0.8));
    }

    private static ExtendedJigsawStructure.Builder extendedJigsawStructure(BootstrapContext<?> context, Structure.StructureSettings settings, JigsawCapability capability, ResourceKey<StructureTemplatePool> poolKey, int maxDepth, HeightProvider heightProvider) {
        return ExtendedJigsawStructure.builder(settings, context.lookup(Registries.TEMPLATE_POOL).getOrThrow(poolKey)).maxDepth(maxDepth).startHeight(heightProvider).capability(capability);
    }
}