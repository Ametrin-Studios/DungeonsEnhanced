package com.barion.dungeons_enhanced.event;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.legacy.structure_gel.access_helpers.BiomeAccessHelper;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import com.legacy.structure_gel.worldgen.structure.GelStructure;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = DungeonsEnhanced.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class DEGameEvents {
    @SubscribeEvent
    public static <C extends IFeatureConfig, S extends GelStructure<C>, T extends StructureRegistrar2<C, S>> void onBiomeLoad(final BiomeLoadingEvent event) {
        for (StructureRegistrar2<?, ?> structure : DEStructures.ALL_STRUCTURE_REGISTRARS) {
            BiomeAccessHelper.addStructureIfAllowed(event, (StructureFeature<C, S>) structure.getStructureFeature());
        }
    }
}
