package com.barion.dungeons_enhanced;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class DEUtil{
    public static ResourceLocation locate(String key){ return new ResourceLocation(DungeonsEnhanced.Mod_ID, key);}
    public static BlockPos Offset(int x, int y, int z){
        return new BlockPos(x, y, z);
    }
}