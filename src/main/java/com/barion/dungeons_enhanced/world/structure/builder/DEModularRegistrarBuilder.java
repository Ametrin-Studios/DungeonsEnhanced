package com.barion.dungeons_enhanced.world.structure.builder;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEModularStructure;
import com.google.common.collect.ImmutableList;
import com.legacy.structure_gel.registrars.GelStructureRegistrar;
import com.legacy.structure_gel.registrars.StructureRegistrar2;
import com.legacy.structure_gel.util.ConfigTemplates;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public final class DEModularRegistrarBuilder {
    public static DEModularRegistrarBuilder create(String id, Supplier<StructureRegistrar2<NoFeatureConfig, DEModularStructure>> registrar) {return new DEModularRegistrarBuilder(()-> registrar.get().getPieceType(), DEUtil.location(id));}

    private final ResourceLocation _resourceLocation;
    private final Supplier<IStructurePieceType> _pieceType;
    private GenerationStage.Decoration _generationStage = GenerationStage.Decoration.SURFACE_STRUCTURES;
    private ConfigTemplates.StructureConfig _config = null;
    private IDEPieceFactory _pieceFactory = null;
    private DEPlacement _placement = DEPlacement.ON_WORLD_SURFACE;
    private final List<DEPlacementFilter> _filters = new ArrayList<>();
    private boolean _allowedNearSpawn = false;

    public DEModularRegistrarBuilder(Supplier<IStructurePieceType> pieceType, ResourceLocation resourceLocation) {
        _resourceLocation = resourceLocation;
        _pieceType = pieceType;
    }

    public DEModularRegistrarBuilder config(ConfigTemplates.StructureConfig config){
        _config = config;
        return this;
    }

    public DEModularRegistrarBuilder piece(String template){return piece(template, 0);}
    public DEModularRegistrarBuilder piece(ResourceLocation template){return piece(template, 0);}
    public DEModularRegistrarBuilder piece(String template, int yOffset){return piece(new DEStructureTemplate(DEUtil.location(template), yOffset));}
    public DEModularRegistrarBuilder piece(ResourceLocation template, int yOffset){return piece(new DEStructureTemplate(template, yOffset));}
    public DEModularRegistrarBuilder piece(DEStructureTemplate template){
        _pieceFactory = new DESinglePieceFactory(template, _pieceType);
        return this;
    }

    public DEModularRegistrarBuilder piece(Function<DERandomPieceFactory.Builder, DERandomPieceFactory.Builder> pieceFactoryConsumer){
        _pieceFactory = pieceFactoryConsumer.apply(new DERandomPieceFactory.Builder()).build(_pieceType);
        return this;
    }

    public DEModularRegistrarBuilder placement(DEPlacement placement) {
        _placement = placement;
        if(_placement == DEPlacement.UNDERGROUND){
            stage(GenerationStage.Decoration.UNDERGROUND_STRUCTURES);
        }
        return this;
    }

    public DEModularRegistrarBuilder stage(GenerationStage.Decoration stage){
        _generationStage = stage;
        return this;
    }

    public DEModularRegistrarBuilder addFilter(DEPlacementFilter filter){
        _filters.add(filter);
        return this;
    }

    public DEModularRegistrarBuilder allowNearSpawn() {return allowedNearSpawn(true);}
    public DEModularRegistrarBuilder allowedNearSpawn(boolean allow){
        _allowedNearSpawn = allow;
        return this;
    }
    public StructureRegistrar2<NoFeatureConfig, DEModularStructure> build(){
        if(_config == null) throw new NullPointerException("structure config of " + _resourceLocation + " has not been configured");
        if(_pieceFactory == null) throw new NullPointerException("structure pieces of " + _resourceLocation + " have not been configured");
        return GelStructureRegistrar.of(_resourceLocation, new DEModularStructure(_config, _pieceFactory, _placement, ImmutableList.copyOf(_filters), _allowedNearSpawn), _pieceFactory::createPiece, NoFeatureConfig.NONE, _generationStage).handle();
    }
}
