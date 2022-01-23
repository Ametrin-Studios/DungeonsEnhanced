package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.structures.prefabs.DEBaseStructure;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DEPieceGeneratorSupplier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;
import java.util.function.Predicate;

public class DEDebugStructure extends DEBaseStructure {
    public DEDebugStructure(){
        super(DEConfig.COMMON.ruined_building, GenerationType.onGround, true, new DEPiece("ruined_building/barn"));
    }

    @Override
    public boolean canGenerate(RegistryAccess registryAccess, ChunkGenerator chunkGen, BiomeSource biomeSource, StructureManager structureManager, long seed, ChunkPos chunkPos, NoneFeatureConfiguration config, LevelHeightAccessor heightAccessor, Predicate<Biome> biomeTest) {

        this.chunkGen = chunkGen;
        this.heightAccessor = heightAccessor;
        int x = chunkPos.x * 16;
        int z = chunkPos.z * 16;
        int y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, this.heightAccessor);
        DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+", "+z);
            /*if(getBlockAt(x, y-1, z) == Blocks.WATER){
                //DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+", "+z+" Canceled because Water");
                return false;
            }
            int tempY = y+3;
            if(getBlockAt(x+3, tempY, z) != Blocks.AIR || getBlockAt(x-3, tempY, z) != Blocks.AIR || getBlockAt(x, tempY, z+3) != Blocks.AIR || getBlockAt(x, tempY, z-3) != Blocks.AIR){
                //DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+", "+z+" Canceled because Cliff");
                return false;
            }
            tempY = y-3;
            if(getBlockAt(x+3, tempY, z) == Blocks.AIR || getBlockAt(x-3, tempY, z) == Blocks.AIR || getBlockAt(x, tempY, z+3) == Blocks.AIR || getBlockAt(x, tempY, z-3) == Blocks.AIR){
                //DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+", "+z+" Canceled because Cliff");
                return false;
            }*/
        return this.pieceGenerator.createGenerator(new DEPieceGeneratorSupplier.Context<>(chunkGen, biomeSource, seed, chunkPos, config, heightAccessor, biomeTest, structureManager, registryAccess)).isPresent();
    }

    @Override
    public void assemble(AssembleContext context) {
        DungeonsEnhanced.LOGGER.info("Structure placed at " + context.pos());

        context.piecesBuilder().addPiece(new Piece(context.structureManager(), Variants[0].Resource, context.pos().offset(0, 30, 0), context.rotation()));
    }

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType) {
            super(DEStructures.RuinedBuilding.getPieceType(), structureManager, templateName, pos, rotation, componentType);
        }

        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            this(structureManager, templateName, pos, rotation, 0);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(DEStructures.RuinedBuilding.getPieceType(), serializationContext, nbt);
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, Random random, BoundingBox box) {}
    }
}