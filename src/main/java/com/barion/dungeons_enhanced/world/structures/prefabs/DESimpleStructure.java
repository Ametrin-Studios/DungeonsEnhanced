package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class DESimpleStructure extends DEBaseStructure {

    public DESimpleStructure(StructureConfig config, DEStructurePiece... resources){
        super(config, GenerationType.onGround, true, resources);
    }

    public DESimpleStructure(StructureConfig config, boolean generateNearSpawn, DEStructurePiece... resources){
        super(config, GenerationType.onGround, generateNearSpawn, resources);
    }

    public DESimpleStructure(StructureConfig config, BlockPos offset, boolean generateNearSpawn, DEStructurePiece... resources){
        super(config, GenerationType.onGround, offset, generateNearSpawn, resources);
    }

    @Override
    public void assemble(AssembleContext context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.variant().Resource, context.pos(), context.rotation()));
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
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, Random random, BoundingBox box) {}
    }
}