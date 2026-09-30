package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.fixture.FixturePreset;
import com.ametrin.structures.fixture.Fixtures;
import com.ametrin.structures.registry.ASRegistries;
import com.ametrin.structures.spawner.SpawnDataBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;

public final class DEFixturePresets {
    public static void bootstrap(BootstrapContext<FixturePreset> context) {
        context.register(key("hendrik_van_der_decken"), FixturePreset.builder()
                .entity(1, new SpawnDataBuilder(EntityType.SKELETON)
                        .equipment(DELootTables.EQUIPMENT_HENDRICK_VAN_DER_DECKEN)
                        .leftHanded()
                        .maxHealth(40)
                        .name(Component.literal("Hendrik van der Decken"))
                        .build())
                .build());

        context.register(key("cauldron"), FixturePreset.builder()
                .add(1, new Fixtures.PlaceBlockState(Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 2)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3)))
                .build());
    }

    private static ResourceKey<FixturePreset> key(String path) {
        return ResourceKey.create(ASRegistries.FIXTURE_PRESET, DungeonsEnhanced.locate(path));
    }
}
