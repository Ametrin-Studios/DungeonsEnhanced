package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.builder.DERandomPieceFactory;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public final class DEModularStructure extends Structure {
    private final DERandomPieceFactory _pieceFactory;
    private final Supplier<StructureType<?>> _typeSupplier;

    public DEModularStructure(StructureSettings settings, DERandomPieceFactory pieceFactory, Supplier<StructureType<?>> typeSupplier) {
        super(settings);
        _pieceFactory = pieceFactory;
        _typeSupplier = typeSupplier;
    }

    @Override @NotNull
    protected Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        final var rawPos = DEUtil.ChunkPosToBlockPos(context.chunkPos(), 0);
        var piece = _pieceFactory.createPiece(context.structureTemplateManager(), rawPos, context.random());

        final var size = piece.getSize();
        final var result = DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 4, context.chunkGenerator(), context.heightAccessor(), context.randomState());
        if(!result.getSecond()) {return Optional.empty();}
        final var pos = rawPos.atY(Math.round(result.getFirst()));
        piece.setPosition(pos);

        return Optional.of(new GenerationStub(pos, (builder)-> builder.addPiece(piece)));
    }

    @Override @NotNull
    public StructureType<?> type() {return _typeSupplier.get();}

    public static class Builder{
        private final DERandomPieceFactory _pieceFactory;
        private final Supplier<StructureType<?>> _typeSupplier;

        public Builder(DERandomPieceFactory pieceFactory, Supplier<StructureType<?>> typeSupplier) {
            _pieceFactory = pieceFactory;
            _typeSupplier = typeSupplier;
        }

        public Builder placement(){

            return this;
        }

        public DEModularStructure build(StructureSettings settings){
            return new DEModularStructure(settings, _pieceFactory, _typeSupplier);
        }
    }
}
