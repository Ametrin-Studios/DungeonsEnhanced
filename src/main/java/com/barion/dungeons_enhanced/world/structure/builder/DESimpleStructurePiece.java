package com.barion.dungeons_enhanced.world.structure.builder;

import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.function.Function;

public final class DESimpleStructurePiece extends GelTemplateStructurePiece {

    private final Function<StructurePlaceSettings, StructurePlaceSettings> _settingsFunction;

    public DESimpleStructurePiece(StructurePieceType structurePieceType, StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Function<StructurePlaceSettings, StructurePlaceSettings> settingsFunction, Rotation rotation) {
        super(structurePieceType, 0, structureManager, templateName, pos);
        _settingsFunction = settingsFunction;
        this.rotation = rotation;
        setupPlaceSettings(structureManager);
    }

    public DESimpleStructurePiece(StructurePieceType structurePieceType, CompoundTag nbt, StructurePieceSerializationContext context, Function<StructurePlaceSettings, StructurePlaceSettings> settingsFunction) {
        super(structurePieceType, nbt, context.structureTemplateManager());
        _settingsFunction = settingsFunction;
        setupPlaceSettings(context.structureTemplateManager());
    }

    @Override
    protected StructurePlaceSettings getPlaceSettings(StructureTemplateManager structureManager) {
        // DO NOT USE getRotation in here!
        var size = template().getSize(rotation);
        var pivot = new BlockPos(size.getX() / 2, 0, size.getZ() / 2);
        return _settingsFunction.apply(super.getPlaceSettings(structureManager).setLiquidSettings(LiquidSettings.IGNORE_WATERLOGGING).setRotationPivot(pivot));
    }

    public Vec3i getSize() {
        return template().getSize(getRotation());
    }

    public void setPosition(BlockPos pos) {
        templatePosition = pos;
        boundingBox = template.getBoundingBox(placeSettings, templatePosition);
    } // probably not good

    @Override
    @ParametersAreNonnullByDefault
    protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource randomSource, BoundingBox boundingBox) { }
}
