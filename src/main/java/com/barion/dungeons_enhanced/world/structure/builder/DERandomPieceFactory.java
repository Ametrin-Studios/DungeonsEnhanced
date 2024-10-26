package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.WeightedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.function.Function;
import java.util.function.Supplier;

public final class DERandomPieceFactory implements IDEPieceFactory {
    private final WeightedList<DEStructureTemplate> _templates;
    private final Supplier<IStructurePieceType> _pieceTypeSupplier;
    private final Function<PlacementSettings, PlacementSettings> _settingsFunction;


    public DERandomPieceFactory(WeightedList<DEStructureTemplate> templates, Supplier<IStructurePieceType> pieceTypeSupplier, Function<PlacementSettings, PlacementSettings> settingsFunction) {
        _settingsFunction = settingsFunction;
        if(templates.isEmpty()) throw new IllegalArgumentException("The template list is empty");
        _pieceTypeSupplier = pieceTypeSupplier;
        _templates = templates;
    }

    @Override
    public DESimpleStructurePiece createPiece(TemplateManager templateManager, BlockPos position, SharedSeedRandom random) {
        final DEStructureTemplate template = _templates.getOne(random);
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), templateManager, template.resourceLocation, position.above(template.yOffset), Rotation.getRandom(random), _settingsFunction);
    }

    @Override
    public StructurePiece createPiece(TemplateManager templateManager, CompoundNBT nbt) {
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), templateManager, nbt, _settingsFunction);
    }

    public static class Builder {
        private final WeightedList<DEStructureTemplate> _templates = new WeightedList<>();
        private Function<PlacementSettings, PlacementSettings> _settingsFunction = settings -> settings;

        public Builder settings(Function<PlacementSettings, PlacementSettings> settingsFunction) {
            _settingsFunction = settingsFunction;
            return this;
        }

        public Builder add(String location) { return add(DEUtil.locate(location)); }
        public Builder add(int weight, String location) { return add(weight, DEUtil.locate(location), 0); }
        public Builder add(int weight, String location, int yOffset) { return add(weight, DEUtil.locate(location), yOffset); }
        public Builder add(ResourceLocation resourceLocation) { return add(1, resourceLocation, 0); }
        public Builder add(int weight, ResourceLocation resourceLocation, int yOffset) { return add(weight, new DEStructureTemplate(resourceLocation, yOffset)); }
        public Builder add(DEStructureTemplate template) { return add(1, template); }
        public Builder add(int weight, DEStructureTemplate template) {
            _templates.add(template, weight);
            return this;
        }

        public DERandomPieceFactory build(Supplier<IStructurePieceType> pieceTypeSupplier) {
            return new DERandomPieceFactory(_templates, pieceTypeSupplier, _settingsFunction);
        }
    }
}
