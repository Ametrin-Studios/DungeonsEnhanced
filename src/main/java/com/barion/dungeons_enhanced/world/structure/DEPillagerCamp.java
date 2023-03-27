package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.DEJigsawTypes;
import com.barion.dungeons_enhanced.world.DEPools;
import com.legacy.structure_gel.api.structure.jigsaw.*;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class DEPillagerCamp{
    public static final String ID = "pillager_camp";

    public static class Capability implements JigsawCapability.IJigsawCapability{
        public static final Capability INSTANCE = new Capability();
        public static final Codec<Capability> CODEC = Codec.unit(INSTANCE);

        @Override
        public JigsawCapability.JigsawType<?> getType(){return DEJigsawTypes.PILLAGER_CAMP;}
        @Override
        public IPieceFactory getPieceFactory() {return Piece::new;}
    }
    public static class Piece extends ExtendedJigsawStructurePiece {
        public Piece(IPieceFactory.Context context) {super(context);}
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.PILLAGER_CAMP.getPieceType().get();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, RandomSource random, BoundingBox box) {}
    }

    public static void pool(BootstapContext<StructureTemplatePool> context){
        JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "pillager_camp/", context);
        registry.registerBuilder().pools(registry.poolBuilder().names("tent/general").maintainWater(false)).register(DEPools.PILLAGER_CAMP);

        JigsawPoolBuilder basicPool = registry.poolBuilder().maintainWater(false);
        JigsawPoolBuilder SleepingTents = basicPool.clone().names("tent/sleep1", "tent/sleep2");
        JigsawPoolBuilder Kitchen = basicPool.clone().names("tent/kitchen");
        JigsawPoolBuilder Decoration = basicPool.clone().names("decoration/campfire", "decoration/cage");
        JigsawPoolBuilder Pillars = basicPool.clone().names("decoration/bell", "decoration/pillar");
        JigsawPoolBuilder VanillaDecoration = basicPool.clone().namesR(mcPiece("logs"), mcPiece("targets"), mcPiece("tent1"), mcPiece("tent2"));

        registry.registerBuilder().pools(basicPool.clone().names("plate/var1", "plate/var2")).projection(StructureTemplatePool.Projection.TERRAIN_MATCHING).register("feature_plates");
        registry.register("features", JigsawPoolBuilder.collect(SleepingTents.weight(2), Kitchen.weight(2), VanillaDecoration.weight(2), Decoration.weight(3), Pillars.weight(1)));
    }

    private static ResourceLocation mcPiece(String key){
        return new ResourceLocation("pillager_outpost/feature_" + key);
    }
}