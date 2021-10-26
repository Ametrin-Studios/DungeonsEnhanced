package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.*;
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
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Random;

public class DESimpleStructure extends GelConfigStructure<NoneFeatureConfiguration> {
    protected ResourceLocation MainPiece = null;
    protected ResourceLocation[] Pieces = null;
    protected ChunkGenerator chunkGen;
    protected LevelHeightAccessor heightAccessor;
    public BlockPos Offset = BlockPos.ZERO;

    public DESimpleStructure(String resource, BlockPos offset, StructureConfig config){
        super(NoneFeatureConfiguration.CODEC, config);
        setLakeProof(true);
        MainPiece = DEStructures.locate(resource);
        Offset = offset;
    }
    public DESimpleStructure(String[] resources, BlockPos offset, StructureConfig config){
        super(NoneFeatureConfiguration.CODEC, config);
        setLakeProof(true);
        Pieces = new ResourceLocation[resources.length];
        for(int i = 0; i < resources.length; i++){
            Pieces[i] = DEStructures.locate(resources[i]);
        }
        Offset = offset;
    }
    public DESimpleStructure(StructureConfig config){
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
        this.chunkGen = chunkGen;
        this.heightAccessor = heightAccessor;
        if(getBlockAt(x, y-1, z) == Blocks.WATER){
            return false;
        }
        if(getBlockAt(x+3, y+3, z) != Blocks.AIR || getBlockAt(x-3, y+3, z) != Blocks.AIR || getBlockAt(x, y+3, z+3) != Blocks.AIR || getBlockAt(x, y+3, z-3) != Blocks.AIR){
            return false;
        }
        if(getBlockAt(x+3, y-3, z) == Blocks.AIR || getBlockAt(x-3, y-3, z) == Blocks.AIR || getBlockAt(x, y-3, z+3) == Blocks.AIR || getBlockAt(x, y-3, z-3) == Blocks.AIR){
            return false;
        }
        return super.isFeatureChunk(chunkGen, biomeSource, seed, rand, chunkPos, biome, potentialChunkPos, config, heightAccessor);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return Start::new;
    }

    public class Start extends StructureStart<NoneFeatureConfiguration> {


        public Start(StructureFeature<NoneFeatureConfiguration> structureFeature, ChunkPos chunkPos, int reference, long seed) {
            super(structureFeature, chunkPos, reference, seed);
        }

        @Override @ParametersAreNonnullByDefault
        public void generatePieces(RegistryAccess registry, ChunkGenerator chunkGen, StructureManager structureManager, ChunkPos chunkPos, Biome biome, NoneFeatureConfiguration config, LevelHeightAccessor heightAccessor) {
            int x = chunkPos.x * 16 + Offset.getX();
            int z = chunkPos.z * 16 + Offset.getZ();
            int y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor) + Offset.getY();

            assemble(structureManager, new BlockPos(x, y, z), Rotation.getRandom(this.random), this.pieces, this.random);
            this.createBoundingBox();
        }
    }

    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand){
        ResourceLocation Piece = MainPiece;
        if(Piece == null){
            int i = rand.nextInt(Pieces.length);
            Piece = Pieces[i];
            DungeonsEnhanced.LOGGER.info(i);
        }
        structurePieces.add(new DESimpleStructure.Piece(structureManager, Piece, pos, rotation));
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType) {
            super(DEStructures.RuinedHouse.getPieceType(), componentType, structureManager, templateName, pos);
            templatePosition = pos;
            this.rotation = rotation;
            setupPlaceSettings(structureManager);
        }

        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            this(structureManager, templateName, pos, rotation, 0);
        }

        public Piece(ServerLevel serverLevel, CompoundTag compoundTag) {
            super(DEStructures.RuinedHouse.getPieceType(), compoundTag, serverLevel);
        }

        @Override
        public boolean postProcess(WorldGenLevel level, StructureFeatureManager structureManager, ChunkGenerator chunkGen, Random rand, BoundingBox bounds, ChunkPos chunkPos, BlockPos pos) {
            return super.postProcess(level, structureManager, chunkGen, rand, bounds, chunkPos, pos);
        }

        @Override
        protected StructurePlaceSettings getPlaceSettings(StructureManager structureManager) {
            Vec3i size = structureManager.get(this.makeTemplateLocation()).get().getSize();
            return new StructurePlaceSettings().setKeepLiquids(false).setRotationPivot(new BlockPos(size.getX()/2, 0, size.getZ()/2));
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor world, Random rnd, BoundingBox box) {

        }
    }

    public Block getBlockAt(int x, int y, int z){
        return chunkGen.getBaseColumn(x, z, heightAccessor).getBlockState(new BlockPos(x, y, z)).getBlock();
    }
    protected static BlockPos Offset(int x, int y, int z){
        return new BlockPos(x, y, z);
    }
}