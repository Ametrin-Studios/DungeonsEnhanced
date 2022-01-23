package com.barion.dungeons_enhanced;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class DEUtil{
    public static BlockPos Offset(int x, int y, int z){
        return new BlockPos(x, y, z);
    }

    public static ResourceLocation createResourceKey(String key){
        return new ResourceLocation(DungeonsEnhanced.Mod_ID, key);
    }

    public enum GenerationType{above, on, under}
}