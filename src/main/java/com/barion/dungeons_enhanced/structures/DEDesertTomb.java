package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.google.common.collect.ImmutableMap;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.JigsawPlacement;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Random;

public class DEDesertTomb extends GelConfigJigsawStructure {
    public DEDesertTomb(){
        super(JigsawConfiguration.CODEC, DEConfig.COMMON.desert_tomb, 70, true, true);
    }

    @Override
    public JigsawPlacement.PieceFactory getPieceType() {return Piece::new;}

    public static final class Piece extends AbstractGelStructurePiece{
        public Piece(StructureManager structureManager, StructurePoolElement piece, BlockPos pos, int groundLevel, Rotation rotation, BoundingBox bounds) {
            super(structureManager, piece, pos, groundLevel, rotation, bounds);
        }
        public Piece(ServerLevel level, CompoundTag nbt) {
            super(level, nbt);
        }

        @Override
        public StructurePieceType getType() {return DEStructures.DesertTomb.getPieceType();}

        @Override
        public void handleDataMarker(String s, BlockPos blockPos, ServerLevelAccessor serverLevelAccessor, Random random, BoundingBox boundingBox) {}
    }

    public static class Pool{
        public static StructureTemplatePool Root;
        public static void init(){}

        static{
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.Mod_ID, "desert_tomb/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false).processors(DEUtil.Processors.AirToCobweb);
            Root = registry.register("root", poolBuilder.clone().names("root").build());

            registry.register("down", poolBuilder.clone().names("down").build());
            registry.register("trap", poolBuilder.clone().names("trap").build());
            registry.register("cross", poolBuilder.clone().names("t-cross").build());
            registry.register("main", poolBuilder.clone().names(ImmutableMap.of("tunnel", 4, "t-cross", 3, "room", 3, "tomb", 2, "exit", 1)).build());
        }
    }
}