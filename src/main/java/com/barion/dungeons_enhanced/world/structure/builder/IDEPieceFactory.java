package com.barion.dungeons_enhanced.world.structure.builder;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public interface IDEPieceFactory {
    StructurePiece createPiece(TemplateManager templateManager, CompoundNBT nbt);
    DESimpleStructurePiece createPiece(TemplateManager templateManager, BlockPos position, SharedSeedRandom random);
}
