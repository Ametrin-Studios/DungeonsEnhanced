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

import static com.barion.dungeons_enhanced.DEUtil.Offset;
import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEIcePit extends DESimpleStructure {
    private final ResourceLocation Entrance = location("ice_pit/top");
    public DEIcePit() {super(DEConfig.COMMON.ice_pit, Offset(-4, -25, -4), false, new DEStructurePiece("ice_pit/var1"), new DEStructurePiece("ice_pit/var2"), new DEStructurePiece("ice_pit/var3"));}

    @Override
    public void assemble(TemplateManager templateManager, DEStructurePiece variant, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, int variantIndex) {
        pieces.add(new Piece(templateManager, Entrance, pos, rotation));

        int offsetY = -6;
        if(variantIndex == 2) {offsetY = -11;}
        pieces.add(new Piece(templateManager, variant.Resource, pos.offset(-17, offsetY, -17), rotation));
    }
}