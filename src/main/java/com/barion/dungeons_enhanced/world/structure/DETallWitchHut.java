package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEGroundStructure;
import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;

import javax.annotation.Nonnull;
import java.util.Optional;

public final class DETallWitchHut extends DEGroundStructure {
    public static final Codec<DETallWitchHut> CODEC = simpleCodec(DETallWitchHut::new);

    public DETallWitchHut(StructureSettings settings) {
        super(settings, DEUtil.pieceBuilder().yOffset(-3).add("tall_witch_hut").build(), DEStructures.TALL_WITCH_HUT::getType);
    }

    @Override
    @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final var piece = _templates.getRandom(context.random());
        final var rawPos = getGenPos(context.chunkPos()).above(piece.yOffset);

        final var rotation = Rotation.getRandom(context.random());

        context.heightAccessor().getHeight();

        final var size = context.structureTemplateManager().getOrCreate(piece.Resource).getSize(rotation);

        final var result = DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 4, Heightmap.Types.WORLD_SURFACE_WG, context.chunkGenerator(), context.heightAccessor(), context.randomState());
        if (!result.getSecond()) {
            return Optional.empty();
        }
        final var pos = rawPos.atY(Math.round(result.getFirst())).above(piece.yOffset);
        final var oceanFloor = context.chunkGenerator().getBaseHeight(pos.getX(), pos.getZ(), Heightmap.Types.OCEAN_FLOOR_WG, context.heightAccessor(), context.randomState());
        final var dif = pos.getY() - oceanFloor;
        if (dif > 4) {
            return Optional.empty();
        }

        return at(pos, (builder) -> generatePieces(builder, pos, piece, rotation, context, DEGroundStructure::assemble));
    }
}
