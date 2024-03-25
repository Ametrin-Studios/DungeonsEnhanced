package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.registry.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.util.ConfigTemplates;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

import java.util.List;

public class DESwimmingStructure extends DEBaseStructure {

    public DESwimmingStructure(ConfigTemplates.StructureConfig config, boolean generateNear00, DEStructurePiece[] resources) {
        super(config, DETerrainAnalyzer.GenerationType.onWater, generateNear00, resources);
    }

    @Override
    protected boolean checkLocation(ChunkGenerator chunkGen, BiomeProvider biomeProvider, long seed, SharedSeedRandom sharedSeedRand, int chunkPosX, int chunkPosZ, Biome biomeIn, ChunkPos chunkPos, NoFeatureConfig config) {
        return true;
    }

    @Override
    public void assemble(TemplateManager templateManager, DEStructurePiece variant, BlockPos pos, Rotation rotation, List<StructurePiece> pieces, int variantIndex) {
        pieces.add(new Piece(templateManager, variant.Resource, pos, rotation));
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(TemplateManager templateManager, ResourceLocation name, BlockPos pos, Rotation rotation) {
            super(DEStructures.RUINED_BUILDING.getPieceType(), templateManager, name, pos, rotation);
        }

        public Piece(TemplateManager templateManager, CompoundNBT nbt) {
            super(DEStructures.RUINED_BUILDING.getPieceType(), templateManager, nbt);
        }
    }
}
