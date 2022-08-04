package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import com.legacy.structure_gel.api.structure.processor.RemoveGelStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static com.barion.dungeons_enhanced.DEUtil.getMaxWeight;

public abstract class DEBaseStructure extends Structure{
    private final DEStructurePiece[] variants;
    private final int maxWeight;
    private final DEPieceAssembler assembler;
    private final Supplier<StructureType<?>> type;
    private final DETerrainAnalyzer.GenerationType generationType;
    public DEBaseStructure(StructureSettings settings, DEStructurePiece[] variants, DEPieceAssembler assembler, Supplier<StructureType<?>> type, DETerrainAnalyzer.GenerationType generationType){
        super(settings);
        this.variants = variants;
        this.maxWeight = getMaxWeight(variants);
        this.assembler = assembler;
        this.type = type;
        this.generationType = generationType;
    }

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        return onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, (builder)-> generatePieces(builder, context, variants, maxWeight, assembler, generationType));
    }

    @Override @Nonnull
    public StructureType<?> type() {return type.get();}

    private static boolean checkLocation(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context, Predicate<PieceGeneratorSupplier.Context<NoneFeatureConfiguration>> locationCheck){
        if(context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG)){
            return locationCheck.test(context);
        }
        return false;
    }

    private static void generatePieces(StructurePiecesBuilder piecesBuilder, GenerationContext context, DEStructurePiece[] variants, int maxWeight, DEPieceAssembler assembler, DETerrainAnalyzer.GenerationType generationType) {
        int x = context.chunkPos().getMiddleBlockX();
        int z = context.chunkPos().getMiddleBlockZ();
        int y = 72;
        ChunkGenerator chunkGen = context.chunkGenerator();
        LevelHeightAccessor heightAccessor = context.heightAccessor();
        RandomState randomState = context.randomState();
        switch (generationType) {
            case onGround, onWater -> y = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState);
            case underwater -> y = chunkGen.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState);
            case inAir -> {
                int minY = chunkGen.getBaseHeight(x, z, Heightmap.Types.WORLD_SURFACE_WG, heightAccessor, randomState) + 50;
                int maxY = 290;
                if (minY >= maxY) {y = maxY;}
                else {y = minY + context.random().nextInt(maxY - minY);}
            }
            case underground -> {
                int minY = chunkGen.getMinY() + 10;
                int maxY = chunkGen.getBaseHeight(x, z, Heightmap.Types.OCEAN_FLOOR_WG, heightAccessor, randomState) - 20;
                if (minY >= maxY) {y = maxY;}
                else {y = minY + context.random().nextInt(maxY - minY);}
            }
        }

        int piece = DEUtil.getRandomPiece(variants, maxWeight, context.random());

        assembler.assemble(new DEPieceAssembler.Context(context.structureTemplateManager(), variants[piece].Resource, new BlockPos(x, y, z).offset(variants[piece].Offset), Rotation.getRandom(context.random()), piecesBuilder, generationType));
    }

    public static class Piece extends GelTemplateStructurePiece{
        public Piece(Registrar.Static<StructurePieceType> pieceType, StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(pieceType.get(), 0, structureManager, templateName, pos);
            this.rotation = rotation;
            setupPlaceSettings(structureManager);
        }

        public Piece(Registrar.Static<StructurePieceType> pieceType, StructurePieceSerializationContext context, CompoundTag nbt){
            super(pieceType.get(), nbt, context.structureTemplateManager());
            setupPlaceSettings(context.structureTemplateManager());
        }

        @Override
        protected StructurePlaceSettings getPlaceSettings(StructureTemplateManager structureManager) {
            Vec3i size = structureManager.get(makeTemplateLocation()).get().getSize();
            BlockPos pivot = new BlockPos(size.getX() / 2, 0, size.getZ() / 2);
            StructurePlaceSettings settings = new StructurePlaceSettings().setKeepLiquids(false).setRotationPivot(pivot);
            settings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR).addProcessor(RemoveGelStructureProcessor.INSTANCE);
            addProcessors(settings);
            return settings;
        }

        protected void addProcessors(StructurePlaceSettings settings) {}

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, RandomSource random, BoundingBox box) {}
    }
}