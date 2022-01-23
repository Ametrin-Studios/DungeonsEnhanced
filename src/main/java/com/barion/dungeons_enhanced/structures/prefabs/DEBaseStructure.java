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
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.StructureFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

public abstract class DEBaseStructure extends GelConfigStructure<NoneFeatureConfiguration> {
    protected ChunkGenerator chunkGen;
    protected LevelHeightAccessor heightAccessor;
    protected DEPiece[] Variants;
    protected final GenerationType generationType;
    protected int maxWeight;
    protected boolean generateNearSpawn;
    private final DEPieceGeneratorSupplier<NoneFeatureConfiguration> pieceGenerator;

    public DEBaseStructure(StructureConfig config, GenerationType generationType, boolean generateNearSpawn, DEPiece... resources) {
        this(config, generationType, generateNearSpawn);
        Variants = resources;
        maxWeight = getMaxWeight();
    }

    public DEBaseStructure(StructureConfig config, GenerationType generationType, BlockPos offset, boolean generateNearSpawn, DEPiece... resources) {
        this(config, generationType, generateNearSpawn);
        for(DEPiece resource : resources){
            resource.Offset = offset;
        }
        Variants = resources;
        maxWeight = getMaxWeight();
    }

    public DEBaseStructure(StructureConfig config, GenerationType generationType, boolean generateNearWorldSpawn) {
        super(NoneFeatureConfiguration.CODEC, config, DEBaseStructure::generatePieces);
        this.pieceGenerator = DEPieceGeneratorSupplier.simple(DEPieceGeneratorSupplier.checkForBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG), DEBaseStructure::generatePieces);
        this.generationType = generationType;
        this.generateNearSpawn = generateNearWorldSpawn;
        setLakeProof(true);
    }

    private static void generatePieces(StructurePiecesBuilder piecesBuilder, PieceGenerator.Context<NoneFeatureConfiguration> context) {DungeonsEnhanced.LOGGER.warn("A Dungeons Enhanced StructureFeature tries to use the Vanilla PieceGenerator instead of the Custom one");}

    @Override
    public boolean isAllowedNearWorldSpawn() {return generateNearSpawn;}

    @Override
    public boolean canGenerate(RegistryAccess registryAccess, ChunkGenerator chunkGen, BiomeSource biomeSource, StructureManager structureManager, long seed, ChunkPos chunkPos, NoneFeatureConfiguration config, LevelHeightAccessor heightAccessor, Predicate<Biome> biomeTest) {
        if(generationType == GenerationType.onGround) {
            this.chunkGen = chunkGen;
            this.heightAccessor = heightAccessor;
            int x = chunkPos.x * 16;
            int z = chunkPos.z * 16;
            int y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, this.heightAccessor);
            if(getBlockAt(x, y-1, z) == Blocks.WATER){
                //DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+" Canceled because Water");
                return false;
            }
            int tempY = y+3;
            if(getBlockAt(x+3, tempY, z) != Blocks.AIR || getBlockAt(x-3, tempY, z) != Blocks.AIR || getBlockAt(x, tempY, z+3) != Blocks.AIR || getBlockAt(x, tempY, z-3) != Blocks.AIR){
                //DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+" Canceled because Cliff");
                return false;
            }
            tempY = y-3;
            if(getBlockAt(x+3, tempY, z) == Blocks.AIR || getBlockAt(x-3, tempY, z) == Blocks.AIR || getBlockAt(x, tempY, z+3) == Blocks.AIR || getBlockAt(x, tempY, z-3) == Blocks.AIR){
                //DungeonsEnhanced.LOGGER.info("Structure at "+x+", "+y+" Canceled because Cliff");
                return false;
            }
        }

        return this.pieceGenerator.createGenerator(new DEPieceGeneratorSupplier.Context<>(chunkGen, biomeSource, seed, chunkPos, config, heightAccessor, biomeTest, structureManager, registryAccess)).isPresent();
    }

    private static void generatePieces(StructurePiecesBuilder piecesBuilder, DEPieceGenerator.Context<NoneFeatureConfiguration> context) {
        int x = context.chunkPos().x * 16;
        int z = context.chunkPos().z * 16;
        int y = 0;
        ChunkGenerator chunkGen = context.chunkGenerator();
        LevelHeightAccessor heightAccessor = context.heightAccessor();
        switch (context.base().generationType) {
            case onGround -> y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor);
            case inAir -> {
                int minY = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor) + 50;
                int maxY = 220;
                if (minY > maxY) {y = maxY;}
                else {y = minY + context.random().nextInt(maxY - minY);}
            }
            case underground -> {
                int minY = chunkGen.getMinY() + 10;
                int maxY = chunkGen.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor);
                if(maxY >= 55) {maxY = 55;}
                y = context.random().nextInt(maxY-minY)+minY;
                if(y < minY){y = minY;}
            }
        }

        int piece = getRandomPiece(context.base().Variants, context.base().maxWeight, context.random());

        context.base().assemble(new AssembleContext(context.structureManager(), context.base().Variants[piece], new BlockPos(x, y, z).offset(context.base().Variants[piece].Offset), Rotation.getRandom(context.random()), piecesBuilder, piece));
    }

    protected abstract void assemble(AssembleContext context);

    protected static int getRandomPiece(DEPiece[] variants, int maxWeight, Random rand){
        int piece = 0;
        if(variants.length > 1) {
            int i = rand.nextInt(maxWeight+1);
            for (int j = 0; j < variants.length; j++) {
                if (variants[j].Weight >= i) {
                    piece = j;
                    break;
                } else {
                    i -= variants[j].Weight;
                }
            }
        }
        return piece;
    }

    @Override @ParametersAreNonnullByDefault @Nonnull
    public StructureStart<?> generate(RegistryAccess registryAccess, ChunkGenerator chunkGenerator, BiomeSource biomeSource, StructureManager structureManager, long seed, ChunkPos chunkPos, int referece, StructureFeatureConfiguration config, NoneFeatureConfiguration featureConfiguration, LevelHeightAccessor heightAccessor, Predicate<Biome> biomePredicate) {
        ChunkPos chunkpos = this.getPotentialFeatureChunk(config, seed, chunkPos.x, chunkPos.z);
        if (chunkPos.x == chunkpos.x && chunkPos.z == chunkpos.z){
            Optional<DEPieceGenerator<NoneFeatureConfiguration>> optional = this.pieceGenerator.createGenerator(new DEPieceGeneratorSupplier.Context<>(chunkGenerator, biomeSource, seed, chunkPos, featureConfiguration, heightAccessor, biomePredicate, structureManager, registryAccess));
            if (optional.isPresent()){
                StructurePiecesBuilder structurepiecesbuilder = new StructurePiecesBuilder();
                WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(0L));
                worldgenrandom.setLargeFeatureSeed(seed, chunkPos.x, chunkPos.z);
                optional.get().generatePieces(structurepiecesbuilder, new DEPieceGenerator.Context<>(featureConfiguration, chunkGenerator, structureManager, chunkPos, heightAccessor, worldgenrandom, seed, this));
                StructureStart<NoneFeatureConfiguration> structureStart = new StructureStart<>(this, chunkPos, referece, structurepiecesbuilder.build());
                if (structureStart.isValid()) {return structureStart;}
            }
        }

        return StructureStart.INVALID_START;
    }

    protected int getMaxWeight() {
        int i = 0;
        for (DEPiece piece : Variants){
            i += piece.Weight;
        }
        return i;
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(StructurePieceType pieceType, StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType) {
            super(pieceType, componentType, structureManager, templateName, getPlaceSettings(structureManager, templateName, pos, rotation), pos);
        }

        public Piece(StructurePieceType pieceType, StructurePieceSerializationContext serializationContext, CompoundTag nbt) {
            super(pieceType, nbt, serializationContext.structureManager(), (name) -> getPlaceSettings(serializationContext.structureManager(), name, new BlockPos(nbt.getInt("TPX"), nbt.getInt("TPY"), nbt.getInt("TPZ")), Rotation.valueOf(nbt.getString("Rot"))));
        }

        protected static StructurePlaceSettings getPlaceSettings(StructureManager structureManager, ResourceLocation name, BlockPos pos, Rotation rotation) {
            Optional<StructureTemplate> temp = structureManager.get(name);
            Vec3i size = Vec3i.ZERO;
            if(temp.isPresent()) {
                size = temp.get().getSize();
            }
            StructurePlaceSettings settings = new StructurePlaceSettings().setKeepLiquids(false).setRotationPivot(new BlockPos(size.getX()/2, 0, size.getZ()/2).rotate(rotation));
            settings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR).addProcessor(RemoveGelStructureProcessor.INSTANCE);
            return settings;
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor world, Random rnd, BoundingBox box) {}
    }

    public record Context<C extends FeatureConfiguration>(C config, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, LevelHeightAccessor heightAccessor, WorldgenRandom random, long seed, GenerationType generationType, DEPiece[] variants, int maxWeight) {}

    protected Block getBlockAt(int x, int y, int z) {return chunkGen.getBaseColumn(x, z, heightAccessor).getBlock(y).getBlock();}

    public enum GenerationType {onGround, inAir, underground}

    public record AssembleContext(StructureManager structureManager, DEPiece variant, BlockPos pos, Rotation rotation, StructurePiecesBuilder piecesBuilder, int piece){}
}