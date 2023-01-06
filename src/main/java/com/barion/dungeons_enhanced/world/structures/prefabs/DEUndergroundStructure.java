package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePieces;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Supplier;

public class DEUndergroundStructure extends DEBaseStructure{
    public DEUndergroundStructure(StructureSettings settings, DEStructurePieces variants, Supplier<StructureType<?>> type) {super(settings, variants, type);}

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        BlockPos rawPos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());

        if(rawPos.getY() < 0) {return Optional.empty();}

        int minY = context.chunkGenerator().getMinY() + 8;
        int maxY = rawPos.getY() - 24;
        int y = maxY;
        if (maxY > minY) {y = minY + context.random().nextInt(maxY - minY);}
        DEStructurePieces.Piece piece = variants.getRandomPiece(context.random());
        final BlockPos pos = rawPos.atY(y).above(piece.yOffset);

        return at(pos, (builder)-> generatePieces(builder, pos, piece, Rotation.getRandom(context.random()), context, DEUndergroundStructure::assemble));
    }

    protected static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            super(DEStructures.DungeonVariant.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.DungeonVariant.getPieceType(), serializationContext, nbt);
        }
    }
}