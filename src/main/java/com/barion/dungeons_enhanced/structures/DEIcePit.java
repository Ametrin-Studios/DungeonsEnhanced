package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import static com.barion.dungeons_enhanced.DEUtil.Offset;
import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEIcePit extends DESimpleStructure {
    private final ResourceLocation Entrance = createRegistryName("ice_pit/top");
    public DEIcePit() {super(DEConfig.COMMON.ice_pit, Offset(-4, -25, -4), false, new DEPiece("ice_pit/var1"), new DEPiece("ice_pit/var2"), new DEPiece("ice_pit/var3"));}

    @Override
    protected void assemble(StructureManager structureManager, DEPiece[] variants, BlockPos pos, Rotation rotation, StructurePiecesBuilder piecesBuilder, int piece) {
        pos = pos.offset(Variants[0].Offset);
        piecesBuilder.addPiece(new DESimpleStructure.Piece(structureManager, Entrance, pos, rotation));
        int offsetY = -6;
        if(piece == 2) {offsetY = -11;}
        piecesBuilder.addPiece(new DESimpleStructure.Piece(structureManager, Variants[piece].Resource, pos.offset(-17, offsetY,-17), rotation));
    }
}