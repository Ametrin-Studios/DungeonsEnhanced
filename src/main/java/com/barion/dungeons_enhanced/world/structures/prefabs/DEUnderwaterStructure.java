package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DEStructurePiece;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.structure.GelTemplateStructurePiece;
import com.legacy.structure_gel.api.structure.processor.RemoveGelStructureProcessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Optional;
import java.util.Random;

public class DEUnderwaterStructure extends DEBaseStructure {

    public DEUnderwaterStructure(StructureConfig config, DEStructurePiece[] resources) {this(config, true, resources);}

    public DEUnderwaterStructure(StructureConfig config, boolean generateNearSpawn, DEStructurePiece[] resources){
        this(config, generateNearSpawn, false, DEUnderwaterStructure::assemble, resources);
    }
    protected DEUnderwaterStructure(StructureConfig config, boolean generateNearSpawn, boolean checkBiomeArea, DEPieceAssembler assembler, DEStructurePiece[] resources){
        super(config, DETerrainAnalyzer.GenerationType.underwater, generateNearSpawn, checkBiomeArea, assembler, resources);
    }

    private static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    public static class Piece extends GelTemplateStructurePiece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(DEStructures.SunkenShrine.getPieceType(), 0, structureManager, templateName, getPlaceSettings(structureManager, templateName, rotation), pos);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(DEStructures.SunkenShrine.getPieceType(), nbt, serializationContext.structureManager(), (name) -> getPlaceSettings(serializationContext.structureManager(), name, Rotation.valueOf(nbt.getString("Rot"))));
        }

        protected static StructurePlaceSettings getPlaceSettings(StructureManager structureManager, ResourceLocation name, Rotation rotation) {
            Optional<StructureTemplate> temp = structureManager.get(name);
            Vec3i size = Vec3i.ZERO;
            if(temp.isPresent()) {size = temp.get().getSize();}
            StructurePlaceSettings settings = new StructurePlaceSettings().setKeepLiquids(true).setRotationPivot(new BlockPos(size.getX()/2, 0, size.getZ()/2).rotate(rotation));
            settings.addProcessor(BlockIgnoreProcessor.STRUCTURE_AND_AIR).addProcessor(RemoveGelStructureProcessor.INSTANCE);
            return settings;
        }

        @Override @ParametersAreNonnullByDefault
        protected void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor level, Random random, BoundingBox box) {}
    }
}