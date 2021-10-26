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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = DungeonsEnhanced.Mod_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DEStructures {
    public static final StructureRegistrar<NoneFeatureConfiguration, DEBattleTower> BattleTower = register("battle_tower", new DEBattleTower(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DECastle> Castle = register("castle", new DECastle(), DECellarStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DECellar> CastleB = GelStructureRegistrar.of(new ResourceLocation(""), new DECellar("castle/bottom", Offset(0, -5, 0), Castle), DECellar.Piece::new, NoneFeatureConfiguration.INSTANCE, GenerationStep.Decoration.SURFACE_STRUCTURES);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDesertTemple> DesertTemple = register("desert_temple", new DEDesertTemple(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDesertTomb> DesertTomb = register("desert_tomb", new DEDesertTomb(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDruidCircle> DruidCircle = register("druid_circle", new DEDruidCircle(), DECellarStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEDungeonVariant> DungeonVariant = register("dungeon_variant", new DEDungeonVariant(), DEUndergroundStructure.Piece::new, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
    //public static final StructureRegistrar<NoneFeatureConfiguration, DEFlyingDutchman> FlyingDutchman = register("flying_dutchman", new DEFlyingDutchman(), DEFlyingDutchman.Piece::new, GenerationStep.Decoration.SURFACE_STRUCTURES);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEHayStorage> HayStorage = register("hay_storage", new DEHayStorage(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEIcePit> IcePit = register("ice_pit", new DEIcePit(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEJungleMonument> JungleMonument = register("jungle_monument", new DEJungleMonument(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DELargeDungeon> LargeDungeon = GelStructureRegistrar.of(locate("large_dungeon"), new DELargeDungeon(), DESimpleStructure.Piece::new, NoneFeatureConfiguration.INSTANCE, GenerationStep.Decoration.SURFACE_STRUCTURES);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEMinersHouse> MinersHouse = register("miners_house", new DEMinersHouse(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEMobTower> MobTower = register("mob_tower", new DEMobTower(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<JigsawConfiguration, DEMonsterMaze> MonsterMaze = register("monster_maze", new DEMonsterMaze(), DEMonsterMazePool.Root, DEMonsterMaze.Piece::new, GenerationStep.Decoration.UNDERGROUND_STRUCTURES);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEMushroomHouse> MushroomHouse = register("mushroom_house", new DEMushroomHouse(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEPillagerCamp> PillagerCamp = register("pillager_camp", new DEPillagerCamp(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DERuinedBarn> RuinedBarn = register("ruined_barn", new DERuinedBarn(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DERuinedHouse> RuinedHouse = register("ruined_house", new DERuinedHouse(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEStables> Stables = register("stables", new DEStables(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DETallWitchHut> TallWitchHut = register("tall_witch_hut", new DETallWitchHut(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DETreeHouse> TreeHouse = register("tree_house", new DETreeHouse(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEWatchTower> WatchTower = register("watch_tower", new DEWatchTower(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar<NoneFeatureConfiguration, DEWitchTower> WitchTower = register("witch_tower", new DEWitchTower(), DESimpleStructure.Piece::new);

    @SubscribeEvent
    public static void onRegistry(final RegistryEvent.Register<StructureFeature<?>> event){
        IForgeRegistry<StructureFeature<?>> registry = event.getRegistry();

        BattleTower.handleForge(registry);
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
        WatchTower.handleForge(registry);
        WitchTower.handleForge(registry);

        noiseAffecting(RuinedHouse, RuinedBarn, DruidCircle, BattleTower, HayStorage, DruidCircle, MinersHouse, MobTower, MushroomHouse, WatchTower, WitchTower, Castle, PillagerCamp, TreeHouse);

        DEMonsterMazePool.init();
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