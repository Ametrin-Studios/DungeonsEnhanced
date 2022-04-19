package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.*;
import com.barion.dungeons_enhanced.world.structures.prefabs.DECellarStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.DEUndergroundStructure;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.api.structure.jigsaw.ExtendedJigsawConfiguration;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public class DEStructures {
    public static final StructureRegistrar<JigsawConfiguration, DECellarStructure> Castle;
    public static final StructureRegistrar<JigsawConfiguration, DEDeepCrypt> DeepCrypt;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDesertTemple> DesertTemple;
    public static final StructureRegistrar<JigsawConfiguration, DEDesertTomb> DesertTomb;
    public static final StructureRegistrar<JigsawConfiguration, DECellarStructure> DruidCircle;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEUndergroundStructure> DungeonVariant;
    //public static final StructureRegistrar<NoneFeatureConfiguration, DEFloatingStructure> FlyingDutchman;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> HayStorage;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEIcePit> IcePit;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> JungleMonument;
    public static final StructureRegistrar<JigsawConfiguration, DELargeDungeon> LargeDungeon;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> MinersHouse;
    public static final StructureRegistrar<ExtendedJigsawConfiguration, DEMonsterMaze> MonsterMaze;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> MushroomHouse;
    public static final StructureRegistrar<JigsawConfiguration, DEPillagerCamp> PillagerCamp;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> RuinedBuilding;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> Stables;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TallWitchHut;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TreeHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> TowerOfTheUndead;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> WatchTower;
    public static final StructureRegistrar<NoneFeatureConfiguration, DESimpleStructure> WitchTower;

    public DEStructures(){}

    static {
        Castle = registerCellarStructure("castle", () -> new DECellarStructure(DEConfig.COMMON.castle, false, DETerrainAnalyzer.defaultCheckSettings), DECellarStructure.CastlePool.Root);
        DeepCrypt = registerJigsaw("deep_crypt", false, DEDeepCrypt::new, new JigsawConfiguration(DEDeepCrypt.Pool.Root, 4), DEDeepCrypt.Piece::new, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        DesertTemple = register("desert_temple", false, DEDesertTemple::new, DESimpleStructure.Piece::new);
        DesertTomb = registerJigsaw("desert_tomb", false, DEDesertTomb::new, new JigsawConfiguration(DEDesertTomb.Pool.Root, 4), DEDesertTomb.Piece::new, GenerationStep.Decoration.SURFACE_STRUCTURES);
        DruidCircle = registerCellarStructure("druid_circle", () -> new DECellarStructure(DEConfig.COMMON.druid_circle, true, DETerrainAnalyzer.defaultCheckSettings), DECellarStructure.DruidCirclePool.Root);
        DungeonVariant = register("dungeon_variant", false, () -> new DEUndergroundStructure(DEConfig.COMMON.dungeon_variant, true, pieceBuilder().offset(-6, 0, -6).add("dungeon_variant/zombie").add("dungeon_variant/skeleton").add("dungeon_variant/spider").build()), DEUndergroundStructure.Piece::new, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        //FlyingDutchman = register("flying_dutchman", () -> new DEFloatingStructure(DEConfig.COMMON.flying_dutchman, false, new DEPiece("flying_dutchman", Offset(-4, 0, -15))), DEFloatingStructure.Piece::new);
        HayStorage = register("hay_storage", true, () -> new DESimpleStructure(DEConfig.COMMON.hay_Storage, true, pieceBuilder().offset(-7, 0, -7).add("hay_storage/small").offset(-9, 0, -9).add("hay_storage/big").build()), DESimpleStructure.Piece::new);
        IcePit = register("ice_pit", false, DEIcePit::new, DESimpleStructure.Piece::new);
        JungleMonument = register("jungle_monument", false, () -> new DESimpleStructure(DEConfig.COMMON.jungle_monument, pieceBuilder().offset(-12, -9, -12).add("jungle_monument").build()), DESimpleStructure.Piece::new);
        LargeDungeon = registerJigsaw("large_dungeon", false, DELargeDungeon::new, new JigsawConfiguration(DELargeDungeon.Pool.Root, 6), DELargeDungeon.Piece::new, GenerationStep.Decoration.SURFACE_STRUCTURES);
        MinersHouse = register("miners_house", true, () -> new DESimpleStructure(DEConfig.COMMON.miners_house, pieceBuilder().offset(-5, 0, -5).add("miners_house").build()), DESimpleStructure.Piece::new);
        MonsterMaze = registerJigsaw("monster_maze", false, DEMonsterMaze::new, new ExtendedJigsawConfiguration(DEMonsterMaze.Pool.Root, 9), DEMonsterMaze.Piece::new, GenerationStep.Decoration.SURFACE_STRUCTURES);
        MushroomHouse = register("mushroom_house", true, () -> new DESimpleStructure(DEConfig.COMMON.mushroom_house, pieceBuilder().offset(-7, 0, -7).add("mushroom_house").build()), DESimpleStructure.Piece::new);
        PillagerCamp = registerJigsaw("pillager_camp", true, DEPillagerCamp::new, new JigsawConfiguration(DEPillagerCamp.Pool.Root, 4), DEPillagerCamp.Piece::new, GenerationStep.Decoration.SURFACE_STRUCTURES);
        RuinedBuilding = register("ruined_building", true, () -> new DESimpleStructure(DEConfig.COMMON.ruined_building, true, pieceBuilder().offset(-5, 0, -5).weight(3).add("ruined_building/house").offset(-6, 0, -8).weight(2).add("ruined_building/house_big").offset(-4, 0, -5).weight(3).add("ruined_building/barn").build()), DESimpleStructure.Piece::new);
        Stables = register("stables", false, () -> new DESimpleStructure(DEConfig.COMMON.stables, pieceBuilder().offset(-8, -6, -13).add("stables").build()), DESimpleStructure.Piece::new);
        TallWitchHut = register("tall_witch_hut", false, () -> new DESimpleStructure(DEConfig.COMMON.tall_witch_hut, pieceBuilder().offset(-3, -3, -4).add("tall_witch_hut").build()), DESimpleStructure.Piece::new);
        TreeHouse = register("tree_house", true, () -> new DESimpleStructure(DEConfig.COMMON.tree_house, pieceBuilder().offset(-11, 0, -12).add("tree_house").build()), DESimpleStructure.Piece::new);
        TowerOfTheUndead = register("tower_of_the_undead", true, () -> new DESimpleStructure(DEConfig.COMMON.tower_of_the_undead, true, pieceBuilder().offset(-5, 0, -5).weight(3).add("tower_of_the_undead/small").offset(-7, 0, -7).weight(2).add("tower_of_the_undead/big").build()), DESimpleStructure.Piece::new);
        WatchTower = register("watch_tower", true, () -> new DESimpleStructure(DEConfig.COMMON.watch_tower, pieceBuilder().offset(-4,0,-4).add("watch_tower").build()), DESimpleStructure.Piece::new);
        WitchTower = register("witch_tower", true, () -> new DESimpleStructure(DEConfig.COMMON.witch_tower, pieceBuilder().offset(-6, 0, -5).weight(3).add("witch_tower/normal").offset(-7, 0, -7).weight(2).add("witch_tower/big").build()), DESimpleStructure.Piece::new);
    }

    @SubscribeEvent
    public static void structureRegistry(final RegistryEvent.Register<StructureFeature<?>> event){
        IForgeRegistry<StructureFeature<?>> registry = event.getRegistry();
        for(StructureRegistrar<?,?> structure : getAllStructureRegistrars()){
            structure.handleForge(registry);
        }
        DECellarStructure.init();

        DungeonsEnhanced.LOGGER.info("Dungeons Enhanced structures loaded");
    }

    public static StructureRegistrar<?,?>[] getAllStructureRegistrars(){
        return new StructureRegistrar<?,?>[]{
                Castle,
                DeepCrypt,
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

    private static  <S extends GelConfigStructure<NoneFeatureConfiguration>> StructureRegistrar<NoneFeatureConfiguration, S> register(String registryName, boolean adaptNoise, Supplier<S> structure, StructurePieceType piece){
        return register(registryName, adaptNoise, structure, piece, GenerationStep.Decoration.SURFACE_STRUCTURES);
    }
    private static  <S extends GelConfigStructure<NoneFeatureConfiguration>> StructureRegistrar<NoneFeatureConfiguration, S> register(String registryName, boolean adaptNoise, Supplier<S> structure, StructurePieceType piece, GenerationStep.Decoration decoration){
        if(adaptNoise) {
            return StructureRegistrar.builder(createRegistryName(registryName), structure).pushConfigured(NoneFeatureConfiguration.INSTANCE).biomes(structure.get().getConfig().getConfigured()).adaptNoise().popConfigured().addPiece(piece).generationStep(decoration).build();
        }
        return StructureRegistrar.builder(createRegistryName(registryName), structure).pushConfigured(NoneFeatureConfiguration.INSTANCE).biomes(structure.get().getConfig().getConfigured()).popConfigured().addPiece(piece).generationStep(decoration).build();
    }

    private static <C extends JigsawConfiguration, S extends GelConfigJigsawStructure<C>> StructureRegistrar<C, S> registerJigsaw(String registryName, boolean adaptNoise, Supplier<S> structure, C configuration, StructurePieceType piece, GenerationStep.Decoration generationStep){
        if(adaptNoise){
            return StructureRegistrar.builder(createRegistryName(registryName), structure).pushConfigured(configuration).biomes(structure.get().getConfig().getConfigured()).adaptNoise().popConfigured().addPiece(piece).generationStep(generationStep).build();
        }
        return StructureRegistrar.builder(createRegistryName(registryName), structure).pushConfigured(configuration).biomes(structure.get().getConfig().getConfigured()).popConfigured().addPiece(piece).generationStep(generationStep).build();
    }

    private static StructureRegistrar<JigsawConfiguration, DECellarStructure> registerCellarStructure(String registryName, Supplier<DECellarStructure> structure, Holder<StructureTemplatePool> pool){
        return StructureRegistrar.builder(createRegistryName(registryName), structure).pushConfigured(new JigsawConfiguration(pool, 1)).biomes(structure.get().getConfig().getConfigured()).adaptNoise().popConfigured().addPiece(DECellarStructure.Piece::new).generationStep(GenerationStep.Decoration.SURFACE_STRUCTURES).build();
    }
}