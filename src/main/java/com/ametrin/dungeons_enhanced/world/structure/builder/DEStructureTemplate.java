package com.ametrin.dungeons_enhanced.world.structure.builder;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import net.minecraft.resources.Identifier;

public record DEStructureTemplate(Identifier identifier, int yOffset) {
    public static DEStructureTemplate of(String id) {
        return of(id, 0);
    }

    public static DEStructureTemplate of(String id, int yOffset) {
        return new DEStructureTemplate(DungeonsEnhanced.locate(id), yOffset);
    }
}