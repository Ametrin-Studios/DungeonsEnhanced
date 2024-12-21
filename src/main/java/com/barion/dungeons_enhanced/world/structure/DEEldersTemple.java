package com.barion.dungeons_enhanced.world.structure;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.DEBaseStructure;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePiece;
import com.barion.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import com.barion.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;
import com.google.common.collect.ImmutableList;
import com.legacy.structure_gel.worldgen.GelPlacementSettings;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

import static com.barion.dungeons_enhanced.DEUtil.locate;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public final class DEEldersTemple extends DEBaseStructure {
    private static final ResourceLocation NE = locate("elders_temple/ne");
    private static final ResourceLocation NW = locate("elders_temple/nw");
    private static final ResourceLocation SE = locate("elders_temple/se");
    private static final ResourceLocation SW = locate("elders_temple/sw");

    public DEEldersTemple() {
        super(DEConfig.COMMON.ELDERS_TEMPLE, DETerrainAnalyzer.GenerationType.underwater, false, pieceBuilder().add("elders_temple/se").build());
        setSpawnList(EntityClassification.MONSTER, ImmutableList.of(new MobSpawnInfo.Spawners(EntityType.GUARDIAN, 2, 2, 4)));
    }

    @Override
    protected boolean checkLocation(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, NoFeatureConfig config) {
        if (DETerrainAnalyzer.isUnderwater(chunkPos, chunkGen, 32)) {
            return DETerrainAnalyzer.areNearbyBiomesValid(biomeProvider, chunkPos, chunkGen, 30, getConfig());
        }
        return false;
    }

    @Override
    public void assemble(TemplateManager templateManager, DEStructurePiece variant, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, int variantIndex) {
        rotation = Rotation.NONE;
        int yOffset = -5;
        pieces.add(new Piece(templateManager, SE, pos.offset(0, yOffset, 0), rotation));
        pieces.add(new Piece(templateManager, SW, pos.offset(-30, yOffset, 0), rotation));
        pieces.add(new Piece(templateManager, NE, pos.offset(0, yOffset, -29), rotation));
        pieces.add(new Piece(templateManager, NW, pos.offset(-30, yOffset, -29), rotation));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(TemplateManager templateManager, ResourceLocation name, BlockPos pos, Rotation rotation) {
            super(DEStructures.ELDERS_TEMPLE.getPieceType(), templateManager, name, pos, rotation);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(DEStructures.ELDERS_TEMPLE.getPieceType(), templateManager, nbt);
        }

        @Override
        public void addProcessors(TemplateManager templateManager, PlacementSettings placementSettings) {
            ((GelPlacementSettings) placementSettings).setMaintainWater(true);
            placementSettings.clearProcessors();
            placementSettings.addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            placementSettings.addProcessor(DEUnderwaterProcessor.INSTANCE);
            placementSettings.addProcessor(DESwapDeadCoralsProcessor.INSTANCE);
        }
    }
}