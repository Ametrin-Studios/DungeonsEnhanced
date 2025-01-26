package com.barion.dungeons_enhanced.world.structure.builder;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.function.Function;
import java.util.function.Supplier;

public final class DESinglePieceFactory implements IDEPieceFactory {
    private final DEStructureTemplate _template;
    private final Supplier<StructurePieceType> _pieceTypeSupplier;
    private final Function<StructurePlaceSettings, StructurePlaceSettings> _settingsFunction;


    public DESinglePieceFactory(DEStructureTemplate template, Supplier<StructurePieceType> pieceTypeSupplier) {
        this(template, pieceTypeSupplier, settings -> settings);
    }

    public DESinglePieceFactory(DEStructureTemplate template, Supplier<StructurePieceType> pieceTypeSupplier, Function<StructurePlaceSettings, StructurePlaceSettings> settingsFunction) {
        _template = template;
        _pieceTypeSupplier = pieceTypeSupplier;
        _settingsFunction = settingsFunction;
    }

    @Override
    public DESimpleStructurePiece createPiece(StructureTemplateManager templateManager, BlockPos position, RandomSource random) {
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), templateManager, _template.resourceLocation(), position.above(_template.yOffset()), _settingsFunction, Rotation.getRandom(random));
    }

    @Override
    public StructurePiece createPiece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), nbt, serializationContext, _settingsFunction);
    }
}
