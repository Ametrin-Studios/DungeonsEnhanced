package com.barion.dungeons_enhanced.event;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEVillagerTrades;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = DungeonsEnhanced.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public final class DEGameEvents {
    @SubscribeEvent
    public static void registerCustomTrades(final VillagerTradesEvent event){
        if(event.getType() != VillagerProfession.CARTOGRAPHER) return;

        var trades = event.getTrades();
        trades.get(2).add(DEVillagerTrades.ELDER_EXPLORER_MAP_TRADE);
        trades.get(3).add(DEVillagerTrades.CASTLE_EXPLORER_MAP_TRADE);
        trades.get(4).add(DEVillagerTrades.MONSTER_MAZE_EXPLORER_MAP_TRADE);
//        trades.get(5).add(DEVillagerTrades.DESERT_EXPLORER_MAP_TRADE);
    }
}
