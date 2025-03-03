package com.barion.dungeons_enhanced.registry;

import com.legacy.structure_gel.api.block_entity.SpawnerAccessHelper;
import com.legacy.structure_gel.api.dynamic_spawner.DynamicSpawnerType;
import net.minecraft.world.entity.EntityType;

import static com.barion.dungeons_enhanced.DungeonsEnhanced.locate;

public final class DEDynamicSpawners {
    public static DynamicSpawnerType TOWER_OF_THE_UNDEAD = DynamicSpawnerType.register(new DynamicSpawnerType(locate("tower_of_the_undead/1"), ((spawner, level, pos) -> {
        var zombie1 = SpawnerAccessHelper.createSpawnerEntity(EntityType.ZOMBIE);
        var zombie2 = SpawnerAccessHelper.createSpawnerEntity(EntityType.ZOMBIE);
        var skeleton = SpawnerAccessHelper.createSpawnerEntity(EntityType.SKELETON);
        SpawnerAccessHelper.setSpawnPotentials(spawner, level, pos, zombie1, zombie2, skeleton);
    })));

}
