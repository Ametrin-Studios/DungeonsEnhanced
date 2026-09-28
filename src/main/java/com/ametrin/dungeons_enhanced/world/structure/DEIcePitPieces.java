package com.ametrin.dungeons_enhanced.world.structure;

import com.ametrin.dungeons_enhanced.registry.DEPieceSources;
import com.ametrin.structures.structure.simple.PieceSource;
import com.ametrin.structures.structure.simple.PieceSourceType;
import com.ametrin.structures.structure.simple.PieceSources;
import com.ametrin.structures.structure.simple.TemplateEntry;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.levelgen.structure.StructurePiece;

import java.util.List;
import java.util.Optional;

import static com.ametrin.dungeons_enhanced.DungeonsEnhanced.locate;

public final class DEIcePitPieces implements PieceSource {
    public static final DEIcePitPieces INSTANCE = new DEIcePitPieces();
    public static final MapCodec<DEIcePitPieces> CODEC = MapCodec.unit(INSTANCE);

    private static final PieceSources.WeightedSource ROOMS = new PieceSources.WeightedSource(WeightedList.of(
            new Weighted<>(new PieceSources.SingleSource(new TemplateEntry(locate("ice_pit/var1"), new BlockPos(-17, -31, -17), Optional.empty())), 1),
            new Weighted<>(new PieceSources.SingleSource(new TemplateEntry(locate("ice_pit/var2"), new BlockPos(-17, -31, -17), Optional.empty())), 1),
            new Weighted<>(new PieceSources.SingleSource(new TemplateEntry(locate("ice_pit/var3"), new BlockPos(-17, -36, -17), Optional.empty())), 1)
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
}