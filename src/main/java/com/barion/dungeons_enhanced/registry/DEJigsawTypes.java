package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.world.structure.*;
import com.legacy.structure_gel.api.events.RegisterJigsawCapabilityEvent;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawCapability;

import static com.barion.dungeons_enhanced.DungeonsEnhanced.locate;

public final class DEJigsawTypes {
    public static final JigsawCapability.JigsawType<DECastle.Capability> CASTLE = () -> DECastle.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEDeepCrypt.Capability> DEEP_CRYPT = () -> DEDeepCrypt.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEDesertTomb.Capability> DESERT_TOMB = () -> DEDesertTomb.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEDruidCircle.Capability> DRUID_CIRCLE = () -> DEDruidCircle.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DELargeDungeon.Capability> LARGE_DUNGEON = () -> DELargeDungeon.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEMonsterMaze.Capability> MONSTER_MAZE = () -> DEMonsterMaze.Capability.CODEC;
    public static final JigsawCapability.JigsawType<DEPillagerCamp.Capability> PILLAGER_CAMP = () -> DEPillagerCamp.Capability.CODEC;

    public static final JigsawCapability.JigsawType<DEBlackCitadel.Capability> BLACK_CITADEL = () -> DEBlackCitadel.Capability.CODEC;

    // RegisterJigsawCapabilityEvent is deprecated but even used by Dungeons Plus
    public static void register(final RegisterJigsawCapabilityEvent event) {
        event.register(locate(DEStructureIDs.CASTLE), CASTLE);
        event.register(locate(DEStructureIDs.DEEP_CRYPT), DEEP_CRYPT);
        event.register(locate(DEStructureIDs.DESERT_TOMB), DESERT_TOMB);
        event.register(locate(DEStructureIDs.DRUID_CIRCLE), DRUID_CIRCLE);
        event.register(locate(DEStructureIDs.LARGE_DUNGEON), LARGE_DUNGEON);
        event.register(locate(DEStructureIDs.MONSTER_MAZE), MONSTER_MAZE);
        event.register(locate(DEStructureIDs.PILLAGER_CAMP), PILLAGER_CAMP);

        event.register(locate(DEStructureIDs.BLACK_CITADEL), BLACK_CITADEL);
    }
}