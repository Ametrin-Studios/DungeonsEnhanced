package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.worldgen.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.worldgen.jigsaw.GelConfigJigsawStructure;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.feature.jigsaw.JigsawManager;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public class DEMonsterMaze extends GelConfigJigsawStructure {
    public DEMonsterMaze(){
        super(VillageConfig.CODEC, DEConfig.COMMON.monster_maze, -17, true, true);
    }

    @Override
    public JigsawManager.IPieceFactory getPieceType() {
        return Piece::new;
    }

    public static final class Piece extends AbstractGelStructurePiece{
        public Piece(TemplateManager template, JigsawPiece piece, BlockPos pos, int groundLevel, Rotation rotation, MutableBoundingBox bounds){
            super(template, piece, pos, groundLevel, rotation, bounds);
        }
        public Piece(TemplateManager template, CompoundNBT nbt){super(template, nbt);}

        @Override
        public IStructurePieceType getType() {
            return DEStructures.MonsterMaze.getPieceType();
        }

        @Override
        public void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random rand, MutableBoundingBox bounds) {

        }
    }
}
