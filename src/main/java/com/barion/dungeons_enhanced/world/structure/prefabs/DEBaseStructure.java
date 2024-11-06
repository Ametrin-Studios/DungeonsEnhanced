package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.util.ConfigTemplates;
import com.legacy.structure_gel.worldgen.GelPlacementSettings;
import com.legacy.structure_gel.worldgen.structure.GelConfigStructure;
import com.legacy.structure_gel.worldgen.structure.GelStructureStart;
import com.legacy.structure_gel.worldgen.structure.GelTemplateStructurePiece;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class DEBaseStructure extends GelConfigStructure<NoFeatureConfig> {
    public DETerrainAnalyzer.Settings terrainAnalyzeSettings;
    public DEStructurePiece[] variants;
    public final DETerrainAnalyzer.GenerationType generationType;
    public int maxWeight;
    protected boolean generateNear00;

    public DEBaseStructure(ConfigTemplates.StructureConfig config, DETerrainAnalyzer.GenerationType generationType, boolean generateNear00, DEStructurePiece[] variants) {
        super(NoFeatureConfig.CODEC, config);
        this.generationType = generationType;
        this.generateNear00 = generateNear00;
        this.variants = variants;
        maxWeight = DEUtil.getMaxWeight(this.variants);
        terrainAnalyzeSettings = DETerrainAnalyzer.DEFAULT_SETTINGS;
        setLakeProof(true);
    }

    @Override @Nonnull
    public IStartFactory<NoFeatureConfig> getStartFactory() { return Start::new; }

    @Override
    public boolean isAllowedNearWorldSpawn() { return generateNear00; }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, NoFeatureConfig config) {
        if(super.isFeatureChunk(chunkGen, biomeProvider, seed, sharedSeedRand, chunkPosX, chunkPosZ, biomeIn, chunkPos, config)){
            return checkLocation(chunkGen, biomeProvider, seed, sharedSeedRand, chunkPosX, chunkPosZ, biomeIn, chunkPos, config);
        }
        return false;
    }

    protected boolean checkLocation(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, NoFeatureConfig config) {
        return DETerrainAnalyzer.isFlatEnough(chunkPos, chunkGen, terrainAnalyzeSettings);
    }

    public abstract void assemble(TemplateManager templateManager, DEStructurePiece variant, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, int variantIndex);

    public class Start extends GelStructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structure, int chunkX, int chunkZ, MutableBoundingBox box, int reference, long seed) {
            super(structure, chunkX, chunkZ, box, reference, seed);
        }

        @Override @ParametersAreNonnullByDefault
        public void generatePieces(DynamicRegistries registry, ChunkGenerator chunkGen, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig featureConfig) {
            int x = chunkX << 4;
            int z = chunkZ << 4;
            int y = 72;

            int minY;
            int maxY;
            switch (generationType) {
                case onGround:
                case onWater:
                    y = chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
                    break;
                case underwater:
                    y = chunkGen.getBaseHeight(x, z, Heightmap.Type.OCEAN_FLOOR_WG);
                    break;
                case inAir:
                    minY = chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG) + 35;
                    maxY = 220;
                    if (minY > maxY) { y = maxY; }
                    else {y = minY + random.nextInt(maxY - minY); }
                    break;
                case underground:
                    minY = 10;
                    maxY = chunkGen.getBaseHeight(x, z, Heightmap.Type.OCEAN_FLOOR_WG) - 20;
                    if (minY >= maxY) { y = maxY; }
                    else {y = minY + random.nextInt(maxY - minY); }
                    break;
            }

            int piece = DEUtil.getRandomPiece(variants, maxWeight, random);

            assemble(templateManager, variants[piece], new BlockPos(x, y, z).offset(variants[piece].Offset), Rotation.getRandom(random), this.pieces, piece);
            calculateBoundingBox();
        }
    }

    public static class Piece extends GelTemplateStructurePiece {
        private final Rotation rotation;
        private final Mirror mirror;

        public Piece(IStructurePieceType structurePieceType, TemplateManager templateManager, ResourceLocation name, BlockPos pos, Rotation rotation) {
            super(structurePieceType, name, 0);
            this.templatePosition = pos;
            this.rotation = rotation;
            this.mirror = Mirror.NONE;
            this.setupTemplate(templateManager);
        }

        public Piece(IStructurePieceType structurePieceType, TemplateManager templateManager, CompoundNBT nbt) {
            super(structurePieceType, nbt);
            this.rotation = Rotation.valueOf(nbt.getString("Rot"));
            this.mirror = Mirror.valueOf(nbt.getString("Mirror"));
            setupTemplate(templateManager);
        }

        @Override
        public PlacementSettings createPlacementSettings(TemplateManager templateManager) {
            BlockPos sizePos = Objects.requireNonNull(templateManager.get(this.name)).getSize();
            BlockPos centerPos = new BlockPos(sizePos.getX() / 2, 0, sizePos.getZ() / 2);
            return new GelPlacementSettings().setMaintainWater(false).setRotation(rotation).setMirror(Mirror.NONE).setRotationPivot(centerPos);
        }

        @Override
        public void addProcessors(TemplateManager templateManager, PlacementSettings placementSettings) {
            super.addProcessors(templateManager, placementSettings);
        }

        @Override
        public boolean postProcess(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator, Random rand, MutableBoundingBox bounds, ChunkPos chunkPos, BlockPos pos) {
            return super.postProcess(world, structureManager, chunkGenerator, rand, bounds, chunkPos, pos);
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random random, MutableBoundingBox box) {}
    }
}