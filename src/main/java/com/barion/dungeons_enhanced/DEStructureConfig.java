package com.barion.dungeons_enhanced;

import com.legacy.structure_gel.util.ConfigTemplates;
import net.minecraftforge.common.ForgeConfigSpec;

public class DEStructureConfig extends ConfigTemplates.StructureConfig {

    public DEStructureConfig(ForgeConfigSpec.Builder builder, String name) {super(builder, name);}

    @Override
    public int getOffset() {return super.getSpacing()/4;}
}