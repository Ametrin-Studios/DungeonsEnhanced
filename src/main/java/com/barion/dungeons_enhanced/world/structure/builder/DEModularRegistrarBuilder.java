package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEModularStructure;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import com.legacy.structure_gel.api.structure.GridStructurePlacement;
import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.Structure;

import javax.annotation.Nullable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public final class DEModularRegistrarBuilder {
    public static DEModularRegistrarBuilder create(Supplier<StructureRegistrar<DEModularStructure>> registrar, String id){return create(registrar, id, null);}
    public static DEModularRegistrarBuilder create(Supplier<StructureRegistrar<DEModularStructure>> registrar, String id, Codec<DEModularStructure> codec) {return create(registrar, DEUtil.location(id), codec);}
    public static DEModularRegistrarBuilder create(Supplier<StructureRegistrar<DEModularStructure>> registrar, ResourceLocation id, Codec<DEModularStructure> codec){
        return new DEModularRegistrarBuilder(registrar, id, codec);
    }

    private final Supplier<StructureRegistrar<DEModularStructure>> _registrar;
    private final StructureRegistrar.Builder<DEModularStructure> _builder;
    private final GridStructurePlacement.Builder _placement = GridStructurePlacement.builder();
    private Codec<DEModularStructure> _codec;

    public DEModularRegistrarBuilder(Supplier<StructureRegistrar<DEModularStructure>> registrar, ResourceLocation resourceLocation, @Nullable Codec<DEModularStructure> codec) {
        _registrar = registrar;
        _codec = codec;
        _builder = StructureRegistrar.builder(resourceLocation, ()-> ()-> _codec);
    }

    public DEModularRegistrarBuilder placement(int spacing){
        return placement(spacing, 1);
    }
    public DEModularRegistrarBuilder placement(int spacing, float probability){
        return placement(spacing, (int)(spacing*0.8), probability);
    }
    public DEModularRegistrarBuilder placement(int spacing, int offset, float probability){
        _placement.spacing(spacing).offset(offset).probability(probability);
        return this;
    }
    public DEModularRegistrarBuilder placement(Consumer<GridStructurePlacement.Builder> placementConsumer){
        placementConsumer.accept(_placement);
        return this;
    }

    public DEModularRegistrarBuilder allowNearSpawn() {return allowedNearSpawn(true);}
    public DEModularRegistrarBuilder allowedNearSpawn(boolean allow){
        _placement.allowedNearSpawn(allow);
        return this;
    }

    public DEModularRegistrarBuilder addStructure(Function<DERandomPieceFactory.Builder, DERandomPieceFactory.Builder> pieceFactoryConsumer, Function<DEModularStructure.Builder, DEModularStructure.Builder> builderConsumer, Function<StructureRegistrar.StructureBuilder<DEModularStructure>, StructureRegistrar.StructureBuilder<DEModularStructure>> configuratorConsumer){
        var pieceFactoryBuilder = pieceFactoryConsumer.apply(new DERandomPieceFactory.Builder());
        var pieceFactory = pieceFactoryBuilder.build(()-> _registrar.get().getPieceType().get());

        var structureBuilder =  builderConsumer.apply(new DEModularStructure.Builder(pieceFactory, ()-> _registrar.get().getType()));

        return addStructure(pieceFactory, structureBuilder, configuratorConsumer);
    }

    public DEModularRegistrarBuilder addStructure(ResourceLocation template, Consumer<DEModularStructure.Builder> builderConsumer, Function<StructureRegistrar.StructureBuilder<DEModularStructure>, StructureRegistrar.StructureBuilder<DEModularStructure>> configuratorConsumer){
        return addStructure(new DEStructureTemplate(template, 0), builderConsumer, configuratorConsumer);
    }
    public DEModularRegistrarBuilder addStructure(DEStructureTemplate template, Consumer<DEModularStructure.Builder> builderConsumer, Function<StructureRegistrar.StructureBuilder<DEModularStructure>, StructureRegistrar.StructureBuilder<DEModularStructure>> configuratorConsumer){
        var pieceFactory = new DESinglePieceFactory(template, ()-> _registrar.get().getPieceType().get());

        var structureBuilder = new DEModularStructure.Builder(pieceFactory, ()-> _registrar.get().getType());
        builderConsumer.accept(structureBuilder);

        return addStructure(pieceFactory, structureBuilder, configuratorConsumer);
    }

    public DEModularRegistrarBuilder addStructure(IDEPieceFactory pieceFactory, DEModularStructure.Builder structureBuilder, Function<StructureRegistrar.StructureBuilder<DEModularStructure>, StructureRegistrar.StructureBuilder<DEModularStructure>> configuratorConsumer){
        _builder.addPiece(()-> pieceFactory::createPiece);

        if(_codec == null) _codec = Structure.simpleCodec(structureBuilder::build);

        var configurator = _builder.pushStructure(structureBuilder::build);
        configuratorConsumer.apply(configurator);
        configurator.popStructure();

        return this;
    }

    public StructureRegistrar<DEModularStructure> build(){
        if (_codec == null) throw new IllegalArgumentException("codec was not given and could not be constructed");
        return _builder.placement(()-> _placement.build(_registrar.get().getRegistryName())).build();
    }
}
