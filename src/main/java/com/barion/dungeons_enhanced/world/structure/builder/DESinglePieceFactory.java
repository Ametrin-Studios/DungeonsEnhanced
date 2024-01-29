package com.barion.dungeons_enhanced.world.structure.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.function.Supplier;

public final class DESinglePieceFactory implements IDEPieceFactory {
    private final DEStructureTemplate _template;
    private final Supplier<StructurePieceType> _pieceTypeSupplier;


    public DESinglePieceFactory(DEStructureTemplate template, Supplier<StructurePieceType> pieceTypeSupplier) {
        _template = template;
        _pieceTypeSupplier = pieceTypeSupplier;
    }

    @Override
    public DESimpleStructurePiece createPiece(StructureTemplateManager templateManager, BlockPos position, RandomSource random) {
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), templateManager, _template.resourceLocation(), position, Rotation.getRandom(random));
    }

    @Override
    public StructurePiece createPiece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), nbt, serializationContext);
    }
}
