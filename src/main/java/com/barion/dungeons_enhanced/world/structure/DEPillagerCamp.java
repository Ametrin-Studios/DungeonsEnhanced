package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.google.common.collect.ImmutableList;
import com.legacy.structure_gel.worldgen.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.worldgen.jigsaw.GelConfigJigsawStructure;
import com.legacy.structure_gel.worldgen.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.worldgen.jigsaw.JigsawRegistryHelper;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.feature.jigsaw.JigsawPattern;
import net.minecraft.world.gen.feature.jigsaw.JigsawPiece;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.Random;

public final class DEPillagerCamp extends GelConfigJigsawStructure {
    public DEPillagerCamp() {
        super(VillageConfig.CODEC, DEConfig.COMMON.PILLAGER_CAMP, 0, true, true);
        Pool.init();
        setSpawnList(EntityClassification.MONSTER, ImmutableList.of(new MobSpawnInfo.Spawners(EntityType.PILLAGER, 4, 1, 3), new MobSpawnInfo.Spawners(EntityType.VINDICATOR, 2, 1, 1)));
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {
        return true;
    }

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(TemplateManager templateManager, JigsawPiece jigsawPiece, BlockPos pos, int groundLevelDelta, Rotation rotation, MutableBoundingBox box) {
            super(templateManager, jigsawPiece, pos, groundLevelDelta, rotation, box);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(templateManager, nbt);
        }

        @Override
        public IStructurePieceType getType() {
            return DEStructures.PILLAGER_CAMP.getPieceType();
        }

        @Override
        public void handleDataMarker(String key, BlockPos pos, IServerWorld world, Random random, MutableBoundingBox box) { }
    }

    public static class Pool {
        public static final JigsawPattern ROOT;

        public static void init() { }

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.MOD_ID, "pillager_camp/");
            ROOT = registry.register("root", registry.builder().names("tent/general").maintainWater(false).build());

            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            JigsawPoolBuilder SleepingTents = poolBuilder.clone().names("tent/sleep1", "tent/sleep2");
            JigsawPoolBuilder Kitchen = poolBuilder.clone().names("tent/kitchen");
            JigsawPoolBuilder Decoration = poolBuilder.clone().names("decoration/campfire", "decoration/cage");
            JigsawPoolBuilder Pillars = poolBuilder.clone().names("decoration/bell", "decoration/pillar");
            JigsawPoolBuilder VanillaDecoration = poolBuilder.clone().namesR(mcFeaturePiece("logs"), mcFeaturePiece("targets"), mcFeaturePiece("tent1"), mcFeaturePiece("tent2"));

            registry.register("feature_plates", poolBuilder.clone().names("plate/var1", "plate/var2").build(), JigsawPattern.PlacementBehaviour.TERRAIN_MATCHING);
            registry.register("features", JigsawPoolBuilder.collect(SleepingTents.weight(2), Kitchen.weight(2), VanillaDecoration.weight(2), Decoration.weight(3), Pillars.weight(1)));
        }

        private static ResourceLocation mcFeaturePiece(String key) {
            return new ResourceLocation("minecraft", "pillager_outpost/feature_" + key);
        }
    }
}