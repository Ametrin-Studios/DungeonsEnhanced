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
    protected ResourceLocation Cellar = null;
    protected int CellarOffset;

    public DECellarStructure(String resourceTop, String resourceBottom, BlockPos offset, int cellarOffset, StructureConfig config){
        this(resourceTop, offset, config);
        Cellar = DEStructures.locate(resourceBottom);
        CellarOffset = cellarOffset;
    }
    public DECellarStructure(String resource, BlockPos offset, StructureConfig config){
        super(config, GenerationType.onGround, offset, resource);
        Cellar = null;
    }

    @Override
    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand) {
        structurePieces.add(new DECellarStructure.Piece(structureManager, Pieces[0], pos, rotation));
        if (Cellar != null) {
            structurePieces.add(new DECellarStructure.Piece(structureManager, Cellar, pos.offset(0, CellarOffset, 0), rotation));
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