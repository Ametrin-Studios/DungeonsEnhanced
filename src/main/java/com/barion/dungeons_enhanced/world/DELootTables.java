package com.barion.dungeons_enhanced.world;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.resources.ResourceLocation;

public class DELootTables {
    public static final ResourceLocation SUNKEN_SHRINE = chest("sunken_shrine");
    public static final ResourceLocation MINERS_HOUSE = chest("miners_house");

    private static ResourceLocation chest(String key) {
        return DEUtil.location("chests/" + key);
    }
}
