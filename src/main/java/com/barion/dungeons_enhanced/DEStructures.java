package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.structures.*;
import com.barion.dungeons_enhanced.structures.pools.DEMonsterMazePool;
import com.barion.dungeons_enhanced.structures.prefabs.DECellar;
import com.barion.dungeons_enhanced.structures.prefabs.DECellarStructure;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.structures.prefabs.DEUndergroundStructure;
import com.legacy.structure_gel.api.registry.registrar.GelStructureRegistrar;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.api.structure.StructureAccessHelper;
import net.minecraft.core.BlockPos;
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

public class DEStructures {
    public static final StructureRegistrar<NoneFeatureConfiguration, DECastle> Castle;
    public static final StructureRegistrar<NoneFeatureConfiguration, DECellar> CastleB;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDesertTemple> DesertTemple;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDesertTomb> DesertTomb;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDruidCircle> DruidCircle;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDungeonVariant> DungeonVariant;
    //public static final StructureRegistrar<NoneFeatureConfiguration, DEFlyingDutchman> FlyingDutchman;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEHayStorage> HayStorage;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEIcePit> IcePit;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEJungleMonument> JungleMonument;
    public static final StructureRegistrar<NoneFeatureConfiguration, DELargeDungeon> LargeDungeon;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEMinersHouse> MinersHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEMobTower> MobTower;
    public static final StructureRegistrar<JigsawConfiguration, DEMonsterMaze> MonsterMaze;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEMushroomHouse> MushroomHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEPillagerCamp> PillagerCamp;
    public static final StructureRegistrar<NoneFeatureConfiguration, DERuinedBarn> RuinedBarn;
    public static final StructureRegistrar<NoneFeatureConfiguration, DERuinedHouse> RuinedHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEStables> Stables;
    public static final StructureRegistrar<NoneFeatureConfiguration, DETallWitchHut> TallWitchHut;
    public static final StructureRegistrar<NoneFeatureConfiguration, DETreeHouse> TreeHouse;
    public static final StructureRegistrar<NoneFeatureConfiguration, DETowerOfTheUndead> TowerOfTheUndead;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEWatchTower> WatchTower;
    public static final StructureRegistrar<NoneFeatureConfiguration, DEWitchTower> WitchTower;

    public DEStructures(){

    }

    static {
        Castle = register("castle", new DECastle(), DECellarStructure.Piece::new);
        CastleB = GelStructureRegistrar.of(new ResourceLocation(""), new DECellar("castle/bottom", Offset(0, -5, 0), Castle), DECellar.Piece::new, NoneFeatureConfiguration.INSTANCE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        DesertTemple = register("desert_temple", new DEDesertTemple(), DESimpleStructure.Piece::new);
        DesertTomb = register("desert_tomb", new DEDesertTomb(), DESimpleStructure.Piece::new);
        DruidCircle = register("druid_circle", new DEDruidCircle(), DECellarStructure.Piece::new);
        DungeonVariant = register("dungeon_variant", new DEDungeonVariant(), DEUndergroundStructure.Piece::new, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
        //FlyingDutchman = register("flying_dutchman", new DEFlyingDutchman(), DEFlyingDutchman.Piece::new, GenerationStep.Decoration.SURFACE_STRUCTURES);
        HayStorage = register("hay_storage", new DEHayStorage(), DESimpleStructure.Piece::new);
        IcePit = register("ice_pit", new DEIcePit(), DESimpleStructure.Piece::new);
        JungleMonument = register("jungle_monument", new DEJungleMonument(), DESimpleStructure.Piece::new);
        LargeDungeon = GelStructureRegistrar.of(locate("large_dungeon"), new DELargeDungeon(), DESimpleStructure.Piece::new, NoneFeatureConfiguration.INSTANCE, GenerationStep.Decoration.SURFACE_STRUCTURES);
        MinersHouse = register("miners_house", new DEMinersHouse(), DESimpleStructure.Piece::new);
        MobTower = register("mob_tower", new DEMobTower(), DESimpleStructure.Piece::new);
        MonsterMaze = register("monster_maze", new DEMonsterMaze(), DEMonsterMazePool.Root, DEMonsterMaze.Piece::new, GenerationStep.Decoration.SURFACE_STRUCTURES);
        MushroomHouse = register("mushroom_house", new DEMushroomHouse(), DESimpleStructure.Piece::new);
        PillagerCamp = register("pillager_camp", new DEPillagerCamp(), DESimpleStructure.Piece::new);
        RuinedBarn = register("ruined_barn", new DERuinedBarn(), DESimpleStructure.Piece::new);
        RuinedHouse = register("ruined_house", new DERuinedHouse(), DESimpleStructure.Piece::new);
        Stables = register("stables", new DEStables(), DESimpleStructure.Piece::new);
        TallWitchHut = register("tall_witch_hut", new DETallWitchHut(), DESimpleStructure.Piece::new);
        TreeHouse = register("tree_house", new DETreeHouse(), DESimpleStructure.Piece::new);
        TowerOfTheUndead = register("tower_of_the_undead", new DETowerOfTheUndead(), DESimpleStructure.Piece::new);
        WatchTower = register("watch_tower", new DEWatchTower(), DESimpleStructure.Piece::new);
        WitchTower = register("witch_tower", new DEWitchTower(), DESimpleStructure.Piece::new);
    }


    @SubscribeEvent
    public static void onRegistry(final RegistryEvent.Register<StructureFeature<?>> event){
        IForgeRegistry<StructureFeature<?>> registry = event.getRegistry();

        DungeonsEnhanced.LOGGER.info("handled");

        DEMonsterMazePool.init();

        Castle.handleForge(registry);
        CastleB.handleForge(registry);
        RuinedHouse.handleForge(registry);
        RuinedBarn.handleForge(registry);
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
        MobTower.handleForge(registry);
        MonsterMaze.handleForge(registry);
        MushroomHouse.handleForge(registry);
        PillagerCamp.handleForge(registry);
        Stables.handleForge(registry);
        TallWitchHut.handleForge(registry);
        TreeHouse.handleForge(registry);
        TowerOfTheUndead.handleForge(registry);
        WatchTower.handleForge(registry);
        WitchTower.handleForge(registry);

        noiseAffecting(RuinedHouse, RuinedBarn, DruidCircle, TowerOfTheUndead, HayStorage, DruidCircle, MinersHouse, MobTower, MushroomHouse, WatchTower, WitchTower, Castle, PillagerCamp, TreeHouse);
    }

    private static  <S extends GelConfigStructure<NoneFeatureConfiguration>> StructureRegistrar<NoneFeatureConfiguration, S> register(String locate, S structure, StructurePieceType piece){
        return GelStructureRegistrar.of(locate(locate), structure, piece, NoneFeatureConfiguration.INSTANCE, GenerationStep.Decoration.SURFACE_STRUCTURES);
    }
    private static  <S extends GelConfigStructure<NoneFeatureConfiguration>> StructureRegistrar<NoneFeatureConfiguration, S> register(String locate, S structure, StructurePieceType piece, GenerationStep.Decoration decoration){
        return GelStructureRegistrar.of(locate(locate), structure, piece, NoneFeatureConfiguration.INSTANCE, decoration);
    }

    private static  <S extends GelConfigJigsawStructure> StructureRegistrar<JigsawConfiguration, S> register(String locate, S structure, StructureTemplatePool root, StructurePieceType piece, GenerationStep.Decoration decoration){
        return GelStructureRegistrar.of(locate(locate), structure, piece, new JigsawConfiguration(() -> root, 7), decoration);
    }

    public static ResourceLocation locate(String key){ return new ResourceLocation(DungeonsEnhanced.Mod_ID, key);}

    private static void noiseAffecting(StructureRegistrar... structureRegs){
        for (StructureRegistrar structure: structureRegs) {
            StructureAccessHelper.addNoiseAffectingStructures(structure.getStructure());
        }
    }

    private static BlockPos Offset(int x, int y, int z){
        return new BlockPos(x, y, z);
    }
}