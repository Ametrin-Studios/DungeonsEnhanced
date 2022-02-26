package com.barion.dungeons_enhanced;

import net.minecraft.resources.ResourceLocation;

public class DELootTables {

    public static final ResourceLocation FlyingDutchman = chestLoot("flying_dutchman");
    public static class IcePit{
        public static final ResourceLocation Armory = chestLoot("ice_pit/armory");
        public static final ResourceLocation Bed = chestLoot("ice_pit/bed");
        public static final ResourceLocation Food = chestLoot("ice_pit/food");
        public static final ResourceLocation Garden = chestLoot("ice_pit/garden");
        public static final ResourceLocation Hall = chestLoot("ice_pit/hall");
    }

    private static ResourceLocation chestLoot(String id){
        return new ResourceLocation(DungeonsEnhanced.Mod_ID, "chests/" + id);
    }
}
