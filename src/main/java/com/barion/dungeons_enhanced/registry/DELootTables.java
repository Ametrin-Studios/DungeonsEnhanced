package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.resources.ResourceLocation;

public interface DELootTables {
    ResourceLocation SUNKEN_SHRINE = chest("sunken_shrine");
    ResourceLocation MINERS_HOUSE = chest("miners_house");

    interface PILLAGER_CAMP{
        ResourceLocation GENERAL = chest("pillager_camp/general");
        ResourceLocation KITCHEN = chest("pillager_camp/kitchen");
    }

    interface BLACK_CITADEL{
        ResourceLocation NORMAL = chest("black_citadel/normal");
        ResourceLocation NORMAL_ALT = chest("black_citadel/normal_alt");
        ResourceLocation KITCHEN = chest("black_citadel/kitchen");
        ResourceLocation TREASURE = chest("black_citadel/treasure");
        ResourceLocation TREASURE_ALT = chest("black_citadel/treasure_alt");
    }

    private static ResourceLocation chest(String key) {
        return DEUtil.location("chests/" + key);
    }
}
