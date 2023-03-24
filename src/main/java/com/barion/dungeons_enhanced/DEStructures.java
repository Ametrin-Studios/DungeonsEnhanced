package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.DEPools;
import com.barion.dungeons_enhanced.world.structures.*;
import com.barion.dungeons_enhanced.world.structures.prefabs.*;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.ExtendedJigsawStructure;
import com.legacy.structure_gel.api.structure.GridStructurePlacement;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawCapability;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.ConstantHeight;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.barion.dungeons_enhanced.DEUtil.location;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

@SuppressWarnings("removal")
public class DEStructures {
    public static StructureRegistrar<ExtendedJigsawStructure> CASTLE;
    public static StructureRegistrar<ExtendedJigsawStructure> DEEP_CRYPT;
    public static StructureRegistrar<DEDesertTemple> DESERT_TEMPLE;
    public static StructureRegistrar<ExtendedJigsawStructure> DESERT_TOMB;
    public static StructureRegistrar<ExtendedJigsawStructure> DRUID_CIRCLE;
    public static StructureRegistrar<DEUndergroundStructure> DUNGEON_VARIANT;
    public static StructureRegistrar<DEEldersTemple> ELDERS_TEMPLE;
    public static StructureRegistrar<DESwimmingStructure> FISHING_SHIP;
    public static StructureRegistrar<DEFlyingStructure> FLYING_DUTCHMAN;
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

    //@formatter: off

    static {
        CASTLE = StructureRegistrar.jigsawBuilder(location("castle"))
                .placement(gridPlacement(56, 52, CASTLE))
                .addPiece(()-> DECastle.Piece::new)
                .pushStructure((context, settings) -> ExtendedJigsawStructure.builder(settings, context.lookup(Registries.TEMPLATE_POOL).getOrThrow(DEPools.CASTLE)).maxDepth(2).startHeight(0).capability(new DECastle.Capability()).build())
                        .biomes(BiomeTags.IS_OVERWORLD)
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        DEEP_CRYPT = StructureRegistrar.jigsawBuilder(location("deep_crypt"))
                .placement(gridPlacement(35, 74, DEEP_CRYPT))
                .addPiece(()-> DEDeepCrypt.Piece::new)
                .pushStructure((context, settings) -> extendedJigsawStructure(context, settings, DEDeepCrypt.Capability.Instance, DEPools.DEEP_CRYPT, 4, UniformHeight.of(VerticalAnchor.aboveBottom(16), VerticalAnchor.aboveBottom(48))).build())
                //.pushStructure((context, settings) -> ExtendedJigsawStructure.builder(settings, context.lookup(Registries.TEMPLATE_POOL).getOrThrow(DEPools.DEEP_CRYPT)).maxDepth(4).startHeight(UniformHeight.of(VerticalAnchor.aboveBottom(16), VerticalAnchor.aboveBottom(48))).capability(new DEDeepCrypt.Capability()).build())
                        .biomes(BiomeTags.IS_OVERWORLD)
                        .dimensions(Level.OVERWORLD)
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .popStructure()
                .build();

        DESERT_TEMPLE = StructureRegistrar.builder(location("desert_temple"), codecOf(DEDesertTemple::new))
                .placement(gridPlacement(31, 65, DESERT_TEMPLE))
                .addPiece(()-> DEDesertTemple.Piece::new)
                .pushStructure(DEDesertTemple::new)
                        .biomes(BiomeTags.HAS_DESERT_PYRAMID)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        DESERT_TOMB = StructureRegistrar.jigsawBuilder(location("desert_tomb"))
                .placement(nearSpawnGridPlacement(25, 75, DESERT_TOMB))
                .addPiece(()-> DEDesertTomb.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDesertTomb.Capability.INSTANCE, DEPools.DESERT_TOMB, 5, ConstantHeight.ZERO).onSurface().build())
                        .biomes(BiomeTags.HAS_DESERT_PYRAMID)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        DRUID_CIRCLE = StructureRegistrar.jigsawBuilder(location("druid_circle"))
                .placement(nearSpawnGridPlacement(41, 68, DRUID_CIRCLE))
                .addPiece(()-> DEDruidCircle.Piece::new)
                .pushStructure((context, settings)-> extendedJigsawStructure(context, settings, DEDruidCircle.Capability.INSTANCE, DEPools.DRUID_CIRCLE, 1, ConstantHeight.ZERO).onSurface().build())
                        .biomes(BiomeTags.HAS_PILLAGER_OUTPOST) //TODO: make own tag
                        .dimensions(Level.OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        DUNGEON_VARIANT = StructureRegistrar.builder(location("dungeon_variant"), ()-> ()-> DEUndergroundStructure.CODEC_DUNGEON_VARIANT)
                .placement(nearSpawnGridPlacement(17, 80, DUNGEON_VARIANT))
                .addPiece(()-> DEUndergroundStructure.Piece::new)
                .pushStructure(DEUndergroundStructure::DungeonVariant)
                        .biomes(BiomeTags.IS_OVERWORLD)
                        .dimensions(Level.OVERWORLD)
                        .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                .popStructure()
                .build();

        ELDERS_TEMPLE = StructureRegistrar.builder(location("elders_temple"), ()-> ()-> DEEldersTemple.CODEC)
                .placement(gridPlacement(29, ELDERS_TEMPLE))
                .addPiece(()-> DEEldersTemple.Piece::new)
                .pushStructure(DEEldersTemple::new)
                        .biomes(BiomeTags.HAS_OCEAN_MONUMENT)
                        .dimensions(Level.OVERWORLD)
                        .noSpawns(StructureSpawnOverride.BoundingBoxType.STRUCTURE, MobCategory.UNDERGROUND_WATER_CREATURE, MobCategory.AXOLOTLS, MobCategory.WATER_AMBIENT, MobCategory.WATER_CREATURE)
                        .spawns(MobCategory.MONSTER, StructureSpawnOverride.BoundingBoxType.STRUCTURE, spawns(spawn(EntityType.GUARDIAN, 1,2,4)))
                .popStructure()
                .build();

        FISHING_SHIP = StructureRegistrar.builder(location("fishing_ship"), ()-> ()-> DESwimmingStructure.CODEC_FISHING_SHIP)
                .placement(nearSpawnGridPlacement(48, 68, FISHING_SHIP))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(DESwimmingStructure::FishingShip)
                        .biomes(BiomeTags.IS_OCEAN)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        FLYING_DUTCHMAN = StructureRegistrar.builder(location("flying_dutchman"), ()-> ()-> DEFlyingStructure.CODEC_FLYING_DUTCHMAN)
                .placement(gridPlacement(73, 49, FLYING_DUTCHMAN))
                .addPiece(()-> DEFlyingStructure.Piece::new)
                .pushStructure(DEFlyingStructure::FlyingDutchman)
                        .biomes(BiomeTags.IS_OCEAN)
                        .dimensions(Level.OVERWORLD)
                .popStructure()
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> hayStorage = (settings)-> new DESimpleStructure(settings, pieceBuilder().add("hay_storage/small").add("hay_storage/big").build(), HayStorage::getType);
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

        Function<Structure.StructureSettings, DESimpleStructure> jungleMonument = (settings)-> new DESimpleStructure(settings, pieceBuilder().yOffset(-9).add("jungle_monument").build(), JungleMonument::getType);
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

        Function<Structure.StructureSettings, DESimpleStructure> minersHouse = (settings)-> new DESimpleStructure(settings, pieceBuilder().add("miners_house").build(), MinersHouse::getType);
        MinersHouse = StructureRegistrar.builder(location("miners_house"), codecOf(minersHouse))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(minersHouse)
                        .config(DEConfig.COMMON.MinersHouse::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.MinersHouse).allowedNearSpawn(true).build(MinersHouse))
                .build();

        MonsterMaze = StructureRegistrar.jigsawBuilder(location("monster_maze"))
                .addPiece(()-> DEMonsterMaze.Piece::new)
                .pushStructure((settings)-> new ExtendedJigsawStructure(settings, DEMonsterMaze.Pool.Root, 11, height(-17), true, Heightmap.Types.WORLD_SURFACE_WG).withPieceFactory(MonsterMaze.getRegistryName(), DEMonsterMaze.Piece::new))
                        .config(DEConfig.COMMON.MonsterMaze::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.MonsterMaze).build(MonsterMaze))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> mushroomHouse = (settings)-> new DESimpleStructure(settings, pieceBuilder().add("mushroom_house/red").add("mushroom_house/brown").build(), MushroomHouse::getType);
        MushroomHouse = StructureRegistrar.builder(location("mushroom_house"), codecOf(mushroomHouse))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(mushroomHouse)
                        .config(DEConfig.COMMON.MushroomHouse::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.MushroomHouse).allowedNearSpawn(true).build(MushroomHouse))
                .build();

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

        Function<Structure.StructureSettings, DESimpleStructure> ruinedBuilding = (settings)-> new DESimpleStructure(settings, pieceBuilder().weight(3).add("ruined_building/house").weight(2).add("ruined_building/house_big").weight(3).add("ruined_building/barn").build(), RuinedBuilding::getType);
        RuinedBuilding = StructureRegistrar.builder(location("ruined_building"), codecOf(ruinedBuilding))
                .placement(()-> GridStructurePlacement.builder().spacing(27).offset(18).probability(45).allowedNearSpawn(true).build(RuinedBuilding))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(ruinedBuilding)
                        .dimensions(Level.OVERWORLD)
                        .biomes(BiomeTags.IS_OVERWORLD)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> stables = (settings)-> new DESimpleStructure(settings, pieceBuilder().yOffset(-6).add("stables").build(), Stables::getType);
        Stables = StructureRegistrar.builder(location("stables"), codecOf(stables))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(stables)
                        .config(DEConfig.COMMON.Stables::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.Stables).allowedNearSpawn(true).build(Stables))
                .build();

        Function<Structure.StructureSettings, DEUnderwaterStructure> sunkenShrine = (settings)-> new DEUnderwaterStructure(settings, pieceBuilder().yOffset(-1).add("sunken_shrine").build(), SunkenShrine::getType);
        SunkenShrine = StructureRegistrar.builder(location("sunken_shrine"), codecOf(sunkenShrine))
                .addPiece(()-> DEUnderwaterStructure.Piece::new)
                .pushStructure(sunkenShrine)
                        .config(DEConfig.COMMON.SunkenShrine::getStructure)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.SunkenShrine).allowedNearSpawn(true).build(SunkenShrine))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> tallWitchHut = (settings)-> new DESimpleStructure(settings, pieceBuilder().yOffset(-3).add("tall_witch_hut").build(), TallWitchHut::getType);
        TallWitchHut = StructureRegistrar.builder(location("tall_witch_hut"), codecOf(tallWitchHut))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(tallWitchHut)
                        .config(DEConfig.COMMON.TallWitchHut::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.TallWitchHut).allowedNearSpawn(true).build(TallWitchHut))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> treeHouse = (settings)-> new DESimpleStructure(settings, pieceBuilder().add("tree_house").build(), TreeHouse::getType);
        TreeHouse = StructureRegistrar.builder(location("tree_house"), codecOf(treeHouse))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(treeHouse)
                        .config(DEConfig.COMMON.TreeHouse::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.TreeHouse).allowedNearSpawn(true).build(TreeHouse))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> undeadTower = (settings)-> new DESimpleStructure(settings, pieceBuilder().weight(3).add("tower_of_the_undead/small").weight(2).add("tower_of_the_undead/big").build(), TowerOfTheUndead::getType);
        TowerOfTheUndead = StructureRegistrar.builder(location("tower_of_the_undead"), codecOf(undeadTower))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(undeadTower)
                        .config(DEConfig.COMMON.TowerOfTheUndead::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.TowerOfTheUndead).allowedNearSpawn(true).build(TowerOfTheUndead))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> watchTower = (settings)-> new DESimpleStructure(settings, pieceBuilder().add("watch_tower").build(), WatchTower::getType);
        WatchTower = StructureRegistrar.builder(location("watch_tower"), codecOf(watchTower))
                .addPiece(()-> DESimpleStructure.Piece::new)
                .pushStructure(watchTower)
                        .config(DEConfig.COMMON.WatchTower::getStructure)
                        .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .placement(()-> GridStructurePlacement.builder().config(()-> DEConfig.COMMON.WatchTower).allowedNearSpawn(true).build(WatchTower))
                .build();

        Function<Structure.StructureSettings, DESimpleStructure> witchTower = (settings)-> new DESimpleStructure(settings, pieceBuilder().weight(3).add("witch_tower/normal").weight(2).add("witch_tower/big").build(), WitchTower::getType);
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
                CASTLE,
                DEEP_CRYPT,
                DESERT_TEMPLE,
                DESERT_TOMB,
                DRUID_CIRCLE,
                DUNGEON_VARIANT,
                ELDERS_TEMPLE,
                FISHING_SHIP,
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
    @Deprecated(forRemoval = true)
    private static <S extends Structure> Supplier<StructureType<S>> codecOf(Function<Structure.StructureSettings, S> constructor){
        return ()-> ()-> Structure.simpleCodec(constructor);
    }

    private static Supplier<StructurePlacement> nearSpawnGridPlacement(int spacing, int probability, StructureRegistrar<?> registrar){
        return ()-> gridPlacement(spacing, probability).allowedNearSpawn(true).build(registrar);
    }
    private static Supplier<StructurePlacement> gridPlacement(int spacing, int probability, StructureRegistrar<?> registrar){
        return ()-> gridPlacement(spacing, probability).build(registrar);
    }
    private static Supplier<StructurePlacement> gridPlacement(int spacing, StructureRegistrar<?> registrar){
        return ()-> gridPlacement(spacing).build(registrar);
    }

    private static GridStructurePlacement.Builder gridPlacement(int spacing, int probability){
        return gridPlacement(spacing).probability(probability/100f);
    }
    private static GridStructurePlacement.Builder gridPlacement(int spacing){
        return GridStructurePlacement.builder().spacing(spacing).offset((int) (spacing*0.8));
    }

    private static ExtendedJigsawStructure.Builder extendedJigsawStructure(BootstapContext<?> context, Structure.StructureSettings settings, JigsawCapability.IJigsawCapability capability, ResourceKey<StructureTemplatePool> poolKey, int maxDepth, HeightProvider heightProvider){
        return ExtendedJigsawStructure.builder(settings, context.lookup(Registries.TEMPLATE_POOL).getOrThrow(poolKey)).maxDepth(maxDepth).startHeight(heightProvider).capability(capability);
    }

    public static void init() {}
}