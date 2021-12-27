package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

public class DECellarStructure extends DEBaseStructure {
    protected DEPiece CellarPiece;
    protected StructureRegistrar<NoneFeatureConfiguration, ?> CellarStructure;
    public int lastPiece;

    public DECellarStructure(DEPiece resourceTop, DEPiece resourceBottom, boolean generateNearSpawn, StructureConfig config){
        super(config, GenerationType.onGround, generateNearSpawn, resourceTop);
        CellarPiece = resourceBottom;
    }

    @Override
    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand) {
        int piece = 0;
        if(Variants.length > 1) {
            int i = rand.nextInt(maxWeight+1);
            for (int j = 0; j < Variants.length; j++) {
                if (Variants[j].Weight >= i) {
                    piece = j;
                    break;
                } else {
                    i -= Variants[j].Weight;
                }
            }
        }
        lastPiece = piece;
        pos = pos.offset(Variants[piece].Offset);
        structurePieces.add(new DECellarStructure.Piece(structureManager, Variants[piece].Resource, pos, rotation));
        if (CellarPiece != null) {
            structurePieces.add(new DECellarStructure.Piece(structureManager, CellarPiece.Resource, pos.offset(CellarPiece.Offset), rotation));
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