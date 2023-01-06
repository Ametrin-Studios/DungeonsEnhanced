package com.barion.dungeons_enhanced.world;

import com.barion.dungeons_enhanced.world.structures.*;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawCapability;

public class DEJigsawTypes {
    public static final JigsawCapability.JigsawType<DECastle.Capability> Castle = ()-> DECastle.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEDeepCrypt.Capability> DeepCrypt = ()-> DEDeepCrypt.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEDesertTomb.Capability> DesertTomb = ()-> DEDesertTomb.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEDruidCircle.Capability> DruidCircle = ()-> DEDruidCircle.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DELargeDungeon.Capability> LargeDungeon = ()-> DELargeDungeon.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEMonsterMaze.Capability> MonsterMaze = ()-> DEMonsterMaze.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEPillagerCamp.Capability> PillagerCamp = ()-> DEPillagerCamp.Capability.CODEC;
}