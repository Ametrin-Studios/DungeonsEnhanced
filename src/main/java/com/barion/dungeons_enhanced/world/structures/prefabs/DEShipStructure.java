package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class DEShipStructure extends DEBaseStructure {

    public DEShipStructure(StructureConfig config, DEStructurePiece[] resources) {this(config, true, resources);}

    public DEShipStructure(StructureConfig config, boolean generateNearSpawn, DEStructurePiece[] resources){
        this(config, generateNearSpawn, false, DEShipStructure::assemble, resources);
    }
    protected DEShipStructure(StructureConfig config, boolean generateNearSpawn, boolean checkBiomeArea, DEPieceAssembler assembler, DEStructurePiece[] resources){
        super(config, DETerrainAnalyzer.GenerationType.onWater, generateNearSpawn, checkBiomeArea, assembler, resources);
    }

    private static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType) {
            super(DEStructures.FishingShip.getPieceType(), structureManager, templateName, pos, rotation, componentType);
        }

        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            this(structureManager, templateName, pos, rotation, 0);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.FishingShip.getPieceType(), serializationContext, nbt);
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, Random random, BoundingBox box) {}
    }
}