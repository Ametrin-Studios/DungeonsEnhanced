package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.structure.*;
import com.legacy.structure_gel.api.registry.RegistrarHolder;
import com.legacy.structure_gel.api.registry.StructureGelRegistries;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawCapabilityType;

@RegistrarHolder
public final class DEJigsawTypes {
    public static final RegistrarHandler<JigsawCapabilityType<?>> HANDLER = RegistrarHandler.getOrCreate(StructureGelRegistries.Keys.JIGSAW_TYPE, DungeonsEnhanced.MOD_ID);

    // Overworld
    public static final Registrar.Static<JigsawCapabilityType<DECastle.Capability>> CASTLE = HANDLER.createStatic(DEStructureIDs.CASTLE, () -> () -> DECastle.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEDeepCrypt.Capability>> DEEP_CRYPT = HANDLER.createStatic(DEStructureIDs.DEEP_CRYPT, () -> () -> DEDeepCrypt.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEDesertTomb.Capability>> DESERT_TOMB = HANDLER.createStatic(DEStructureIDs.DESERT_TOMB, () -> () -> DEDesertTomb.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEDruidCircle.Capability>> DRUID_CIRCLE = HANDLER.createStatic(DEStructureIDs.DESERT_TEMPLE, () -> () -> DEDruidCircle.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DELargeDungeon.Capability>> LARGE_DUNGEON = HANDLER.createStatic(DEStructureIDs.LARGE_DUNGEON, () -> () -> DELargeDungeon.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEMonsterMaze.Capability>> MONSTER_MAZE = HANDLER.createStatic(DEStructureIDs.MONSTER_MAZE, () -> () -> DEMonsterMaze.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEPillagerCamp.Capability>> PILLAGER_CAMP = HANDLER.createStatic(DEStructureIDs.PILLAGER_CAMP, () -> () -> DEPillagerCamp.Capability.CODEC);

    // Nether
    public static final Registrar.Static<JigsawCapabilityType<DEBlackCitadel.Capability>> BLACK_CITADEL = HANDLER.createStatic(DEStructureIDs.BLACK_CITADEL, () -> () -> DEBlackCitadel.Capability.CODEC);
}