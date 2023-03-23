package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.DEJigsawTypes;
import com.barion.dungeons_enhanced.world.DEPools;
import com.legacy.structure_gel.api.structure.jigsaw.ExtendedJigsawStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.IPieceFactory;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawCapability;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class DEDruidCircle {

    public static class Capability implements JigsawCapability.IJigsawCapability{
        public static final Capability Instance = new Capability();
        public static final Codec<Capability> CODEC = Codec.unit(Instance);

        @Override
        public JigsawCapability.JigsawType<?> getType(){return DEJigsawTypes.DruidCircle;}

        @Override
        public IPieceFactory getPieceFactory() {return Piece::new;}

    }

    public static class Piece extends ExtendedJigsawStructurePiece {
        public Piece(IPieceFactory.Context context) {super(context);}
        public Piece(StructurePieceSerializationContext context, CompoundTag nbt) {super(context, nbt);}
        @Override
        public StructurePieceType getType() {return DEStructures.DruidCircle.getPieceType().get();}
        @Override
        public void handleDataMarker(String key, BlockPos blockPos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) {}
    }

    public static void pool(BootstapContext<StructureTemplatePool> context){
        JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "druid_circle/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("top_big", "small").maintainWater(false)).register(DEPools.DRUID_CIRCLE);

        registry.register("bottom_big", registry.poolBuilder().maintainWater(false).names("bottom_big").build());
    }
}