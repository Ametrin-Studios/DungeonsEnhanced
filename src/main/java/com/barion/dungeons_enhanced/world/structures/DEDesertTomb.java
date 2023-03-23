package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.DEJigsawTypes;
import com.barion.dungeons_enhanced.world.DEPools;
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

import java.util.Map;

public class DEDesertTomb{
    /*private static boolean checkLocation(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context){
        if(context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG)){
            return DETerrainAnalyzer.isFlatEnough(context.chunkPos(), context.chunkGenerator(), DETerrainAnalyzer.defaultCheckSettings, context.heightAccessor(), context.randomState());
        }

        return false;
    }*/

    public static class Capability implements JigsawCapability.IJigsawCapability{
        public static final Capability Instance = new Capability();
        public static final Codec<Capability> CODEC = Codec.unit(Instance);

        @Override
        public JigsawCapability.JigsawType<?> getType(){return DEJigsawTypes.DesertTomb;}
        @Override
        public IPieceFactory getPieceFactory() {return Piece::new;}
    }

    public static class Piece extends ExtendedJigsawStructurePiece {
        public Piece(IPieceFactory.Context context) {super(context);}
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.DesertTomb.getPieceType().get();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) {}
    }

    public static void pool(BootstapContext<StructureTemplatePool> context){
        JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "desert_tomb/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("root").maintainWater(false)).register(DEPools.DESERT_TOMB);

        JigsawPoolBuilder basicPool = registry.poolBuilder().maintainWater(false);
        registry.register("down", basicPool.clone().names("down"));
        registry.register("trap", basicPool.clone().names("trap"));
        registry.register("cross", basicPool.clone().names("t-cross"));
        registry.register("main", basicPool.clone().names(Map.of("tunnel", 5, "t-cross", 4, "room", 4, "tomb", 3, "exit", 2)));
    }
}