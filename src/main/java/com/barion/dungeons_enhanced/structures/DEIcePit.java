package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;
import java.util.Random;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEIcePit extends DESimpleStructure {
    public DEIcePit(){
        super(DEConfig.COMMON.ice_pit);
        MainPiece = DEStructures.locate("ice_pit/top");
        Pieces = new ResourceLocation[] {DEStructures.locate("ice_pit/var1"), DEStructures.locate("ice_pit/var2"), DEStructures.locate("ice_pit/var3")};
        Offset = Offset(-4,-25,-4);
    }

    @Override
    public void assemble(TemplateManager templateManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand) {
        structurePieces.add(new DESimpleStructure.Piece(templateManager, MainPiece, pos, rotation));
        int offsetY = -6;
        int type = rand.nextInt(Pieces.length);
        DungeonsEnhanced.LOGGER.debug(type);
        if(type == 2){
            offsetY = -11;
        }
        structurePieces.add(new DESimpleStructure.Piece(templateManager, Pieces[type], pos.offset(-17, offsetY,-17), rotation));
    }

    @Override
    public int getSeed() {
        return 571368;
    }
}