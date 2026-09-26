package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.registry.DEPieceSources;
import com.ametrin.structures.api.structure.simple.PieceSource;
import com.ametrin.structures.api.structure.simple.PieceSourceType;
import com.ametrin.structures.api.structure.simple.TemplateEntry;
import com.ametrin.structures.impl.structure.simple.PieceSources;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.levelgen.structure.StructurePiece;

import java.util.List;
import java.util.Optional;

import static com.ametrin.dungeons_enhanced.DungeonsEnhanced.locate;

public final class DEIcePit implements PieceSource {
    public static final DEIcePit INSTANCE = new DEIcePit();
    public static final MapCodec<DEIcePit> CODEC = MapCodec.unit(INSTANCE);

    private static final PieceSources.WeightedSource ROOMS = new PieceSources.WeightedSource(WeightedList.of(
            new Weighted<>(new TemplateEntry(locate("ice_pit/var1"), new BlockPos(-17, -31, -17), Optional.empty()), 1),
            new Weighted<>(new TemplateEntry(locate("ice_pit/var2"), new BlockPos(-17, -31, -17), Optional.empty()), 1),
            new Weighted<>(new TemplateEntry(locate("ice_pit/var3"), new BlockPos(-17, -36, -17), Optional.empty()), 1)
    ));

    private static final TemplateEntry ENTRANCE = new TemplateEntry(locate("ice_pit/top"), new BlockPos(0, -25, 0), Optional.empty());

    @Override
    public void appendPieces(List<StructurePiece> builder, Context context) {
        builder.add(PieceSources.createPiece(ENTRANCE, context));
        ROOMS.appendPieces(builder, context);
    }

    @Override
    public PieceSourceType type() {
        return DEPieceSources.ICE_PIT.get();
    }
//    private static final Identifier ENTRANCE = locate("ice_pit/top");
//
//    public DEIcePit(StructureSettings settings) {
//        super(settings, DEUtil.pieceBuilder().yOffset(-25).add("ice_pit/var1").add("ice_pit/var2").add("ice_pit/var3").build(), DEStructures.ICE_PIT::getType);
//    }
//
//    @Override
//    @Nonnull
//    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
//        var template = _templates.getRandom(context.random());
//        final var pos = DEUtil.chunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()).above(template.yOffset());
//        return at(pos, (builder) -> generatePieces(builder, pos, template, Rotation.getRandom(context.random()), context, DEIcePit::assembleIcePit));
//    }
//
//    private static void assembleIcePit(DEPieceAssembler.Context context) {
//        var pos = context.pos();
//        context.piecesBuilder().addPiece(new Piece(context.structureManager(), ENTRANCE, pos, context.rotation()));
//        int yOffset = -6;
//        if (context.piece().getPath().contains("var3")) {
//            yOffset = -11;
//        }
//        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), pos.offset(-17, yOffset, -17), context.rotation()));
//    }
}