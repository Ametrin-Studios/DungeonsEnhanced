package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraftforge.common.ForgeConfigSpec;

public class DEStructureConfig extends StructureConfig {
    public DEStructureConfig(ForgeConfigSpec.Builder builder, String name) {super(builder, name);}

    @Override
    public int getOffset() {return getSpacing()/4;}

    @Override
    public int getSpacing() {
        DungeonsEnhanced.LOGGER.info("Spacing is: " + super.getSpacing());
        return super.getSpacing();
    }
}