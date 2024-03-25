package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.util.ResourceLocation;

public class DEStructureTemplate {
    public final ResourceLocation resourceLocation;
    public final int yOffset;

    public DEStructureTemplate(ResourceLocation resourceLocation, int yOffset) {
        this.resourceLocation = resourceLocation;
        this.yOffset = yOffset;
    }

    public static DEStructureTemplate of(String id){
        return of(id, 0);
    }
    public static DEStructureTemplate of(String id, int yOffset){
        return new DEStructureTemplate(DEUtil.location(id), yOffset);
    }
}