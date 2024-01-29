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
    public static final Registrar.Static<JigsawCapabilityType<DECastle.Capability>> CASTLE = HANDLER.createStatic(DECastle.ID, () -> () -> DECastle.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEDeepCrypt.Capability>> DEEP_CRYPT = HANDLER.createStatic(DEDeepCrypt.ID, () -> () -> DEDeepCrypt.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEDesertTomb.Capability>> DESERT_TOMB = HANDLER.createStatic(DEDesertTomb.ID, () -> () -> DEDesertTomb.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEDruidCircle.Capability>> DRUID_CIRCLE = HANDLER.createStatic(DEDruidCircle.ID, () -> () -> DEDruidCircle.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DELargeDungeon.Capability>> LARGE_DUNGEON = HANDLER.createStatic(DELargeDungeon.ID, () -> () -> DELargeDungeon.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEMonsterMaze.Capability>> MONSTER_MAZE = HANDLER.createStatic(DEMonsterMaze.ID, () -> () -> DEMonsterMaze.Capability.CODEC);
    public static final Registrar.Static<JigsawCapabilityType<DEPillagerCamp.Capability>> PILLAGER_CAMP = HANDLER.createStatic(DEPillagerCamp.ID, () -> () -> DEPillagerCamp.Capability.CODEC);
}