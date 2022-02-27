package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DECellarPiece;
import com.legacy.structure_gel.util.ConfigTemplates;
import com.legacy.structure_gel.worldgen.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.worldgen.jigsaw.GelConfigJigsawStructure;
import com.legacy.structure_gel.worldgen.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.worldgen.jigsaw.JigsawRegistryHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class DECellarStructure extends GelConfigJigsawStructure {
    protected DECellarPiece[] Variants;
    protected boolean generateNear00;
    protected String prefix;
    protected DETerrainAnalyzer.TerrainCheckSettings terrainCheckSettings;
    protected int maxWeight;
    protected Pool pool;

    public DECellarStructure(ConfigTemplates.StructureConfig config, String prefix, DETerrainAnalyzer.TerrainCheckSettings terrainCheckSettings, boolean generateNear00, DECellarPiece... variants){
        super(VillageConfig.CODEC, config, 0, true, true);
        this.generateNear00 = generateNear00;
        this.Variants = variants;
        this.prefix = prefix;
        this.maxWeight = DEUtil.getMaxWeight(Variants);
        this.terrainCheckSettings = terrainCheckSettings;
        this.pool = new Pool();
        pool.init();
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, VillageConfig config) {
        boolean canGenerate = super.isFeatureChunk(chunkGen, biomeProvider, seed, sharedSeedRand, chunkPosX, chunkPosZ, biomeIn, chunkPos, config);
        if(!canGenerate) {return false;}

        return DETerrainAnalyzer.isPositionSuitable(chunkPos, chunkGen, terrainCheckSettings);
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return generateNear00;}

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos pos, int groundLevelDelta, Rotation rotation, MutableBoundingBox box) {
            super(templateManager, jigsawPiece, pos, groundLevelDelta, rotation, box);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(templateManager, nbt);
        }

        @Override
        public IStructurePieceType getType() {return DEStructures.Castle.getPieceType();}

        @Override
        public void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random random, MutableBoundingBox box) {}
    }

    public JigsawPattern getRootPool() {return pool.Root;}

    protected class Pool{
        protected JigsawPattern Root;
        public void init() {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.Mod_ID, prefix +"/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            String[] topParts = new String[Variants.length];

            for(int i = 0; i < Variants.length; i++){
                topParts[i] = Variants[i].Resource.getPath();
            }


            Root = registry.register("root", poolBuilder.clone().names(topParts).build());

            for(DECellarPiece piece : Variants) {
                if(piece.Cellar != null) {
                    registry.register(piece.Cellar, poolBuilder.clone().names(piece.Cellar).build());
                }
            }
        }
    }
}