package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.structures.*;
import com.barion.dungeons_enhanced.world.structures.prefabs.*;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.ExtendedJigsawStructure;
import com.legacy.structure_gel.api.structure.GridStructurePlacement;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.barion.dungeons_enhanced.DEUtil.location;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public class DEStructures {
    public static StructureRegistrar<ExtendedJigsawStructure> Castle;
    public static StructureRegistrar<ExtendedJigsawStructure> DeepCrypt;
    public static StructureRegistrar<DEDesertTemple> DesertTemple;
    public static StructureRegistrar<ExtendedJigsawStructure> DesertTomb;
    public static StructureRegistrar<ExtendedJigsawStructure> DruidCircle;
    public static StructureRegistrar<DEUndergroundStructure> DungeonVariant;
    public static StructureRegistrar<DEEldersTemple> EldersTemple;
    public static StructureRegistrar<DESwimmingStructure> FishingShip;
    public static StructureRegistrar<DEFlyingStructure> FlyingDutchman;
    public static StructureRegistrar<DESimpleStructure> HayStorage;
    public static StructureRegistrar<DEIcePit> IcePit;
    public static StructureRegistrar<DESimpleStructure> JungleMonument;
    public static StructureRegistrar<ExtendedJigsawStructure> LargeDungeon;
    public static StructureRegistrar<DESimpleStructure> MinersHouse;
    public static StructureRegistrar<ExtendedJigsawStructure> MonsterMaze;
    public static StructureRegistrar<DESimpleStructure> MushroomHouse;
    public static StructureRegistrar<ExtendedJigsawStructure> PillagerCamp;
    public static StructureRegistrar<DEPirateShip> PirateShip;
    public static StructureRegistrar<DESimpleStructure> RuinedBuilding;
    public static StructureRegistrar<DESimpleStructure> Stables;
    public static StructureRegistrar<DEUnderwaterStructure> SunkenShrine;
    public static StructureRegistrar<DESimpleStructure> TallWitchHut;
    public static StructureRegistrar<DESimpleStructure> TreeHouse;
    public static StructureRegistrar<DESimpleStructure> TowerOfTheUndead;
    public static StructureRegistrar<DESimpleStructure> WatchTower;
    public static StructureRegistrar<DESimpleStructure> WitchTower;

    private DEStructures(){}

    static {
        DECellarStructure.init();

        Castle = StructureRegistrar.jigsawBuilder(location("castle"))
                .addPiece(()-> DECellarStructure.Piece::new)
                .pushStructure((settings)-> new ExtendedJigsawStructure(settings, DECellarStructure.CastlePool.Root, 1, ConstantHeight.ZERO, false, Heightmap.Types.WORLD_SURFACE_WG).withPieceFactory(Castle.getRegistryName(), DECellarStructure.Piece::new)/*.withPlacementTest(DEUtil.location("is_flat_enough"), (context, pos, jigsawContext)-> DECellarStructure.checkLocation(context, pos, new DETerrainAnalyzer.Settings(1, 2, 2)))*/)
                        .config(DEConfig.COMMON.Castle::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.Castle).build(Castle))
                .build();

        DEDeepCrypt.Pool.init();
        DeepCrypt = StructureRegistrar.jigsawBuilder(location("deep_crypt"))
                .addPiece(()-> DEDeepCrypt.Piece::new)
                .pushStructure((settings)-> new ExtendedJigsawStructure(settings, DEDeepCrypt.Pool.Root, 4, ConstantHeight.of(VerticalAnchor.absolute(-16)), false).withPieceFactory(DeepCrypt.getRegistryName(), DEDeepCrypt.Piece::new))
                        .config(DEConfig.COMMON.DeepCrypt::getStructure)
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.DeepCrypt).build(DeepCrypt))
                .build();

        DesertTemple = StructureRegistrar.builder(location("desert_temple"), codecOf(DEDesertTemple::new))
                .addPiece(()-> DEDesertTemple.Piece::new)
                .pushStructure(DEDesertTemple::new)
                        .config(DEConfig.COMMON.DesertTemple::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.DesertTemple).build(DesertTemple))
                .build();

        DEDesertTomb.Pool.init();
        DesertTomb = StructureRegistrar.jigsawBuilder(location("desert_tomb"))
                .addPiece(()-> DEDesertTomb.Piece::new)
                .pushStructure(((settings)-> new ExtendedJigsawStructure(settings, DEDesertTomb.Pool.Root, 4, ConstantHeight.ZERO, false, Heightmap.Types.WORLD_SURFACE_WG).withPieceFactory(DesertTomb.getRegistryName(), DEDesertTomb.Piece::new)))
                        .config(DEConfig.COMMON.DesertTomb::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.DesertTemple).allowedNearSpawn(true).build(DesertTomb))
                .build();

        DruidCircle = StructureRegistrar.jigsawBuilder(location("druid_circle"))
                .addPiece(()-> DECellarStructure.Piece::new)
                .pushStructure((settings)-> new ExtendedJigsawStructure(settings, DECellarStructure.DruidCirclePool.Root, 1, ConstantHeight.ZERO, false, Heightmap.Types.WORLD_SURFACE_WG).withPieceFactory(DruidCircle.getRegistryName(), DECellarStructure.Piece::new))
                        .config(DEConfig.COMMON.DruidCircle::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.DruidCircle).allowedNearSpawn(true).build(DruidCircle))
                .build();

        Function<Structure.StructureSettings, DEUndergroundStructure> dungeonVariant = (settings)-> new DEUndergroundStructure(settings, pieceBuilder().offset(-6, 0, -6).add("dungeon_variant/zombie").add("dungeon_variant/skeleton").add("dungeon_variant/spider").build(), DungeonVariant::getType);
        DungeonVariant = StructureRegistrar.builder(location("dungeon_variant"), codecOf(dungeonVariant))
                .addPiece(()-> DEUndergroundStructure.Piece::new)
                .pushStructure(dungeonVariant)
                        .config(DEConfig.COMMON.DungeonVariant::getStructure)
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.DungeonVariant).allowedNearSpawn(true).build(DungeonVariant))
                .build();

        EldersTemple = StructureRegistrar.builder(location("elders_temple"), codecOf(DEEldersTemple::new))
                .addPiece(()-> DEEldersTemple.Piece::new)
                .pushStructure(DEEldersTemple::new)
                        .config(DEConfig.COMMON.EldersTemple::getStructure)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.GUARDIAN, 1,2,4)))
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.EldersTemple).build(EldersTemple))
                .build();

        Function<Structure.StructureSettings, DESwimmingStructure> fishingShip = (settings)-> new DESwimmingStructure(settings, pieceBuilder().offset(-4, -3, -14).add("fishing_ship").build(), FishingShip::getType);
        FishingShip = StructureRegistrar.builder(location("fishing_ship"), codecOf(fishingShip))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(fishingShip)
                        .config(DEConfig.COMMON.FishingShip::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.FishingShip).allowedNearSpawn(true).build(FishingShip))
                .build();

        Function<Structure.StructureSettings, DEFlyingStructure> flyingDutchman = (settings) -> new DEFlyingStructure(settings, pieceBuilder().offset(-4, 0, -15).add("flying_dutchman").build(), FlyingDutchman::getType);
        FlyingDutchman = StructureRegistrar.builder(location("flying_dutchman"), codecOf(flyingDutchman))
                .addPiece(()-> DEFlyingStructure.Piece::new)
                .pushStructure(flyingDutchman)
                        .config(DEConfig.COMMON.FlyingDutchman::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.FlyingDutchman).build(FlyingDutchman))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> hayStorage = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-7, 0, -7).add("hay_storage/small").offset(-9, 0, -9).add("hay_storage/big").build(), HayStorage::getType);
        HayStorage = StructureRegistrar.builder(location("hay_storage"), codecOf(hayStorage))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(hayStorage)
                        .config(DEConfig.COMMON.HayStorage::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.HayStorage).allowedNearSpawn(true).build(HayStorage))
                .build();

        IcePit = StructureRegistrar.builder(location("ice_pit"), codecOf(DEIcePit::new))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(DEIcePit::new)
                        .config(DEConfig.COMMON.IcePit::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.IcePit).build(IcePit))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> jungleMonument = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-12, -9, -12).add("jungle_monument").build(), JungleMonument::getType);
        JungleMonument = StructureRegistrar.builder(location("jungle_monument"), codecOf(jungleMonument))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(jungleMonument)
                        .config(DEConfig.COMMON.JungleMonument::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.JungleMonument).build(JungleMonument))
                .build();

        DELargeDungeon.Pool.init();
        LargeDungeon = StructureRegistrar.jigsawBuilder(location("large_dungeon"))
                .addPiece(()-> DELargeDungeon.Piece::new)
                .pushStructure((settings)-> new ExtendedJigsawStructure(settings, DELargeDungeon.Pool.Root, 5, height(-16), false, Heightmap.Types.WORLD_SURFACE_WG))
                        .config(DEConfig.COMMON.LargeDungeon::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.LargeDungeon).allowedNearSpawn(true).build(LargeDungeon))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> minersHouse = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-5, 0, -5).add("miners_house").build(), MinersHouse::getType);
        MinersHouse = StructureRegistrar.builder(location("miners_house"), codecOf(minersHouse))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(minersHouse)
                        .config(DEConfig.COMMON.MinersHouse::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.MinersHouse).allowedNearSpawn(true).build(MinersHouse))
                .build();

        DEMonsterMaze.Pool.init();
        MonsterMaze = StructureRegistrar.jigsawBuilder(location("monster_maze"))
                .addPiece(()-> DEMonsterMaze.Piece::new)
                .pushStructure((settings)-> new ExtendedJigsawStructure(settings, DEMonsterMaze.Pool.Root, 11, height(-17), true, Heightmap.Types.WORLD_SURFACE_WG).withPieceFactory(MonsterMaze.getRegistryName(), DEMonsterMaze.Piece::new))
                        .config(DEConfig.COMMON.MonsterMaze::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.MonsterMaze).build(MonsterMaze))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> mushroomHouse = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-7, 0, -7).add("mushroom_house/red").add("mushroom_house/brown").build(), MushroomHouse::getType);
        MushroomHouse = StructureRegistrar.builder(location("mushroom_house"), codecOf(mushroomHouse))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(mushroomHouse)
                        .config(DEConfig.COMMON.MushroomHouse::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.MushroomHouse).allowedNearSpawn(true).build(MushroomHouse))
                .build();

        DEPillagerCamp.Pool.init();
        PillagerCamp = StructureRegistrar.jigsawBuilder(location("pillager_camp"))
                .addPiece(()-> DEPillagerCamp.Piece::new)
                .pushStructure((settings)-> new ExtendedJigsawStructure(settings, DEPillagerCamp.Pool.Root, 4, ConstantHeight.ZERO, false, Heightmap.Types.WORLD_SURFACE_WG).withPieceFactory(PillagerCamp.getRegistryName(), DEPillagerCamp.Piece::new))
                        .config(DEConfig.COMMON.PillagerCamp::getStructure)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 2, 3), spawn(EntityType.VINDICATOR, 2, 1, 2)))
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.PillagerCamp).build(PillagerCamp))
                .build();

        PirateShip = StructureRegistrar.builder(location("pirate_ship"), codecOf(DEPirateShip::new))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(DEPirateShip::new)
                        .config(DEConfig.COMMON.PirateShip::getStructure)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.PILLAGER, 4, 3, 4), spawn(EntityType.VINDICATOR, 3, 1, 2)))
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.PirateShip).build(PirateShip))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> ruinedBuilding = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-5, 0, -5).weight(3).add("ruined_building/house").offset(-6, 0, -8).weight(2).add("ruined_building/house_big").offset(-4, 0, -5).weight(3).add("ruined_building/barn").build(), RuinedBuilding::getType);
        RuinedBuilding = StructureRegistrar.builder(location("ruined_building"), codecOf(ruinedBuilding))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(ruinedBuilding)
                        .config(DEConfig.COMMON.RuinedBuilding::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.RuinedBuilding).allowedNearSpawn(true).build(RuinedBuilding))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> stables = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-8, -6, -13).add("stables").build(), Stables::getType);
        Stables = StructureRegistrar.builder(location("stables"), codecOf(stables))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(stables)
                        .config(DEConfig.COMMON.Stables::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.Stables).allowedNearSpawn(true).build(Stables))
                .build();

        Function<Structure.StructureSettings, DEUnderwaterStructure> sunkenShrine = (settings)-> new DEUnderwaterStructure(settings, pieceBuilder().offset(-5, -1, -8).add("sunken_shrine").build(), SunkenShrine::getType);
        SunkenShrine = StructureRegistrar.builder(location("sunken_shrine"), codecOf(sunkenShrine))
                .addPiece(()-> DEUnderwaterStructure.Piece::new)
                .pushStructure(sunkenShrine)
                        .config(DEConfig.COMMON.SunkenShrine::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.SunkenShrine).allowedNearSpawn(true).build(SunkenShrine))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> tallWitchHut = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-3, -3, -4).add("tall_witch_hut").build(), TallWitchHut::getType);
        TallWitchHut = StructureRegistrar.builder(location("tall_witch_hut"), codecOf(tallWitchHut))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(tallWitchHut)
                        .config(DEConfig.COMMON.TallWitchHut::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.TallWitchHut).allowedNearSpawn(true).build(TallWitchHut))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> treeHouse = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-11, 0, -12).add("tree_house").build(), TreeHouse::getType);
        TreeHouse = StructureRegistrar.builder(location("tree_house"), codecOf(treeHouse))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(treeHouse)
                        .config(DEConfig.COMMON.TreeHouse::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.TreeHouse).allowedNearSpawn(true).build(TreeHouse))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> undeadTower = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-5, 0, -5).weight(3).add("tower_of_the_undead/small").offset(-7, 0, -7).weight(2).add("tower_of_the_undead/big").build(), TowerOfTheUndead::getType);
        TowerOfTheUndead = StructureRegistrar.builder(location("tower_of_the_undead"), codecOf(undeadTower))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(undeadTower)
                        .config(DEConfig.COMMON.TowerOfTheUndead::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.TowerOfTheUndead).allowedNearSpawn(true).build(TowerOfTheUndead))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> watchTower = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-4,0,-4).add("watch_tower").build(), WatchTower::getType);
        WatchTower = StructureRegistrar.builder(location("watch_tower"), codecOf(watchTower))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(watchTower)
                        .config(DEConfig.COMMON.WatchTower::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.WatchTower).allowedNearSpawn(true).build(WatchTower))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> witchTower = (settings)-> new DESimpleStructure(settings, pieceBuilder().offset(-6, 0, -5).weight(3).add("witch_tower/normal").offset(-7, 0, -7).weight(2).add("witch_tower/big").build(), WitchTower::getType);
        WitchTower = StructureRegistrar.builder(location("witch_tower"), codecOf(witchTower))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(witchTower)
                        .config(DEConfig.COMMON.WitchTower::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.WitchTower).allowedNearSpawn(true).build(WitchTower))
                .build();
    }

    public static StructureRegistrar<?>[] getAllStructureRegistrars() {
        return new StructureRegistrar<?>[] {
                Castle,
                DeepCrypt,
                DesertTemple,
                DesertTomb,
                DruidCircle,
                DungeonVariant,
                EldersTemple,
                FishingShip,
                FlyingDutchman,
                HayStorage,
                IcePit,
                JungleMonument,
                LargeDungeon,
                MinersHouse,
                MonsterMaze,
                MushroomHouse,
                PillagerCamp,
                PirateShip,
                RuinedBuilding,
                Stables,
                SunkenShrine,
                TallWitchHut,
                TreeHouse,
                TowerOfTheUndead,
                WatchTower,
                WitchTower
        };
    }

    private static ConstantHeight height(int y) {return ConstantHeight.of(new VerticalAnchor.Absolute(y));}
    private static Supplier<List<MobSpawnSettings.SpawnerData>> spawns(MobSpawnSettings.SpawnerData... spawns) {return ()-> Arrays.stream(spawns).toList();}
    private static MobSpawnSettings.SpawnerData spawn(EntityType<?> entity, int weight, int min, int max) {
        return new MobSpawnSettings.SpawnerData(entity, weight, min, max);
    }
    private static <S extends Structure> Supplier<StructureType<S>> codecOf(Function<Structure.StructureSettings, S> constructor){
        return ()-> ()-> Structure.simpleCodec(constructor);
    }

    public static void init() {}
}