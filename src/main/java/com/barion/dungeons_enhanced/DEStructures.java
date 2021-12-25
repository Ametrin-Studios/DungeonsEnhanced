package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.structures.*;
import com.barion.dungeons_enhanced.structures.pools.DEMonsterMazePool;
import com.barion.dungeons_enhanced.structures.prefabs.*;
import com.legacy.structure_gel.api.registry.registrar.GelStructureRegistrar;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.api.structure.StructureAccessHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.barion.dungeons_enhanced.DEUtil.Offset;
import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEStructures {
    public static final StructureRegistrar<NoneFeatureConfiguration, DECastle> Castle;
    public static final StructureRegistrar<NoneFeatureConfiguration, DECellar> CastleB;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDesertTemple> DesertTemple;
    public static final StructureRegistrar<JigsawConfiguration, DEDesertTomb> DesertTomb;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDruidCircle> DruidCircle;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDungeonVariant> DungeonVariant;
    //public static final StructureRegistrar<NoneFeatureConfiguration, DEFloatingStructure> FlyingDutchman;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEHayStorage> HayStorage;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEIcePit> IcePit;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEJungleMonument> JungleMonument;
    public static final StructureRegistrar<NoneFeatureConfiguration, DELargeDungeon> LargeDungeon;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEMinersHouse> MinersHouse;
    public static final StructureRegistrar<JigsawConfiguration, DEMonsterMaze> MonsterMaze;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEMushroomHouse> MushroomHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEPillagerCamp> PillagerCamp;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> RuinedBuilding;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEStables> Stables;
    public static final StructureRegistrar<NoneFeatureConfiguration, DETallWitchHut> TallWitchHut;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TreeHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TowerOfTheUndead;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> WatchTower;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> WitchTower;

    public DEStructures(){}

    static {
        Castle = register("castle", new DECastle(), DECellarStructure.Piece::new);
        CastleB = GelStructureRegistrar.of(new ResourceLocation(""), new DECellar(Castle, new DEPiece("castle/bottom1", Offset(0, -5, 0)), new DEPiece("castle/bottom2", Offset(0, -5, 0))), DECellar.Piece::new, NoneFeatureConfiguration.INSTANCE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        DesertTemple = register("desert_temple", new DEDesertTemple(), DESimpleStructure.Piece::new);
        DesertTomb = registerJigsaw("desert_tomb", new DEDesertTomb(), DEDesertTomb.Pool.Root, 4, DEDesertTomb.Piece::new);
        DruidCircle = register("druid_circle", new DEDruidCircle(), DECellarStructure.Piece::new);
        DungeonVariant = register("dungeon_variant", new DEDungeonVariant(), DEUndergroundStructure.Piece::new, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        //FlyingDutchman = register("flying_dutchman", new DEFloatingStructure(DEConfig.COMMON.flying_dutchman, false, new DEPiece("flying_dutchman", Offset(-4, 0, -15))), DEFloatingStructure.Piece::new);
        HayStorage = register("hay_storage", new DEHayStorage(), DESimpleStructure.Piece::new);
        IcePit = register("ice_pit", new DEIcePit(), DESimpleStructure.Piece::new);
        JungleMonument = register("jungle_monument", new DEJungleMonument(), DESimpleStructure.Piece::new);
        LargeDungeon = GelStructureRegistrar.of(createRegistryName("large_dungeon"), new DELargeDungeon(), DESimpleStructure.Piece::new, NoneFeatureConfiguration.INSTANCE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        MinersHouse = register("miners_house", new DEMinersHouse(), DESimpleStructure.Piece::new);
        MonsterMaze = registerJigsaw("monster_maze", new DEMonsterMaze(), DEMonsterMazePool.Root, 9, DEMonsterMaze.Piece::new);
        MushroomHouse = register("mushroom_house", new DEMushroomHouse(), DESimpleStructure.Piece::new);
        PillagerCamp = register("pillager_camp", new DEPillagerCamp(), DESimpleStructure.Piece::new);
        RuinedBuilding = register("ruined_building", new DESimpleStructure(DEConfig.COMMON.ruined_building, true, new DEPiece("ruined/house", Offset(-5, 0, -5)), new DEPiece("ruined/house2", Offset(-5, 0, -5)), new DEPiece("ruined/barn", Offset(-5, 0, -5))), DESimpleStructure.Piece::new);
        Stables = register("stables", new DEStables(), DESimpleStructure.Piece::new);
        TallWitchHut = register("tall_witch_hut", new DETallWitchHut(), DESimpleStructure.Piece::new);
        TreeHouse = register("tree_house", new DESimpleStructure(DEConfig.COMMON.tree_house, true, new DEPiece("tree_house", Offset(-9,0,-10))), DESimpleStructure.Piece::new);
        TowerOfTheUndead = register("tower_of_the_undead", new DESimpleStructure(DEConfig.COMMON.tower_of_the_undead, true, new DEPiece("tower_of_the_undead/big", Offset(-7, 0, -7), 2), new DEPiece("tower_of_the_undead/small", Offset(-5, 0, -5), 3)), DESimpleStructure.Piece::new);
        WatchTower = register("watch_tower", new DESimpleStructure(DEConfig.COMMON.watch_tower, true, new DEPiece("watch_tower", Offset(-4,0,-4))), DESimpleStructure.Piece::new);
        WitchTower = register("witch_tower", new DESimpleStructure(DEConfig.COMMON.witch_tower, true, new DEPiece("witch_tower/normal", Offset(-6,0,-5), 3), new DEPiece("witch_tower/big", Offset(-7,0,-7), 1)), DESimpleStructure.Piece::new);
    }

    @SubscribeEvent
    public static void onRegistry(final RegistryEvent.Register<StructureFeature<?>> event){
        IForgeRegistry<StructureFeature<?>> registry = event.getRegistry();

        DEMonsterMazePool.init();
        DEDesertTomb.Pool.init();

        Castle.handleForge(registry);
        CastleB.handleForge(registry);
        RuinedBuilding.handleForge(registry);
        DesertTemple.handleForge(registry);
        DesertTomb.handleForge(registry);
        DruidCircle.handleForge(registry);
        //FlyingDutchman.handleForge(registry);
        HayStorage.handleForge(registry);
        IcePit.handleForge(registry);
        JungleMonument.handleForge(registry);
        LargeDungeon.handleForge(registry);
        DungeonVariant.handleForge(registry);
        MinersHouse.handleForge(registry);
        MonsterMaze.handleForge(registry);
        MushroomHouse.handleForge(registry);
        PillagerCamp.handleForge(registry);
        Stables.handleForge(registry);
        TallWitchHut.handleForge(registry);
        TreeHouse.handleForge(registry);
        TowerOfTheUndead.handleForge(registry);
        WatchTower.handleForge(registry);
        WitchTower.handleForge(registry);

        DungeonsEnhanced.LOGGER.info("Dungeons Enhanced structures loaded");
        noiseAffecting(RuinedBuilding, DruidCircle, TowerOfTheUndead, HayStorage, DesertTomb, DruidCircle, MinersHouse, MushroomHouse, WatchTower, WitchTower, Castle, PillagerCamp, TreeHouse, MonsterMaze);
    }

    private static  <S extends GelConfigStructure<NoneFeatureConfiguration>> StructureRegistrar<NoneFeatureConfiguration, S> register(String registryName, S structure, StructurePieceType piece){
        return GelStructureRegistrar.of(createRegistryName(registryName), structure, piece, NoneFeatureConfiguration.INSTANCE, GenerationStep.Decoration.SURFACE_STRUCTURES);
    }
    private static  <S extends GelConfigStructure<NoneFeatureConfiguration>> StructureRegistrar<NoneFeatureConfiguration, S> register(String registryName, S structure, StructurePieceType piece, GenerationStep.Decoration decoration){
        return GelStructureRegistrar.of(createRegistryName(registryName), structure, piece, NoneFeatureConfiguration.INSTANCE, decoration);
    }

    private static  <S extends GelConfigJigsawStructure> StructureRegistrar<JigsawConfiguration, S> registerJigsaw(String registryName, S structure, StructureTemplatePool root, Integer level, StructurePieceType piece){
        return GelStructureRegistrar.of(createRegistryName(registryName), structure, piece, new JigsawConfiguration(() -> root, level), GenerationStep.Decoration.SURFACE_STRUCTURES);
    }

    private static void noiseAffecting(StructureRegistrar<?, ?>... structureRegs){
        for (StructureRegistrar<?, ?> structure: structureRegs) {
            StructureAccessHelper.addNoiseAffectingStructures(structure.getStructure());
        }
    }
}