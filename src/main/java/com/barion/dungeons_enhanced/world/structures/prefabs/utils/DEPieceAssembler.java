package com.barion.dungeons_enhanced.world.structures.prefabs.utils;

import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import javax.annotation.ParametersAreNonnullByDefault;

@FunctionalInterface
public interface DEPieceAssembler {
    @ParametersAreNonnullByDefault
    void assemble(Context context);

    record Context(StructureManager structureManager, ResourceLocation piece, BlockPos pos, Rotation rotation, StructurePiecesBuilder piecesBuilder, DETerrainAnalyzer.GenerationType generationType){}
}