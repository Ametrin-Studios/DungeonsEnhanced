package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.fixture.FixtureConditions;
import com.ametrin.structures.fixture.FixturePreset;
import com.ametrin.structures.fixture.Fixtures;
import com.ametrin.structures.registry.ASRegistries;
import com.ametrin.structures.spawner.SpawnDataBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.neoforged.neoforge.common.Tags;

public final class DEFixturePresets {
    public static void bootstrap(BootstrapContext<FixturePreset> context) {
        var biomes = context.lookup(Registries.BIOME);
        context.register(key("hendrik_van_der_decken"), FixturePreset.builder()
                .entity(1, new SpawnDataBuilder(EntityType.SKELETON)
                        .equipment(DELootTables.EQUIPMENT_HENDRICK_VAN_DER_DECKEN)
                        .leftHanded()
                        .maxHealth(40)
                        .name(Component.literal("Hendrik van der Decken"))
                        .build())
                .build());

        context.register(key("water_cauldron"), FixturePreset.builder()
                .add(1, new Fixtures.PlaceBlockState(Blocks.CAULDRON.defaultBlockState()))
                .add(1, new Fixtures.PlaceBlockState(Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 2)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3)))
                .build());

        context.register(key("composter"), FixturePreset.builder()
                .add(1, new Fixtures.PlaceBlockState(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 0)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 1)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 2)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 3)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 4)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 5)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 6)))
                .add(1, new Fixtures.PlaceBlockState(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 7)))
                .build());

        context.register(key("anvil"), FixturePreset.builder()
                .add(1, new Fixtures.PlaceBlockState(Blocks.ANVIL.defaultBlockState()))
                .add(1, new Fixtures.PlaceBlockState(Blocks.CHIPPED_ANVIL.defaultBlockState()))
                .add(1, new Fixtures.PlaceBlockState(Blocks.DAMAGED_ANVIL.defaultBlockState()))
                .build());

        context.register(key("biome_based_zombie_spawner"), FixturePreset.builder()
                .add(100, Fixtures.Spawner.of(EntityType.HUSK), new FixtureConditions.InBiome(biomes.getOrThrow(Tags.Biomes.IS_SANDY)))
                .add(1, Fixtures.Spawner.of(EntityType.ZOMBIE))
                .build());

        context.register(key("biome_based_skeleton_spawner"), FixturePreset.builder()
                .add(100, Fixtures.Spawner.of(EntityType.BOGGED), new FixtureConditions.InBiome(biomes.getOrThrow(Tags.Biomes.IS_SWAMP)))
                .add(100, Fixtures.Spawner.of(EntityType.STRAY), new FixtureConditions.InBiome(biomes.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD)))
                .add(1, Fixtures.Spawner.of(EntityType.SKELETON))
                .build());


        context.register(key("black_citadel/normal_chest"), FixturePreset.builder()
                .add(1, Fixtures.LootContainer.chest(DELootTables.BlackCitadel.NORMAL))
                .add(1, Fixtures.LootContainer.chest(DELootTables.BlackCitadel.NORMAL_ALT))
                .build());

        context.register(key("black_citadel/normal_chest_left"), FixturePreset.builder()
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.NORMAL, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.LEFT)))
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.NORMAL_ALT, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.LEFT)))
                .build());

        context.register(key("black_citadel/normal_chest_right"), FixturePreset.builder()
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.NORMAL, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.RIGHT)))
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.NORMAL_ALT, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.RIGHT)))
                .build());

        context.register(key("black_citadel/normal_barrel"), FixturePreset.builder()
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.NORMAL, Blocks.BARREL.defaultBlockState()))
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.NORMAL_ALT, Blocks.BARREL.defaultBlockState()))
                .build());

        context.register(key("black_citadel/treasure_chest"), FixturePreset.builder()
                .add(1, Fixtures.LootContainer.chest(DELootTables.BlackCitadel.TREASURE))
                .add(1, Fixtures.LootContainer.chest(DELootTables.BlackCitadel.TREASURE_ALT))
                .build());

        context.register(key("black_citadel/treasure_chest_left"), FixturePreset.builder()
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.TREASURE, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.LEFT)))
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.TREASURE_ALT, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.LEFT)))
                .build());

        context.register(key("black_citadel/treasure_chest_right"), FixturePreset.builder()
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.TREASURE, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.RIGHT)))
                .add(1, new Fixtures.LootContainer(DELootTables.BlackCitadel.TREASURE_ALT, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.RIGHT)))
                .build());

        context.register(key("undead_horse"), FixturePreset.builder()
                .entity(1, EntityType.ZOMBIE_HORSE)
                .entity(1, EntityType.SKELETON_HORSE)
                .build());
    }

    private static ResourceKey<FixturePreset> key(String path) {
        return ResourceKey.create(ASRegistries.FIXTURE_PRESET, DungeonsEnhanced.locate(path));
    }
}
