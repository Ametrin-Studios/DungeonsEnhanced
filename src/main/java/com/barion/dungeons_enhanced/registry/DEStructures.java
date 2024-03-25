package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.*;
import com.barion.dungeons_enhanced.world.structure.builder.DEModularRegistrarBuilder;
import com.barion.dungeons_enhanced.world.structure.builder.DEPlacement;
import com.barion.dungeons_enhanced.world.structure.prefabs.*;
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

import static com.barion.dungeons_enhanced.DEUtil.location;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public final class DEStructures {
    public static final StructureRegistrar2<VillageConfig, DECellarStructure> Castle;
    public static final StructureRegistrar2<NoFeatureConfig, DEDesertTemple> DesertTemple;
    public static final StructureRegistrar2<VillageConfig, DEDesertTomb> DesertTomb;
    public static final StructureRegistrar2<VillageConfig, DECellarStructure> DruidCircle;
    public static final StructureRegistrar2<NoFeatureConfig, DEModularStructure> DUNGEON_VARIANT;
    public static final StructureRegistrar2<NoFeatureConfig, DEEldersTemple> EldersTemple;
    public static final StructureRegistrar2<NoFeatureConfig, DESwimmingStructure> FishingShip;
    public static final StructureRegistrar2<NoFeatureConfig, DEModularStructure> FLYING_DUTCHMAN;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> HayStorage;
    public static final StructureRegistrar2<NoFeatureConfig, DEIcePit> IcePit;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> JungleMonument;
    public static final StructureRegistrar2<VillageConfig, DELargeDungeon> LargeDungeon;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> MinersHouse;
    public static final StructureRegistrar2<VillageConfig, DEMonsterMaze> MonsterMaze;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> MushroomHouse;
    public static final StructureRegistrar2<VillageConfig, DEPillagerCamp> PillagerCamp;
    public static final StructureRegistrar2<NoFeatureConfig, DEPirateShip> PirateShip;
    public static final StructureRegistrar2<NoFeatureConfig, DEModularStructure> RUINED_BUILDING;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> Stables;
    public static final StructureRegistrar2<NoFeatureConfig, DEUnderwaterStructure> SunkenShrine;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> TallWitchHut;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> TreeHouse;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> TowerOfTheUndead;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> WatchTower;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> WitchTower;

    public DEStructures(){}

    static {
        Castle = registerCellarStructure("castle", new DECellarStructure(DEConfig.COMMON.Castle, DETerrainAnalyzer.defaultSettings, false), DECellarStructure.CastlePool.ROOT, DECellarStructure.Piece::new);
        DesertTemple = register("desert_temple", new DEDesertTemple(), DESimpleStructure.Piece::new);
        DesertTomb = registerJigsaw("desert_tomb", new DEDesertTomb(), DEDesertTomb.Pool.Root, 4, DEDesertTomb.Piece::new);
        DruidCircle = registerCellarStructure("druid_circle", new DECellarStructure(DEConfig.COMMON.DruidCircle, DETerrainAnalyzer.defaultSettings, true), DECellarStructure.DruidCirclePool.ROOT, DECellarStructure.Piece::new);

        DUNGEON_VARIANT = DEModularRegistrarBuilder.create("dungeon_variant", ()-> DEStructures.DUNGEON_VARIANT)
                .config(DEConfig.COMMON.DUNGEON_VARIANT)
                .piece(builder -> builder
                        .add("dungeon_variant/zombie")
                        .add("dungeon_variant/skeleton")
                        .add("dungeon_variant/spider")
                )
                .placement(DEPlacement.UNDERGROUND)
                .allowNearSpawn()
                .build();

        EldersTemple = register("elders_temple", new DEEldersTemple(), DEEldersTemple.Piece::new);
        FishingShip = register("fishing_ship", new DESwimmingStructure(DEConfig.COMMON.FishingShip, true, pieceBuilder().offset(-4, -3, -14).add("fishing_ship").build()), DESwimmingStructure.Piece::new);

        FLYING_DUTCHMAN = DEModularRegistrarBuilder.create("flying_dutchman", ()-> DEStructures.FLYING_DUTCHMAN)
                .config(DEConfig.COMMON.FLYING_DUTCHMAN)
                .piece("flying_dutchman")
                .placement(DEPlacement.ABOVE_GROUND)
                .build();
        //        FLYING_DUTCHMAN = register("flying_dutchman", new DEFlyingStructure(DEConfig.COMMON.FlyingDutchman, false, pieceBuilder().offset(-4, -4, -15).add("flying_dutchman").build()), DEFlyingStructure.Piece::new);

        HayStorage = register("hay_storage", new DESimpleStructure(DEConfig.COMMON.HayStorage, true, pieceBuilder().offset(-7, 0, -7).add("hay_storage/small").offset(-9, 0, -9).add("hay_storage/big").build()), DESimpleStructure.Piece::new);
        IcePit = register("ice_pit", new DEIcePit(), DESimpleStructure.Piece::new);
        JungleMonument = register("jungle_monument", new DESimpleStructure(DEConfig.COMMON.JungleMonument, pieceBuilder().offset(-12, -9, -12).add("jungle_monument").build()), DESimpleStructure.Piece::new);
        LargeDungeon = registerJigsaw("large_dungeon", new DELargeDungeon(), DELargeDungeon.Pool.Root, 6, DELargeDungeon.Piece::new);
        MinersHouse = register("miners_house", new DESimpleStructure(DEConfig.COMMON.MinersHouse, pieceBuilder().offset(-5, 0, -5).add("miners_house").build()), DESimpleStructure.Piece::new);
        MonsterMaze = registerJigsaw("monster_maze", new DEMonsterMaze(), DEMonsterMaze.Pool.Root, 9, DEMonsterMaze.Piece::new);
        MushroomHouse = register("mushroom_house", new DESimpleStructure(DEConfig.COMMON.MushroomHouse, pieceBuilder().offset(-7, 0, -7).add("mushroom_house/red").add("mushroom_house/brown").build()), DESimpleStructure.Piece::new);
        PillagerCamp = registerJigsaw("pillager_camp", new DEPillagerCamp(), DEPillagerCamp.Pool.Root, 4, DEPillagerCamp.Piece::new);
        PirateShip = register("pirate_ship", new DEPirateShip(), DESwimmingStructure.Piece::new);

        RUINED_BUILDING = DEModularRegistrarBuilder.create("ruined_building", ()-> DEStructures.RUINED_BUILDING)
                .config(DEConfig.COMMON.RUINED_BUILDING)
                .piece(builder -> builder
                        .add(3, "ruined_building/house")
                        .add(2, "ruined_building/house_big")
                        .add(3, "ruined_building/barn"))
                .allowNearSpawn()
                .build();

        Stables = register("stables", new DESimpleStructure(DEConfig.COMMON.Stables, pieceBuilder().offset(-8, -6, -13).add("stables").build()), DESimpleStructure.Piece::new);
        SunkenShrine = register("sunken_shrine", new DEUnderwaterStructure(DEConfig.COMMON.SunkenShrine, true, pieceBuilder().offset(-5, -1, -8).add("sunken_shrine").build()), DEUnderwaterStructure.Piece::new);
        TallWitchHut = register("tall_witch_hut", new DESimpleStructure(DEConfig.COMMON.TallWitchHut, pieceBuilder().offset(-3, -3, -4).add("tall_witch_hut").build()), DESimpleStructure.Piece::new);
        TreeHouse = register("tree_house", new DESimpleStructure(DEConfig.COMMON.TreeHouse,pieceBuilder().offset(-11, 0, -12).add("tree_house").build()), DESimpleStructure.Piece::new);
        TowerOfTheUndead = register("tower_of_the_undead", new DESimpleStructure(DEConfig.COMMON.TowerOfTheUndead, true, pieceBuilder().offset(-5, 0, -5).weight(3).add("tower_of_the_undead/small").offset(-7, 0, -7).weight(2).add("tower_of_the_undead/big").build()), DESimpleStructure.Piece::new);
        WatchTower = register("watch_tower", new DESimpleStructure(DEConfig.COMMON.WatchTower, pieceBuilder().offset(-4,0,-4).add("watch_tower").build()), DESimpleStructure.Piece::new);
        WitchTower = register("witch_tower", new DESimpleStructure(DEConfig.COMMON.WitchTower, true, pieceBuilder().offset(-6, 0, -5).weight(3).add("witch_tower/normal").offset(-7, 0, -7).weight(2).add("witch_tower/big").build()), DESimpleStructure.Piece::new);
    }

    public static final StructureRegistrar2<?, ?>[] ALL_STRUCTURE_REGISTRARS = {
            Castle,
            DesertTemple,
            DesertTomb,
            DruidCircle,
            DUNGEON_VARIANT,
            EldersTemple,
            FishingShip,
            FLYING_DUTCHMAN,
            HayStorage,
            IcePit,
            JungleMonument,
            LargeDungeon,
            MinersHouse,
            MonsterMaze,
            MushroomHouse,
            PillagerCamp,
            PirateShip,
            RUINED_BUILDING,
            Stables,
            SunkenShrine,
            TallWitchHut,
            TreeHouse,
            TowerOfTheUndead,
            WatchTower,
            WitchTower,
    };

    private static  <S extends GelConfigStructure<NoFeatureConfig>> StructureRegistrar2<NoFeatureConfig, S> register(String registryName, S structure, IStructurePieceType piece){
        return GelStructureRegistrar.of(location(registryName), structure, piece, NoFeatureConfig.NONE, GenerationStage.Decoration.SURFACE_STRUCTURES).handle();
    }

    private static  <S extends GelConfigJigsawStructure> StructureRegistrar2<VillageConfig, S> registerJigsaw(String registryName, S structure, JigsawPattern root, Integer level, IStructurePieceType piece){
        return GelStructureRegistrar.of(location(registryName), structure, piece, new VillageConfig(() -> root, level), GenerationStage.Decoration.SURFACE_STRUCTURES).handle();
    }

    private static StructureRegistrar2<VillageConfig, DECellarStructure> registerCellarStructure(String registryName, DECellarStructure structure, JigsawPattern root, IStructurePieceType piece){
        return GelStructureRegistrar.of(location(registryName), structure, piece, new VillageConfig(() -> root, 1), GenerationStage.Decoration.SURFACE_STRUCTURES).handle();
    }

    public static Structure<?>[] getAllStructures(){
        Structure<?>[] temp = new Structure<?>[ALL_STRUCTURE_REGISTRARS.length];
        for(int i = 0; i < ALL_STRUCTURE_REGISTRARS.length; i++){
            temp[i] = ALL_STRUCTURE_REGISTRARS[i].getStructure();
        }
        return temp;
    }
}