package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

public class DECellarStructure extends DEBaseStructure {
    protected DEPiece Cellar;

    public DECellarStructure(DEPiece resourceTop, DEPiece resourceBottom, StructureConfig config){
        this(config, resourceTop);
        Cellar = resourceBottom;
    }
    public DECellarStructure(StructureConfig config, DEPiece resource){
        super(config, GenerationType.onGround, resource);
        Cellar = null;
    }

    @Override
    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand) {
        pos = pos.offset(Variants[0].Offset);
        structurePieces.add(new DECellarStructure.Piece(structureManager, Variants[0].Resource, pos, rotation));
        if (Cellar != null) {
            structurePieces.add(new DECellarStructure.Piece(structureManager, Cellar.Resource, pos.offset(Cellar.Offset), rotation));
        }
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            super(DEStructures.DruidCircle.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(ServerLevel serverLevel, CompoundTag compoundTag) {
            super(DEStructures.DruidCircle.getPieceType(), compoundTag, serverLevel);
        }
    }
}