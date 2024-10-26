package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.*;
import com.barion.dungeons_enhanced.world.structure.builder.DEModularRegistrarBuilder;
import com.barion.dungeons_enhanced.world.structure.builder.DEPlacement;
import com.barion.dungeons_enhanced.world.structure.builder.DEPlacementFilter;
import com.barion.dungeons_enhanced.world.structure.prefabs.DECellarStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEModularStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.DESwimmingStructure;
import com.barion.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.barion.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;
import com.legacy.structure_gel.registrars.GelStructureRegistrar;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import com.legacy.structure_gel.worldgen.jigsaw.GelConfigJigsawStructure;
import com.legacy.structure_gel.worldgen.structure.GelConfigStructure;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;

import static com.barion.dungeons_enhanced.DEUtil.locate;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public final class DEStructures {
    public static final StructureRegistrar2<VillageConfig, DECellarStructure> CASTLE;
    public static final StructureRegistrar2<NoFeatureConfig, DEDesertTemple> DESERT_TEMPLE;
    public static final StructureRegistrar2<VillageConfig, DEDesertTomb> DESERT_TOMB;
    public static final StructureRegistrar2<VillageConfig, DECellarStructure> DRUID_CIRCLE;
    public static final StructureRegistrar2<NoFeatureConfig, DEModularStructure> DUNGEON_VARIANT;
    public static final StructureRegistrar2<NoFeatureConfig, DEEldersTemple> ELDERS_TEMPLE;
    public static final StructureRegistrar2<NoFeatureConfig, DESwimmingStructure> FISHING_SHIP;
    public static final StructureRegistrar2<NoFeatureConfig, DEModularStructure> FLYING_DUTCHMAN;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> HAY_STORAGE;
    public static final StructureRegistrar2<NoFeatureConfig, DEIcePit> ICE_PIT;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> JUNGLE_MONUMENT;
    public static final StructureRegistrar2<VillageConfig, DELargeDungeon> LARGE_DUNGEON;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> MINERS_HOUSE;
    public static final StructureRegistrar2<VillageConfig, DEMonsterMaze> MONSTER_MAZE;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> MUSHROOM_HOUSE;
    public static final StructureRegistrar2<VillageConfig, DEPillagerCamp> PILLAGER_CAMP;
    public static final StructureRegistrar2<NoFeatureConfig, DEPirateShip> PIRATE_SHIP;
    public static final StructureRegistrar2<NoFeatureConfig, DEModularStructure> RUINED_BUILDING;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> STABLES;
    public static final StructureRegistrar2<NoFeatureConfig, DEModularStructure> SUNKEN_SHRINE;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> TALL_WITCH_HUT;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> TREE_HOUSE;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> TOWER_OF_THE_UNDEAD;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> WATCH_TOWER;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> WITCH_TOWER;

    public DEStructures(){}

    static {
        CASTLE = registerCellarStructure("castle", new DECellarStructure(DEConfig.COMMON.CASTLE, DETerrainAnalyzer.DEFAULT_SETTINGS, false), DECellarStructure.CastlePool.ROOT, DECellarStructure.Piece::new);
        DESERT_TEMPLE = register("desert_temple", new DEDesertTemple(), DESimpleStructure.Piece::new);
        DESERT_TOMB = registerJigsaw("desert_tomb", new DEDesertTomb(), DEDesertTomb.Pool.ROOT, 4, DEDesertTomb.Piece::new);
        DRUID_CIRCLE = registerCellarStructure("druid_circle", new DECellarStructure(DEConfig.COMMON.DRUID_CIRCLE, DETerrainAnalyzer.DEFAULT_SETTINGS, true), DECellarStructure.DruidCirclePool.ROOT, DECellarStructure.Piece::new);

        DUNGEON_VARIANT = DEModularRegistrarBuilder.create("dungeon_variant", ()-> DEStructures.DUNGEON_VARIANT)
                .config(DEConfig.COMMON.DUNGEON_VARIANT)
                .piece(builder -> builder
                        .add("dungeon_variant/zombie")
                        .add("dungeon_variant/skeleton")
                        .add("dungeon_variant/spider")
                        .add("dungeon_variant/special")
                )
                .placement(DEPlacement.UNDERGROUND)
                .allowNearSpawn()
                .build();

        ELDERS_TEMPLE = register("elders_temple", new DEEldersTemple(), DEEldersTemple.Piece::new);
        FISHING_SHIP = register("fishing_ship", new DESwimmingStructure(DEConfig.COMMON.FISHING_SHIP, true, pieceBuilder().offset(-4, -3, -14).add("fishing_ship").build()), DESwimmingStructure.Piece::new);

        FLYING_DUTCHMAN = DEModularRegistrarBuilder.create("flying_dutchman", ()-> DEStructures.FLYING_DUTCHMAN)
                .config(DEConfig.COMMON.FLYING_DUTCHMAN)
                .piece("flying_dutchman")
                .placement(DEPlacement.ABOVE_GROUND)
                .build();
        //        FLYING_DUTCHMAN = register("flying_dutchman", new DEFlyingStructure(DEConfig.COMMON.FLYING_DUTCHMAN, false, pieceBuilder().offset(-4, -4, -15).add("flying_dutchman").build()), DEFlyingStructure.Piece::new);

        HAY_STORAGE = register("hay_storage", new DESimpleStructure(DEConfig.COMMON.HAY_STORAGE, true, pieceBuilder().offset(-7, 0, -7).add("hay_storage/small").offset(-9, 0, -9).add("hay_storage/big").build()), DESimpleStructure.Piece::new);
        ICE_PIT = register("ice_pit", new DEIcePit(), DESimpleStructure.Piece::new);
        JUNGLE_MONUMENT = register("jungle_monument", new DESimpleStructure(DEConfig.COMMON.JUNGLE_MONUMENT, pieceBuilder().offset(-12, -9, -12).add("jungle_monument").build()), DESimpleStructure.Piece::new);
        LARGE_DUNGEON = registerJigsaw("large_dungeon", new DELargeDungeon(), DELargeDungeon.Pool.Root, 6, DELargeDungeon.Piece::new);
        MINERS_HOUSE = register("miners_house", new DESimpleStructure(DEConfig.COMMON.MINERS_HOUSE, pieceBuilder().offset(-5, 0, -5).add("miners_house").build()), DESimpleStructure.Piece::new);
        MONSTER_MAZE = registerJigsaw("monster_maze", new DEMonsterMaze(), DEMonsterMaze.Pool.Root, 9, DEMonsterMaze.Piece::new);
        MUSHROOM_HOUSE = register("mushroom_house", new DESimpleStructure(DEConfig.COMMON.MUSHROOM_HOUSE, pieceBuilder().offset(-7, 0, -7).add("mushroom_house/red").add("mushroom_house/brown").build()), DESimpleStructure.Piece::new);
        PILLAGER_CAMP = registerJigsaw("pillager_camp", new DEPillagerCamp(), DEPillagerCamp.Pool.Root, 4, DEPillagerCamp.Piece::new);
        PIRATE_SHIP = register("pirate_ship", new DEPirateShip(), DESwimmingStructure.Piece::new);

        RUINED_BUILDING = DEModularRegistrarBuilder.create("ruined_building", ()-> DEStructures.RUINED_BUILDING)
                .config(DEConfig.COMMON.RUINED_BUILDING)
                .piece(builder -> builder
                        .add(3, "ruined_building/house")
                        .add(2, "ruined_building/house_big")
                        .add(3, "ruined_building/barn"))
                .allowNearSpawn()
                .build();

        STABLES = register("stables", new DESimpleStructure(DEConfig.COMMON.STABLES, pieceBuilder().offset(-8, -6, -13).add("stables").build()), DESimpleStructure.Piece::new);

        SUNKEN_SHRINE = DEModularRegistrarBuilder.create("sunken_shrine", ()-> DEStructures.SUNKEN_SHRINE)
                .config(DEConfig.COMMON.SUNKEN_SHRINE)
                .placement(DEPlacement.ON_OCEAN_FLOOR)
                .piece(builder -> builder
                        .settings(placementSettings -> placementSettings.clearProcessors()
                                .addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK)
                                .addProcessor(DEUnderwaterProcessor.INSTANCE)
                                .addProcessor(DESwapDeadCoralsProcessor.INSTANCE)
                        )
                        .add(2, "sunken_shrine/small", -2)
                        .add(1, "sunken_shrine/big", -2)
                )
                .addFilter(DEPlacementFilter.DIFFERENCE_OCEAN_FLOOR_MIN(8))
                .allowNearSpawn()
                .build();
        //        SUNKEN_SHRINE = register("sunken_shrine", new DEUnderwaterStructure(DEConfig.COMMON.SUNKEN_SHRINE, true, pieceBuilder().offset(-5, -1, -8).add("sunken_shrine").build()), DEUnderwaterStructure.Piece::new);

        TALL_WITCH_HUT = register("tall_witch_hut", new DESimpleStructure(DEConfig.COMMON.TALL_WITCH_HUT, pieceBuilder().offset(-3, -3, -4).add("tall_witch_hut").build()), DESimpleStructure.Piece::new);
        TREE_HOUSE = register("tree_house", new DESimpleStructure(DEConfig.COMMON.TREE_HOUSE,pieceBuilder().offset(-11, 0, -12).add("tree_house").build()), DESimpleStructure.Piece::new);
        TOWER_OF_THE_UNDEAD = register("tower_of_the_undead", new DESimpleStructure(DEConfig.COMMON.TOWER_OF_THE_UNDEAD, true, pieceBuilder().offset(-5, 0, -5).weight(3).add("tower_of_the_undead/small").offset(-7, 0, -7).weight(2).add("tower_of_the_undead/big").build()), DESimpleStructure.Piece::new);
        WATCH_TOWER = register("watch_tower", new DESimpleStructure(DEConfig.COMMON.WATCH_TOWER, pieceBuilder().offset(-4,0,-4).add("watch_tower").build()), DESimpleStructure.Piece::new);
        WITCH_TOWER = register("witch_tower", new DESimpleStructure(DEConfig.COMMON.WITCH_TOWER, true, pieceBuilder().offset(-6, 0, -5).weight(3).add("witch_tower/normal").offset(-7, 0, -7).weight(2).add("witch_tower/big").build()), DESimpleStructure.Piece::new);
    }

    public static final StructureRegistrar2<?, ?>[] ALL_STRUCTURE_REGISTRARS = {
            CASTLE,
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
            TREE_HOUSE,
            TOWER_OF_THE_UNDEAD,
            WATCH_TOWER,
            WITCH_TOWER,
    };

    private static  <S extends GelConfigStructure<NoFeatureConfig>> StructureRegistrar2<NoFeatureConfig, S> register(String registryName, S structure, IStructurePieceType piece) {
        return GelStructureRegistrar.of(locate(registryName), structure, piece, NoFeatureConfig.NONE, GenerationStage.Decoration.SURFACE_STRUCTURES).handle();
    }

    private static  <S extends GelConfigJigsawStructure> StructureRegistrar2<VillageConfig, S> registerJigsaw(String registryName, S structure, JigsawPattern root, Integer level, IStructurePieceType piece) {
        return GelStructureRegistrar.of(locate(registryName), structure, piece, new VillageConfig(() -> root, level), GenerationStage.Decoration.SURFACE_STRUCTURES).handle();
    }

    private static StructureRegistrar2<VillageConfig, DECellarStructure> registerCellarStructure(String registryName, DECellarStructure structure, JigsawPattern root, IStructurePieceType piece) {
        return GelStructureRegistrar.of(locate(registryName), structure, piece, new VillageConfig(() -> root, 1), GenerationStage.Decoration.SURFACE_STRUCTURES).handle();
    }

    public static Structure<?>[] getAllStructures() {
        Structure<?>[] temp = new Structure<?>[ALL_STRUCTURE_REGISTRARS.length];
        for(int i = 0; i < ALL_STRUCTURE_REGISTRARS.length; i++){
            temp[i] = ALL_STRUCTURE_REGISTRARS[i].getStructure();
        }
        return temp;
    }
}