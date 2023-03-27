package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePieces;
import com.mojang.serialization.Codec;
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

import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public class DEFlyingStructure extends DEBaseStructure{
    public static final String ID_FLYING_DUTCHMAN = "flying_dutchman";
    public static final Codec<DEFlyingStructure> CODEC_FLYING_DUTCHMAN = simpleCodec(DEFlyingStructure::FlyingDutchman);
    public static DEFlyingStructure FlyingDutchman(StructureSettings settings){
        return new DEFlyingStructure(settings, pieceBuilder().add("flying_dutchman").build(), DEStructures.FLYING_DUTCHMAN::getType);
    }

    protected DEFlyingStructure(StructureSettings settings, DEStructurePieces variants, Supplier<StructureType<?>> type) {
        super(settings, variants, type);
    }

    public static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        BlockPos rawPos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());

        if(rawPos.getY() > 224) {return Optional.empty();}

        int minY = rawPos.getY() + 48;
        int maxY = 288;
        int y = maxY;
        if (maxY > minY) {y = minY + context.random().nextInt(maxY - minY);}
        final BlockPos pos = rawPos.atY(y);

        return at(pos, (builder)-> generatePieces(builder, pos, variants.getRandomPiece(context.random()), Rotation.getRandom(context.random()), context, DEFlyingStructure::assemble));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(DEStructures.FLYING_DUTCHMAN.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(DEStructures.FLYING_DUTCHMAN.getPieceType(), serializationContext, nbt);
        }
    }
}