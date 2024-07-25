package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructureTemplates;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import com.legacy.structure_gel.api.structure.processor.RemoveGelStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class DEBaseStructure extends Structure {
    protected final DEStructureTemplates _templates;
    protected final Supplier<StructureType<?>> _typeSupplier;
    public DEBaseStructure(StructureSettings settings, DEStructureTemplates templates, Supplier<StructureType<?>> type) {
        super(settings);
        _templates = templates;
        _typeSupplier = type;
    }

    protected static Optional<Structure.GenerationStub> at(BlockPos pos, Consumer<StructurePiecesBuilder> piecesBuilder) {
        return Optional.of(new GenerationStub(pos, piecesBuilder));
    }

    @Override @Nonnull
    public StructureType<?> type() { return _typeSupplier.get(); }

    protected static void generatePieces(StructurePiecesBuilder piecesBuilder, BlockPos pos, DEStructureTemplates.Template template, Rotation rotation, GenerationContext context, DEPieceAssembler assembler) {
        assembler.assemble(new DEPieceAssembler.Context(context.structureTemplateManager(), template.Resource, pos, rotation, piecesBuilder));
    }

    public static class Piece extends GelTemplateStructurePiece {
        private Rotation _rotation;
        public Piece(Registrar.Static<StructurePieceType> pieceType, StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(pieceType.get(), 0, structureManager, templateName, pos);
            this._rotation = rotation;
            setupPlaceSettings(structureManager);
        }

        public Piece(Registrar.Static<StructurePieceType> pieceType, StructurePieceSerializationContext context, CompoundTag nbt) {
            super(pieceType.get(), nbt, context.structureTemplateManager());
            setupPlaceSettings(context.structureTemplateManager());
        }

        @Override
        protected StructurePlaceSettings getPlaceSettings(StructureTemplateManager structureManager) {
            var size = structureManager.get(makeTemplateLocation()).get().getSize();
            var pivot = new BlockPos(size.getX() / 2, 0, size.getZ() / 2);
            var settings = super.getPlaceSettings(structureManager).setKeepLiquids(false).setRotationPivot(pivot).setRotation(this._rotation);
            settings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR).addProcessor(RemoveGelStructureProcessor.INSTANCE);
            addProcessors(settings);
            return settings;
        }

        protected void addProcessors(StructurePlaceSettings settings) {}

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {}
    }
}