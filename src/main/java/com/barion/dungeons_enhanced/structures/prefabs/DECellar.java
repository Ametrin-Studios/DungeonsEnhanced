package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Mirror;
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

public class DECellar extends GelConfigStructure<NoneFeatureConfiguration> {
    protected ResourceLocation Piece;
    public BlockPos Offset;
    protected DECellarStructure Parent;

    public <S extends DECellarStructure> DECellar(String resource, BlockPos offset, StructureRegistrar<NoneFeatureConfiguration, S> parent) {
        super(NoneFeatureConfiguration.CODEC, parent.getStructure().getConfig());
        Piece = DEStructures.locate(resource);
        Parent = parent.getStructure();
        Offset = Parent.Offset.offset(offset);
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeSource biomeSource, long seed, WorldgenRandom rand, ChunkPos chunkPos, Biome biome, ChunkPos potentialChunkPos, NoneFeatureConfiguration config, LevelHeightAccessor level) {
        return Parent.isFeatureChunk(chunkGen, biomeSource, seed, rand, chunkPos, biome, potentialChunkPos, config, level);
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {
        return true;
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
            Rotation rotation = Rotation.getRandom(this.random);
            assemble(structureManager, new BlockPos(x, y, z), rotation, this.pieces);
            this.createBoundingBox();
        }
    }

    public void assemble(StructureManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces){
        structurePieces.add(new DECellarStructure.Piece(templateManager, Piece, pos, rotation));
    }

    @Override
    public int getSeed() {
        return Parent.getSeed();
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType) {
            super(DEStructures.CastleB.getPieceType(), componentType, structureManager, templateName, pos);
            templatePosition = pos;
            this.rotation = rotation;
            setupPlaceSettings(structureManager);
        }

        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            this(structureManager, templateName, pos, rotation, 0);
        }

        @Override
        protected StructurePlaceSettings getPlaceSettings(StructureManager structureManager) {
            Vec3i sizePos = structureManager.get(this.makeTemplateLocation()).get().getSize();
            BlockPos centerPos = new BlockPos(sizePos.getX() / 2, 0, sizePos.getZ() / 2);
            return new StructurePlaceSettings().setKeepLiquids(false).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(centerPos);
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String p_73683_, BlockPos p_73684_, ServerLevelAccessor p_73685_, Random p_73686_, BoundingBox p_73687_) {

        }
    }
}