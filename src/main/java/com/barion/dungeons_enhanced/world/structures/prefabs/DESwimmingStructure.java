package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;

public class DESwimmingStructure extends DEBaseStructure {

    public DESwimmingStructure(StructureSettings settings, DEStructurePiece[] variants, DEPieceAssembler assembler){
        super(settings, variants, assembler, DETerrainAnalyzer.GenerationType.onWater);
    }

    public DESwimmingStructure(StructureSettings settings, DEStructurePiece[] variants){
        super(settings, variants, DESwimmingStructure::assemble, DETerrainAnalyzer.GenerationType.onWater);
    }

    protected static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }


    @Override @Nonnull
    public StructureType<?> type() {return DEStructures.FishingShip.getType();}

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            super(DEStructures.FishingShip.getPieceType(), structureManager, templateName, pos, rotation);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.FishingShip.getPieceType(), serializationContext, nbt);
        }
    }
}