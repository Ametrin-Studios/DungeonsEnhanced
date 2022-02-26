package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
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

public class DEDeepCrypt extends GelConfigJigsawStructure{
    public DEDeepCrypt(){
        super(JigsawConfiguration.CODEC, DEConfig.COMMON.deep_crypt, -10, true, false);
        Pool.init();
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return true;}

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(StructureManager structureManager, StructurePoolElement poolElement, BlockPos pos, int groundLevelDelta, Rotation rotation, BoundingBox bounds) {
            super(structureManager, poolElement, pos, groundLevelDelta, rotation, bounds);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.DeepCrypt.getPieceType();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, Random random, BoundingBox box) {}
    }

    public static class Pool{
        public static StructureTemplatePool Root;
        public static void init(){}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.Mod_ID, "deep_crypt/");
            Root = registry.register("root", registry.builder().names("root").maintainWater(false).build());

            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            JigsawPoolBuilder Tunnels = poolBuilder.clone().processors(DEUtil.Processors.AirToCobweb).names("tunnel", "cross");
            JigsawPoolBuilder Rooms = poolBuilder.clone().names("big_tunnel", "large_tomb", "prison", "tomb", "tombs", "treasure", "root");

            registry.register("main", JigsawPoolBuilder.collect(Tunnels.weight(3), Rooms.weight(1)));
        }
    }
}