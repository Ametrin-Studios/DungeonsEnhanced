package com.barion.dungeons_enhanced;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DungeonsEnhanced.MOD_ID)
public final class DungeonsEnhanced {
    public static final String MOD_ID = "dungeons_enhanced";
    public static final Logger LOGGER = LogManager.getLogger();

    public DungeonsEnhanced() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DEConfig.COMMON_SPEC);
    }
}