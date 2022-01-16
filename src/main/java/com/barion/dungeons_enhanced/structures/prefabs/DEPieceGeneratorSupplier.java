package com.barion.dungeons_enhanced.structures.prefabs;

import net.minecraft.core.QuartPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Optional;
import java.util.function.Predicate;

@FunctionalInterface
public interface DEPieceGeneratorSupplier<C extends FeatureConfiguration> {
    Optional<DEPieceGenerator<C>> createGenerator(Context<C> context);

    static <C extends FeatureConfiguration> DEPieceGeneratorSupplier<C> simple(Predicate<Context<C>> contextPredicate, DEPieceGenerator<C> pieceGenerator) {
        Optional<DEPieceGenerator<C>> optional = Optional.of(pieceGenerator);
        return (t) -> contextPredicate.test(t) ? optional : Optional.empty();
    }

    static <C extends FeatureConfiguration> Predicate<Context<C>> checkForBiomeOnTop(Heightmap.Types heightmap) {
        return (context) -> context.validBiomeOnTop(heightmap);
    }

    public record Context<C extends FeatureConfiguration>(ChunkGenerator chunkGenerator, BiomeSource biomeSource, long seed, ChunkPos chunkPos, C config, LevelHeightAccessor heightAccessor, Predicate<Biome> validBiome, StructureManager structureManager, RegistryAccess registryAccess) {
        public boolean validBiomeOnTop(Heightmap.Types heightmap) {
            int x = this.chunkPos.getMiddleBlockX();
            int z = this.chunkPos.getMiddleBlockZ();
            int y = this.chunkGenerator.getFirstOccupiedHeight(x, z, heightmap, this.heightAccessor);
            Biome biome = this.chunkGenerator.getNoiseBiome(QuartPos.fromBlock(x), QuartPos.fromBlock(y), QuartPos.fromBlock(z));
            return this.validBiome.test(biome);
        }

        public int[] getCornerHeights(int x, int sizeX, int z, int sizeZ) {
            return new int[]{this.chunkGenerator.getFirstOccupiedHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, this.heightAccessor), this.chunkGenerator.getFirstOccupiedHeight(x, z + sizeZ, Heightmap.Types.WORLD_SURFACE_WG, this.heightAccessor), this.chunkGenerator.getFirstOccupiedHeight(x + sizeX, z, Heightmap.Types.WORLD_SURFACE_WG, this.heightAccessor), this.chunkGenerator.getFirstOccupiedHeight(x + sizeX, z + sizeZ, Heightmap.Types.WORLD_SURFACE_WG, this.heightAccessor)};
        }

        public int getLowestY(int sizeX, int sizeZ) {
            int x = this.chunkPos.getMinBlockX();
            int z = this.chunkPos.getMinBlockZ();
            int[] cornerHeights = this.getCornerHeights(x, sizeX, z, sizeZ);
            return Math.min(Math.min(cornerHeights[0], cornerHeights[1]), Math.min(cornerHeights[2], cornerHeights[3]));
        }
    }
}