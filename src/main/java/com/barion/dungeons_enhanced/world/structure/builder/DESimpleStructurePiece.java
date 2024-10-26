package com.barion.dungeons_enhanced.world.structure.builder;

import com.legacy.structure_gel.worldgen.GelPlacementSettings;
import com.legacy.structure_gel.worldgen.structure.GelTemplateStructurePiece;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Function;

public final class DESimpleStructurePiece extends GelTemplateStructurePiece {
    private final Function<PlacementSettings, PlacementSettings> _settingsFunction;
    public DESimpleStructurePiece(IStructurePieceType structurePieceType, TemplateManager templateManager, ResourceLocation template, BlockPos pos, Rotation rotation, Function<PlacementSettings, PlacementSettings> settingsFunction) {
        super(structurePieceType, template, 0);
        this.templatePosition = pos;
        this.rotation = rotation;
        _settingsFunction = settingsFunction;
        setupTemplate(templateManager);
    }

    public DESimpleStructurePiece(IStructurePieceType structurePieceType, TemplateManager templateManager, CompoundNBT nbt, Function<PlacementSettings, PlacementSettings> settingsFunction) {
        super(structurePieceType, nbt);
        _settingsFunction = settingsFunction;
        setupTemplate(templateManager);
    }

    public BlockPos getSize() { return template.getSize(getRotation()); }

    @Override
    public PlacementSettings createPlacementSettings(TemplateManager templateManager) {
        BlockPos sizePos = templateManager.get(name).getSize(); //rotation and template get set later. This is to determine where to rotate
        BlockPos pivot = new BlockPos(sizePos.getX() / 2, 0, sizePos.getZ() / 2);
        return _settingsFunction.apply(new GelPlacementSettings().setMaintainWater(false).setRotationPivot(pivot).setRotation(rotation).setMirror(mirror));
    }

    public void setPosition(BlockPos pos) {
        templatePosition = pos;
        boundingBox = template.getBoundingBox(placeSettings, templatePosition);
    } // probably not good

    @Override @ParametersAreNonnullByDefault
    protected void handleDataMarker(String key, BlockPos pos, IServerWorld serverWorld, Random random, MutableBoundingBox bounds) { }
}
