package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.config.StructureConfig;
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
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
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

public class DEUndergroundStructure extends GelConfigStructure<NoneFeatureConfiguration> {
    public ResourceLocation MainPiece = null;
    public ResourceLocation[] Pieces = null;
    public BlockPos Offset = BlockPos.ZERO;

    public DEUndergroundStructure(String resource, BlockPos offset, StructureConfig config){
        this(config);
        MainPiece = DEStructures.locate(resource);
        Offset = offset;
    }
    public DEUndergroundStructure(StructureConfig config, BlockPos offset, String... resources){
        this(config, resources);
        Offset = offset;
    }
    public DEUndergroundStructure(StructureConfig config, String... resources){
        this(config);
        Pieces = new ResourceLocation[resources.length];
        for(int i = 0; i < resources.length; i++){
            Pieces[i] = DEStructures.locate(resources[i]);
        }
    }

    public DEUndergroundStructure(StructureConfig config){
        super(NoneFeatureConfiguration.CODEC, config);
        setLakeProof(true);
    }

    @Override
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return DEUndergroundStructure.Start::new;
    }

    public class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structureFeature, ChunkPos chunkPos, int reference, long seed) {
            super(structureFeature, chunkPos, reference, seed);
        }

        @Override @ParametersAreNonnullByDefault
        public void generatePieces(RegistryAccess registry, ChunkGenerator chunkGen, StructureManager structureManager, ChunkPos chunkPos, Biome biome, NoneFeatureConfiguration config, LevelHeightAccessor heightAccessor) {
            int x = chunkPos.x * 16 + Offset.getX();
            int z = chunkPos.z * 16 + Offset.getZ();
            int maxY = chunkGen.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor);
            if(maxY >= 55)
                maxY = 55;
            int y = Math.abs(this.random.nextInt(Math.max(8, maxY))) + Offset.getY();

            assemble(structureManager, new BlockPos(x, y, z), Rotation.getRandom(this.random), this.pieces, this.random);

            this.createBoundingBox();
        }
    }

    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand){
        ResourceLocation Piece = MainPiece;
        if(Piece == null){
            Piece = Pieces[rand.nextInt(Pieces.length)];
        }
        structurePieces.add(new DEUndergroundStructure.Piece(structureManager, Piece, pos, rotation));
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(StructureManager structureManager, ResourceLocation location, BlockPos pos, Rotation rotation, int componentType){
            super(DEStructures.DungeonVariant.getPieceType(), componentType, structureManager, location, pos);
            this.templatePosition = pos;
            this.rotation = rotation;
            setupPlaceSettings(structureManager);
        }
        public Piece(StructureManager structureManager, ResourceLocation location, BlockPos pos, Rotation rotation) {
            this(structureManager, location, pos, rotation, 0);
        }

        @Override
        protected StructurePlaceSettings getPlaceSettings(StructureManager structureManager) {
            Vec3i sizePos = structureManager.get(this.makeTemplateLocation()).get().getSize();
            BlockPos centerPos = new BlockPos(sizePos.getX() / 2, 0, sizePos.getZ() / 2);
            return new StructurePlaceSettings().setKeepLiquids(false).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(centerPos);
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, Random rnd, BoundingBox box) {

        }
    }
    protected static BlockPos Offset(int x, int y, int z){
        return new BlockPos(x, y, z);
    }
}