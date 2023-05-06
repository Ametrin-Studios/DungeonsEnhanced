package com.barion.dungeons_enhanced.event;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.DEVillagerTrades;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DungeonsEnhanced.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DEForgeEvents {
    @SubscribeEvent
    public static void registerCustomTrades(final VillagerTradesEvent event){
        if(event.getType() != VillagerProfession.CARTOGRAPHER) return;

        var trades = event.getTrades();
        trades.get(2).add(DEVillagerTrades.DESERT_EXPLORER_MAP_TRADE);
        trades.get(3).add(DEVillagerTrades.CASTLE_EXPLORER_MAP_TRADE);
        trades.get(3).add(DEVillagerTrades.MONSTER_MAZE_EXPLORER_MAP_TRADE);
    }
}
