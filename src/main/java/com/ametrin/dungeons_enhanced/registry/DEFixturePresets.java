package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.structures.fixture.*;
import com.ametrin.structures.registry.ASRegistries;
import com.ametrin.structures.spawner.EntityDataBuilder;
import com.barion.block_variants.BlockVariants;
import com.barion.block_variants.registry.BVOtherBlocks;
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
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

public final class DEFixturePresets {
    public static final String VIKTOR_TRANSLATION_KEY = "entity.dungeons_enhanced.catacombs.viktor";
    public static final String HENDRIK_VAN_DER_DECKEN_TRANSLATION_KEY = "entity.dungeons_enhanced.flying_dutchman.captain";
    public static final String ZOMBIE_KING_TRANSLATION_KEY = "entity.dungeons_enhanced.monster_maze.zombie_king";

    public static void bootstrap(BootstrapContext<FixturePreset> context) {
        var biomes = context.lookup(Registries.BIOME);
        context.register(key("catacombs/viktor"), FixturePreset.builder()
                .add(1, EntityFixture.of(EntityDataBuilder.of(EntityType.ZOMBIE_VILLAGER)
                        .maxHealth(40)
                        .deathLootTable(Blocks.LIGHTNING_ROD.getLootTable().orElseThrow())
                        .name(Component.translatable(VIKTOR_TRANSLATION_KEY)))
                ).build());

        context.register(key("hendrik_van_der_decken"), FixturePreset.builder()
                .add(1, EntityFixture.of(EntityDataBuilder.of(EntityType.SKELETON)
                                .leftHanded()
                                .maxHealth(40)
                                .name(Component.translatable(HENDRIK_VAN_DER_DECKEN_TRANSLATION_KEY))
                                .nameVisible())
                        .withEquipment(DELootTables.EQUIPMENT_HENDRICK_VAN_DER_DECKEN)
                ).build());

        context.register(key("monster_maze/zombie_king"), FixturePreset.builder()
                .add(1, EntityFixture.of(EntityDataBuilder.of(EntityType.ZOMBIE)
                                .maxHealth(40)
                                .name(Component.translatable(ZOMBIE_KING_TRANSLATION_KEY)))
                        .withEquipment(DELootTables.MonsterMaze.EQUIPMENT_ZOMBIE_KING)
                ).build());

        context.register(key("water_cauldron"), FixturePreset.builder()
                .add(1, new BlockStateFixture(Blocks.CAULDRON.defaultBlockState()))
                .add(1, new BlockStateFixture(Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 1)))
                .add(1, new BlockStateFixture(Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 2)))
                .add(1, new BlockStateFixture(Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, 3)))
                .build());

        context.register(key("composter"), FixturePreset.builder()
                .add(1, new BlockStateFixture(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 0)))
                .add(1, new BlockStateFixture(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 1)))
                .add(1, new BlockStateFixture(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 2)))
                .add(1, new BlockStateFixture(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 3)))
                .add(1, new BlockStateFixture(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 4)))
                .add(1, new BlockStateFixture(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 5)))
                .add(1, new BlockStateFixture(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 6)))
                .add(1, new BlockStateFixture(Blocks.COMPOSTER.defaultBlockState().setValue(ComposterBlock.LEVEL, 7)))
                .build());

        context.register(key("anvil"), FixturePreset.builder()
                .add(1, new BlockStateFixture(Blocks.ANVIL.defaultBlockState()))
                .add(1, new BlockStateFixture(Blocks.CHIPPED_ANVIL.defaultBlockState()))
                .add(1, new BlockStateFixture(Blocks.DAMAGED_ANVIL.defaultBlockState()))
                .build());

        context.register(key("biome_based_zombie_spawner"), FixturePreset.builder()
                .add(1000, SpawnerFixture.of(EntityType.HUSK), new FixtureConditions.InBiome(biomes.getOrThrow(Tags.Biomes.IS_SANDY)))
                .add(1, SpawnerFixture.of(EntityType.ZOMBIE))
                .build());

        context.register(key("biome_based_skeleton_spawner"), FixturePreset.builder()
                .add(1000, SpawnerFixture.of(EntityType.BOGGED), new FixtureConditions.InBiome(biomes.getOrThrow(Tags.Biomes.IS_SWAMP)))
                .add(1000, SpawnerFixture.of(EntityType.STRAY), new FixtureConditions.InBiome(biomes.getOrThrow(Tags.Biomes.IS_COLD_OVERWORLD)))
                .add(1, SpawnerFixture.of(EntityType.SKELETON))
                .build());


        context.register(key("black_citadel/normal_chest"), FixturePreset.builder()
                .add(1, LootContainerFixture.chest(DELootTables.BlackCitadel.NORMAL))
                .add(1, LootContainerFixture.chest(DELootTables.BlackCitadel.NORMAL_ALT))
                .build());

        context.register(key("black_citadel/normal_chest_left"), FixturePreset.builder()
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.NORMAL, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.LEFT)))
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.NORMAL_ALT, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.LEFT)))
                .build());

        context.register(key("black_citadel/normal_chest_right"), FixturePreset.builder()
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.NORMAL, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.RIGHT)))
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.NORMAL_ALT, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.RIGHT)))
                .build());

        context.register(key("black_citadel/normal_barrel"), FixturePreset.builder()
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.NORMAL, Blocks.BARREL.defaultBlockState()))
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.NORMAL_ALT, Blocks.BARREL.defaultBlockState()))
                .build());

        context.register(key("black_citadel/treasure_chest"), FixturePreset.builder()
                .add(1, LootContainerFixture.chest(DELootTables.BlackCitadel.TREASURE))
                .add(1, LootContainerFixture.chest(DELootTables.BlackCitadel.TREASURE_ALT))
                .build());

        context.register(key("black_citadel/treasure_chest_left"), FixturePreset.builder()
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.TREASURE, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.LEFT)))
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.TREASURE_ALT, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.LEFT)))
                .build());

        context.register(key("black_citadel/treasure_chest_right"), FixturePreset.builder()
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.TREASURE, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.RIGHT)))
                .add(1, new LootContainerFixture(DELootTables.BlackCitadel.TREASURE_ALT, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.TYPE, ChestType.RIGHT)))
                .build());

        context.register(key("undead_horse"), FixturePreset.builder()
                .entity(1, EntityType.ZOMBIE_HORSE)
                .entity(1, EntityType.SKELETON_HORSE)
                .build());

        context.register(key("dark_prismarine_wall"), FixturePreset.builder()
                        .add(1000, new BlockStateFixture(BVOtherBlocks.DARK_PRISMARINE_WALL.get().defaultBlockState()), new FixtureConditions.NeoForge(new ModLoadedCondition(BlockVariants.MOD_ID)))
                        .add(1, new BlockStateFixture(Blocks.PRISMARINE_WALL.defaultBlockState()))
                .build());
    }

    private static ResourceKey<FixturePreset> key(String path) {
        return ResourceKey.create(ASRegistries.FIXTURE_PRESET, DungeonsEnhanced.locate(path));
    }
}
