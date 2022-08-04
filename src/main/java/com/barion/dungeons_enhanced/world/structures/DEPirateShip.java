package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESwimmingStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEPirateShip extends DESwimmingStructure {
    private static final ResourceLocation Back = location("pirate_ship/back");

    public DEPirateShip(StructureSettings settings) {super(settings, DEUtil.pieceBuilder().offset(-7, -3, -25).add("pirate_ship/front").build(), DEPirateShip::assembleShip, DEStructures.PirateShip::getType);}

    public static void assembleShip(DEPieceAssembler.Context context) {
        Rotation rotation = Rotation.NONE;
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), Back, context.pos().offset(0, 0, 25), rotation));
    }
}