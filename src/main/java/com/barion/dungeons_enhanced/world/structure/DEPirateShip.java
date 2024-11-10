package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEBaseStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Optional;

import static com.barion.dungeons_enhanced.DEUtil.locate;

public final class DEPirateShip extends Structure {
    public static final Codec<DEPirateShip> CODEC = simpleCodec(DEPirateShip::new);
    private static final ResourceLocation FRONT = locate("pirate_ship/front");
    private static final ResourceLocation BACK = locate("pirate_ship/back");

    public DEPirateShip(StructureSettings settings) {
        super(settings);
    }

    @Override
    @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final var pos = getGenPos(context).below(3);

        return Optional.of(new GenerationStub(pos, (builder) -> {
            builder.addPiece(new DEBaseStructure.Piece(DEStructures.PIRATE_SHIP.getPieceType(), context.structureTemplateManager(), FRONT, pos, Rotation.NONE));
            builder.addPiece(new DEBaseStructure.Piece(DEStructures.PIRATE_SHIP.getPieceType(), context.structureTemplateManager(), BACK, pos.offset(25, 0, 0), Rotation.NONE));
        }));
    }

    @Override
    public @NotNull StructureType<?> type() {
        return DEStructures.PIRATE_SHIP.getType();
    }

    private BlockPos getGenPos(GenerationContext context) {
        return DEUtil.chunkPosToBlockPosFromHeightMap(context.chunkPos(), context.chunkGenerator(), Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor(), context.randomState());
    }
}