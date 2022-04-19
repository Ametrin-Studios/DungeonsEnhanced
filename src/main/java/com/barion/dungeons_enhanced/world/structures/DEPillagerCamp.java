package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
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

public class DEPillagerCamp extends GelConfigJigsawStructure<JigsawConfiguration> {
    public DEPillagerCamp() {
        super(JigsawConfiguration.CODEC, DEConfig.COMMON.pillager_camp, 1, true, true);
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
        public StructurePieceType getType() {return DEStructures.PillagerCamp.getPieceType();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, Random random, BoundingBox box) {}
    }

    public static class Pool{
        public static Holder<StructureTemplatePool> Root;
        public static void init() {}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID, "pillager_camp/");
            Root = registry.register("root", registry.builder().names("tent/general").maintainWater(false).build());

            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            JigsawPoolBuilder SleepingTents = poolBuilder.clone().names("tent/sleep1", "tent/sleep2");
            JigsawPoolBuilder Kitchen = poolBuilder.clone().names("tent/kitchen");
            JigsawPoolBuilder Campfire = poolBuilder.clone().names("decoration/bell", "decoration/campfire", "decoration/pillar");
            JigsawPoolBuilder Pillars = poolBuilder.clone().names("decoration/bell", "decoration/campfire", "decoration/pillar");
            JigsawPoolBuilder Decoration = poolBuilder.clone().namesR(mcFeaturePiece("cage1"), mcFeaturePiece("cage2"), mcFeaturePiece("logs"), mcFeaturePiece("targets"), mcFeaturePiece("tent1"), mcFeaturePiece("tent2"));

            registry.register("feature_plates", poolBuilder.clone().names("plate/var1", "plate/var2").build(), StructureTemplatePool.Projection.TERRAIN_MATCHING);
            registry.register("features", JigsawPoolBuilder.collect(SleepingTents.weight(1), Kitchen.weight(1), Decoration.weight(2), Campfire.weight(2), Pillars.weight(1)));
        }

        private static ResourceLocation mcFeaturePiece(String key){
            return new ResourceLocation("minecraft", "pillager_outpost/feature_" + key);
        }
    }
}