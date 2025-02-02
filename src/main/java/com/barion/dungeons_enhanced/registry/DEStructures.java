package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DEUtil;
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

import static com.barion.dungeons_enhanced.DEUtil.locate;

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
    public static final StructureRegistrar<DEGroundStructure> MINERS_HOUSE;
    public static final StructureRegistrar<ExtendedJigsawStructure> MONSTER_MAZE;
    public static final StructureRegistrar<DEGroundStructure> MUSHROOM_HOUSE;
    public static final StructureRegistrar<ExtendedJigsawStructure> PILLAGER_CAMP;
    public static final StructureRegistrar<DEPirateShip> PIRATE_SHIP;
    public static final StructureRegistrar<DEModularStructure> RUINED_BUILDING;
    public static final StructureRegistrar<DEModularStructure> STABLES;
    public static final StructureRegistrar<DEModularStructure> SUNKEN_SHRINE;
    public static final StructureRegistrar<DEModularStructure> TALL_WITCH_HUT;
    public static final StructureRegistrar<DEGroundStructure> TREE_HOUSE;
    public static final StructureRegistrar<DEGroundStructure> TOWER_OF_THE_UNDEAD;
    public static final StructureRegistrar<DEGroundStructure> WATCH_TOWER;
    public static final StructureRegistrar<DEGroundStructure> WITCH_TOWER;

    // Nether
    public static final StructureRegistrar<ExtendedJigsawStructure> BLACK_CITADEL;

    private DEStructures() { }

    static {
        //Overworld
        CASTLE = StructureRegistrar.jigsawBuilder(locate(DECastle.ID))
                .placement(()-> gridPlacement(69, 78).build(DEStructures.CASTLE))
                .addPiece(()-> DECastle.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DECastle.Capability.INSTANCE, DETemplatePools.CASTLE, 1, ConstantHeight.ZERO).onSurface().build())
                        .terrainAdjustment(TerrainAdjustment.BEARD_BOX)
                .popStructure()
                .build();

        DEEP_CRYPT = StructureRegistrar.jigsawBuilder(locate(DEDeepCrypt.ID))
                .placement(()-> gridPlacement(39, 67).build(DEStructures.DEEP_CRYPT))
                .addPiece(()-> DEDeepCrypt.Piece::new)
                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEDeepCrypt.Capability.INSTANCE, DETemplatePools.DEEP_CRYPT, 4, UniformHeight.of(VerticalAnchor.aboveBottom(16), VerticalAnchor.aboveBottom(48))).build())
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .popStructure()
                .build();

        DESERT_TEMPLE = StructureRegistrar.builder(locate(DEStructureIDs.DESERT_TEMPLE), ()-> ()-> DEDesertTemple.CODEC)
                .placement(()-> gridPlacement(39, 86).build(DEStructures.DESERT_TEMPLE))
                .addPiece(()-> DEDesertTemple.Piece::new)
                .pushStructure(DEDesertTemple::new)
                .popStructure()
                .build();

        DESERT_TOMB = StructureRegistrar.jigsawBuilder(locate(DEDesertTomb.ID))
                .placement(()-> gridPlacement(29, 65).allowedNearSpawn(true).build(DEStructures.DESERT_TOMB))
                .addPiece(()-> DEDesertTomb.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDesertTomb.Capability.INSTANCE, DETemplatePools.DESERT_TOMB, 5, ConstantHeight.ZERO).onSurface().build())
                .popStructure()
                .build();

        DRUID_CIRCLE = StructureRegistrar.jigsawBuilder(locate(DEDruidCircle.ID))
                .placement(()-> gridPlacement(41, 68).allowedNearSpawn(true).build(DEStructures.DRUID_CIRCLE))
                .addPiece(()-> DEDruidCircle.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDruidCircle.Capability.INSTANCE, DETemplatePools.DRUID_CIRCLE, 1, ConstantHeight.ZERO).onSurface().build())//TODO: make own tag
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
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
                                .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES))
                .placement(19, 0.59f).allowNearSpawn()
                .build();

        ELDERS_TEMPLE = StructureRegistrar.builder(locate(DEEldersTemple.ID), ()-> ()-> DEEldersTemple.CODEC)
                .placement(()-> gridPlacement(24).build(DEStructures.ELDERS_TEMPLE))
                .addPiece(()-> DEEldersTemple.Piece::new)
                .pushStructure(DEEldersTemple::new)
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.GUARDIAN, 1,2,4)))
                .popStructure()
                .build();

        FISHING_SHIP = DEModularRegistrarBuilder.create(() -> DEStructures.FISHING_SHIP, DEStructureIDs.FISHING_SHIP)
                .addStructure(DEStructureTemplate.of("fishing_ship", -3),
                        structure -> structure
                                .placement(DEPlacement.ON_WORLD_SURFACE),
                        config -> config
                )
                .placement(48, 0.68f).allowNearSpawn()
                .build();

        FLYING_DUTCHMAN = DEModularRegistrarBuilder.create(() -> DEStructures.FLYING_DUTCHMAN, DEStructureIDs.FLYING_DUTCHMAN)
                .addStructure(DEUtil.locate("flying_dutchman"),
                        structure -> structure
                                .placement(DEPlacement.ABOVE_GROUND),
                        config -> config
                )
                .placement(134, 0.63f)
                .build();

        HAY_STORAGE = DEModularRegistrarBuilder.create(() -> DEStructures.HAY_STORAGE, DEGroundStructure.ID_HAY_STORAGE)
                .addStructure(pieceFactory -> pieceFactory
                                .add("hay_storage/small")
                                .add("hay_storage/big"),
                        structure -> structure
                                .placement(DEPlacement.ON_OCEAN_FLOOR),
                        config -> config
                                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                )
                .placement(23, 0.77f).allowNearSpawn()
                .build();

        ICE_PIT = StructureRegistrar.builder(locate(DEIcePit.ID), ()-> ()-> DEIcePit.CODEC)
                .addPiece(()-> DEGroundStructure.Piece::new)
                .placement(()-> gridPlacement(34, 77).build(DEStructures.ICE_PIT))
                .pushStructure(DEIcePit::new)
                .popStructure()
                .build();

        JUNGLE_MONUMENT = StructureRegistrar.builder(locate(DEGroundStructure.ID_JUNGLE_MONUMENT), ()-> ()-> DEGroundStructure.CODEC_JUNGLE_MONUMENT)
                .placement(()-> gridPlacement(46, 74).build(DEStructures.JUNGLE_MONUMENT))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::JungleMonument)
                .popStructure()
                .build();

        LARGE_DUNGEON = StructureRegistrar.jigsawBuilder(locate(DELargeDungeon.ID))
                .placement(()-> gridPlacement(59, 56).allowedNearSpawn(true).build(DEStructures.LARGE_DUNGEON))
                .addPiece(()-> DELargeDungeon.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DELargeDungeon.Capability.INSTANCE, DETemplatePools.LARGE_DUNGEON, 5, height(-16)).onSurface().build())
                .popStructure()
                .build();

        MINERS_HOUSE = StructureRegistrar.builder(locate(DEGroundStructure.ID_MINERS_HOUSE), ()-> ()-> DEGroundStructure.CODEC_MINERS_HOUSE)
                .placement(()-> gridPlacement(24, 80).allowedNearSpawn(true).build(DEStructures.MINERS_HOUSE))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::MinersHouse)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        MONSTER_MAZE = StructureRegistrar.jigsawBuilder(locate(DEMonsterMaze.ID))
                .placement(()-> gridPlacement(32, 52).build(DEStructures.MONSTER_MAZE))
                .addPiece(()-> DEMonsterMaze.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEMonsterMaze.Capability.INSTANCE, DETemplatePools.MONSTER_MAZE, 12, height(-17)).onSurface().build())
                .popStructure()
                .build();

        MUSHROOM_HOUSE = StructureRegistrar.builder(locate(DEGroundStructure.ID_MUSHROOM_HOUSE), ()-> ()-> DEGroundStructure.CODEC_MUSHROOM_HOUSE)
                .placement(()-> gridPlacement(19, 83).allowedNearSpawn(true).build(DEStructures.MUSHROOM_HOUSE))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::MushroomHouse)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        PILLAGER_CAMP = StructureRegistrar.jigsawBuilder(locate(DEPillagerCamp.ID))
                .placement(()-> gridPlacement(56, 39).build(DEStructures.PILLAGER_CAMP))
                .addPiece(()-> DEPillagerCamp.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEPillagerCamp.Capability.INSTANCE, DETemplatePools.PILLAGER_CAMP, 4, ConstantHeight.ZERO).onSurface().build())
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 2, 3), spawn(EntityType.VINDICATOR, 2, 1, 2)))
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        PIRATE_SHIP = StructureRegistrar.builder(locate(DEStructureIDs.PIRATE_SHIP), ()-> ()-> DEPirateShip.CODEC)
                .placement(()-> gridPlacement(67, 49).build(DEStructures.PIRATE_SHIP))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEPirateShip::new)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 3, 4), spawn(EntityType.VINDICATOR, 3, 1, 2)))
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .popStructure()
                .build();

        RUINED_BUILDING = DEModularRegistrarBuilder.create(() -> DEStructures.RUINED_BUILDING, DEStructureIDs.RUINED_BUILDING)
                .addStructure(pieceFactory -> pieceFactory
                                .add(3, "ruined_building/house")
                                .add(3, "ruined_building/barn")
                                .add(2, "ruined_building/house_big"),
                        structure -> structure.placement(DEPlacement.ON_OCEAN_FLOOR),
                        config -> config
                                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                )
                .placement(27, 0.54f).allowNearSpawn()
                .build();

        STABLES = DEModularRegistrarBuilder.create(()-> DEStructures.STABLES, DEStructureIDs.STABLES)
                .addStructure(DEStructureTemplate.of("stables", -5),
                        structure -> structure.placement(DEPlacement.ON_OCEAN_FLOOR),
                        config -> config
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
                        structure -> structure.placement(DEPlacement.ON_OCEAN_FLOOR),
                        config -> config
                )
                .placement(32, 0.55f).allowNearSpawn()
                .build();

        TALL_WITCH_HUT = DEModularRegistrarBuilder.create(()-> DEStructures.TALL_WITCH_HUT, DEStructureIDs.TALL_WITCH_HUT)
                .addStructure(DEStructureTemplate.of("tall_witch_hut", -3),
                        structure -> structure
                                .placement(DEPlacement.ON_WORLD_SURFACE_FLAT)
                                .filter(DEPlacementFilter.DIFFERENCE_OCEAN_FLOOR(4)),
                        config -> config
                )
                .placement(21, 0.61f).allowNearSpawn()
                .build();

        TOWER_OF_THE_UNDEAD = StructureRegistrar.builder(locate(DEGroundStructure.ID_TOWER_OF_THE_UNDEAD), ()-> ()-> DEGroundStructure.CODEC_TOWER_OF_THE_UNDEAD)
                .placement(()-> gridPlacement(49, 65).allowedNearSpawn(true).build(DEStructures.TOWER_OF_THE_UNDEAD))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::TowerOfTheUndead)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        TREE_HOUSE = StructureRegistrar.builder(locate(DEGroundStructure.ID_TREE_HOUSE), ()-> ()-> DEGroundStructure.CODEC_TREE_HOUSE)
                .placement(()-> gridPlacement(29, 40).allowedNearSpawn(true).build(DEStructures.TREE_HOUSE))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::TreeHouse)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        WATCH_TOWER = StructureRegistrar.builder(locate(DEGroundStructure.ID_WATCH_TOWER), ()-> ()-> DEGroundStructure.CODEC_WATCH_TOWER)
                .placement(()-> gridPlacement(27, 45).allowedNearSpawn(true).build(DEStructures.WATCH_TOWER))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::WatchTower)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        WITCH_TOWER = StructureRegistrar.builder(locate(DEGroundStructure.ID_WITCH_TOWER), ()-> ()-> DEGroundStructure.CODEC_WITCH_TOWER)
                .placement(()-> gridPlacement(79, 54).allowedNearSpawn(true).build(DEStructures.WITCH_TOWER))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(DEGroundStructure::WitchTower)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        // Nether
        BLACK_CITADEL = StructureRegistrar.jigsawBuilder(locate(DEStructureIDs.BLACK_CITADEL))
                .placement(()-> gridPlacement(67, 75).build(DEStructures.BLACK_CITADEL))
                .addPiece(()-> DEBlackCitadel.Piece::new)
                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEBlackCitadel.Capability.INSTANCE, DETemplatePools.BLACK_CITADEL, 6, height(28)).maxDistanceFromCenter(116).build())
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
            MONSTER_MAZE,
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