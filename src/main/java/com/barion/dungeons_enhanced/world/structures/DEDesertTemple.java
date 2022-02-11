package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;

import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEDesertTemple extends DESimpleStructure {
    private final ResourceLocation Bottom = createRegistryName("desert_temple/down");

    public DEDesertTemple() {super(DEConfig.COMMON.desert_temple, new DEStructurePiece("desert_temple/main"));}

    @Override
    public void assemble(AssembleContext context) {
        Rotation rotation = Rotation.NONE;
        BlockPos pos = context.pos().offset(-18, -6, -20);
        context.piecesBuilder().addPiece(new DESimpleStructure.Piece(context.structureManager(), context.variant().Resource, pos, rotation));
        context.piecesBuilder().addPiece(new DESimpleStructure.Piece(context.structureManager(), Bottom, pos.offset(15, -11, 2), rotation));
        context.piecesBuilder().addPiece(new DESimpleStructure.Piece(context.structureManager(), Bottom, pos.offset(25, -11, 16), rotation));
        context.piecesBuilder().addPiece(new DESimpleStructure.Piece(context.structureManager(), Bottom, pos.offset(13, -11, 14), rotation));
    }
}