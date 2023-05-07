package com.barion.dungeons_enhanced.world;

import com.barion.dungeons_enhanced.data.DETags;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;


public interface DEVillagerTrades {
    VillagerTrades.ItemListing CASTLE_EXPLORER_MAP_TRADE = new TreasureMapForEmeralds(13, DETags.Structures.ON_CASTLE_EXPLORER_MAPS, "filled_map.dungeons_enhanced.castle", MapDecoration.Type.TARGET_X, 12, 10);
    VillagerTrades.ItemListing DESERT_EXPLORER_MAP_TRADE = new VillagerTrades.TreasureMapForEmeralds(14, DETags.Structures.ON_DESERT_EXPLORER_MAPS, "filled_map.dungeons_enhanced.desert_temple", MapDecoration.Type.TARGET_X, 12, 10);
    VillagerTrades.ItemListing MONSTER_MAZE_EXPLORER_MAP_TRADE = new VillagerTrades.TreasureMapForEmeralds(15, DETags.Structures.ON_MONSTER_MAZE_EXPLORER_MAPS, "filled_map.dungeons_enhanced.monster_maze", MapDecoration.Type.TARGET_X, 12, 10);

    public static class TreasureMapForEmeralds implements VillagerTrades.ItemListing {
        private final int emeraldCost;
        private final TagKey<Structure> destination;
        private final String displayName;
        private final MapDecoration.Type destinationType;
        private final int maxUses;
        private final int villagerXp;

        public TreasureMapForEmeralds(int emeraldCost, TagKey<Structure> structureTag, String displayName, MapDecoration.Type markerType, int maxUses, int villagerXP) {
            this.emeraldCost = emeraldCost;
//            destination = getHolders(structureTag).orElseThrow();
            this.destination = structureTag;
            this.displayName = displayName;
            this.destinationType = markerType;
            this.maxUses = maxUses;
            this.villagerXp = villagerXP;
        }

        @Nullable @Override
        public MerchantOffer getOffer(Entity trader, @Nonnull RandomSource random) {
            if(!(trader.level instanceof ServerLevel level)) return null;
            var holders = getHolders(destination, level.registryAccess().registryOrThrow(Registries.STRUCTURE)).orElseThrow();
            var structurePos = level.getChunkSource().getGenerator().findNearestMapStructure(level, holders, trader.blockPosition(), 100, true).getFirst();
            if (structurePos != null) {
                var itemStack = MapItem.create(level, structurePos.getX(), structurePos.getZ(), (byte)2, true, true);
                MapItem.renderBiomePreviewMap(level, itemStack);
                MapItemSavedData.addTargetDecoration(itemStack, structurePos, "+", destinationType);
                itemStack.setHoverName(Component.translatable(displayName));
                return new MerchantOffer(new ItemStack(Items.EMERALD, emeraldCost), new ItemStack(Items.COMPASS), itemStack, maxUses, villagerXp, 0.2F);
            } else {
                return null;
            }
        }

        private static Optional<? extends HolderSet.ListBacked<Structure>> getHolders(TagKey<Structure> structureTag, Registry<Structure> pStructureRegistry) {
            return pStructureRegistry.getTag(structureTag);
        }
    }
}
