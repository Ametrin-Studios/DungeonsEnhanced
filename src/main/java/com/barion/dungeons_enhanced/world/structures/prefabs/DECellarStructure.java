package com.barion.dungeons_enhanced.world.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.legacy.structure_gel.api.config.StructureConfig;
import com.legacy.structure_gel.api.structure.GelConfigJigsawStructure;
import com.legacy.structure_gel.api.structure.jigsaw.AbstractGelStructurePiece;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawPoolBuilder;
import com.legacy.structure_gel.api.structure.jigsaw.JigsawRegistryHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.pieces.PieceGeneratorSupplier;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

import java.util.Random;

public class DECellarStructure extends GelConfigJigsawStructure<JigsawConfiguration>{
    protected boolean generateNear00;

    public DECellarStructure(StructureConfig config, boolean generateNear00, DETerrainAnalyzer.Settings settings) {
        super(JigsawConfiguration.CODEC, config, 0, true, true, (context) -> checkLocation(context, settings));
        this.generateNear00 = generateNear00;
    }

    private static boolean checkLocation(PieceGeneratorSupplier.Context<JigsawConfiguration> context, DETerrainAnalyzer.Settings checkSettings) {
        if(context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG)){
            return DETerrainAnalyzer.isPositionSuitable(context.chunkPos(), context.chunkGenerator(), DETerrainAnalyzer.GenerationType.onGround, checkSettings, context.heightAccessor());
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

    public static void init(){
        CastlePool.init();
        DruidCirclePool.init();
    }

    public static class CastlePool{
        public static final Holder<StructureTemplatePool> Root;
        public static void init() {}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID,"castle/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            Root = registry.register("root", poolBuilder.clone().names("top1", "top2").build());

            registry.register("bottom1", poolBuilder.clone().names("bottom1").build());
            registry.register("bottom2", poolBuilder.clone().names("bottom2").build());
        }
    }

    public static class DruidCirclePool{
        public static final Holder<StructureTemplatePool> Root;
        public static void init(){}

        static {
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.ModID,"druid_circle/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false);
            Root = registry.register("root", poolBuilder.clone().names("top_big", "small").build());

            registry.register("bottom_big", poolBuilder.clone().names("bottom_big").build());
        }
    }
}