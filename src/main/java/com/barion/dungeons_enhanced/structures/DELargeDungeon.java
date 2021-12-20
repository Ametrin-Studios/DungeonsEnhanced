package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DELargeDungeon extends DESimpleStructure {
    private final ResourceLocation Top = createRegistryName("large_dungeon/top");
    private final ResourceLocation Stairs = createRegistryName("large_dungeon/stairs");
    private final ResourceLocation MainPiece = createRegistryName("large_dungeon/main");

    public DELargeDungeon(){
        super(DEConfig.COMMON.large_dungeon, true);
    }

    @Override
    public void assemble(StructureManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand) {
        rotation = Rotation.NONE;
        structurePieces.add(new DESimpleStructure.Piece(templateManager, Top, pos.offset(-3, -2, -3), rotation));
        structurePieces.add(new DESimpleStructure.Piece(templateManager, Stairs, pos.offset(-2, -10, -2), rotation));
        structurePieces.add(new DESimpleStructure.Piece(templateManager, MainPiece, pos.offset(-23, -21, -29), rotation));
    }
}