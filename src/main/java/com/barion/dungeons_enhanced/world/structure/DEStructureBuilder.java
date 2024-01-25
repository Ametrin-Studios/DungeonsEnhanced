package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.world.structure.prefabs.DEGroundStructure;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GridStructurePlacement;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

import java.util.function.Consumer;
import java.util.function.Function;

public final class DEStructureBuilder<S extends Structure> {
    private final StructureRegistrar.Builder<S> _builder;
    private final GridStructurePlacement.Builder _placement = GridStructurePlacement.builder();

    public DEStructureBuilder(StructureRegistrar.Builder<S> builder) {
        _builder = builder;
    }

    public static <S extends Structure> DEStructureBuilder<S> create(ResourceLocation id, Codec<S> codec){
        return new DEStructureBuilder<>(StructureRegistrar.builder(id, ()-> () -> codec));
    }

    public DEStructureBuilder<S> placement(int spacing){
        return placement(spacing, (int)(spacing*0.8), 1);
    }
    public DEStructureBuilder<S> placement(int spacing, int probability){
        return placement(spacing, (int)(spacing*0.8), probability);
    }
    public DEStructureBuilder<S> placement(int spacing, int offset, float probability){
        _placement.spacing(spacing).offset(offset).probability(probability);
        return this;
    }
    public DEStructureBuilder<S> placement(Consumer<GridStructurePlacement.Builder> placementConsumer){
        placementConsumer.accept(_placement);
        return this;
    }

    public DEStructureBuilder<S> allowNearSpawn() {return allowNearSpawn(true);}
    public DEStructureBuilder<S> allowNearSpawn(boolean allow){
        _placement.allowedNearSpawn(allow);
        return this;
    }

    public StructureRegistrar<S> build(StructureRegistrar<S> registrar, Function<Structure.StructureSettings, S> pieceFactory){
         return _builder
                 .placement(()-> _placement.build(registrar))
                .addPiece(()-> DEGroundStructure.Piece::new)
                .pushStructure(pieceFactory)
                .dimensions(Level.OVERWORLD)
                .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build();
    }
}
