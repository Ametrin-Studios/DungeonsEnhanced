package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.resources.ResourceLocation;

public interface DELootTables {
    // Overworld
    interface DESERT_TEMPLE {
        ResourceLocation COFFIN = chest("desert_temple/coffin");
        ResourceLocation FLOOR = chest("desert_temple/floor");
        ResourceLocation TREASURE = chest("desert_temple/treasure");
    }

    ResourceLocation MINERS_HOUSE = chest("miners_house");

    interface PILLAGER_CAMP {
        ResourceLocation GENERAL = chest("pillager_camp/general");
        ResourceLocation KITCHEN = chest("pillager_camp/kitchen");
    }

    ResourceLocation SUNKEN_SHRINE = chest("sunken_shrine");

    // Nether
    interface BLACK_CITADEL {
        ResourceLocation NORMAL = chest("black_citadel/normal");
        ResourceLocation NORMAL_ALT = chest("black_citadel/normal_alt");
        ResourceLocation KITCHEN = chest("black_citadel/kitchen");
        ResourceLocation TREASURE = chest("black_citadel/treasure");
        ResourceLocation TREASURE_ALT = chest("black_citadel/treasure_alt");
    }

    private static ResourceLocation chest(String key) {
        return DEUtil.locate("chests/" + key);
    }
}
