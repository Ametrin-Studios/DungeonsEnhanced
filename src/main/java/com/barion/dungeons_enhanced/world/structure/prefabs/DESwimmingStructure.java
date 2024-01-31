package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructureTemplates;
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

public class DESwimmingStructure extends DEBaseStructure {
    public static final String ID_FISHING_SHIP = "fishing_ship";
    public static final Codec<DESwimmingStructure> CODEC_FISHING_SHIP = simpleCodec(DESwimmingStructure::FishingShip);
    public static DESwimmingStructure FishingShip(StructureSettings settings){
        return new DESwimmingStructure(settings, pieceBuilder().yOffset(-3).add("fishing_ship").build(), DEStructures.FISHING_SHIP::getType);
    }

    protected DESwimmingStructure(StructureSettings settings, DEStructureTemplates variants, Supplier<StructureType<?>> type){
        super(settings, variants, type);
    }

    protected static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        DEStructureTemplates.Template template = Templates.getRandom(context.random());
        BlockPos pos = getGenPos(context).above(template.yOffset);

        return at(pos, (builder) -> generatePieces(builder, pos, template, Rotation.getRandom(context.random()), context, DESwimmingStructure::assemble));
    }

    protected BlockPos getGenPos(GenerationContext context){
        return DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
    }

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            super(DEStructures.FISHING_SHIP.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.FISHING_SHIP.getPieceType(), serializationContext, nbt);
        }
    }
}