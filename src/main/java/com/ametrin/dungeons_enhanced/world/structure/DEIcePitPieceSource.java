package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.registry.DEPieceSources;
import com.ametrin.structures.structure.TerrainBox;
import com.ametrin.structures.structure.jigsaw.ExtendedSinglePoolElement;
import com.ametrin.structures.structure.modular.PieceSource;
import com.ametrin.structures.structure.modular.PieceSourceType;
import com.ametrin.structures.structure.modular.PieceSources;
import com.ametrin.structures.structure.modular.TemplateEntry;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.Optional;
import java.util.stream.Stream;

import static com.ametrin.dungeons_enhanced.DungeonsEnhanced.locate;

public final class DEIcePitPieceSource implements PieceSource {
    public static final DEIcePitPieceSource INSTANCE = new DEIcePitPieceSource();
    public static final MapCodec<DEIcePitPieceSource> CODEC = MapCodec.unit(INSTANCE);

    private static final PieceSources.WeightedSource ROOMS = new PieceSources.WeightedSource(WeightedList.of(
            new Weighted<>(new PieceSources.SingleSource(new TemplateEntry(new ExtendedSinglePoolElement(Either.left(locate("ice_pit/var1")), ExtendedSinglePoolElement.NO_PROCESSORS, StructureTemplatePool.Projection.RIGID, Optional.empty(), true, Optional.empty(), TerrainBox.template()), new BlockPos(-17, -31, -17))), 1),
            new Weighted<>(new PieceSources.SingleSource(new TemplateEntry(new ExtendedSinglePoolElement(Either.left(locate("ice_pit/var2")), ExtendedSinglePoolElement.NO_PROCESSORS, StructureTemplatePool.Projection.RIGID, Optional.empty(), true, Optional.empty(), TerrainBox.template()), new BlockPos(-17, -31, -17))), 1),
            new Weighted<>(new PieceSources.SingleSource(new TemplateEntry(new ExtendedSinglePoolElement(Either.left(locate("ice_pit/var3")), ExtendedSinglePoolElement.NO_PROCESSORS, StructureTemplatePool.Projection.RIGID, Optional.empty(), true, Optional.empty(), TerrainBox.template()), new BlockPos(-17, -36, -17))), 1)
    ));

    private static final TemplateEntry ENTRANCE = new TemplateEntry(new ExtendedSinglePoolElement(Either.left(locate("ice_pit/top")), ExtendedSinglePoolElement.NO_PROCESSORS, StructureTemplatePool.Projection.RIGID, Optional.empty(), true, Optional.empty(), TerrainBox.template()), new BlockPos(0, -25, 0));

    @Override
    public void appendPieces(StructurePiecesBuilder builder, Context context) {
        builder.addPiece(PieceSources.createPiece(ENTRANCE, context));
        ROOMS.appendPieces(builder, context);
    }

    @Override
    public PieceSourceType type() {
        return DEPieceSources.ICE_PIT.get();
    }

    @Override
    public Stream<TemplateEntry> templates() {
        return Stream.concat(ROOMS.templates(), Stream.of(ENTRANCE));
    }
}