package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.registry.ASRegistries;
import com.ametrin.structures.spawner.SpawnDataBuilder;
import com.ametrin.structures.spawner.SpawnerProfile;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentTable;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.storage.loot.LootTable;


public final class DESpawnerProfiles {
    public static void bootstrap(BootstrapContext<SpawnerProfile> context) {
        context.register(key("castle/guards"), SpawnerProfile.builder()
                .add(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.Castle.EQUIPMENT_ZOMBIE_GUARD), 1)
                .build());

        context.register(key("castle/default"), SpawnerProfile.builder()
                .add(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.Castle.EQUIPMENT_ZOMBIE), 3)
                .add(createSpawnDataWithEquipment(EntityType.SKELETON, DELootTables.Castle.EQUIPMENT_SKELETON), 2)
                .build());

        context.register(key("monster_maze/default"), SpawnerProfile.builder()
                .add(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE), 1)
                .add(createSpawnDataWithEquipment(EntityType.SKELETON, DELootTables.MonsterMaze.EQUIPMENT_SKELETON), 1)
                .add(builder(EntityType.SPIDER).passenger(new SpawnDataBuilder(EntityType.SKELETON).equip(EquipmentSlot.MAINHAND, Items.BOW)), 1)
                .add(EntityType.CAVE_SPIDER, 1)
                .build());

        context.register(key("monster_maze/brewery"), SpawnerProfile.builder()
                .add(EntityType.ZOMBIE, 1)
                .add(EntityType.SKELETON, 1)
                .add(EntityType.SPIDER, 1)
                .add(EntityType.CAVE_SPIDER, 1)
                .build());

        context.register(key("monster_maze/church"), SpawnerProfile.builder()
                .add(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE), 3)
                .add(EntityType.SKELETON, 2)
                .add(EntityType.SPIDER, 1)
                .add(EntityType.CAVE_SPIDER, 1)
                .build());

        context.register(key("monster_maze/prison"), SpawnerProfile.builder()
                .add(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.MonsterMaze.EQUIPMENT_PRISON_ZOMBIE), 3)
                .add(EntityType.SKELETON, 2)
                .add(EntityType.SPIDER, 1)
                .add(EntityType.CAVE_SPIDER, 1)
                .build());

        context.register(key("flying_dutchman"), SpawnerProfile.builder()
                .add(builder(EntityType.SKELETON).equipment(DELootTables.EQUIPMENT_FLYING_DUTCHMAN_SKELETONS).build(), 1)
                .build());

        context.register(key("undead_desert"), SpawnerProfile.builder()
                .add(EntityType.PARCHED, 2)
                .add(EntityType.HUSK, 3)
                .build());

        context.register(key("undead_frozen"), SpawnerProfile.builder()
                .add(EntityType.STRAY, 2)
                .build());
    }

    private static SpawnDataBuilder builder(EntityType<?> entityType) {
        return new SpawnDataBuilder(entityType);
    }

    private static SpawnData createSpawnDataWithEquipment(EntityType<?> entityType, ResourceKey<LootTable> equipmentTable) {
        return createSpawnDataWithEquipment(entityType, new EquipmentTable(equipmentTable, 0.085f));
    }

    private static SpawnData createSpawnDataWithEquipment(EntityType<?> entityType, EquipmentTable equipmentTable) {
        return builder(entityType).equipment(equipmentTable).build();
    }

    private static ResourceKey<SpawnerProfile> key(String path) {
        return ResourceKey.create(ASRegistries.SPAWNER_PROFILE, DungeonsEnhanced.locate(path));
    }
}