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
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.ParametersAreNonnullByDefault;

public class DESimpleStructurePiece extends GelTemplateStructurePiece {
    public DESimpleStructurePiece(StructurePieceType structurePieceType, StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
        super(structurePieceType, 0, structureManager, templateName, pos);
        this.rotation = rotation;
        setupPlaceSettings(structureManager);
    }
    public DESimpleStructurePiece(StructurePieceType structurePieceType, CompoundTag nbt, StructurePieceSerializationContext context) {
        super(structurePieceType, nbt, context.structureTemplateManager());
        setupPlaceSettings(context.structureTemplateManager());
    }

    public Vec3i getSize(){
        return template().getSize(getRotation());
    }
    // probably not good
    public void setPosition(BlockPos pos){
        templatePosition = pos;
    }

    @Override @ParametersAreNonnullByDefault
    protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource randomSource, BoundingBox boundingBox) {}
}
