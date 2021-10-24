package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

public class DEDesertTemple extends DESimpleStructure {
    private final ResourceLocation MainPiece = DEStructures.locate("desert_temple/main");
    private final ResourceLocation Bottom = DEStructures.locate("desert_temple/down");

    public DEDesertTemple(){
        super(DEConfig.COMMON.desert_temple);
    }

    @Override
    public void assemble(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand) {
        rotation = Rotation.NONE;
        pos = pos.offset(-18, -6, -20);
        structurePieces.add(new DESimpleStructure.Piece(templateManager, MainPiece, pos, rotation));
        structurePieces.add(new DESimpleStructure.Piece(templateManager, Bottom, pos.offset(15, -11, 2), rotation));
        structurePieces.add(new DESimpleStructure.Piece(templateManager, Bottom, pos.offset(25, -11, 16), rotation));
        structurePieces.add(new DESimpleStructure.Piece(templateManager, Bottom, pos.offset(13, -11, 14), rotation));
    }
}