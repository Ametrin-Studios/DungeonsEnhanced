package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.resources.ResourceLocation;

public interface DELootTables {
    // Overworld
    interface DesertTemple {
        ResourceLocation COFFIN = chest("desert_temple/coffin");
        ResourceLocation FLOOR = chest("desert_temple/floor");
        ResourceLocation TREASURE = chest("desert_temple/treasure");
    }

    ResourceLocation DESERT_TOMB = chest("desert_tomb");
    ResourceLocation FISHING_SHIP = chest("fishing_ship");
    ResourceLocation HAY_STORAGE = chest("hay_storage");

    interface IcePit {
        ResourceLocation ARMORY = chest("ice_pit/armory");
        ResourceLocation BED = chest("ice_pit/bed");
        ResourceLocation FOOD = chest("ice_pit/food");
        ResourceLocation GARDEN = chest("ice_pit/garden");
        ResourceLocation HALL = chest("ice_pit/hall");
    }
    interface JungleMonument {
        ResourceLocation FLOOR = chest("jungle_monument/floor");
        ResourceLocation TREASURE = chest("jungle_monument/treasure");
    }

    ResourceLocation MINERS_HOUSE = chest("miners_house");
    ResourceLocation MUSHROOM_HOUSE = chest("mushroom_house");

    interface PillagerCamp {
        ResourceLocation GENERAL = chest("pillager_camp/general");
        ResourceLocation KITCHEN = chest("pillager_camp/kitchen");
    }

    interface Ruined {
        ResourceLocation HOUSE = chest("ruined/house");
    }

    ResourceLocation STABLES = chest("stables");
    ResourceLocation SUNKEN_SHRINE = chest("sunken_shrine");

    // Nether
    interface BlackCitadel {
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
