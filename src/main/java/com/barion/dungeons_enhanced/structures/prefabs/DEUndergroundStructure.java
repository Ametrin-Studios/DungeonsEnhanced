package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.util.ConfigTemplates;
import com.legacy.structure_gel.worldgen.GelPlacementSettings;
import com.legacy.structure_gel.worldgen.structure.GelConfigStructure;
import com.legacy.structure_gel.worldgen.structure.GelStructureStart;
import com.legacy.structure_gel.worldgen.structure.GelTemplateStructurePiece;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class DEUndergroundStructure extends GelConfigStructure<NoFeatureConfig> {
    public ResourceLocation MainPiece = null;
    public ResourceLocation[] Pieces = null;
    public BlockPos Offset = BlockPos.ZERO;

    public DEUndergroundStructure(String resource, BlockPos offset, ConfigTemplates.StructureConfig config){
        super(NoFeatureConfig.CODEC, config);
        setLakeProof(true);
        MainPiece = DEStructures.locate(resource);
        Offset = offset;
    }
    public DEUndergroundStructure(String[] resources, BlockPos offset, ConfigTemplates.StructureConfig config){
        super(NoFeatureConfig.CODEC, config);
        setLakeProof(true);
        Pieces = new ResourceLocation[resources.length];
        for(int i = 0; i < resources.length; i++){
            Pieces[i] = DEStructures.locate(resources[i]);
        }
        Offset = offset;
    }
    public DEUndergroundStructure(ConfigTemplates.StructureConfig config, String... resources){
        super(NoFeatureConfig.CODEC, config);
        setLakeProof(true);
        Pieces = new ResourceLocation[resources.length];
        for(int i = 0; i < resources.length; i++){
            Pieces[i] = DEStructures.locate(resources[i]);
        }
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return DEUndergroundStructure.Start::new;
    }

    public class Start extends GelStructureStart<NoFeatureConfig> {

        public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundsIn, int referenceIn, long seed) {
            super(structureIn, chunkX, chunkZ, boundsIn, referenceIn, seed);
        }

        @Override
        public void generatePieces(DynamicRegistries registry, ChunkGenerator chunkGen, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig configIn) {
            int x = chunkX * 16 + Offset.getX();
            int z = chunkZ * 16 + Offset.getZ();
            int maxY = chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG);
            if(maxY >= 55)
                maxY = 55;
            int y = Math.abs(this.random.nextInt(Math.max(8, maxY))) + Offset.getY();

            assemble(templateManagerIn, new BlockPos(x, y, z), Rotation.getRandom(this.random), this.pieces, this.random);

            this.calculateBoundingBox();
        }
    }

    public void assemble(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand){
        ResourceLocation Piece = MainPiece;
        if(Piece == null){
            Piece = Pieces[rand.nextInt(Math.max(0, Pieces.length))];
        }
        structurePieces.add(new DEUndergroundStructure.Piece(templateManager, Piece, pos, rotation));
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation, int componentType){
            super(DEStructures.DungeonVariant.getPieceType(), location, componentType);
            this.templatePosition = pos;
            this.rotation = rotation;
            this.setupTemplate(templateManager);
        }
        public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation) {
            this(templateManager, location, pos, rotation, 0);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbtCompound) {
            super(DEStructures.DungeonVariant.getPieceType(), nbtCompound);
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
    protected static BlockPos Offset(int x, int y, int z){
        return new BlockPos(x, y, z);
    }
}