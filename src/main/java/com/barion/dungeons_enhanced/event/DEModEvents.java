package com.barion.dungeons_enhanced.event;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEProcessorTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@SuppressWarnings("unused")
@EventBusSubscriber(modid = DungeonsEnhanced.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public final class DEModEvents {
    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(BuiltInRegistries.STRUCTURE_PROCESSOR.key(), helper -> {
            DEProcessorTypes.register();
        });
    }
}
