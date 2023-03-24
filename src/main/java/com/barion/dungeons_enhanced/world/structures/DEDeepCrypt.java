package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.DEJigsawTypes;
import com.barion.dungeons_enhanced.world.DEPools;
import com.barion.dungeons_enhanced.world.DEProcessors;
import com.legacy.structure_gel.api.structure.jigsaw.*;
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

public class DEDeepCrypt{
    public static class Capability implements JigsawCapability.IJigsawCapability{
        public static final Capability Instance = new Capability();
        public static final Codec<Capability> CODEC = Codec.unit(Instance);

        @Override
        public JigsawCapability.JigsawType<?> getType(){return DEJigsawTypes.DeepCrypt;}
        @Override
        public IPieceFactory getPieceFactory() {return Piece::new;}
    }
    public static class Piece extends ExtendedJigsawStructurePiece {
        public Piece(IPieceFactory.Context context) {super(context);}
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.DEEP_CRYPT.getPieceType().get();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) {}
    }

    public static void pool(BootstapContext<StructureTemplatePool> context){
        JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "deep_crypt/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("root").maintainWater(false)).register(DEPools.DEEP_CRYPT);

        JigsawPoolBuilder basicPool = registry.poolBuilder().maintainWater(false);
        JigsawPoolBuilder Tunnels = basicPool.clone().processors(DEProcessors.AirToCobweb.getKey()).names("tunnel", "cross");
        JigsawPoolBuilder Treasure = basicPool.clone().names("treasure");
        JigsawPoolBuilder Rooms = basicPool.clone().names("big_tunnel", "large_tomb", "prison", "tomb", "tombs", "root");

        registry.register("main", JigsawPoolBuilder.collect(Tunnels.weight(6), Rooms.weight(2), Treasure.weight(1)));
    }
}