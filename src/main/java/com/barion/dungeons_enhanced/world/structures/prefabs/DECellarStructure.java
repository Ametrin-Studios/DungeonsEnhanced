package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.TerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structures.prefabs.utils.DECellarPiece;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.StructurePieceType;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.feature.structures.StructurePoolElement;
import net.minecraft.world.level.levelgen.feature.structures.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Random;

public class DECellarStructure extends GelConfigJigsawStructure {
    protected DECellarPiece[] Variants;
    protected boolean generateNear00;
    protected String prefix;
    protected TerrainAnalyzer.LandscapeCheckSettings landscapeCheckSettings;
    protected int maxWeight;
    protected Pool pool;

    public DECellarStructure(StructureConfig config, boolean generateNear00, String prefix, TerrainAnalyzer.LandscapeCheckSettings landscapeCheckSettings, DECellarPiece... variants){
        super(JigsawConfiguration.CODEC, config, 0, true, true, (context) -> checkLocation(context, landscapeCheckSettings));
        this.generateNear00 = generateNear00;
        this.Variants = variants;
        this.prefix = prefix;
        this.maxWeight = DEUtil.getMaxWeight(Variants);
        this.landscapeCheckSettings = landscapeCheckSettings;
        this.pool = new Pool();
        pool.init();
    }

    private static boolean checkLocation(PieceGeneratorSupplier.Context context, TerrainAnalyzer.LandscapeCheckSettings checkSettings){
        if(context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG)){
            return TerrainAnalyzer.isPositionSuitable(context.chunkPos(), context.chunkGenerator(), checkSettings, context.heightAccessor());
        }

        return false;
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return generateNear00;}

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(StructureManager structureManager, StructurePoolElement poolElement, BlockPos pos, int groundLevelDelta, Rotation rotation, BoundingBox box) {
            super(structureManager, poolElement, pos, groundLevelDelta, rotation, box);
        }
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.Castle.getPieceType();}
        @Override
        public void handleDataMarker(String key, BlockPos blockPos, ServerLevelAccessor levelAccessor, Random random, BoundingBox box) {}
    }

    public StructureTemplatePool getRootPool(){
        return pool.Root;
    }

    protected class Pool{
        protected StructureTemplatePool Root;
        public void init() {}

        {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.Mod_ID, prefix +"/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            String[] topParts = new String[Variants.length];

            for(int i = 0; i < Variants.length; i++){
                topParts[i] = Variants[i].Resource.getPath();
            }


            Root = registry.register("root", poolBuilder.clone().names(topParts).build());

            for(DECellarPiece piece : Variants) {
                registry.register(piece.Cellar, poolBuilder.clone().names(piece.Cellar).build());
            }
        }
    }
}