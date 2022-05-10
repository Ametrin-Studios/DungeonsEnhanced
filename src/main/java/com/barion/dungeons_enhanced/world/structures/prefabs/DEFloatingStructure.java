package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class DEFloatingStructure extends DEBaseStructure{
    public DEFloatingStructure(StructureConfig config, boolean generateNearSpawn, DEStructurePiece[] resources) {
        super(config, DETerrainAnalyzer.GenerationType.inAir, generateNearSpawn, false, DEFloatingStructure::assemble, resources);
    }

    public static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType) {
            super(DEStructures.FlyingDutchman.getPieceType(), structureManager, templateName, pos, rotation, componentType);
        }
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            this(structureManager, templateName, pos, rotation, 0);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.FlyingDutchman.getPieceType(), serializationContext, nbt);
        }
    }
}
