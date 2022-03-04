package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.world.structures.prefabs.DESimpleStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

import static com.barion.dungeons_enhanced.DEUtil.createRegistryName;

public class DEDesertTemple extends DESimpleStructure {
    private final ResourceLocation Bottom = createRegistryName("desert_temple/down");

    public DEDesertTemple() {super(DEConfig.COMMON.desert_temple, new DEStructurePiece("desert_temple/main"));}

    @Override
    public void assemble(TemplateManager templateManager, DEStructurePiece variant, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, int variantIndex){
        rotation = Rotation.NONE;
        pos = pos.offset(-18, -6, -20);
        pieces.add(new Piece(templateManager, variant.Resource, pos, rotation));
        pieces.add(new Piece(templateManager, Bottom, pos.offset(15, -11, 2), rotation));
        pieces.add(new Piece(templateManager, Bottom, pos.offset(25, -11, 16), rotation));
        pieces.add(new Piece(templateManager, Bottom, pos.offset(13, -11, 14), rotation));
    }
}