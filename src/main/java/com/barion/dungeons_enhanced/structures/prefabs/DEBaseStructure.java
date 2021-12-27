package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.structure.GelConfigStructure;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import com.legacy.structure_gel.api.structure.processor.RemoveGelStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class DEBaseStructure extends GelConfigStructure<NoneFeatureConfiguration> {
    protected ChunkGenerator chunkGen;
    protected LevelHeightAccessor heightAccessor;
    protected DEPiece[] Variants;
    protected final GenerationType generationType;
    protected int maxWeight;
    protected boolean generateNearSpawn;

    public DEBaseStructure(StructureConfig config, GenerationType generation, boolean generateNearSpawn, DEPiece... resources) {
        this(config, generation, generateNearSpawn);
        Variants = resources;
        maxWeight = getMaxWeight();
    }

    public DEBaseStructure(StructureConfig config, GenerationType generation, BlockPos offset, boolean generateNearSpawn, DEPiece... resources) {
        this(config, generation, generateNearSpawn);
        for(DEPiece resource : resources){
            resource.Offset = offset;
        }
        Variants = resources;
        maxWeight = getMaxWeight();
    }

    private DEBaseStructure(StructureConfig config, GenerationType generationType, boolean generateNearWorldSpawn){
        super(NoneFeatureConfiguration.CODEC, config);
        this.generationType = generationType;
        this.generateNearSpawn = generateNearWorldSpawn;
        setLakeProof(true);
    }

    @Override @Nonnull
    public StructureStartFactory<NoneFeatureConfiguration> getStartFactory() {
        return Start::new;
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return generateNearSpawn;}

    @Override
    protected boolean isFeatureChunk(ChunkGenerator chunkGen, BiomeSource biomeSource, long seed, WorldgenRandom rand, ChunkPos chunkPos, Biome biome, ChunkPos potentialChunkPos, NoneFeatureConfiguration config, LevelHeightAccessor heightAccessor) {
        if(generationType == GenerationType.onGround) {
            this.chunkGen = chunkGen;
            this.heightAccessor = heightAccessor;
            int x = chunkPos.x * 16;
            int z = chunkPos.z * 16;
            int y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
            if(getBlockAt(x, y-1, z) == Blocks.WATER){
                DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+" Canceled because Water");
                return false;
            }
            int tempY = y+3;
            if(getBlockAt(x+3, tempY, z) != Blocks.AIR || getBlockAt(x-3, tempY, z) != Blocks.AIR || getBlockAt(x, tempY, z+3) != Blocks.AIR || getBlockAt(x, tempY, z-3) != Blocks.AIR){
                DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+" Canceled because Cliff");
                return false;
            }
            tempY = y-3;
            if(getBlockAt(x+3, tempY, z) == Blocks.AIR || getBlockAt(x-3, tempY, z) == Blocks.AIR || getBlockAt(x, tempY, z+3) == Blocks.AIR || getBlockAt(x, tempY, z-3) == Blocks.AIR){
                DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+" Canceled because Cliff");
                return false;
            }
        }

        return super.isFeatureChunk(chunkGen, biomeSource, seed, rand, chunkPos, biome, potentialChunkPos, config, heightAccessor);
    }

    public class Start extends StructureStart<NoneFeatureConfiguration> {
        public Start(StructureFeature<NoneFeatureConfiguration> structureFeature, ChunkPos chunkPos, int reference, long seed) {
            super(structureFeature, chunkPos, reference, seed);
        }

        @Override @ParametersAreNonnullByDefault
        public void generatePieces(RegistryAccess registry, ChunkGenerator chunkGen, StructureManager structureManager, ChunkPos chunkPos, Biome biome, NoneFeatureConfiguration config, LevelHeightAccessor heightAccessor) {
            int x = chunkPos.x * 16;
            int z = chunkPos.z * 16;
            int y = 0;
            if(generationType == GenerationType.onGround) {
                y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
            } else if(generationType == GenerationType.inAir){
                int minY = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor) + 50;
                int maxY = 220;
                if(minY > maxY){
                    y = maxY;
                } else {
                    y = minY + random.nextInt(maxY - minY);
                }
            } else if(generationType == GenerationType.underground){
                int minY = 10;
                int maxY = chunkGen.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor);
                if(maxY >= 55)
                    maxY = 55;
                y = Math.abs(this.random.nextInt(maxY));
                if(y < minY){
                    y = minY;
                }
            }

            assemble(structureManager, new BlockPos(x, y, z), Rotation.getRandom(this.random), this.pieces, this.random);
            this.createBoundingBox();
        }
    }

    public void assemble(StructureManager structureManager, BlockPos pos, Rotation rotation, List<StructurePiece> structurePieces, Random rand){
        int piece = 0;
        if(Variants.length > 1) {
            int i = rand.nextInt(maxWeight+1);
            for (int j = 0; j < Variants.length; j++) {
                if (Variants[j].Weight >= i) {
                    piece = j;
                    break;
                } else {
                    i -= Variants[j].Weight;
                }
            }
        }
        structurePieces.add(new DESimpleStructure.Piece(structureManager, Variants[piece].Resource, pos.offset(Variants[piece].Offset), rotation));
    }

    protected int getMaxWeight() {
        int i = 0;
        for (DEPiece piece : Variants){
            i += piece.Weight;
        }
        return i;
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(StructurePieceType pieceType, int componentType, StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            super(pieceType, componentType, structureManager, templateName, getPlaceSettings(structureManager, templateName, pos, rotation), pos);
            templatePosition = pos;
            this.rotation = rotation;
        }

        public Piece(StructurePieceType pieceType, StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation) {
            this(pieceType, 0, structureManager, templateName, pos, rotation);
        }

        public Piece(StructurePieceType pieceType, CompoundTag nbt, ServerLevel level) {
            super(pieceType, nbt, level, (name) -> getPlaceSettings(level.getStructureManager(), name, new BlockPos(nbt.getInt("TPX"), nbt.getInt("TPY"), nbt.getInt("TPZ")), Rotation.valueOf(nbt.getString("Rot"))));
        }

        protected static StructurePlaceSettings getPlaceSettings(StructureManager structureManager, ResourceLocation name, BlockPos pos, Rotation rotation) {
            Optional<StructureTemplate> temp = structureManager.get(name);
            Vec3i size = Vec3i.ZERO;
            if(temp.isPresent()) {
                size = temp.get().getSize();
            }
            StructurePlaceSettings settings = new StructurePlaceSettings().setKeepLiquids(false).setRotationPivot(new BlockPos(size.getX()/2, 0, size.getZ()/2).rotate(rotation));
            settings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR);
            settings.addProcessor(RemoveGelStructureProcessor.INSTANCE);
            return settings;
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor world, Random rnd, BoundingBox box) {}
    }

    protected Block getBlockAt(int x, int y, int z){
        return chunkGen.getBaseColumn(x, z, heightAccessor).getBlockState(new BlockPos(x, y, z)).getBlock();
    }

    public enum GenerationType {onGround, inAir, underground}
}