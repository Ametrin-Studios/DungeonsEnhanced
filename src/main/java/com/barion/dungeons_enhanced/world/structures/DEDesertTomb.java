package com.barion.dungeons_enhanced.world.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.DungeonsEnhanced;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.google.common.collect.ImmutableMap;
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

public class DEDesertTomb extends GelConfigJigsawStructure {
    public DEDesertTomb(){
        super(JigsawConfiguration.CODEC, DEConfig.COMMON.desert_tomb, 1, true, true, (context -> checkLocation(context, DETerrainAnalyzer.defaultCheckSettings)));
        Pool.init();
    }

    @Override
    public boolean isAllowedNearWorldSpawn() {return true;}

    private static boolean checkLocation(PieceGeneratorSupplier.Context<JigsawConfiguration> context, DETerrainAnalyzer.TerrainCheckSettings checkSettings){
        if(context.validBiomeOnTop(Heightmap.Types.WORLD_SURFACE_WG)){
            return DETerrainAnalyzer.isPositionSuitable(context.chunkPos(), context.chunkGenerator(), checkSettings, context.heightAccessor());
        }

        return false;
    }

    public static class Piece extends AbstractGelStructurePiece {
        public Piece(StructureManager structureManager, StructurePoolElement poolElement, BlockPos pos, int groundLevelDelta, Rotation rotation, BoundingBox bounds) {super(structureManager, poolElement, pos, groundLevelDelta, rotation, bounds);}
        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt) {super(serializationContext, nbt);}

        @Override
        public StructurePieceType getType() {return DEStructures.DesertTomb.getPieceType();}
        @Override
        public void handleDataMarker(String key, BlockPos pos, ServerLevelAccessor levelAccessor, Random random, BoundingBox box) {}
    }

    public static class Pool{
        public static StructureTemplatePool Root;
        public static void init(){}
        static{
            JigsawRegistryHelper registry = new JigsawRegistryHelper(DungeonsEnhanced.Mod_ID, "desert_tomb/");
            JigsawPoolBuilder poolBuilder = registry.builder().maintainWater(false).processors(DEUtil.Processors.AirToCobweb);
            Root = registry.register("root", poolBuilder.clone().names("root").build());

            registry.register("down", poolBuilder.clone().names("down").build());
            registry.register("trap", poolBuilder.clone().names("trap").build());
            registry.register("cross", poolBuilder.clone().names("t-cross").build());
            registry.register("main", poolBuilder.clone().names(ImmutableMap.of("tunnel", 5, "t-cross", 4, "room", 4, "tomb", 3, "exit", 2)).build());
        }
    }
}