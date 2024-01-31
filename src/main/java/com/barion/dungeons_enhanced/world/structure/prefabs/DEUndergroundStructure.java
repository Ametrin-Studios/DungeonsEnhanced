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

public class DEUndergroundStructure extends DEBaseStructure{
    public static final String ID_DUNGEON_VARIANT = "dungeon_variant";
    public static final Codec<DEUndergroundStructure> CODEC_DUNGEON_VARIANT = simpleCodec(DEUndergroundStructure::DungeonVariant);
    public static DEUndergroundStructure DungeonVariant(StructureSettings settings){
        return new DEUndergroundStructure(settings, pieceBuilder().add("dungeon_variant/zombie").add("dungeon_variant/skeleton").add("dungeon_variant/spider").build(), DEStructures.DUNGEON_VARIANT::getType);
    }

    protected DEUndergroundStructure(StructureSettings settings, DEStructureTemplates variants, Supplier<StructureType<?>> type) {super(settings, variants, type);}

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        BlockPos rawPos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());

        if(rawPos.getY() < 0) {return Optional.empty();}

        int minY = context.chunkGenerator().getMinY() + 8;
        int maxY = rawPos.getY() - 24;
        int y = maxY;
        if (maxY > minY) {y = minY + context.random().nextInt(maxY - minY);}
        DEStructureTemplates.Template template = Templates.getRandom(context.random());
        final BlockPos pos = rawPos.atY(y).above(template.yOffset);

        return at(pos, (builder)-> generatePieces(builder, pos, template, Rotation.getRandom(context.random()), context, DEUndergroundStructure::assemble));
    }

    protected static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            super(DEStructures.DUNGEON_VARIANT.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.DUNGEON_VARIANT.getPieceType(), serializationContext, nbt);
        }
    }
}