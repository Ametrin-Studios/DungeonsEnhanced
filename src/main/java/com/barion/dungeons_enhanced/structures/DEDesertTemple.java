package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEDesertTemple extends DESimpleStructure {
    private final ResourceLocation Bottom = createRegistryName("desert_temple/down");

    public DEDesertTemple(){
        super(DEConfig.COMMON.desert_temple, false, new DEPiece("desert_temple/main"));
    }

    @Override
    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand) {
        rotation = Rotation.NONE;
        pos = pos.offset(-18, -6, -20);
        structurePieces.add(new DESimpleStructure.Piece(structureManager, Variants[0].Resource, pos, rotation));
        structurePieces.add(new DESimpleStructure.Piece(structureManager, Bottom, pos.offset(15, -11, 2), rotation));
        structurePieces.add(new DESimpleStructure.Piece(structureManager, Bottom, pos.offset(25, -11, 16), rotation));
        structurePieces.add(new DESimpleStructure.Piece(structureManager, Bottom, pos.offset(13, -11, 14), rotation));
    }
}