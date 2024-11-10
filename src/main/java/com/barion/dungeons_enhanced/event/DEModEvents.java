package com.barion.dungeons_enhanced.event;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DELootTableAliases;
import com.barion.dungeons_enhanced.registry.DEProcessorTypes;
import com.legacy.structure_gel.api.events.RegisterLootTableAliasEvent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@SuppressWarnings({"unused", "removal"})
@Mod.EventBusSubscriber(modid = DungeonsEnhanced.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DEModEvents {
    @SubscribeEvent
    public static void register(RegisterEvent event) {
        event.register(BuiltInRegistries.STRUCTURE_PROCESSOR.key(), helper -> {
            DEProcessorTypes.register();
        });
    }

    @SubscribeEvent
    public static void registerLootTableAlias(final RegisterLootTableAliasEvent event) {
        DELootTableAliases.register(event);
    }
}
