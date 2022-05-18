package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import com.legacy.structure_gel.api.structure.processor.RemoveGelStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

public abstract class DEBaseStructure extends GelConfigStructure<NoneFeatureConfiguration> {
    protected boolean generateNear00;

    public DEBaseStructure(StructureConfig config, DETerrainAnalyzer.GenerationType generationType, boolean generateNear00, Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheck, DEPieceAssembler assembler, DEStructurePiece[] variants) {
        super(NoneFeatureConfiguration.CODEC, config, PieceGeneratorSupplier.simple((context)-> checkLocation(context, locationCheck),
                (piecesBuilder, context) -> generatePieces(piecesBuilder, context, generationType, variants, DEUtil.getMaxWeight(variants), assembler)));
        this.generateNear00 = generateNear00;
    }

    private static boolean checkLocation(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheck){
        if(context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG)){
            return locationCheck.test(context);
        }
        return false;
    }

    private static void generatePieces(StructurePiecesBuilder piecesBuilder, PieceGenerator.Context<NoneFeatureConfiguration> context, DETerrainAnalyzer.GenerationType generationType, DEStructurePiece[] variants, int maxWeight, DEPieceAssembler assembler) {
        int x = context.chunkPos().getMinBlockX();
        int z = context.chunkPos().getMinBlockZ();
        int y = 72;
        ChunkGenerator chunkGen = context.chunkGenerator();
        LevelHeightAccessor heightAccessor = context.heightAccessor();
        switch (generationType) {
            case onGround, onWater -> y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
            case underwater -> y = chunkGen.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor);
            case inAir -> {
                int minY = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor) + 50;
                int maxY = 290;
                if (minY >= maxY) {y = maxY;}
                else {y = minY + context.random().nextInt(maxY - minY);}
            }
            case underground -> {
                int minY = chunkGen.getMinY() + 10;
                int maxY = chunkGen.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor) - 20;
                if (minY >= maxY) {y = maxY;}
                else {y = minY + context.random().nextInt(maxY - minY);}
            }
        }

        int piece = DEUtil.getRandomPiece(variants, maxWeight, context.random());

        assembler.assemble(new DEPieceAssembler.Context(context.structureManager(), variants[piece].Resource, new BlockPos(x, y, z).offset(variants[piece].Offset), Rotation.getRandom(context.random()), piecesBuilder, generationType));
    }

    @Override public boolean isAllowedNearWorldSpawn() {return generateNear00;}

    public static class Piece extends GelTemplateStructurePiece{
        public Piece(StructurePieceType pieceType, StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType){
            super(pieceType, componentType, structureManager, templateName, getPlaceSettings(structureManager, templateName, pos, rotation), pos);
        }

        public Piece(StructurePieceType pieceType, StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(pieceType, nbt, serializationContext.structureManager(), (name) -> getPlaceSettings(serializationContext.structureManager(), name, new BlockPos(nbt.getInt("TPX"), nbt.getInt("TPY"), nbt.getInt("TPZ")), Rotation.valueOf(nbt.getString("Rot"))));
        }

        protected static StructurePlaceSettings getPlaceSettings(StructureManager structureManager, ResourceLocation name, BlockPos pos, Rotation rotation) {
            Optional<StructureTemplate> temp = structureManager.get(name);
            Vec3i size = Vec3i.ZERO;
            if(temp.isPresent()) {size = temp.get().getSize();}
            StructurePlaceSettings settings = new StructurePlaceSettings().setKeepLiquids(false).setRotationPivot(new BlockPos(size.getX()/2, 0, size.getZ()/2).rotate(rotation));
            settings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR).addProcessor(RemoveGelStructureProcessor.INSTANCE);
            return settings;
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, Random random, BoundingBox box) {}
    }
}