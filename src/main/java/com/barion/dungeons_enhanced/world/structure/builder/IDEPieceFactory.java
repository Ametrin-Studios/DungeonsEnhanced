package com.barion.dungeons_enhanced.world.structure.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public interface IDEPieceFactory {
    StructurePiece createPiece(StructurePieceSerializationContext serializationContext, CompoundTag nbt);

    DESimpleStructurePiece createPiece(StructureTemplateManager templateManager, BlockPos position, RandomSource random);
}
