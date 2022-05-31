package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
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
    protected boolean generateNear00;
    protected DETerrainAnalyzer.Settings terrainAnalyzeSettings;


    public DECellarStructure(ConfigTemplates.StructureConfig config, DETerrainAnalyzer.Settings terrainAnalyzeSettings, boolean generateNear00){
        super(VillageConfig.CODEC, config, 0, true, true);
        this.generateNear00 = generateNear00;
        this.terrainAnalyzeSettings = terrainAnalyzeSettings;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, VillageConfig config) {
        if(super.isFeatureChunk(chunkGen, biomeProvider, seed, sharedSeedRand, chunkPosX, chunkPosZ, biomeIn, chunkPos, config)){
            return DETerrainAnalyzer.isFlatEnough(chunkPos, chunkGen, terrainAnalyzeSettings);
        }
        return false;
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return generateNear00;}

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos pos, int groundLevelDelta, Rotation rotation, MutableBoundingBox box) {
            super(templateManager, jigsawPiece, pos, groundLevelDelta, rotation, box);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {super(templateManager, nbt);}

        @Override
        public IStructurePieceType getType() {return DEStructures.Castle.getPieceType();}

        @Override
        public void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random random, MutableBoundingBox box) {}
    }

    public static void init(){
        CastlePool.init();
        DruidCirclePool.init();
    }

    public static class CastlePool{
        public static final JigsawPattern Root;
        public static void init() {}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID,"castle/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            Root = registry.register("root", poolBuilder.clone().names("top1", "top2").build());

            registry.register("bottom1", poolBuilder.clone().names("bottom1").build());
            registry.register("bottom2", poolBuilder.clone().names("bottom2").build());
        }
    }

    public static class DruidCirclePool{
        public static final JigsawPattern Root;
        public static void init(){}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID,"druid_circle/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            Root = registry.register("root", poolBuilder.clone().names("top_big", "small").build());

            registry.register("bottom_big", poolBuilder.clone().names("bottom_big").build());
        }
    }
}