package com.barion.dungeons_enhanced.structures.prefabs;

import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class DEBaseStructure extends GelConfigStructure<NoneFeatureConfiguration> {

    public DEBaseStructure(Codec<NoneFeatureConfiguration> codec, StructureConfig config) {
        super(codec, config);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return null;
    }
}
