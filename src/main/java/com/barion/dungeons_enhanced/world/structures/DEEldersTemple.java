package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.DEUnderwaterStructure;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import com.legacy.structure_gel.api.structure.processor.RemoveGelStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Random;

import static com.barion.dungeons_enhanced.DEUtil.location;

public class DEEldersTemple extends DEUnderwaterStructure {
    private static final ResourceLocation NE = location("elders_temple/ne");
    private static final ResourceLocation NW = location("elders_temple/nw");
    private static final ResourceLocation SE = location("elders_temple/se");
    private static final ResourceLocation SW = location("elders_temple/sw");

    public DEEldersTemple() {super(DEConfig.COMMON.elders_temple, true, DEEldersTemple::checkLocation, DEEldersTemple::assembleTemple, DEUtil.pieceBuilder().add("elders_temple/se").build());}

    private static boolean checkLocation(PieceGeneratorSupplier.Context<NoneFeatureConfiguration> context){
        if(DETerrainAnalyzer.isUnderwater(context.chunkPos(), context.chunkGenerator(), 32, context.heightAccessor())){
            return DETerrainAnalyzer.areNearbyBiomesValid(context.biomeSource(), context.chunkPos(), context.chunkGenerator(), 30, context.validBiome());
        }
        return false;
    }

    public static void assembleTemple(DEPieceAssembler.Context context) {
        Rotation rotation = Rotation.NONE;
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), SE, context.pos().offset(0,-3,0), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), SW, context.pos().offset(-30, -3, 0), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), NE, context.pos().offset(0, -3, -29), rotation));
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), NW, context.pos().offset(-30, -3, -29), rotation));
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(DEStructures.EldersTemple.getPieceType(), 0, structureManager, templateName, getPlaceSettings(structureManager, templateName, pos, rotation), pos);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(DEStructures.EldersTemple.getPieceType(), nbt, serializationContext.structureManager(), (name) -> getPlaceSettings(serializationContext.structureManager(), name, new BlockPos(nbt.getInt("TPX"), nbt.getInt("TPY"), nbt.getInt("TPZ")), Rotation.valueOf(nbt.getString("Rot"))));
        }

        protected static StructurePlaceSettings getPlaceSettings(StructureManager structureManager, ResourceLocation name, BlockPos pos, Rotation rotation) {
            Optional<StructureTemplate> temp = structureManager.get(name);
            Vec3i size = Vec3i.ZERO;
            if(temp.isPresent()) {size = temp.get().getSize();}
            StructurePlaceSettings settings = new StructurePlaceSettings().setKeepLiquids(true).setRotationPivot(new BlockPos(size.getX()/2, 0, size.getZ()/2).rotate(Rotation.NONE));
            settings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR).addProcessor(RemoveGelStructureProcessor.INSTANCE)
                    .addProcessor(DEUtil.Processors.BrainCoral)
                    .addProcessor(DEUtil.Processors.BubbleCoral)
                    .addProcessor(DEUtil.Processors.FireCoral)
                    .addProcessor(DEUtil.Processors.HornCoral)
                    .addProcessor(DEUtil.Processors.TubeCoral);
            return settings;
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, Random random, BoundingBox box) {}
    }
}