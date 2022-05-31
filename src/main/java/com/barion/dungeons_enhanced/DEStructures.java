package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.*;
import com.barion.dungeons_enhanced.world.structures.prefabs.DECellarStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.DEUndergroundStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.access_helpers.JigsawAccessHelper;
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
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import static com.barion.dungeons_enhanced.DEUtil.*;

public class DEStructures {
    public static final StructureRegistrar2<VillageConfig, DECellarStructure> Castle;
    public static final StructureRegistrar2<NoFeatureConfig, DEDesertTemple> DesertTemple;
    public static final StructureRegistrar2<VillageConfig, DEDesertTomb> DesertTomb;
    public static final StructureRegistrar2<VillageConfig, DECellarStructure> DruidCircle;
    public static final StructureRegistrar2<NoFeatureConfig, DEUndergroundStructure> DungeonVariant;
    //public static final StructureRegistrar2<NoFeatureConfig, DEFloatingStructure> FlyingDutchman;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> HayStorage;
    public static final StructureRegistrar2<NoFeatureConfig, DEIcePit> IcePit;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> JungleMonument;
    public static final StructureRegistrar2<VillageConfig, DELargeDungeon> LargeDungeon;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> MinersHouse;
    public static final StructureRegistrar2<VillageConfig, DEMonsterMaze> MonsterMaze;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> MushroomHouse;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> PillagerCamp;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> RuinedBuilding;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> Stables;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> TallWitchHut;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> TreeHouse;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> TowerOfTheUndead;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> WatchTower;
    public static final StructureRegistrar2<NoFeatureConfig, DESimpleStructure> WitchTower;

    public DEStructures(){}

    static {
        Castle = registerCellarStructure("castle", new DECellarStructure(DEConfig.COMMON.castle, DETerrainAnalyzer.defaultSettings, false), DECellarStructure.CastlePool.Root, DECellarStructure.Piece::new);
        DesertTemple = register("desert_temple", new DEDesertTemple(), DESimpleStructure.Piece::new);
        DesertTomb = registerJigsaw("desert_tomb", new DEDesertTomb(), DEDesertTomb.Pool.Root, 4, DEDesertTomb.Piece::new);
        DruidCircle = registerCellarStructure("druid_circle", new DECellarStructure(DEConfig.COMMON.druid_circle, DETerrainAnalyzer.defaultSettings, true), DECellarStructure.DruidCirclePool.Root, DECellarStructure.Piece::new);
        DungeonVariant = register("dungeon_variant", new DEUndergroundStructure(DEConfig.COMMON.dungeon_variant, true, pieceBuilder().offset(-6, 0, -6).add("dungeon_variant/zombie").add("dungeon_variant/skeleton").add("dungeon_variant/spider").build()), DEUndergroundStructure.Piece::new);
        //FlyingDutchman = register("flying_dutchman", new DEFloatingStructure(DEConfig.COMMON.flying_dutchman, false, new DEPiece("flying_dutchman", Offset(-4, 0, -15))), DEFloatingStructure.Piece::new);
        HayStorage = register("hay_storage", new DESimpleStructure(DEConfig.COMMON.hay_Storage, true, pieceBuilder().offset(-7, 0, -7).add("hay_storage/small").offset(-9, 0, -9).add("hay_storage/big").build()), DESimpleStructure.Piece::new);
        IcePit = register("ice_pit", new DEIcePit(), DESimpleStructure.Piece::new);
        JungleMonument = register("jungle_monument", new DESimpleStructure(DEConfig.COMMON.jungle_monument, new DEStructurePiece("jungle_monument", Offset(-12,-9,-12))), DESimpleStructure.Piece::new);
        LargeDungeon = registerJigsaw("large_dungeon", new DELargeDungeon(), DELargeDungeon.Pool.Root, 6, DELargeDungeon.Piece::new);
        MinersHouse = register("miners_house", new DESimpleStructure(DEConfig.COMMON.miners_house, new DEStructurePiece("miners_house", Offset(-5, 0, -5))), DESimpleStructure.Piece::new);
        MonsterMaze = registerJigsaw("monster_maze", new DEMonsterMaze(), DEMonsterMaze.Pool.Root, 9, DEMonsterMaze.Piece::new);
        MushroomHouse = register("mushroom_house", new DESimpleStructure(DEConfig.COMMON.mushroom_house, new DEStructurePiece("mushroom_house", Offset(-7,0,-7))), DESimpleStructure.Piece::new);
        PillagerCamp = register("pillager_camp", new DESimpleStructure(DEConfig.COMMON.pillager_camp, new DEStructurePiece("pillager_camp", Offset(-9,0,-14))), DESimpleStructure.Piece::new);
        RuinedBuilding = register("ruined_building", new DESimpleStructure(DEConfig.COMMON.ruined_building, true, new DEStructurePiece("ruined_building/house", Offset(-5, 0, -5), 3), new DEStructurePiece("ruined_building/house_big", Offset(-6, 0, -8), 2), new DEStructurePiece("ruined_building/barn", Offset(-4, 0, -5), 3)), DESimpleStructure.Piece::new);
        Stables = register("stables", new DESimpleStructure(DEConfig.COMMON.stables, new DEStructurePiece("stables", Offset(-8,-6,-13))), DESimpleStructure.Piece::new);
        TallWitchHut = register("tall_witch_hut", new DESimpleStructure(DEConfig.COMMON.tall_witch_hut, new DEStructurePiece("tall_witch_hut", Offset(-3,-3,-4))), DESimpleStructure.Piece::new);
        TreeHouse = register("tree_house", new DESimpleStructure(DEConfig.COMMON.tree_house, new DEStructurePiece("tree_house", Offset(-11,0,-12))), DESimpleStructure.Piece::new);
        TowerOfTheUndead = register("tower_of_the_undead", new DESimpleStructure(DEConfig.COMMON.tower_of_the_undead, true, new DEStructurePiece("tower_of_the_undead/big", Offset(-7, 0, -7), 2), new DEStructurePiece("tower_of_the_undead/small", Offset(-5, 0, -5), 3)), DESimpleStructure.Piece::new);
        WatchTower = register("watch_tower", new DESimpleStructure(DEConfig.COMMON.watch_tower, new DEStructurePiece("watch_tower", Offset(-4,0,-4))), DESimpleStructure.Piece::new);
        WitchTower = register("witch_tower", new DESimpleStructure(DEConfig.COMMON.witch_tower, true, new DEStructurePiece("witch_tower/normal", Offset(-6,0,-5), 3), new DEStructurePiece("witch_tower/big", Offset(-7,0,-7), 1)), DESimpleStructure.Piece::new);
    }

    @SubscribeEvent
    public static void structureRegistry(final RegistryEvent.Register<Structure<?>> event){
        IForgeRegistry<Structure<?>> registry = event.getRegistry();
        for(StructureRegistrar2<?,?> structure : getAllStructureRegistrars()){
            structure.handleForge(registry);
        }
        DECellarStructure.init();

        noiseAffecting(
                Castle,
                DruidCircle,
                HayStorage,
                MinersHouse,
                MushroomHouse,
                PillagerCamp,
                RuinedBuilding,
                TowerOfTheUndead,
                TreeHouse,
                WatchTower,
                WitchTower
        );
        DungeonsEnhanced.LOGGER.info("Dungeons Enhanced structures loaded");
    }

    public static StructureRegistrar2<?,?>[] getAllStructureRegistrars(){
        return new StructureRegistrar2<?,?>[]{
                Castle,
                DesertTemple,
                DesertTomb,
                DruidCircle,
                DungeonVariant,
                HayStorage,
                IcePit,
                JungleMonument,
                LargeDungeon,
                MinersHouse,
                MonsterMaze,
                MushroomHouse,
                PillagerCamp,
                RuinedBuilding,
                Stables,
                TallWitchHut,
                TreeHouse,
                TowerOfTheUndead,
                WatchTower,
                WitchTower
        };
    }

    private static  <S extends GelConfigStructure<NoFeatureConfig>> StructureRegistrar2<NoFeatureConfig, S> register(String registryName, S structure, IStructurePieceType piece){
        return GelStructureRegistrar.of(location(registryName), structure, piece, NoFeatureConfig.NONE, GenerationStage.Decoration.SURFACE_STRUCTURES).handle();
    }
    private static  <S extends GelConfigStructure<NoFeatureConfig>> StructureRegistrar2<NoFeatureConfig, S> register(String registryName, S structure, IStructurePieceType piece, GenerationStage.Decoration decoration){
        return GelStructureRegistrar.of(location(registryName), structure, piece, NoFeatureConfig.NONE, decoration).handle();
    }

    private static  <S extends GelConfigJigsawStructure> StructureRegistrar2<VillageConfig, S> registerJigsaw(String registryName, S structure, JigsawPattern root, Integer level, IStructurePieceType piece){
        return GelStructureRegistrar.of(location(registryName), structure, piece, new VillageConfig(() -> root, level), GenerationStage.Decoration.SURFACE_STRUCTURES).handle();
    }

    private static StructureRegistrar2<VillageConfig, DECellarStructure> registerCellarStructure(String registryName, DECellarStructure structure, JigsawPattern root, IStructurePieceType piece){
        return GelStructureRegistrar.of(location(registryName), structure, piece, new VillageConfig(() -> root, 1), GenerationStage.Decoration.SURFACE_STRUCTURES).handle();
    }

    public static Structure<?>[] getAllStructures(){
        StructureRegistrar2<?,?>[] registrars = getAllStructureRegistrars();
        Structure<?>[] temp = new Structure<?>[registrars.length];
        for(int i = 0; i < registrars.length; i++){
            temp[i] = registrars[i].getStructure();
        }
        return temp;
    }

    private static void noiseAffecting(StructureRegistrar2<?, ?>... structureRegs){
        for (StructureRegistrar2<?, ?> structure: structureRegs) {
            JigsawAccessHelper.addIllagerStructures(structure.getStructure());
        }
    }
}