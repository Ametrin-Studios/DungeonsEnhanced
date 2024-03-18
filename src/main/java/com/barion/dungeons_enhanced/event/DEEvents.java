package com.barion.dungeons_enhanced.event;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEProcessorTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = DungeonsEnhanced.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DEEvents {
    @SubscribeEvent
    public static void register(RegisterEvent event){
        event.register(BuiltInRegistries.STRUCTURE_PROCESSOR.key(), helper ->{
            DEProcessorTypes.register();
        });
    }
}
