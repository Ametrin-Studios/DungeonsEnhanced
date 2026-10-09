package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.spawner.SpawnDataBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.trialspawner.TrialSpawnerConfig;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;

public final class DETrialSpawnerConfigs {
    public static void bootstrap(BootstrapContext<TrialSpawnerConfig> context) {
        context.register(key("dungeon_variant/copper_shrine"), TrialSpawnerConfig.builder()
                .spawnPotentialsDefinition(WeightedList.of(
                        new SpawnDataBuilder(EntityType.BREEZE).build(1),
                        new SpawnDataBuilder(EntityType.BOGGED).build(2),
                        new SpawnDataBuilder(EntityType.CAVE_SPIDER).build(1)
                ))
                .spawnRange(6)
                .simultaneousMobs(3)
                .simultaneousMobsAddedPerPlayer(1)
                .lootTablesToEject(
                        WeightedList.<ResourceKey<LootTable>>builder()
                                .add(BuiltInLootTables.SPAWNER_TRIAL_CHAMBER_KEY, 3)
                                .add(BuiltInLootTables.SPAWNER_TRIAL_CHAMBER_CONSUMABLES, 7)
                                .build()
                )
                .build());

        context.register(key("tower_of_the_undead/zombie/normal"), TrialSpawnerConfig.builder()
                .spawnPotentialsDefinition(WeightedList.of(
                        new SpawnDataBuilder(EntityType.ZOMBIE).equipment(DELootTables.Castle.EQUIPMENT_ZOMBIE).build()
                ))
                .spawnRange(4)
                .simultaneousMobs(3)
                .simultaneousMobsAddedPerPlayer(1)
                .lootTablesToEject(
                        WeightedList.<ResourceKey<LootTable>>builder()
                                .add(BuiltInLootTables.SPAWNER_TRIAL_CHAMBER_KEY, 3)
                                .add(BuiltInLootTables.SPAWNER_TRIAL_CHAMBER_CONSUMABLES, 7)
                                .build()
                )
                .build());

        context.register(key("tower_of_the_undead/mixed/normal"), TrialSpawnerConfig.builder()
                .spawnPotentialsDefinition(WeightedList.of(
                        new SpawnDataBuilder(EntityType.ZOMBIE).equipment(DELootTables.Castle.EQUIPMENT_ZOMBIE).build(1),
                        new SpawnDataBuilder(EntityType.SKELETON).equipment(DELootTables.Castle.EQUIPMENT_SKELETON).build(1)
                ))
                .spawnRange(4)
                .simultaneousMobs(3)
                .simultaneousMobsAddedPerPlayer(1)
                .lootTablesToEject(
                        WeightedList.<ResourceKey<LootTable>>builder()
                                .add(BuiltInLootTables.SPAWNER_TRIAL_CHAMBER_KEY, 3)
                                .add(BuiltInLootTables.SPAWNER_TRIAL_CHAMBER_CONSUMABLES, 7)
                                .build()
                )
                .build());

        context.register(key("tower_of_the_undead/zombie/ominous"), TrialSpawnerConfig.builder()
                .spawnPotentialsDefinition(WeightedList.of(
                        new SpawnDataBuilder(EntityType.ZOMBIE).equipment(DELootTables.Castle.EQUIPMENT_ZOMBIE_GUARD).build()
                ))
                .spawnRange(4)
                .simultaneousMobs(3)
                .simultaneousMobsAddedPerPlayer(1)
                .lootTablesToEject(
                        WeightedList.<ResourceKey<LootTable>>builder()
                                .add(BuiltInLootTables.SPAWNER_OMINOUS_TRIAL_CHAMBER_KEY, 3)
                                .add(BuiltInLootTables.SPAWNER_OMINOUS_TRIAL_CHAMBER_CONSUMABLES, 5)
                                .build()
                )
                .build());
    }

    private static ResourceKey<TrialSpawnerConfig> key(String path) {
        return ResourceKey.create(Registries.TRIAL_SPAWNER_CONFIG, DungeonsEnhanced.locate(path));
    }
}
