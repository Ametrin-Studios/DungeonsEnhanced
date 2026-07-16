package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.function.Function;
import java.util.function.Supplier;

public final class DERandomPieceFactory implements IDEPieceFactory {
    private final WeightedList<DEStructureTemplate> _templates;
    private final Supplier<StructurePieceType> _pieceTypeSupplier;
    private final Function<StructurePlaceSettings, StructurePlaceSettings> _settingsFunction;

    public DERandomPieceFactory(WeightedList<DEStructureTemplate> templates, Supplier<StructurePieceType> pieceTypeSupplier, Function<StructurePlaceSettings, StructurePlaceSettings> settingsFunction) {
        _settingsFunction = settingsFunction;
        if (templates.isEmpty()) throw new IllegalArgumentException("The template list is empty");
        _pieceTypeSupplier = pieceTypeSupplier;
        _templates = templates;
    }

    @Override
    public StructurePiece createPiece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), nbt, serializationContext, _settingsFunction);
    }

    @Override
    public DESimpleStructurePiece createPiece(StructureTemplateManager templateManager, BlockPos position, RandomSource random) {
        var template = _templates.getRandomOrThrow(random);
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), templateManager, template.identifier(), position, _settingsFunction, template.yOffset(), Rotation.getRandom(random));
    }

    public static class Builder {
        private final WeightedList.Builder<DEStructureTemplate> _templates = new WeightedList.Builder<>();
        private Function<StructurePlaceSettings, StructurePlaceSettings> _settingsFunction = settings -> settings;

        public Builder settings(Function<StructurePlaceSettings, StructurePlaceSettings> settingsFunction) {
            _settingsFunction = settingsFunction;
            return this;
        }

        public Builder add(String location) {
            return add(DungeonsEnhanced.locate(location));
        }

        public Builder add(int weight, String location) {
            return add(weight, DungeonsEnhanced.locate(location), 0);
        }

        public Builder add(int weight, String location, int yOffset) {
            return add(weight, DungeonsEnhanced.locate(location), yOffset);
        }

        public Builder add(Identifier resourceLocation) {
            return add(1, resourceLocation, 0);
        }

        public Builder add(int weight, Identifier resourceLocation, int yOffset) {
            return add(weight, new DEStructureTemplate(resourceLocation, yOffset));
        }

        public Builder add(DEStructureTemplate template) {
            return add(1, template);
        }

        public Builder add(int weight, DEStructureTemplate template) {
            _templates.add(template, weight);
            return this;
        }

        public DERandomPieceFactory build(Supplier<StructurePieceType> pieceTypeSupplier) {
            return new DERandomPieceFactory(_templates.build(), pieceTypeSupplier, _settingsFunction);
        }
    }
}
