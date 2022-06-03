package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.*;
import com.barion.dungeons_enhanced.world.structures.prefabs.*;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.barion.dungeons_enhanced.DEUtil.location;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public class DEStructures {
    public static final StructureRegistrar<JigsawConfiguration, DECellarStructure> Castle;
    public static final StructureRegistrar<JigsawConfiguration, DEDeepCrypt> DeepCrypt;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDesertTemple> DesertTemple;
    public static final StructureRegistrar<JigsawConfiguration, DEDesertTomb> DesertTomb;
    public static final StructureRegistrar<JigsawConfiguration, DECellarStructure> DruidCircle;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEUndergroundStructure> DungeonVariant;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEEldersTemple> EldersTemple;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEShipStructure> FishingShip;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEFloatingStructure> FlyingDutchman;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> HayStorage;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEIcePit> IcePit;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> JungleMonument;
    public static final StructureRegistrar<JigsawConfiguration, DELargeDungeon> LargeDungeon;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> MinersHouse;
    public static final StructureRegistrar<JigsawConfiguration, DEMonsterMaze> MonsterMaze;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> MushroomHouse;
    public static final StructureRegistrar<JigsawConfiguration, DEPillagerCamp> PillagerCamp;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEPirateShip> PirateShip;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> RuinedBuilding;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> Stables;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEUnderwaterStructure> SunkenShrine;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TallWitchHut;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TreeHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TowerOfTheUndead;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> WatchTower;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> WitchTower;

    public DEStructures(){}

    static {
        Castle = StructureRegistrar.builder(location("castle"), () -> new DECellarStructure(DEConfig.COMMON.Castle, false, new DETerrainAnalyzer.Settings(1, 3, 4)))
                .pushConfigured(new JigsawConfiguration(DECellarStructure.CastlePool.Root, 1))
                        .biomes(DEConfig.COMMON.Castle.getConfigured())
                        .dimensions(DEConfig.COMMON.Castle.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DECellarStructure.Piece::new)
                .build();

        DeepCrypt = StructureRegistrar.builder(location("deep_crypt"), DEDeepCrypt::new)
                .pushConfigured(new JigsawConfiguration(DEDeepCrypt.Pool.Root, 4))
                        .biomes(DEConfig.COMMON.DeepCrypt.getConfigured())
                        .dimensions(DEConfig.COMMON.DeepCrypt.getConfigured())
                .popConfigured()
                .addPiece(DEDeepCrypt.Piece::new)
                .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .build();

        DesertTemple = StructureRegistrar.builder(location("desert_temple"), DEDesertTemple::new)
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.DesertTemple.getConfigured())
                        .dimensions(DEConfig.COMMON.DesertTemple.getConfigured())
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        DesertTomb = StructureRegistrar.builder(location("desert_tomb"), DEDesertTomb::new)
                .pushConfigured(new JigsawConfiguration(DEDesertTomb.Pool.Root, 4))
                        .biomes(DEConfig.COMMON.DesertTomb.getConfigured())
                        .dimensions(DEConfig.COMMON.DesertTomb.getConfigured())
                .popConfigured()
                .addPiece(DEDesertTomb.Piece::new)
                .build();

        DruidCircle = StructureRegistrar.builder(location("druid_circle"), () -> new DECellarStructure(DEConfig.COMMON.DruidCircle, true, DETerrainAnalyzer.defaultCheckSettings))
                .pushConfigured(new JigsawConfiguration(DECellarStructure.DruidCirclePool.Root, 1))
                        .biomes(DEConfig.COMMON.DruidCircle.getConfigured())
                        .dimensions(DEConfig.COMMON.DruidCircle.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DECellarStructure.Piece::new)
                .build();

        DungeonVariant = StructureRegistrar.builder(location("dungeon_variant"), () -> new DEUndergroundStructure(DEConfig.COMMON.DungeonVariant, true, pieceBuilder().offset(-6, 0, -6).add("dungeon_variant/zombie").add("dungeon_variant/skeleton").add("dungeon_variant/spider").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.DungeonVariant.getConfigured())
                        .dimensions(DEConfig.COMMON.DungeonVariant.getConfigured())
                .popConfigured()
                .addPiece(DEUndergroundStructure.Piece::new)
                .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .build();

        EldersTemple = StructureRegistrar.builder(location("elders_temple"), DEEldersTemple::new)
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.EldersTemple.getConfigured())
                        .dimensions(DEConfig.COMMON.EldersTemple.getConfigured())
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawn(EntityType.GUARDIAN, 1, 2, 4))
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .popConfigured()
                .addPiece(DEEldersTemple.Piece::new)
                .build();

        FishingShip = StructureRegistrar.builder(location("fishing_ship"), () -> new DEShipStructure(DEConfig.COMMON.FishingShip, pieceBuilder().offset(-4, -3, -14).add("fishing_ship").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.FishingShip.getConfigured())
                        .dimensions(DEConfig.COMMON.FishingShip.getConfigured())
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        FlyingDutchman = StructureRegistrar.builder(location("flying_dutchman"), () -> new DEFloatingStructure(DEConfig.COMMON.FlyingDutchman, false, pieceBuilder().offset(-4, 0, -15).add("flying_dutchman").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.FlyingDutchman.getConfigured())
                        .dimensions(DEConfig.COMMON.FlyingDutchman.getConfigured())
                .popConfigured()
                .addPiece(DEFloatingStructure.Piece::new)
                .build();

        HayStorage = StructureRegistrar.builder(location("hay_storage"), () -> new DESimpleStructure(DEConfig.COMMON.HayStorage, pieceBuilder().offset(-7, 0, -7).add("hay_storage/small").offset(-9, 0, -9).add("hay_storage/big").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.HayStorage.getConfigured())
                        .dimensions(DEConfig.COMMON.HayStorage.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        IcePit = StructureRegistrar.builder(location("ice_pit"), DEIcePit::new)
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.IcePit.getConfigured())
                        .dimensions(DEConfig.COMMON.IcePit.getConfigured())
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        JungleMonument = StructureRegistrar.builder(location("jungle_monument"), () -> new DESimpleStructure(DEConfig.COMMON.JungleMonument, pieceBuilder().offset(-12, -9, -12).add("jungle_monument").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.JungleMonument.getConfigured())
                        .dimensions(DEConfig.COMMON.JungleMonument.getConfigured())
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        LargeDungeon = StructureRegistrar.builder(location("large_dungeon"), DELargeDungeon::new)
                .pushConfigured(new JigsawConfiguration(DELargeDungeon.Pool.Root, 5))
                        .biomes(DEConfig.COMMON.LargeDungeon.getConfigured())
                        .dimensions(DEConfig.COMMON.LargeDungeon.getConfigured())
                .popConfigured()
                .addPiece(DELargeDungeon.Piece::new)
                .build();

        MinersHouse = StructureRegistrar.builder(location("miners_house"), () -> new DESimpleStructure(DEConfig.COMMON.MinersHouse, pieceBuilder().offset(-5, 0, -5).add("miners_house").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.MinersHouse.getConfigured())
                        .dimensions(DEConfig.COMMON.MinersHouse.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        MonsterMaze = StructureRegistrar.builder(location("monster_maze"), DEMonsterMaze::new)
                .pushConfigured(new JigsawConfiguration(DEMonsterMaze.Pool.Root, 7))
                        .biomes(DEConfig.COMMON.MonsterMaze.getConfigured())
                        .dimensions(DEConfig.COMMON.MonsterMaze.getConfigured())
                .popConfigured()
                .addPiece(DEMonsterMaze.Piece::new)
                .build();

        MushroomHouse = StructureRegistrar.builder(location("mushroom_house"), () -> new DESimpleStructure(DEConfig.COMMON.MushroomHouse, pieceBuilder().offset(-7, 0, -7).add("mushroom_house/red").add("mushroom_house/brown").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.MushroomHouse.getConfigured())
                        .dimensions(DEConfig.COMMON.MushroomHouse.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        PillagerCamp = StructureRegistrar.builder(location("pillager_camp"), DEPillagerCamp::new)
                .pushConfigured(new JigsawConfiguration(DEPillagerCamp.Pool.Root, 4))
                        .biomes(DEConfig.COMMON.PillagerCamp.getConfigured())
                        .dimensions(DEConfig.COMMON.PillagerCamp.getConfigured())
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawn(EntityType.PILLAGER, 9), spawn(EntityType.VINDICATOR, 3))
                        .adaptNoise()
                .popConfigured()
                .addPiece(DEPillagerCamp.Piece::new)
                .build();

        PirateShip = StructureRegistrar.builder(location("pirate_ship"), DEPirateShip::new)
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.PirateShip.getConfigured())
                        .dimensions(DEConfig.COMMON.PirateShip.getConfigured())
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawn(EntityType.PILLAGER, 9), spawn(EntityType.VINDICATOR, 3))
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        RuinedBuilding = StructureRegistrar.builder(location("ruined_building"), () -> new DESimpleStructure(DEConfig.COMMON.RuinedBuilding, pieceBuilder().offset(-5, 0, -5).weight(3).add("ruined_building/house").offset(-6, 0, -8).weight(2).add("ruined_building/house_big").offset(-4, 0, -5).weight(3).add("ruined_building/barn").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.RuinedBuilding.getConfigured())
                        .dimensions(DEConfig.COMMON.RuinedBuilding.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        Stables = StructureRegistrar.builder(location("stables"), () -> new DESimpleStructure(DEConfig.COMMON.Stables, pieceBuilder().offset(-8, -6, -13).add("stables").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.Stables.getConfigured())
                        .dimensions(DEConfig.COMMON.Stables.getConfigured())
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        SunkenShrine = StructureRegistrar.builder(location("sunken_shrine"), () -> new DEUnderwaterStructure(DEConfig.COMMON.SunkenShrine, true, pieceBuilder().offset(-5, -1, -8).add("sunken_shrine").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.SunkenShrine.getConfigured())
                        .dimensions(DEConfig.COMMON.SunkenShrine.getConfigured())
                .popConfigured()
                .addPiece(DEUnderwaterStructure.Piece::new)
                .build();

        TallWitchHut = StructureRegistrar.builder(location("tall_witch_hut"), () -> new DESimpleStructure(DEConfig.COMMON.TallWitchHut, pieceBuilder().offset(-3, -3, -4).add("tall_witch_hut").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                                .biomes(DEConfig.COMMON.TallWitchHut.getConfigured())
                                .dimensions(DEConfig.COMMON.TallWitchHut.getConfigured())
                                .adaptNoise()
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        TreeHouse = StructureRegistrar.builder(location("tree_house"), () -> new DESimpleStructure(DEConfig.COMMON.TreeHouse, pieceBuilder().offset(-11, 0, -12).add("tree_house").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.TreeHouse.getConfigured())
                        .dimensions(DEConfig.COMMON.TreeHouse.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        TowerOfTheUndead = StructureRegistrar.builder(location("tower_of_the_undead"), () -> new DESimpleStructure(DEConfig.COMMON.TowerOfTheUndead, pieceBuilder().offset(-5, 0, -5).weight(3).add("tower_of_the_undead/small").offset(-7, 0, -7).weight(2).add("tower_of_the_undead/big").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.TowerOfTheUndead.getConfigured())
                        .dimensions(DEConfig.COMMON.TowerOfTheUndead.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        WatchTower = StructureRegistrar.builder(location("watch_tower"), () -> new DESimpleStructure(DEConfig.COMMON.WatchTower, pieceBuilder().offset(-4,0,-4).add("watch_tower").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.WatchTower.getConfigured())
                        .dimensions(DEConfig.COMMON.WatchTower.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();

        WitchTower = StructureRegistrar.builder(location("witch_tower"), () -> new DESimpleStructure(DEConfig.COMMON.WitchTower, pieceBuilder().offset(-6, 0, -5).weight(3).add("witch_tower/normal").offset(-7, 0, -7).weight(2).add("witch_tower/big").build()))
                .pushConfigured(NoneFeatureConfiguration.INSTANCE)
                        .biomes(DEConfig.COMMON.WitchTower.getConfigured())
                        .dimensions(DEConfig.COMMON.WitchTower.getConfigured())
                        .adaptNoise()
                .popConfigured()
                .addPiece(DESimpleStructure.Piece::new)
                .build();
    }

    @SubscribeEvent
    public static void structureRegistry(final RegistryEvent.Register<StructureFeature<?>> event) {
        IForgeRegistry<StructureFeature<?>> registry = event.getRegistry();
        for(StructureRegistrar<?,?> structure : getAllStructureRegistrars()){
            structure.handleForge(registry);
        }
        DECellarStructure.init();

        DungeonsEnhanced.LOGGER.info("Dungeons Enhanced structures loaded");
    }

    public static StructureRegistrar<?,?>[] getAllStructureRegistrars() {
        return new StructureRegistrar<?,?>[] {
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

    private static MobSpawnSettings.SpawnerData spawn(EntityType<?> entity, int weight) {
        return spawn(entity, weight, 1, 1);
    }
    private static MobSpawnSettings.SpawnerData spawn(EntityType<?> entity, int weight, int min, int max) {
        return new MobSpawnSettings.SpawnerData(entity, weight, min, max);
    }
}