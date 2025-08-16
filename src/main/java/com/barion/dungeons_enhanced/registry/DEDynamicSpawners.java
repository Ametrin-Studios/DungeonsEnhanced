package com.barion.dungeons_enhanced.registry;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.SpawnDataBuilder;
import com.legacy.structure_gel.api.block_entity.SpawnerAccessHelper;
import com.legacy.structure_gel.api.dynamic_spawner.DynamicSpawnerType;
import com.legacy.structure_gel.api.registry.RegistrarHolder;
import com.legacy.structure_gel.api.registry.StructureGelRegistries;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.registry.registrar.RegistrarHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentTable;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Optional;

@RegistrarHolder
public interface DEDynamicSpawners {
    RegistrarHandler<DynamicSpawnerType> HANDLER = RegistrarHandler.getOrCreate(StructureGelRegistries.Keys.DYNAMIC_SPAWNER_TYPE, DungeonsEnhanced.MOD_ID);

    Registrar.Static<DynamicSpawnerType> MONSTER_MAZE_DEFAULT = HANDLER.createStatic("monster_maze/default", () -> (builder, registry) ->
    {
//        builder.spawnData(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE))
//                .spawnData(createSpawnDataWithEquipment(EntityType.SKELETON, DELootTables.MonsterMaze.EQUIPMENT_SKELETON))
                builder.spawnData(builder(EntityType.SPIDER).passenger(EntityType.SKELETON, Items.BOW, registry).build())
//                .spawnData(EntityType.CAVE_SPIDER)
        ;
    });

    Registrar.Static<DynamicSpawnerType> MONSTER_MAZE_BREWERY = HANDLER.createStatic("monster_maze/brewery", () -> (builder, registry) ->
    {
        builder.spawnData(EntityType.ZOMBIE)
                .spawnData(EntityType.SKELETON)
                .spawnData(EntityType.SPIDER)
                .spawnData(EntityType.CAVE_SPIDER)
        ;
    });

    Registrar.Static<DynamicSpawnerType> MONSTER_MAZE_CHURCH = HANDLER.createStatic("monster_maze/church", () -> (builder, registry) ->
    {
        builder.spawnData(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.MonsterMaze.EQUIPMENT_PRISON_ZOMBIE), 3)
                .spawnData(EntityType.SKELETON, 2)
                .spawnData(EntityType.SPIDER)
                .spawnData(EntityType.CAVE_SPIDER)
        ;
    });

    Registrar.Static<DynamicSpawnerType> MONSTER_MAZE_PRISON = HANDLER.createStatic("monster_maze/prison", () -> (builder, registry) ->
    {
        builder.spawnData(createSpawnDataWithEquipment(EntityType.ZOMBIE, DELootTables.MonsterMaze.EQUIPMENT_PRISON_ZOMBIE), 3)
                .spawnData(EntityType.SKELETON, 2)
                .spawnData(EntityType.SPIDER)
                .spawnData(EntityType.CAVE_SPIDER)
        ;
    });

    private static SpawnDataBuilder builder(EntityType<?> entityType) {
        return new SpawnDataBuilder(entityType);
    }

    private static SpawnData createSpawnDataWithEquipment(EntityType<?> entityType, ResourceKey<LootTable> equipmentTable) {
        return createSpawnDataWithEquipment(entityType, new EquipmentTable(equipmentTable, 0.085f));
    }

    private static SpawnData createSpawnDataWithEquipment(EntityType<?> entityType, EquipmentTable equipmentTable) {
        return SpawnerAccessHelper.createSpawnerEntity(entityType, new CompoundTag(), Optional.empty(), Optional.of(equipmentTable));
    }
}