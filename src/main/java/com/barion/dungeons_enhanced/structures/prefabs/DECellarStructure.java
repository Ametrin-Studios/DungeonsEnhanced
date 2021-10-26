package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import com.legacy.structure_gel.worldgen.GelPlacementSettings;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

public class DECellarStructure extends GelConfigStructure<NoneFeatureConfiguration> {
    protected ResourceLocation MainPiece;
    protected ResourceLocation Cellar;
    public BlockPos Offset;
    protected int CellarOffset;
    public DECellarStructure(String resourceTop, String resourceBottom, BlockPos offset, int cellarOffset, StructureConfig config){
        this(config);
        MainPiece = DEStructures.locate(resourceTop);
        Cellar = DEStructures.locate(resourceBottom);
        Offset = offset;
        CellarOffset = cellarOffset;
    }
    public DECellarStructure(String resource, BlockPos offset, StructureConfig config){
        this(config);
        MainPiece = DEStructures.locate(resource);
        Cellar = null;
        Offset = offset;
    }
    public DECellarStructure(StructureConfig config){
        super(NoneFeatureConfiguration.CODEC, config);
        setLakeProof(true);
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {
        return true;
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeSource biomeSource, long seed, WorldgenRandom rand, ChunkPos chunkPos, Biome biome, ChunkPos potentialChunkPos, NoneFeatureConfiguration config, LevelHeightAccessor heightAccessor) {
        int x = chunkPos.x * 16;
        int z = chunkPos.z * 16;
        int y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
        Block block = chunkGen.getBaseColumn(x, z, heightAccessor).getBlockState(new BlockPos(x, y - 1, z)).getBlock();
        if(block == Blocks.WATER){
            return false;
        }
        return super.isFeatureChunk(chunkGen, biomeSource, seed, rand, chunkPos, biome, potentialChunkPos, config, heightAccessor);
    }



    @Override
    public IStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return Start::new;
    }

    public class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structureFeature, ChunkPos pos, int reference, long seed) {
            super(structureFeature, pos, reference, seed);
        }

        @Override
        public void generatePieces(RegistryAccess registry, ChunkGenerator chunkGen, StructureManager structureManager, ChunkPos chunkPos, Biome biome, NoneFeatureConfiguration config, LevelHeightAccessor heightAccessor) {
            int x = chunkPos.x * 16 + Offset.getX();
            int z = chunkPos.z * 16 + Offset.getZ();
            int y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor) + Offset.getY();
            Rotation rotation = Rotation.getRandom(this.random);
            assemble(structureManager, new BlockPos(x, y, z), rotation, this.pieces);
            this.createBoundingBox();
            if (Cellar != null) {
                assembleCellar(structureManager, new BlockPos(x, y + CellarOffset, z), rotation, this.pieces);
            }
        }
    }

    public void assemble(StructureManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces){
        structurePieces.add(new DECellarStructure.Piece(templateManager, MainPiece, pos, rotation));
    }
    public void assembleCellar(StructureManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces){
        structurePieces.add(new DECellarStructure.Piece(templateManager, Cellar, pos, rotation));
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType) {
            super(DEStructures.DruidCircle.getPieceType(), componentType, structureManager, templateName, pos);
            templatePosition = pos;
            this.rotation = rotation;
            setupPlaceSettings(structureManager);
        }

        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            this(structureManager, templateName, pos, rotation, 0);
        }

        @Override
        public PlacementSettings createPlacementSettings(StructureManager templateManager) {
            BlockPos sizePos = templateManager.get(this.name).getSize();
            BlockPos centerPos = new BlockPos(sizePos.getX() / 2, 0, sizePos.getZ() / 2);
            return new GelPlacementSettings().setMaintainWater(false).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(centerPos);
        }

        @Override
        public void addProcessors(StructureManager templateManager, PlacementSettings placementSettings) {
            super.addProcessors(templateManager, placementSettings);
        }

        @Override
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor accessor, Random rnd, BoundingBox p_73687_) {

        }
    }
    protected static BlockPos Offset(int x, int y, int z){
        return new BlockPos(x, y, z);
    }
}
