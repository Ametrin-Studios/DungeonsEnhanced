package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.builder.DEPlacementFilter;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructureTemplates;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Supplier;

import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;
import static com.barion.dungeons_enhanced.registry.DEStructures.*;

public class DEGroundStructure extends DEBaseStructure {
    public static final MapCodec<DEGroundStructure> CODEC_JUNGLE_MONUMENT = simpleCodec(DEGroundStructure::JungleMonument);
    public static DEGroundStructure JungleMonument(StructureSettings settings) {
        return new DEGroundStructure(settings, pieceBuilder().yOffset(-9).add("jungle_monument").build(), JUNGLE_MONUMENT::getType);
    }

    public static final MapCodec<DEGroundStructure> CODEC_MINERS_HOUSE = simpleCodec(DEGroundStructure::MinersHouse);
    public static DEGroundStructure MinersHouse(StructureSettings settings) {
        return new DEGroundStructure(settings, pieceBuilder().add("miners_house").build(), MINERS_HOUSE::getType);
    }

    public static final MapCodec<DEGroundStructure> CODEC_MUSHROOM_HOUSE = simpleCodec(DEGroundStructure::MushroomHouse);
    public static DEGroundStructure MushroomHouse(StructureSettings settings) {
        return new DEGroundStructure(settings, pieceBuilder().add("mushroom_house/red").add("mushroom_house/brown").build(), MUSHROOM_HOUSE::getType);
    }

    public static final MapCodec<DEGroundStructure> CODEC_WATCH_TOWER = simpleCodec(DEGroundStructure::WatchTower);
    public static DEGroundStructure WatchTower(StructureSettings settings) {
        return new DEGroundStructure(settings, pieceBuilder().add("watch_tower").build(), WATCH_TOWER::getType);
    }

    public static final MapCodec<DEGroundStructure> CODEC_WITCH_TOWER = simpleCodec(DEGroundStructure::WitchTower);
    public static DEGroundStructure WitchTower(StructureSettings settings) {
        return new DEGroundStructure(settings, pieceBuilder().weight(3).add("witch_tower/normal").weight(2).add("witch_tower/big").build(), WITCH_TOWER::getType);
    }


    public DEGroundStructure(StructureSettings settings, DEStructureTemplates variants, Supplier<StructureType<?>> type) {
        super(settings, variants, type);
    }

    protected static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    @Override
    @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final var piece = _templates.getRandom(context.random());
        final var rawPos = getGenPos(context.chunkPos()).above(piece.yOffset);
        final var rotation = Rotation.getRandom(context.random());

        final var size = context.structureTemplateManager().getOrCreate(piece.Resource).getSize(rotation);

        final var result = DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 4, context.chunkGenerator(), context.heightAccessor(), context.randomState());
        if (!result.getSecond()) {
            return Optional.empty();
        }
        final var pos = rawPos.atY(Math.round(result.getFirst())).above(piece.yOffset);
        if (DEPlacementFilter.NO_WATER.cannotGenerate(pos, context)) {
            return Optional.empty();
        }

        return at(pos, (builder) -> generatePieces(builder, pos, piece, rotation, context, DEGroundStructure::assemble));
    }

    protected BlockPos getGenPos(ChunkPos chunkPos) {
        return DEUtil.chunkPosToBlockPos(chunkPos, 0);
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            super(DEStructures.RUINED_BUILDING.getPieceType(), structureManager, templateName, pos, rotation);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.RUINED_BUILDING.getPieceType(), serializationContext, nbt);
        }
    }
}