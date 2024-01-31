package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEGroundStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEIcePit extends DEGroundStructure {
    public static final String ID = "ice_pit";
    public static final Codec<DEIcePit> CODEC = simpleCodec(DEIcePit::new);
    private static final ResourceLocation ENTRANCE = location("ice_pit/top");
    public DEIcePit(StructureSettings settings) {super(settings, DEUtil.pieceBuilder().yOffset(-25).add("ice_pit/var1").add("ice_pit/var2").add("ice_pit/var3").build(), DEStructures.ICE_PIT::getType);}

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        var template = Templates.getRandom(context.random());
        final var pos = DEUtil.ChunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState()).above(template.yOffset);
        return at(pos, (builder)-> generatePieces(builder, pos, template, Rotation.getRandom(context.random()), context, DEIcePit::assembleIcePit));
    }

    private static void assembleIcePit(DEPieceAssembler.Context context) {
        var pos = context.pos();
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), ENTRANCE, pos, context.rotation()));
        int yOffset = -6;
        if(context.piece().getPath().contains("var3")) {yOffset = -11;}
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), pos.offset(-17, yOffset,-17), context.rotation()));
    }
}