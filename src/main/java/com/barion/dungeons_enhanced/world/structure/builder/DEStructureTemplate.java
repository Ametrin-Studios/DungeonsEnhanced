package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import net.minecraft.resources.ResourceLocation;

public record DEStructureTemplate(ResourceLocation resourceLocation, int yOffset) {
    public static DEStructureTemplate of(String id) {
        return of(id, 0);
    }

    public static DEStructureTemplate of(String id, int yOffset) {
        return new DEStructureTemplate(DungeonsEnhanced.locate(id), yOffset);
    }
}