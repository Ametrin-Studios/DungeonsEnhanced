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
    protected static ChunkGenerator chunkGenerator;
    protected static LevelHeightAccessor heightAccessor;
    public landscapeCheckSettings landscapeCheckSettings = new landscapeCheckSettings(1, 3, 3);
    public DEPiece[] Variants;
    public final GenerationType generationType;
    public int maxWeight;
    protected boolean generateNear00;
    protected final DEPieceGeneratorSupplier<NoneFeatureConfiguration> pieceGenerator;

    public DEBaseStructure(StructureConfig config, GenerationType generationType, BlockPos offset, boolean generateNear00, DEPiece... resources){
        this(config, generationType, generateNear00, resources);
        for(DEPiece resource : Variants){
            resource.Offset = offset;
        }
    }

    public DEBaseStructure(StructureConfig config, GenerationType generationType, boolean generateNear00, DEPiece... variants) {
        super(NoneFeatureConfiguration.CODEC, config, DEBaseStructure::generatePieces);
        this.pieceGenerator = DEPieceGeneratorSupplier.simple(DEBaseStructure::checkLocation, DEBaseStructure::generatePieces);
        this.generationType = generationType;
        this.generateNear00 = generateNear00;
        this.Variants = variants;
        maxWeight = getMaxWeight();
        setLakeProof(true);
        //DungeonsEnhanced.LOGGER.info("Offset: " + getOffset());
    }

    private static void generatePieces(StructurePiecesBuilder piecesBuilder, PieceGenerator.Context<NoneFeatureConfiguration> context) {DungeonsEnhanced.LOGGER.warn("A Dungeons Enhanced StructureFeature tries to use the Vanilla PieceGenerator instead of the Custom one");}

    @Override
    public boolean isAllowedNearWorldSpawn() {return generateNear00;}

    private static boolean checkLocation(DEPieceGeneratorSupplier.Context<NoneFeatureConfiguration> context){
        boolean correctBiome = context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG);

        if(correctBiome && context.structure().generationType == GenerationType.onGround){
            int x = context.chunkPos().x * 16;
            int z = context.chunkPos().z * 16;
            int y = context.chunkGenerator().getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, context.heightAccessor());

            chunkGenerator = context.chunkGenerator();
            heightAccessor = context.heightAccessor();

            if(getBlockAt(x, y-1, z) == Blocks.WATER) {
                DungeonsEnhanced.LOGGER.info("Canceled at " + new BlockPos(x, y, z) + " because Water");
                return false;
            }

            int columSpreading = context.structure().landscapeCheckSettings.columSpreading();

            if(isColumBlocked(new BlockPos(x+columSpreading, y, z), context)) {
                DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
                return false;
            }
            if(isColumBlocked(new BlockPos(x-columSpreading, y, z), context)) {
                DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
                return false;
            }
            if(isColumBlocked(new BlockPos(x, y, z+columSpreading), context)) {
                DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
                return false;
            }
            if(isColumBlocked(new BlockPos(x, y, z-columSpreading), context)) {
                DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " failed");
                return false;
            }

            DungeonsEnhanced.LOGGER.info("Structure at " + x + ", " + y + ", " + z + " passed");
        }

        return correctBiome;
    }

    protected static boolean isColumBlocked(BlockPos pos, DEPieceGeneratorSupplier.Context<NoneFeatureConfiguration> context){
        int maxRangePerColum = context.structure().landscapeCheckSettings.maxRangePerColum();
        int stepSize = context.structure().landscapeCheckSettings.stepSize();

        if(!isDownwardsFree(pos, stepSize, maxRangePerColum)){
            return isUpwardsBlocked(pos, stepSize, maxRangePerColum);
        }

        return true;
    }

    protected static boolean isUpwardsBlocked(BlockPos pos, int stepSize, int range){
        for(int i = 1; i <= range; i++){
            if(getBlockAt(pos.getX(), pos.getY() + (i * stepSize), pos.getZ()) != Blocks.AIR) {return true;}
        }

        return false;
    }

    protected static boolean isDownwardsFree(BlockPos pos, int stepSize, int range){
        for(int i = 1; i <= range; i++){
            if(getBlockAt(pos.getX(), pos.getY() - (i * stepSize), pos.getZ()) == Blocks.AIR) {return true;}
        }

        return false;
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

    public abstract void assemble(AssembleContext context);

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
        ChunkPos potentialChunkPos = this.getPotentialFeatureChunk(config, seed, chunkPos.x, chunkPos.z);
        if (chunkPos.x == potentialChunkPos.x && chunkPos.z == potentialChunkPos.z){
            Optional<DEPieceGenerator<NoneFeatureConfiguration>> optional = this.pieceGenerator.createGenerator(new DEPieceGeneratorSupplier.Context<>(chunkGenerator, biomeSource, seed, chunkPos, featureConfiguration, heightAccessor, biomePredicate, structureManager, registryAccess, this));
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

    protected static Block getBlockAt(int x, int y, int z) {return chunkGenerator.getBaseColumn(x, z, heightAccessor).getBlock(y).getBlock();}

    public enum GenerationType {onGround, inAir, underground}

    protected record landscapeCheckSettings(int maxRangePerColum, int stepSize, int columSpreading){}

    public record AssembleContext(StructureManager structureManager, DEPiece variant, BlockPos pos, Rotation rotation, StructurePiecesBuilder piecesBuilder, int piece){}
}