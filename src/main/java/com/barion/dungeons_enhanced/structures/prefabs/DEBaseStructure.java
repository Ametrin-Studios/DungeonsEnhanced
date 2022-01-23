package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructureConfig;
import com.barion.dungeons_enhanced.DEUtil;
import com.legacy.structure_gel.worldgen.structure.GelConfigStructure;
import com.legacy.structure_gel.worldgen.structure.GelStructureStart;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.ParametersAreNonnullByDefault;

public abstract class DEBaseStructure extends GelConfigStructure<NoFeatureConfig> {
    protected ChunkGenerator chunkGen;

    protected final DEPiece[] Variants;
    protected final DEUtil.GenerationType generationType;
    protected final int maxWeight;
    protected final boolean generateNearSpawn;

    public DEBaseStructure(DEStructureConfig config, DEUtil.GenerationType generationType, BlockPos offset, boolean generateNearSpawn, DEPiece... variants){
        this(config, generationType, generateNearSpawn, variants);
        for(DEPiece piece : Variants) {
           piece.offset = offset;
        }
    }

    public DEBaseStructure(DEStructureConfig config, DEUtil.GenerationType generationType, boolean generateNearSpawn, DEPiece... variants){
        super(NoFeatureConfig.CODEC, config);
        this.generationType = generationType;
        this.generateNearSpawn = generateNearSpawn;
        this.Variants = variants;
        this.maxWeight = getMaxWeight();
    }

    protected int getMaxWeight() {
        if(Variants.length == 1) {return 1;}
        int i = 0;
        for(DEPiece piece : Variants){
            i += piece.weight;
        }
        return i;
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return generateNearSpawn;}

    public class Start extends GelStructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundsIn, int referenceIn, long seed) {
            super(structureIn, chunkX, chunkZ, boundsIn, referenceIn, seed);
        }

        @Override @ParametersAreNonnullByDefault
        public void generatePieces(DynamicRegistries registry, ChunkGenerator chunkGen, TemplateManager templateManager, int chunkX, int chunkZ, Biome biome, NoFeatureConfig config) {

        }
    }
}