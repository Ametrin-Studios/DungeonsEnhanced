package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEGroundStructure;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GridStructurePlacement;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class DEStructureBuilder<S extends Structure> {
    private final StructureRegistrar.Builder<S> _builder;
    private final GridStructurePlacement.Builder _placement = GridStructurePlacement.builder();

    public DEStructureBuilder(StructureRegistrar.Builder<S> builder) {
        _builder = builder;
    }

    static {
        DEStructureBuilder.create(DEGroundStructure.ID_RUINED_BUILDING, DEGroundStructure.CODEC_RUINED_BUILDING)
                .placement(12, 1).allowNearSpawn()
                .addPiece(()-> DEGroundStructure.Piece::new)
                .addStructure(DEGroundStructure::RuinedBuilding)
                    .dimensions(Level.OVERWORLD)
                    .terrainAdjustment(TerrainAdjustment.BEARD_THIN)
                .popStructure()
                .build(DEStructures.RUINED_BUILDING);
    }

    public static <S extends Structure> DEStructureBuilder<S> create(String id, Codec<S> codec) {return create(DEUtil.location(id), codec);}
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

    public DEStructureBuilder<S> addPiece(Supplier<StructurePieceType> piece){
        _builder.addPiece(piece);
        return this;
    }

    public DEStructureBuilder<S>.SubStructure addStructure(Function<Structure.StructureSettings, S> factory){
        return new SubStructure(this, _builder.pushStructure(factory));
    }

    public StructureRegistrar<S> build(StructureRegistrar<S> registrar){
         return _builder.placement(()-> _placement.build(registrar)).build();
    }

    public class SubStructure{
        private final DEStructureBuilder<S> _parent;
        private final StructureRegistrar.StructureBuilder<S> _builder;

        public SubStructure(DEStructureBuilder<S> parent, StructureRegistrar.StructureBuilder<S> builder) {
            _parent = parent;
            _builder = builder;
        }

        @SafeVarargs
        public final SubStructure dimensions(final ResourceKey<Level>... dimensions){
            _builder.dimensions(dimensions);
            return this;
        }

        public SubStructure terrainAdjustment(TerrainAdjustment adjustment){
            _builder.terrainAdjustment(adjustment);
            return this;
        }

        public DEStructureBuilder<S> popStructure(){
            return _parent;
        }
    }
}
