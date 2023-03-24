package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePieces;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import com.legacy.structure_gel.api.structure.processor.RemoveGelStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
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

public abstract class DEBaseStructure extends Structure{
    protected final DEStructurePieces variants;
    protected final Supplier<StructureType<?>> type;
    public DEBaseStructure(StructureSettings settings, DEStructurePieces variants, Supplier<StructureType<?>> type){
        super(settings);
        this.variants = variants;
        this.type = type;
    }

    protected static Optional<Structure.GenerationStub> at(BlockPos pos, Consumer<StructurePiecesBuilder> piecesBuilder){
        return Optional.of(new GenerationStub(pos, piecesBuilder));
    }

    @Override @Nonnull
    public StructureType<?> type() {return type.get();}

    protected static void generatePieces(StructurePiecesBuilder piecesBuilder, BlockPos pos, DEStructurePieces.Piece piece, Rotation rotation, GenerationContext context, DEPieceAssembler assembler) {
        assembler.assemble(new DEPieceAssembler.Context(context.structureTemplateManager(), piece.Resource, pos, rotation, piecesBuilder));
    }

    public static class Piece extends GelTemplateStructurePiece{
        public Piece(Registrar.Static<StructurePieceType> pieceType, StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(pieceType.get(), 0, structureManager, templateName, pos);
            this.rotation = rotation;
            setupPlaceSettings(structureManager);
        }
        public Piece(Registrar.Static<StructurePieceType> pieceType, StructurePieceSerializationContext context, CompoundTag nbt){
            super(pieceType.get(), nbt, context.structureTemplateManager());
            setupPlaceSettings(context.structureTemplateManager());
        }

        @Override
        protected StructurePlaceSettings getPlaceSettings(StructureTemplateManager structureManager) {
            Vec3i size = structureManager.get(makeTemplateLocation()).get().getSize();
            BlockPos pivot = new BlockPos(size.getX() / 2, 0, size.getZ() / 2);
            StructurePlaceSettings settings = new StructurePlaceSettings().setKeepLiquids(false).setRotationPivot(pivot);
            settings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR).addProcessor(RemoveGelStructureProcessor.INSTANCE);
            addProcessors(settings);
            return settings;
        }

        protected void addProcessors(StructurePlaceSettings settings) {}
        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {}

    }
}