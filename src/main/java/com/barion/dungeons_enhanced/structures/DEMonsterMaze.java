package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.jigsaw.AbstractGelStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Random;

public class DEMonsterMaze extends GelConfigJigsawStructure {
    public DEMonsterMaze(){
        super(JigsawConfiguration.CODEC, DEConfig.COMMON.monster_maze, -17, true, true);
    }

    @Override
    public JigsawPlacement.PieceFactory getPieceType() {
        return Piece::new;
    }

    public static final class Piece extends AbstractGelStructurePiece {
        public Piece(StructureManager manager, StructurePoolElement piece, BlockPos pos, int groundLevel, Rotation rotation, BoundingBox bounds){
            super(manager, piece, pos, groundLevel, rotation, bounds);
        }
        public Piece(ServerLevel level, CompoundTag nbt){super(level, nbt);}

        @Override
        public StructurePieceType getType() {
            return DEStructures.MonsterMaze.getPieceType();
        }

        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor serverLevelAccessor, Random random, BoundingBox boundingBox) {

        }
    }
}
