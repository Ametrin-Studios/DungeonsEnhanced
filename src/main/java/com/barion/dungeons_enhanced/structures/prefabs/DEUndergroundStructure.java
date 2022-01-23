package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class DEUndergroundStructure extends DEBaseStructure {

    public DEUndergroundStructure(StructureConfig config, boolean generateNearSpawn, DEPiece... resources){
        super(config, GenerationType.underground, generateNearSpawn, resources);
    }
    public DEUndergroundStructure(StructureConfig config, BlockPos offset, boolean generateNearSpawn, DEPiece... resources){
        super(config, GenerationType.underground, offset, generateNearSpawn, resources);
    }

    @Override
    public void assemble(AssembleContext context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.variant().Resource, context.pos(), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType){
            super(DEStructures.DungeonVariant.getPieceType(), structureManager, templateName, pos, rotation, componentType);
        }
        public Piece(StructureManager structureManager, ResourceLocation location, BlockPos pos, Rotation rotation) {
            this(structureManager, location, pos, rotation, 0);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.DungeonVariant.getPieceType(), serializationContext, nbt);
        }
    }
}