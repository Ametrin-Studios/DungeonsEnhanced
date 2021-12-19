package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.registry.registrar.StructureRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.List;
import java.util.Random;

public class DECellar extends DEBaseStructure {
    protected DECellarStructure Parent;

    public <S extends DECellarStructure> DECellar(DEPiece resource, StructureRegistrar<NoneFeatureConfiguration, S> parent) {
        super(parent.getStructure().getConfig(), parent.getStructure().generationType, parent.getStructure().isAllowedNearWorldSpawn(), resource);
        Parent = parent.getStructure();
    }

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeSource biomeSource, long seed, WorldgenRandom rand, ChunkPos chunkPos, Biome biome, ChunkPos potentialChunkPos, NoneFeatureConfiguration config, LevelHeightAccessor level) {
        return Parent.isFeatureChunk(chunkGen, biomeSource, seed, rand, chunkPos, biome, potentialChunkPos, config, level);
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return Parent.isAllowedNearWorldSpawn();}

    @Override
    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand) {
        structurePieces.add(new Piece(structureManager, Variants[0].Resource, pos.offset(Parent.Variants[0].Offset).offset(Variants[0].Offset), rotation));
    }

    @Override
    public int getSeed() {
        return Parent.getSeed();
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            super(DEStructures.Castle.getPieceType(), structureManager, templateName, pos, rotation);
        }
        public Piece(ServerLevel serverLevel, CompoundTag compoundTag) {
            super(DEStructures.CastleB.getPieceType(), compoundTag, serverLevel);
        }
    }
}