package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.legacy.structure_gel.api.registry.RegistrarHolder;
import com.legacy.structure_gel.api.registry.StructureGelRegistries;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.neoforged.neoforge.registries.RegisterEvent.RegisterHelper;

import static com.barion.dungeons_enhanced.DEUtil.location;

@RegistrarHolder
public class DELootTableAliases {
    public static final RegistrarHandler<ResourceLocation> HANDLER = RegistrarHandler.getOrCreate(StructureGelRegistries.Keys.LOOT_TABLE_ALIAS, DungeonsEnhanced.MOD_ID).addListener(DELootTableAliases::register);

    protected static void register(RegisterHelper<ResourceLocation> event){
        event.register(new ResourceLocation("simple_dungeon"), BuiltInLootTables.SIMPLE_DUNGEON);

        event.register(location("sunken_shrine"), DELootTables.SUNKEN_SHRINE);

        event.register(location("miners_house"), DELootTables.MINERS_HOUSE);

        event.register(location("pillager_camp/general"), DELootTables.PILLAGER_CAMP.GENERAL);
        event.register(location("pillager_camp/kitchen"), DELootTables.PILLAGER_CAMP.KITCHEN);

        event.register(location("black_citadel/normal"), DELootTables.BLACK_CITADEL.NORMAL);
        event.register(location("black_citadel/kitchen"), DELootTables.BLACK_CITADEL.KITCHEN);
        event.register(location("black_citadel/treasure"), DELootTables.BLACK_CITADEL.TREASURE);
    }
}
