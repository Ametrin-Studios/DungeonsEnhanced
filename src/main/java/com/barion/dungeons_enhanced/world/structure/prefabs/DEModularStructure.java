package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.world.structure.builder.DEPlacement;
import com.barion.dungeons_enhanced.world.structure.builder.DEPlacementFilter;
import com.barion.dungeons_enhanced.world.structure.builder.IDEPieceFactory;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public final class DEModularStructure extends Structure {
    private final IDEPieceFactory _pieceFactory;
    private final DEPlacement _placementProvider;
    private final ImmutableList<DEPlacementFilter> _filters;
    private final Supplier<StructureType<?>> _typeSupplier;

    public DEModularStructure(StructureSettings settings, IDEPieceFactory pieceFactory, DEPlacement placement, ImmutableList<DEPlacementFilter> filters, Supplier<StructureType<?>> typeSupplier) {
        super(settings);
        _pieceFactory = pieceFactory;
        _placementProvider = placement;
        _filters = filters;
        _typeSupplier = typeSupplier;
    }

    @Override @NotNull
    protected Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        return _placementProvider.getPlacement(context, this::cannotGenerate, _pieceFactory);
    }

    public boolean cannotGenerate(BlockPos pos, GenerationContext context){
        for (var filter : _filters){
            if(filter.cannotGenerate(pos, context)){
                return true;
            }
        }
        return false;
    }

    @Override @NotNull
    public StructureType<?> type() {return _typeSupplier.get();}

    public static class Builder{
        private final IDEPieceFactory _pieceFactory;
        private DEPlacement _placementProvider;
        private final List<DEPlacementFilter> _filters = new ArrayList<>();
        private final Supplier<StructureType<?>> _typeSupplier;

        public Builder(IDEPieceFactory pieceFactory, Supplier<StructureType<?>> typeSupplier) {
            _pieceFactory = pieceFactory;
            _typeSupplier = typeSupplier;
        }

        public Builder placement(DEPlacement placement){
            _placementProvider = placement;
            return this;
        }

        public Builder filter(DEPlacementFilter filter){
            _filters.add(filter);
            return this;
        }

        public DEModularStructure build(StructureSettings settings){
            if(_placementProvider == null) throw new IllegalArgumentException("placement not set");
            return new DEModularStructure(settings, _pieceFactory, _placementProvider, ImmutableList.copyOf(_filters), _typeSupplier);
        }
    }
}
