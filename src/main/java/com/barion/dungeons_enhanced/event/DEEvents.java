package com.barion.dungeons_enhanced.event;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.DELootTables;
import com.barion.dungeons_enhanced.world.structure.processor.DEProcessors;
import com.legacy.structure_gel.api.events.RegisterLootTableAliasEvent;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

import static com.barion.dungeons_enhanced.DEUtil.location;

@Mod.EventBusSubscriber(modid = DungeonsEnhanced.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DEEvents {
    @SubscribeEvent
    public static void register(RegisterEvent event){
        event.register(BuiltInRegistries.STRUCTURE_PROCESSOR.key(), helper ->{
            DEProcessors.Types.register();
        });
    }

    @SubscribeEvent
    public static void registerLootTableAlias(final RegisterLootTableAliasEvent event){
        event.register(location("sunken_shrine"), DELootTables.SUNKEN_SHRINE);
        event.register(location("miners_house"), DELootTables.MINERS_HOUSE);
        event.register(location("pillager_camp/general"), DELootTables.PILLAGER_CAMP.GENERAL);
        event.register(location("pillager_camp/kitchen"), DELootTables.PILLAGER_CAMP.KITCHEN);
    }
}
