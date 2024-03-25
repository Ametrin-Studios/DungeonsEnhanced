package com.barion.dungeons_enhanced.world.gen;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;

public final class GenerationContext {
    public final ChunkGenerator chunkGenerator;
    public final ChunkPos chunkPos;
    public final Biome biome;
    public final SharedSeedRandom random;

    public GenerationContext(ChunkGenerator chunkGenerator, ChunkPos chunkPos, Biome biome, SharedSeedRandom random) {
        this.chunkGenerator = chunkGenerator;
        this.chunkPos = chunkPos;
        this.biome = biome;
        this.random = random;
    }
}
