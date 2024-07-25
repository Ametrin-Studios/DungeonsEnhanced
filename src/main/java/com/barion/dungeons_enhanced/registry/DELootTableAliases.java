package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.legacy.structure_gel.api.registry.RegistrarHolder;
import com.legacy.structure_gel.api.registry.StructureGelRegistries;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.neoforged.neoforge.registries.RegisterEvent.RegisterHelper;

import static com.barion.dungeons_enhanced.DEUtil.locate;

@RegistrarHolder
public final class DELootTableAliases {
    public static final RegistrarHandler<ResourceLocation> HANDLER = RegistrarHandler.getOrCreate(StructureGelRegistries.Keys.LOOT_TABLE_ALIAS, DungeonsEnhanced.MOD_ID).addListener(DELootTableAliases::register);

    private static void register(RegisterHelper<ResourceLocation> event){
        // Vanilla
        event.register(new ResourceLocation("desert_pyramid"), BuiltInLootTables.DESERT_PYRAMID);
        event.register(new ResourceLocation("simple_dungeon"), BuiltInLootTables.SIMPLE_DUNGEON);

        // Overworld
        event.register(locate("desert_temple/coffin"), DELootTables.DESERT_TEMPLE.COFFIN);
        event.register(locate("desert_temple/floor"), DELootTables.DESERT_TEMPLE.FLOOR);
        event.register(locate("desert_temple/treasure"), DELootTables.DESERT_TEMPLE.TREASURE);

        event.register(locate("fishing_ship"), DELootTables.FISHING_SHIP);
        event.register(locate("miners_house"), DELootTables.MINERS_HOUSE);

        event.register(locate("pillager_camp/general"), DELootTables.PILLAGER_CAMP.GENERAL);
        event.register(locate("pillager_camp/kitchen"), DELootTables.PILLAGER_CAMP.KITCHEN);

        event.register(locate("sunken_shrine"), DELootTables.SUNKEN_SHRINE);

        // Nether
        event.register(locate("black_citadel/normal"), DELootTables.BLACK_CITADEL.NORMAL);
        event.register(locate("black_citadel/normal_alt"), DELootTables.BLACK_CITADEL.NORMAL_ALT);
        event.register(locate("black_citadel/kitchen"), DELootTables.BLACK_CITADEL.KITCHEN);
        event.register(locate("black_citadel/treasure"), DELootTables.BLACK_CITADEL.TREASURE);
        event.register(locate("black_citadel/treasure_alt"), DELootTables.BLACK_CITADEL.TREASURE_ALT);
    }
}
