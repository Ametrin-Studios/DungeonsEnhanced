package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.structures.*;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.structures.prefabs.DEUndergroundStructure;
import com.legacy.structure_gel.api.registry.registrar.GelStructureRegistrar;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.api.structure.StructureAccessHelper;
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
    public static final StructureRegistrar<JigsawConfiguration, DECastle> Castle;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDesertTemple> DesertTemple;
    public static final StructureRegistrar<JigsawConfiguration, DEDesertTomb> DesertTomb;
    public static final StructureRegistrar<JigsawConfiguration, DEDruidCircle> DruidCircle;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEUndergroundStructure> DungeonVariant;
    //public static final StructureRegistrar<NoneFeatureConfiguration, DEFloatingStructure> FlyingDutchman;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> HayStorage;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEIcePit> IcePit;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> JungleMonument;
    public static final StructureRegistrar<JigsawConfiguration, DELargeDungeon> LargeDungeon;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> MinersHouse;
    public static final StructureRegistrar<JigsawConfiguration, DEMonsterMaze> MonsterMaze;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> MushroomHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> PillagerCamp;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> RuinedBuilding;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> Stables;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TallWitchHut;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TreeHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TowerOfTheUndead;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> WatchTower;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> WitchTower;

    public DEStructures(){}

    static {
        Castle = registerJigsaw("castle", new DECastle(), DECastle.Pool.Root, 1, DECastle.Piece::new);
        DesertTemple = register("desert_temple", new DEDesertTemple(), DESimpleStructure.Piece::new);
        DesertTomb = registerJigsaw("desert_tomb", new DEDesertTomb(), DEDesertTomb.Pool.Root, 4, DEDesertTomb.Piece::new);
        DruidCircle = registerJigsaw("druid_circle", new DEDruidCircle(), DEDruidCircle.Pool.Root, 2, DEDruidCircle.Piece::new);
        DungeonVariant = register("dungeon_variant", new DEUndergroundStructure(DEConfig.COMMON.dungeon_variant, Offset(-6, 0, -6), true, new DEPiece("dungeon_variant/zombie"), new DEPiece("dungeon_variant/skeleton"), new DEPiece("dungeon_variant/spider")), DEUndergroundStructure.Piece::new, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        //FlyingDutchman = register("flying_dutchman", new DEFloatingStructure(DEConfig.COMMON.flying_dutchman, false, new DEPiece("flying_dutchman", Offset(-4, 0, -15))), DEFloatingStructure.Piece::new);
        HayStorage = register("hay_storage", new DESimpleStructure(DEConfig.COMMON.hay_Storage, true, new DEPiece("hay_storage/small", Offset(-7,0,-7)), new DEPiece("hay_storage/big", Offset(-9,0,-9))), DESimpleStructure.Piece::new);
        IcePit = register("ice_pit", new DEIcePit(), DESimpleStructure.Piece::new);
        JungleMonument = register("jungle_monument", new DESimpleStructure(DEConfig.COMMON.jungle_monument, new DEPiece("jungle_monument", Offset(-12,-9,-12))), DESimpleStructure.Piece::new);
        LargeDungeon = registerJigsaw("large_dungeon", new DELargeDungeon(), DELargeDungeon.Pool.Root, 6, DELargeDungeon.Piece::new);
        MinersHouse = register("miners_house", new DESimpleStructure(DEConfig.COMMON.miners_house, new DEPiece("miners_house", Offset(-5, 0, -5))), DESimpleStructure.Piece::new);
        MonsterMaze = registerJigsaw("monster_maze", new DEMonsterMaze(), DEMonsterMaze.Pool.Root, 9, DEMonsterMaze.Piece::new);
        MushroomHouse = register("mushroom_house", new DESimpleStructure(DEConfig.COMMON.mushroom_house, new DEPiece("mushroom_house", Offset(-7,0,-7))), DESimpleStructure.Piece::new);
        PillagerCamp = register("pillager_camp", new DESimpleStructure(DEConfig.COMMON.pillager_camp, new DEPiece("pillager_camp", Offset(-9,0,-14))), DESimpleStructure.Piece::new);
        RuinedBuilding = register("ruined_building", new DESimpleStructure(DEConfig.COMMON.ruined_building, true, new DEPiece("ruined_building/house", Offset(-5, 0, -5), 3), new DEPiece("ruined_building/house_big", Offset(-6, 0, -8), 2), new DEPiece("ruined_building/barn", Offset(-4, 0, -5), 3)), DESimpleStructure.Piece::new);
        Stables = register("stables", new DESimpleStructure(DEConfig.COMMON.stables, new DEPiece("stables", Offset(-8,-6,-13))), DESimpleStructure.Piece::new);
        TallWitchHut = register("tall_witch_hut", new DESimpleStructure(DEConfig.COMMON.tall_witch_hut, new DEPiece("tall_witch_hut", Offset(-3,-3,-4))), DESimpleStructure.Piece::new);
        TreeHouse = register("tree_house", new DESimpleStructure(DEConfig.COMMON.tree_house, new DEPiece("tree_house", Offset(-11,0,-12))), DESimpleStructure.Piece::new);
        TowerOfTheUndead = register("tower_of_the_undead", new DESimpleStructure(DEConfig.COMMON.tower_of_the_undead, true, new DEPiece("tower_of_the_undead/big", Offset(-7, 0, -7), 2), new DEPiece("tower_of_the_undead/small", Offset(-5, 0, -5), 3)), DESimpleStructure.Piece::new);
        WatchTower = register("watch_tower", new DESimpleStructure(DEConfig.COMMON.watch_tower, new DEPiece("watch_tower", Offset(-4,0,-4))), DESimpleStructure.Piece::new);
        WitchTower = register("witch_tower", new DESimpleStructure(DEConfig.COMMON.witch_tower, true, new DEPiece("witch_tower/normal", Offset(-6,0,-5), 3), new DEPiece("witch_tower/big", Offset(-7,0,-7), 1)), DESimpleStructure.Piece::new);
    }

    @SubscribeEvent
    public static void structureRegistry(final RegistryEvent.Register<StructureFeature<?>> event){
        IForgeRegistry<StructureFeature<?>> registry = event.getRegistry();

        DECastle.Pool.init();
        DEDesertTomb.Pool.init();
        DEDruidCircle.Pool.init();
        DEMonsterMaze.Pool.init();
        DELargeDungeon.Pool.init();

        Castle.handleForge(registry);
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

        noiseAffecting(RuinedBuilding, DruidCircle, TowerOfTheUndead, HayStorage, DruidCircle, MinersHouse, MushroomHouse, WatchTower, WitchTower, Castle, PillagerCamp, TreeHouse, MonsterMaze);
        DungeonsEnhanced.LOGGER.info("Dungeons Enhanced structures loaded");
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