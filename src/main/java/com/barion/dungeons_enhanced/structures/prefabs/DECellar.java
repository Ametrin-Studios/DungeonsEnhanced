package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import com.legacy.structure_gel.util.ConfigTemplates;
import com.legacy.structure_gel.worldgen.GelPlacementSettings;
import com.legacy.structure_gel.worldgen.structure.GelStructureStart;
import com.legacy.structure_gel.worldgen.structure.GelTemplateStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
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
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.List;
import java.util.Random;

public class DECellar extends GelConfigStructure<NoneFeatureConfiguration> {
    protected ResourceLocation Piece;
    public BlockPos Offset;
    protected DECellarStructure Parent;

    public <S extends DECellarStructure> DECellar(String resource, BlockPos offset, StructureRegistrar2<NoFeatureConfig, S> parent, ConfigTemplates.StructureConfig config) {
        super(NoneFeatureConfiguration.CODEC, config);
        Piece = DEStructures.locate(resource);
        Parent = parent.getStructure();
        Offset = Parent.Offset.offset(offset);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, NoFeatureConfig config) {
        return Parent.isFeatureChunk(chunkGen, biomeProvider, seed, sharedSeedRand, chunkPosX, chunkPosZ, biomeIn, chunkPos, config);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }

    public class Start extends GelStructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundsIn, int referenceIn, long seed){
            super(structureIn, chunkX, chunkZ, boundsIn, referenceIn, seed);
        }

        @Override
        public void generatePieces(DynamicRegistries registry, ChunkGenerator chunkGen, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig configIn) {
            int x = chunkX * 16 + Offset.getX();
            int z = chunkZ * 16 + Offset.getZ();
            int y = chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG) + Offset.getY();
            Rotation rotation = Rotation.getRandom(this.random);
            assemble(templateManagerIn, new BlockPos(x, y, z), rotation, this.pieces);
            this.calculateBoundingBox();
        }
    }

    public void assemble(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces){
        structurePieces.add(new DECellarStructure.Piece(templateManager, Piece, pos, rotation));
    }

    @Override
    public int getSeed() {
        return Parent.getSeed();
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation, int componentType){
            super(DEStructures.CastleB.getPieceType(), location, componentType);
            this.templatePosition = pos;
            this.rotation = rotation;
            this.setupTemplate(templateManager);
        }
        public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation) {
            this(templateManager, location, pos, rotation, 0);
        }
        public Piece(TemplateManager templateManager, CompoundNBT nbtCompound) {
            super(DEStructures.CastleB.getPieceType(), nbtCompound);
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
}