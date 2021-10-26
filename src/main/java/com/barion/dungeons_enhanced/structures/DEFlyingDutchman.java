package com.barion.dungeons_enhanced.structures;
/*
import com.barion.dungeons_enhanced.*;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import com.legacy.structure_gel.worldgen.structure.GelConfigStructure;
import com.legacy.structure_gel.worldgen.structure.GelStructureStart;
import com.legacy.structure_gel.worldgen.structure.GelTemplateStructurePiece;
import net.minecraft.block.Blocks;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.nbt.CompoundNBT;
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
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class DEFlyingDutchman extends GelConfigStructure<NoFeatureConfig> {

    public DEFlyingDutchman() {
        super(NoFeatureConfig.CODEC, DEConfig.COMMON.flying_dutchman);
        setLakeProof(true);
    }

    @Override
    public IStartFactory<NoFeatureConfig> getStartFactory() {
        return Start::new;
    }
    public class Start extends GelStructureStart<NoFeatureConfig> {

         public Start(Structure<NoFeatureConfig> structureIn, int chunkX, int chunkZ, MutableBoundingBox boundsIn, int referenceIn, long seed) {
             super(structureIn, chunkX, chunkZ, boundsIn, referenceIn, seed);
         }

         @Override
         public void generatePieces(DynamicRegistries registry, ChunkGenerator chunkGen, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn, NoFeatureConfig config) {
             int x = chunkX * 16 - 4;
             int z = chunkZ * 16 - 15;
             int y = RandomValueRange.between(chunkGen.getBaseHeight(x, z, Heightmap.Type.WORLD_SURFACE_WG) + 70, 220).getInt(this.random);
             assemble(templateManagerIn, new BlockPos(x, y, z), Rotation.getRandom(this.random), this.pieces);
             this.calculateBoundingBox();
         }
    }

     public void assemble(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces){
        structurePieces.add(new DESimpleStructure.Piece(templateManager, DEStructures.locate("flying_dutchman"), pos, rotation));
     }

     public static class Piece extends GelTemplateStructurePiece {

         public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation, int componentType){
             super(DEStructures.FlyingDutchman.getPieceType(), location, componentType);
             this.templatePosition = pos;
             this.rotation = rotation;
             this.setupTemplate(templateManager);
         }
         public Piece(TemplateManager templateManager, ResourceLocation location, BlockPos pos, Rotation rotation) {
             this(templateManager, location, pos, rotation, 0);
         }

         public Piece(TemplateManager templateManager, CompoundNBT nbtCompound) {
             super(DEStructures.FlyingDutchman.getPieceType(), nbtCompound);
             this.setupTemplate(templateManager);
         }

         @Override
         protected void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random random, MutableBoundingBox box) {
             DungeonsEnhanced.LOGGER.info(key);
         }
     }
}*/
