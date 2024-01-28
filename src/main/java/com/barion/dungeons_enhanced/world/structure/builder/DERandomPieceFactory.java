package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.function.Supplier;

public final class DERandomPieceFactory implements IDETemplatePieceFactory {
    private final SimpleWeightedRandomList<DEStructureTemplate> _templates;
    private final Supplier<StructurePieceType> _pieceTypeSupplier;

    public DERandomPieceFactory(SimpleWeightedRandomList<DEStructureTemplate> templates, Supplier<StructurePieceType> pieceTypeSupplier) {
        if(templates.isEmpty()) throw new IllegalArgumentException("The template list is empty");
        _pieceTypeSupplier = pieceTypeSupplier;
        _templates = templates;
    }


    @Override
    public StructurePiece createPiece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), nbt, serializationContext);
    }
    @Override
    public DESimpleStructurePiece createPiece(StructureTemplateManager templateManager, BlockPos position, RandomSource random) {
        var template = _templates.getRandomValue(random).get();
        return new DESimpleStructurePiece(_pieceTypeSupplier.get(), templateManager, template.resourceLocation(), position.above(template.yOffset()), Rotation.getRandom(random));
    }

    public static class Builder{
        private final SimpleWeightedRandomList.Builder<DEStructureTemplate> _templates = new SimpleWeightedRandomList.Builder<>();

        public Builder add(String location) {return add(DEUtil.location(location));}
        public Builder add(int weight, String location) {return add(weight, DEUtil.location(location), 0);}
        public Builder add(int weight, String location, int yOffset) {return add(weight, DEUtil.location(location), yOffset);}
        public Builder add(ResourceLocation resourceLocation) {return add(1, resourceLocation, 0);}
        public Builder add(int weight, ResourceLocation resourceLocation, int yOffset) {return add(weight, new DEStructureTemplate(resourceLocation, yOffset));}
        public Builder add(DEStructureTemplate template){return add(1, template);}
        public Builder add(int weight, DEStructureTemplate template){
            _templates.add(template, weight);
            return this;
        }

        public DERandomPieceFactory build(Supplier<StructurePieceType> pieceTypeSupplier){
            return new DERandomPieceFactory(_templates.build(), pieceTypeSupplier);
        }
    }
}
