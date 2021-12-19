package com.barion.dungeons_enhanced;

import com.barion.dungeons_enhanced.structures.*;
import com.barion.dungeons_enhanced.structures.pools.DEMonsterMazePool;
import com.barion.dungeons_enhanced.structures.prefabs.DECellar;
import com.barion.dungeons_enhanced.structures.prefabs.DECellarStructure;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.structures.prefabs.DEUndergroundStructure;
import com.legacy.structure_gel.access_helpers.JigsawAccessHelper;
import com.legacy.structure_gel.registrars.GelStructureRegistrar;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import com.legacy.structure_gel.worldgen.jigsaw.GelConfigJigsawStructure;
import com.legacy.structure_gel.worldgen.structure.GelConfigStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

@Mod.EventBusSubscriber(modid = DungeonsEnhanced.Mod_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DEStructures {
    public static final StructureRegistrar2<NoFeatureConfig, DETowerOfTheUndead> TowerOfTheUndead = register("tower_of_the_undead", new DETowerOfTheUndead(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DECastle> Castle = register("castle", new DECastle(), DECellarStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DECellar> CastleB = GelStructureRegistrar.of(locate(""), new DECellar("castle/bottom", Offset(0, -5, 0), Castle, DEConfig.COMMON.castle), DECellar.Piece::new, NoFeatureConfig.INSTANCE, Decoration.SURFACE_STRUCTURES).handle();
    public static final StructureRegistrar2<NoFeatureConfig, DEDesertTemple> DesertTemple = register("desert_temple", new DEDesertTemple(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEDesertTomb> DesertTomb = register("desert_tomb", new DEDesertTomb(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEDruidCircle> DruidCircle = register("druid_circle", new DEDruidCircle(), DECellarStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEDungeonVariant> DungeonVariant = register("dungeon_variant", new DEDungeonVariant(), DEUndergroundStructure.Piece::new, Decoration.UNDERGROUND_STRUCTURES);
    //public static final StructureRegistrar2<NoFeatureConfig, DEFlyingDutchman> FlyingDutchman = register("flying_dutchman", new DEFlyingDutchman(), DEFlyingDutchman.Piece::new, Decoration.SURFACE_STRUCTURES);
    public static final StructureRegistrar2<NoFeatureConfig, DEHayStorage> HayStorage = register("hay_storage", new DEHayStorage(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEIcePit> IcePit = register("ice_pit", new DEIcePit(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEJungleMonument> JungleMonument = register("jungle_monument", new DEJungleMonument(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DELargeDungeon> LargeDungeon = GelStructureRegistrar.of(locate("large_dungeon"), new DELargeDungeon(), DESimpleStructure.Piece::new, NoFeatureConfig.INSTANCE, Decoration.SURFACE_STRUCTURES);
    public static final StructureRegistrar2<NoFeatureConfig, DEMinersHouse> MinersHouse = register("miners_house", new DEMinersHouse(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEMobTower> MobTower = register("mob_tower", new DEMobTower(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<VillageConfig, DEMonsterMaze> MonsterMaze = register("monster_maze", new DEMonsterMaze(), DEMonsterMazePool.Root, DEMonsterMaze.Piece::new, Decoration.UNDERGROUND_STRUCTURES);
    public static final StructureRegistrar2<NoFeatureConfig, DEMushroomHouse> MushroomHouse = register("mushroom_house", new DEMushroomHouse(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEPillagerCamp> PillagerCamp = register("pillager_camp", new DEPillagerCamp(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DERuinedBarn> RuinedBarn = register("ruined_barn", new DERuinedBarn(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DERuinedHouse> RuinedHouse = register("ruined_house", new DERuinedHouse(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEStables> Stables = register("stables", new DEStables(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DETallWitchHut> TallWitchHut = register("tall_witch_hut", new DETallWitchHut(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DETreeHouse> TreeHouse = register("tree_house", new DETreeHouse(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEWatchTower> WatchTower = register("watch_tower", new DEWatchTower(), DESimpleStructure.Piece::new);
    public static final StructureRegistrar2<NoFeatureConfig, DEWitchTower> WitchTower = register("witch_tower", new DEWitchTower(), DESimpleStructure.Piece::new);

    @SubscribeEvent
    public static void onRegistry(final RegistryEvent.Register<Structure<?>> event){
        IForgeRegistry<Structure<?>> registry = event.getRegistry();

        Castle.handleForge(registry);
        CastleB.handleForge(registry);
        RuinedHouse.handleForge(registry);
        RuinedBarn.handleForge(registry);
        DesertTemple.handleForge(registry);
        DesertTomb.handleForge(registry);
        DungeonVariant.handleForge(registry);
        DruidCircle.handleForge(registry);
        //FlyingDutchman.handleForge(registry);
        HayStorage.handleForge(registry);
        IcePit.handleForge(registry);
        JungleMonument.handleForge(registry);
        LargeDungeon.handleForge(registry);
        MinersHouse.handleForge(registry);
        MobTower.handleForge(registry);
        MonsterMaze.handleForge(registry);
        MushroomHouse.handleForge(registry);
        PillagerCamp.handleForge(registry);
        Stables.handleForge(registry);
        TallWitchHut.handleForge(registry);
        TowerOfTheUndead.handleForge(registry);
        TreeHouse.handleForge(registry);
        WatchTower.handleForge(registry);
        WitchTower.handleForge(registry);

        genFloor(RuinedHouse, RuinedBarn, DruidCircle, TowerOfTheUndead, HayStorage, DruidCircle, MinersHouse, MobTower, MushroomHouse, WatchTower, WitchTower, Castle, PillagerCamp, TreeHouse);

        DEMonsterMazePool.init();
    }

    private static  <S extends GelConfigStructure<NoFeatureConfig>> StructureRegistrar2<NoFeatureConfig, S> register(String locate, S structure, IStructurePieceType piece){
        return GelStructureRegistrar.of(locate(locate), structure, piece, NoFeatureConfig.INSTANCE, Decoration.SURFACE_STRUCTURES).handle();
    }
    private static  <S extends GelConfigStructure<NoFeatureConfig>> StructureRegistrar2<NoFeatureConfig, S> register(String locate, S structure, IStructurePieceType piece, Decoration decoration){
        return GelStructureRegistrar.of(locate(locate), structure, piece, NoFeatureConfig.INSTANCE, decoration).handle();
    }

    private static  <S extends GelConfigJigsawStructure> StructureRegistrar2<VillageConfig, S> register(String locate, S structure, JigsawPattern root, IStructurePieceType piece, Decoration decoration){
        return GelStructureRegistrar.of(locate(locate), structure, piece, new VillageConfig(() -> root, 7), decoration).handle();
    }

    public static ResourceLocation locate(String key){ return new ResourceLocation(DungeonsEnhanced.Mod_ID, key);}

    private static void genFloor(StructureRegistrar2<?,?>... structureRegs){
        for (StructureRegistrar2<?,?> structure: structureRegs) {
            JigsawAccessHelper.addIllagerStructures(structure.getStructure());
        }
    }
}