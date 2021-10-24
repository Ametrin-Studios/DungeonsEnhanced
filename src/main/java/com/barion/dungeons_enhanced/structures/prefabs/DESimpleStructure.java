package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.worldgen.GelPlacementSettings;
import com.legacy.structure_gel.worldgen.structure.GelTemplateStructurePiece;
import com.sun.jna.Structure;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

public class DESimpleStructure extends GelConfigStructure<NoneFeatureConfiguration> {
    protected ResourceLocation MainPiece = null;
    protected ResourceLocation[] Pieces = null;
    protected ChunkGenerator chunkGen;
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
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeSource biomeSource, long seed, WorldgenRandom rand, ChunkPos chunkPos, Biome biome, ChunkPos potentialChunkPos, NoneFeatureConfiguration config, LevelHeightAccessor level) {
        int x = chunkPos.x * 16;
        int z = chunkPos.z * 16;
        int y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, level);
        this.chunkGen = chunkGen;
        if(getBlockAt(x, y-1, z) == Blocks.WATER){
            return false;
        }
        if(getBlockAt(x+3, y+3, z) != Blocks.AIR || getBlockAt(x-3, y+3, z) != Blocks.AIR || getBlockAt(x, y+3, z+3) != Blocks.AIR || getBlockAt(x, y+3, z-3) != Blocks.AIR){
            return false;
        }
        if(getBlockAt(x+3, y-3, z) == Blocks.AIR || getBlockAt(x-3, y-3, z) == Blocks.AIR || getBlockAt(x, y-3, z+3) == Blocks.AIR || getBlockAt(x, y-3, z-3) == Blocks.AIR){
            return false;
        }
        return super.isFeatureChunk(chunkGen, biomeSource, seed, rand, chunkPos, biome, potentialChunkPos, config, level);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return Start::new;
    }

    public class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(Structure<NoneFeatureConfiguration> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundsIn, int referenceIn, long seed){
            super(structureIn, chunkX, chunkZ, boundsIn, referenceIn, seed);
        }

        @Override
        public void generatePieces(RegistryAccess registry, ChunkGenerator chunkGen, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoneFeatureConfiguration configIn) {
            int x = chunkX * 16 + Offset.getX();
            int z = chunkZ * 16 + Offset.getZ();
            int y = chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG) + Offset.getY();

            assemble(templateManagerIn, new BlockPos(x, y, z), Rotation.getRandom(this.random), this.pieces, this.random);
            this.calculateBoundingBox();
        }

        @Override
        public void generatePieces(RegistryAccess p_163615_, ChunkGenerator p_163616_, StructureManager p_163617_, ChunkPos p_163618_, Biome p_163619_, NoneFeatureConfiguration p_163620_, LevelHeightAccessor p_163621_) {

        }
    }

    public void assemble(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand){
        ResourceLocation Piece = MainPiece;
        if(Piece == null){
            Piece = Pieces[rand.nextInt(Math.max(0, Pieces.length))];
        }
        structurePieces.add(new DESimpleStructure.Piece(templateManager, Piece, pos, rotation));
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation, int componentType){
            super(DEStructures.RuinedHouse.getPieceType(), location, componentType);
            this.templatePosition = pos;
            this.rotation = rotation;
            this.setupTemplate(templateManager);
        }
        public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation) {
            this(templateManager, location, pos, rotation, 0);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbtCompound) {
            super(DEStructures.RuinedHouse.getPieceType(), nbtCompound);
            this.setupTemplate(templateManager);
        }

        @Override
        public PlacementSettings createPlacementSettings(TemplateManager templateManager) {
            BlockPos sizePos = templateManager.get(this.name).getSize();
            BlockPos centerPos = new BlockPos(sizePos.getX() / 2, 0, sizePos.getZ() / 2);
            return new GelPlacementSettings().setMaintainWater(false).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(centerPos);
        }

        @Override
        public void addProcessors(TemplateManager templateManager, PlacementSettings placementSettings) {
            super.addProcessors(templateManager, placementSettings);
        }

        @Override
        protected void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random rnd, MutableBoundingBox bounds) {

        }
    }

    public Block getBlockAt(int x, int y, int z){
        return chunkGen.getBaseColumn(x, z).getBlockState(new BlockPos(x, y, z)).getBlock();
    }
    protected static BlockPos Offset(int x, int y, int z){
        return new BlockPos(x, y, z);
    }
}