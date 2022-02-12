package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.TerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceGenerator;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceGeneratorSupplier;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
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
    public TerrainAnalyzer.LandscapeCheckSettings landscapeCheckSettings = new TerrainAnalyzer.LandscapeCheckSettings(1, 3, 3);
    public DEStructurePiece[] Variants;
    public final GenerationType generationType;
    public int maxWeight;
    protected boolean generateNear00;
    protected final DEPieceGeneratorSupplier<NoneFeatureConfiguration> pieceGenerator;

    public DEBaseStructure(StructureConfig config, GenerationType generationType, BlockPos offset, boolean generateNear00, DEStructurePiece... resources){
        this(config, generationType, generateNear00, resources);
        for(DEStructurePiece resource : Variants){
            resource.Offset = offset;
        }
    }

    public DEBaseStructure(StructureConfig config, GenerationType generationType, boolean generateNear00, DEStructurePiece... variants) {
        super(NoneFeatureConfiguration.CODEC, config, (piecesBuilder, context) -> DungeonsEnhanced.LOGGER.warn("A Dungeons Enhanced StructureFeature tries to use the Vanilla PieceGenerator instead of the Custom one"));
        this.pieceGenerator = DEPieceGeneratorSupplier.simple(DEBaseStructure::checkLocation, DEBaseStructure::generatePieces);
        this.generationType = generationType;
        this.generateNear00 = generateNear00;
        this.Variants = variants;
        maxWeight = DEUtil.getMaxWeight(Variants);
        setLakeProof(true);
    }

    @Override public boolean isAllowedNearWorldSpawn() {return generateNear00;}

    private static boolean checkLocation(DEPieceGeneratorSupplier.Context<NoneFeatureConfiguration> context){
        boolean isCorrectBiome = context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG);

        if(isCorrectBiome && context.structure().generationType == GenerationType.onGround){
            return TerrainAnalyzer.isPositionSuitable(context.chunkPos(), context.chunkGenerator(), context.structure().landscapeCheckSettings, context.heightAccessor());
        }

        return isCorrectBiome;
    }

    private static void generatePieces(StructurePiecesBuilder piecesBuilder, DEPieceGenerator.Context<NoneFeatureConfiguration> context) {
        int x = context.chunkPos().getMinBlockX();
        int z = context.chunkPos().getMinBlockZ();
        int y = 70;
        ChunkGenerator chunkGen = context.chunkGenerator();
        LevelHeightAccessor heightAccessor = context.heightAccessor();
        switch (context.structure().generationType) {
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

        int piece = getRandomPiece(context.structure().Variants, context.structure().maxWeight, context.random());

        context.structure().assemble(new AssembleContext(context.structureManager(), context.structure().Variants[piece], new BlockPos(x, y, z).offset(context.structure().Variants[piece].Offset), Rotation.getRandom(context.random()), piecesBuilder, piece));
    }

    public abstract void assemble(AssembleContext context);

    protected static int getRandomPiece(DEStructurePiece[] variants, int maxWeight, Random rand){
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

    public record Context<C extends FeatureConfiguration>(C config, ChunkGenerator chunkGenerator, StructureManager structureManager, ChunkPos chunkPos, LevelHeightAccessor heightAccessor, WorldgenRandom random, long seed, GenerationType generationType, DEStructurePiece[] variants, int maxWeight) {}

    public enum GenerationType {onGround, inAir, underground}

    public record AssembleContext(StructureManager structureManager, DEStructurePiece variant, BlockPos pos, Rotation rotation, StructurePiecesBuilder piecesBuilder, int piece){}
}