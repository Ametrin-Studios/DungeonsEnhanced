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

public class DECellarStructure extends GelConfigStructure<NoFeatureConfig> {
    protected ResourceLocation MainPiece;
    protected ResourceLocation Cellar;
    public BlockPos Offset;
    protected int CellarOffset;
    public DECellarStructure(String resourceTop, String resourceBottom, BlockPos offset, int cellarOffset, ConfigTemplates.StructureConfig config){
        super(NoFeatureConfig.CODEC, config);
        setLakeProof(true);
        MainPiece = DEStructures.locate(resourceTop);
        Cellar = DEStructures.locate(resourceBottom);
        Offset = offset;
        CellarOffset = cellarOffset;
    }
    public DECellarStructure(String resource, BlockPos offset, ConfigTemplates.StructureConfig config){
        super(NoFeatureConfig.CODEC, config);
        setLakeProof(true);
        MainPiece = DEStructures.locate(resource);
        Cellar = null;
        Offset = offset;
    }
    public DECellarStructure(ConfigTemplates.StructureConfig config){
        super(NoFeatureConfig.CODEC, config);
        setLakeProof(true);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, NoFeatureConfig config) {
        int x = chunkPosX * 16;
        int z = chunkPosZ * 16;
        int y = chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
        Block block = chunkGen.getBaseColumn(x, z).getBlockState(new BlockPos(x, y - 1, z)).getBlock();
        if(block == Blocks.WATER){
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
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundsIn, int referenceIn, long seed) {
            super(structureIn, chunkX, chunkZ, boundsIn, referenceIn, seed);
        }

        @Override @ParametersAreNonnullByDefault
        public void generatePieces(DynamicRegistries registry, ChunkGenerator chunkGen, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig configIn) {
            int x = chunkX * 16 + Offset.getX();
            int z = chunkZ * 16 + Offset.getZ();
            int y = chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG) + Offset.getY();
            Rotation rotation = Rotation.getRandom(this.random);
            assemble(templateManagerIn, new BlockPos(x, y, z), rotation, this.pieces);
            this.calculateBoundingBox();
            if (Cellar != null) {
                assembleCellar(templateManagerIn, new BlockPos(x, y + CellarOffset, z), rotation, this.pieces);
            }
        }
    }

    public void assemble(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces){
        structurePieces.add(new DECellarStructure.Piece(templateManager, MainPiece, pos, rotation));
    }
    public void assembleCellar(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces){
        structurePieces.add(new DECellarStructure.Piece(templateManager, Cellar, pos, rotation));
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation, int componentType){
            super(DEStructures.DruidCircle.getPieceType(), location, componentType);
            this.templatePosition = pos;
            this.rotation = rotation;
            this.setupTemplate(templateManager);
        }
        public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation) {
            this(templateManager, location, pos, rotation, 0);
        }
        public Piece(TemplateManager templateManager, CompoundNBT nbtCompound) {
            super(DEStructures.DruidCircle.getPieceType(), nbtCompound);
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
        protected void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random rnd, MutableBoundingBox bounds){}
    }
    protected static BlockPos Offset(int x, int y, int z){
        return new BlockPos(x, y, z);
    }
}
