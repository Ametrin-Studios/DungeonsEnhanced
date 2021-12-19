package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.util.ConfigTemplates;
import com.legacy.structure_gel.worldgen.GelPlacementSettings;
import com.legacy.structure_gel.worldgen.structure.GelConfigStructure;
import com.legacy.structure_gel.worldgen.structure.GelStructureStart;
import com.legacy.structure_gel.worldgen.structure.GelTemplateStructurePiece;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class DESimpleStructure extends GelConfigStructure<NoFeatureConfig> {
    protected ResourceLocation MainPiece = null;
    protected ResourceLocation[] Pieces = null;
    protected ChunkGenerator chunkGen;
    public BlockPos Offset = BlockPos.ZERO;

    public DESimpleStructure(String resource, BlockPos offset, ConfigTemplates.StructureConfig config){
        super(NoFeatureConfig.CODEC, config);
        setLakeProof(true);
        MainPiece = DEStructures.locate(resource);
        Offset = offset;

    }
    public DESimpleStructure(String[] resources, BlockPos offset, ConfigTemplates.StructureConfig config){
        super(NoFeatureConfig.CODEC, config);
        setLakeProof(true);
        Pieces = new ResourceLocation[resources.length];
        for(int i = 0; i < resources.length; i++){
            Pieces[i] = DEStructures.locate(resources[i]);
        }
        Offset = offset;
    }
    public DESimpleStructure(ConfigTemplates.StructureConfig config){
        super(NoFeatureConfig.CODEC, config);
        setLakeProof(true);

    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, NoFeatureConfig config) {
        int x = chunkPosX * 16;
        int z = chunkPosZ * 16;
        int y = chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
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
        return super.isFeatureChunk(chunkGen, biomeProvider, seed, sharedSeedRand, chunkPosX, chunkPosZ, biomeIn, chunkPos, config);
    }

    @Override @Nonnull
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return true;}

    public class Start extends GelStructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundsIn, int referenceIn, long seed){
            super(structureIn, chunkX, chunkZ, boundsIn, referenceIn, seed);
        }

        @Override @ParametersAreNonnullByDefault
        public void generatePieces(DynamicRegistries registry, ChunkGenerator chunkGen, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig configIn) {
            int x = chunkX * 16 + Offset.getX();
            int z = chunkZ * 16 + Offset.getZ();
            int y = chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG) + Offset.getY();

            assemble(templateManagerIn, new BlockPos(x, y, z), Rotation.getRandom(this.random), this.pieces, this.random);
            this.calculateBoundingBox();
        }
    }

    public void assemble(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand){
        ResourceLocation Piece = MainPiece;
        if(Piece == null){
            Piece = Pieces[rand.nextInt(Pieces.length)];
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
            BlockPos sizePos = Objects.requireNonNull(templateManager.get(this.name)).getSize();
            BlockPos centerPos = new BlockPos(sizePos.getX() / 2, 0, sizePos.getZ() / 2);
            return new GelPlacementSettings().setMaintainWater(false).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(centerPos);
        }

        @Override
        public void addProcessors(TemplateManager templateManager, PlacementSettings placementSettings) {
            super.addProcessors(templateManager, placementSettings);
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random rnd, MutableBoundingBox bounds) {}
    }

    public Block getBlockAt(int x, int y, int z){
        return chunkGen.getBaseColumn(x, z).getBlockState(new BlockPos(x, y, z)).getBlock();
    }
}