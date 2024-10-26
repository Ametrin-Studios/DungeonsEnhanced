package com.barion.dungeons_enhanced.world.structure.builder;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;
import java.util.function.Supplier;

public final class DESinglePieceFactory implements IDEPieceFactory {
    private final DEStructureTemplate _template;
    private final Supplier<IStructurePieceType> _pieceTypeSupplier;
    private final Function<PlacementSettings, PlacementSettings> _settingsFunction;

    public DESinglePieceFactory(DEStructureTemplate template, Supplier<IStructurePieceType> pieceTypeSupplier) {
        this(template, pieceTypeSupplier, settings -> settings);
    }
    public DESinglePieceFactory(DEStructureTemplate template, Supplier<IStructurePieceType> pieceTypeSupplier, Function<PlacementSettings, PlacementSettings> settingsFunction) {
        _template = template;
        _pieceTypeSupplier = pieceTypeSupplier;
        _settingsFunction = settingsFunction;
    }


    @Override
    public DESimpleStructurePiece createPiece(TemplateManager templateManager, BlockPos position, SharedSeedRandom random) {
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), templateManager, _template.resourceLocation, position.above(_template.yOffset), Rotation.getRandom(random), _settingsFunction);
    }
    @Override
    public StructurePiece createPiece(TemplateManager templateManager, CompoundNBT nbt) {
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), templateManager, nbt, _settingsFunction);
    }
}
