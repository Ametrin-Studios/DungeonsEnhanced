package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.world.gen.GenerationContext;
import com.barion.dungeons_enhanced.world.structure.builder.DEPlacement;
import com.barion.dungeons_enhanced.world.structure.builder.DEPlacementFilter;
import com.barion.dungeons_enhanced.world.structure.builder.IDEPieceFactory;
import com.google.common.collect.ImmutableList;
import com.legacy.structure_gel.util.ConfigTemplates;
import com.legacy.structure_gel.worldgen.structure.GelConfigStructure;
import com.legacy.structure_gel.worldgen.structure.GelStructureStart;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;

public class DEModularStructure extends GelConfigStructure<NoFeatureConfig> {
    private final IDEPieceFactory _pieceFactory;
    private final DEPlacement _placementProvider;
    private final ImmutableList<DEPlacementFilter> _filters;
    private final boolean _allowedNearSpawn;

    public DEModularStructure(ConfigTemplates.StructureConfig config, IDEPieceFactory pieceFactory, DEPlacement placement, ImmutableList<DEPlacementFilter> filters, boolean allowedNearSpawn) {
        super(NoFeatureConfig.CODEC, config);
        _pieceFactory = pieceFactory;
        _placementProvider = placement;
        _filters = filters;
        _allowedNearSpawn = allowedNearSpawn;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator generator, BiomeProvider biomeProvider, long seed, SharedSeedRandom random, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, NoFeatureConfig config) {
        if(super.isFeatureChunk(generator, biomeProvider, seed, random, chunkPosX, chunkPosZ, biomeIn, chunkPos, config)){
            for (DEPlacementFilter filter : _filters){
                if(filter.cannotGenerate(chunkPos, new GenerationContext(generator, chunkPos, biomeIn, random))){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return _allowedNearSpawn;}

    @Override @Nonnull
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends GelStructureStart<NoFeatureConfig>{

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundsIn, int referenceIn, long seed) {
            super(structureIn, chunkX, chunkZ, boundsIn, referenceIn, seed);
        }

        @Override @ParametersAreNonnullByDefault
        public void generatePieces(DynamicRegistries dynamicRegistries, ChunkGenerator generator, TemplateManager templateManager, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig noFeatureConfig) {
            Optional<StructurePiece> piece = _placementProvider.getPiece(new GenerationContext(generator, new ChunkPos(chunkX, chunkZ), biomeIn, random), _pieceFactory, templateManager);
            piece.ifPresent(pieces::add);
            calculateBoundingBox();
        }
    }
}
