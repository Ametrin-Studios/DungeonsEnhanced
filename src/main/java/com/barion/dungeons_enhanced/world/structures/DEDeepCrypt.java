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
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Random;

public class DEDeepCrypt extends GelConfigJigsawStructure<JigsawConfiguration>{
    public DEDeepCrypt(){
        super(JigsawConfiguration.CODEC, DEConfig.COMMON.DeepCrypt, -10, true, false);
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
        public static Holder<StructureTemplatePool> Root;
        public static void init(){}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID, "deep_crypt/");
            Root = registry.register("root", registry.builder().names("root").maintainWater(false).build());

            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            JigsawPoolBuilder Tunnels = poolBuilder.clone().processors(DEUtil.Processors.AirToCobweb).names("tunnel", "cross");
            JigsawPoolBuilder Treasure = poolBuilder.clone().names("treasure");
            JigsawPoolBuilder Rooms = poolBuilder.clone().names("big_tunnel", "large_tomb", "prison", "tomb", "tombs", "root");

            registry.register("main", JigsawPoolBuilder.collect(Tunnels.weight(6), Rooms.weight(2), Treasure.weight(1)));
        }
    }
}