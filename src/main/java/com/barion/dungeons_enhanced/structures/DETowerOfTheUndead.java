package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DETowerOfTheUndead extends DESimpleStructure {
    public DETowerOfTheUndead(){
        super(DEConfig.COMMON.tower_of_the_undead, new DEPiece("tower_of_the_undead/big", Offset(-7, 0, -7)), new DEPiece("tower_of_the_undead/small", Offset(-5, 0, -5)));
    }

    @Override
    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand){
        int i = rand.nextInt(5);
        if(i>3) {i=0;}
        else {i=1;}
        structurePieces.add(new DESimpleStructure.Piece(structureManager, Variants[i].Resource, pos.offset(Variants[i].Offset), rotation));
    }
}