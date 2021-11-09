package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

public class DEIcePit extends DESimpleStructure {
    private final ResourceLocation Entrance = DEStructures.locate("ice_pit/top");
    public DEIcePit(){
        super(DEConfig.COMMON.ice_pit, Offset(-4, -25, -4), "ice_pit/var1", "ice_pit/var2", "ice_pit/var3");
    }

    @Override
    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand) {
        structurePieces.add(new DESimpleStructure.Piece(structureManager, Entrance, pos, rotation));
        int offsetY = -6;
        int type = rand.nextInt(Pieces.length);
        DungeonsEnhanced.LOGGER.debug(type);
        if(type == 2){
            offsetY = -11;
        }
        structurePieces.add(new DESimpleStructure.Piece(structureManager, Pieces[type], pos.offset(-17, offsetY,-17), rotation));
    }
}