package com.barion.dungeons_enhanced.world;

import com.barion.dungeons_enhanced.world.structure.*;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawCapability;

public class DEJigsawTypes {
    public static final JigsawCapability.JigsawType<DECastle.Capability> CASTLE = ()-> DECastle.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEDeepCrypt.Capability> DEEP_CRYPT = ()-> DEDeepCrypt.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEDesertTomb.Capability> DESERT_TOMB = ()-> DEDesertTomb.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEDruidCircle.Capability> DRUID_CIRCLE = ()-> DEDruidCircle.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DELargeDungeon.Capability> LARGE_DUNGEON = ()-> DELargeDungeon.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEMonsterMaze.Capability> MONSTER_MAZE = ()-> DEMonsterMaze.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEPillagerCamp.Capability> PILLAGER_CAMP = ()-> DEPillagerCamp.Capability.CODEC;
}