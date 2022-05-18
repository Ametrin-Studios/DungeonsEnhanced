package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Predicate;

public class DESimpleStructure extends DEBaseStructure {

    public DESimpleStructure(StructureConfig config, DEStructurePiece[] resources) {this(config, true, resources);}

    public DESimpleStructure(StructureConfig config, boolean generateNearSpawn, DEStructurePiece[] resources){
        this(config, generateNearSpawn, (context)-> DETerrainAnalyzer.isFlatEnough(context.chunkPos(), context.chunkGenerator(), context.heightAccessor()), DESimpleStructure::assemble, resources);
    }
    protected DESimpleStructure(StructureConfig config, boolean generateNearSpawn, Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> pieceGenerator, DEPieceAssembler assembler, DEStructurePiece[] resources){
        super(config, DETerrainAnalyzer.GenerationType.onGround, generateNearSpawn, pieceGenerator, assembler, resources);
    }

    protected static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType) {
            super(DEStructures.RuinedBuilding.getPieceType(), structureManager, templateName, pos, rotation, componentType);
        }

        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            this(structureManager, templateName, pos, rotation, 0);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.RuinedBuilding.getPieceType(), serializationContext, nbt);
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, Random random, BoundingBox box) {}
    }
}