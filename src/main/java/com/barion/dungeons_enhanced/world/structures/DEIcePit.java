package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

import static com.barion.dungeons_enhanced.DEUtil.Offset;
import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEIcePit extends DESimpleStructure {
    private final ResourceLocation Entrance = createRegistryName("ice_pit/top");
    public DEIcePit() {super(DEConfig.COMMON.ice_pit, Offset(-4, -25, -4), false, new DEStructurePiece("ice_pit/var1"), new DEStructurePiece("ice_pit/var2"), new DEStructurePiece("ice_pit/var3"));}

    @Override
    public void assemble(AssembleContext context) {
        BlockPos pos = context.pos();
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), Entrance, pos, context.rotation()));
        int offsetY = -6;
        if(context.variant().Resource.getPath().contains("var3")) {offsetY = -11;}
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.variant().Resource, pos.offset(-17, offsetY,-17), context.rotation()));
    }
}