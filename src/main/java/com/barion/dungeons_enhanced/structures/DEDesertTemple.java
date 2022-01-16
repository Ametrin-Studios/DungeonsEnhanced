package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEDesertTemple extends DESimpleStructure {
    private final ResourceLocation Bottom = createRegistryName("desert_temple/down");

    public DEDesertTemple(){
        super(DEConfig.COMMON.desert_temple, new DEPiece("desert_temple/main"));
    }

    @Override
    public void assemble(StructureManager structureManager, DEPiece[] variants, BlockPos pos, Rotation rotation, StructurePiecesBuilder piecesBuilder, int piece) {
        rotation = Rotation.NONE;
        pos = pos.offset(-18, -6, -20);
        piecesBuilder.addPiece(new DESimpleStructure.Piece(structureManager, Variants[0].Resource, pos, rotation));
        piecesBuilder.addPiece(new DESimpleStructure.Piece(structureManager, Bottom, pos.offset(15, -11, 2), rotation));
        piecesBuilder.addPiece(new DESimpleStructure.Piece(structureManager, Bottom, pos.offset(25, -11, 16), rotation));
        piecesBuilder.addPiece(new DESimpleStructure.Piece(structureManager, Bottom, pos.offset(13, -11, 14), rotation));
    }
}