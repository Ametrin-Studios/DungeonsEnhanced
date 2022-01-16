package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.google.common.collect.ImmutableMap;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Random;

public class DEDruidCircle extends GelConfigJigsawStructure {
    public DEDruidCircle(){
        super(JigsawConfiguration.CODEC, DEConfig.COMMON.druid_circle, 1, true, true);
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return true;}

    public static final class Piece extends AbstractGelStructurePiece{
        public Piece(StructureManager structureManager, StructurePoolElement piece, BlockPos pos, int groundLevel, Rotation rotation, BoundingBox bounds) {
            super(structureManager, piece, pos, groundLevel, rotation, bounds);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.DruidCircle.getPieceType();}

        @Override
        public void handleDataMarker(String s, BlockPos blockPos, ServerLevelAccessor serverLevelAccessor, Random random, BoundingBox boundingBox) {}
    }

    public static class Pool{
        public static StructureTemplatePool Root;
        public static void init(){}

        static{
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.Mod_ID, "druid_circle/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            Root = registry.register("root", poolBuilder.clone().names(ImmutableMap.of("top_big", 2, "small", 3)).build());

            registry.register("bottom_big", poolBuilder.clone().names("bottom_big").build());
        }
    }
}