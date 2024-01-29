package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.world.structure.builder.DEPlacement;
import com.barion.dungeons_enhanced.world.structure.builder.IDEPieceFactory;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Supplier;

public final class DEModularStructure extends Structure {
    private final IDEPieceFactory _pieceFactory;
    private final DEPlacement _placementProvider;
    private final Supplier<StructureType<?>> _typeSupplier;

    public DEModularStructure(StructureSettings settings, IDEPieceFactory pieceFactory, DEPlacement placement, Supplier<StructureType<?>> typeSupplier) {
        super(settings);
        _pieceFactory = pieceFactory;
        _placementProvider = placement;
        _typeSupplier = typeSupplier;
    }

    @Override @NotNull
    protected Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        return _placementProvider.getPlacement(context, _pieceFactory);
    }

    @Override @NotNull
    public StructureType<?> type() {return _typeSupplier.get();}

    public static class Builder{
        private final IDEPieceFactory _pieceFactory;
        private DEPlacement _placementProvider;
        private final Supplier<StructureType<?>> _typeSupplier;

        public Builder(IDEPieceFactory pieceFactory, Supplier<StructureType<?>> typeSupplier) {
            _pieceFactory = pieceFactory;
            _typeSupplier = typeSupplier;
        }

        public Builder placement(DEPlacement placement){
            _placementProvider = placement;
            return this;
        }

        public DEModularStructure build(StructureSettings settings){
            if(_placementProvider == null) throw new IllegalArgumentException("placement not set");
            return new DEModularStructure(settings, _pieceFactory, _placementProvider, _typeSupplier);
        }
    }
}
