package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.api.registry.registrar.Registrar;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import com.legacy.structure_gel.api.structure.processor.RemoveGelStructureProcessor;
import net.minecraft.core.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.levelgen.structure.StructureType;
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
    public DEBaseStructure(StructureSettings settings, DEStructurePiece[] variants, DEPieceAssembler assembler, Supplier<StructureType<?>> type){
        super(settings);
        this.variants = variants;
        this.maxWeight = getMaxWeight(variants);
        this.assembler = assembler;
        this.type = type;
    }

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        return onTopOfChunkCenter(context, Heightmap.Types.WORLD_SURFACE_WG, (builder)-> generatePieces(builder, context, variants, maxWeight, assembler, getGenPos(context)));
    }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public StructureStart generate(RegistryAccess registryAccess, ChunkGenerator generator, BiomeSource biomeSource, RandomState randomState, StructureTemplateManager templateManager, long seed, ChunkPos chunkPos, int references, LevelHeightAccessor heightAccessor, Predicate<Holder<Biome>> biomePredicate) {
        final GenerationContext context = new Structure.GenerationContext(registryAccess, generator, biomeSource, randomState, templateManager, seed, chunkPos, heightAccessor, biomePredicate);
        Optional<Structure.GenerationStub> optional = this.findGenerationPoint(context);
        if (optional.isPresent() && isValidBiome(optional.get(), generator, randomState, biomePredicate)) {
            if(checkLocation(context)){
                StructurePiecesBuilder structurepiecesbuilder = optional.get().getPiecesBuilder();
                StructureStart structurestart = new StructureStart(this, chunkPos, references, structurepiecesbuilder.build());
                if (structurestart.isValid()) {
                    return structurestart;
                }
            }
        }

        return StructureStart.INVALID_START;
    }

    protected static boolean isValidBiome(Structure.GenerationStub stub, ChunkGenerator generator, RandomState state, Predicate<Holder<Biome>> biomePredicate) {
        BlockPos blockpos = stub.position();
        return biomePredicate.test(generator.getBiomeSource().getNoiseBiome(QuartPos.fromBlock(blockpos.getX()), QuartPos.fromBlock(blockpos.getY()), QuartPos.fromBlock(blockpos.getZ()), state.sampler()));
    }

    @Override @Nonnull
    public StructureType<?> type() {return type.get();}

    protected abstract boolean checkLocation(GenerationContext context);

    protected abstract BlockPos getGenPos(GenerationContext context);

    private static void generatePieces(StructurePiecesBuilder piecesBuilder, GenerationContext context, DEStructurePiece[] variants, int maxWeight, DEPieceAssembler assembler, BlockPos rawPos) {
        int piece = DEUtil.getRandomPiece(variants, maxWeight, context.random());

        assembler.assemble(new DEPieceAssembler.Context(context.structureTemplateManager(), variants[piece].Resource, rawPos.offset(variants[piece].Offset), Rotation.getRandom(context.random()), piecesBuilder));
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