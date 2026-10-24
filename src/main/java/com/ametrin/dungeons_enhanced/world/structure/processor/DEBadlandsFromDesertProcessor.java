package com.ametrin.dungeons_enhanced.world.structure.processor;

import com.ametrin.dungeons_enhanced.registry.DEProcessorTypes;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.QuartPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.neoforged.neoforge.common.Tags;

import java.util.ArrayList;
import java.util.List;

public class DEBadlandsFromDesertProcessor extends StructureProcessor {
    public static final DEBadlandsFromDesertProcessor INSTANCE = new DEBadlandsFromDesertProcessor();
    public static final MapCodec<DEBadlandsFromDesertProcessor> CODEC = MapCodec.unit(INSTANCE);

    private static final BiMap<Block, Block> SANDSTONE_MAPPING = ImmutableBiMap.<Block, Block>builder()
            .put(Blocks.SAND, Blocks.RED_SAND)
            .put(Blocks.SANDSTONE, Blocks.RED_SANDSTONE)
            .put(Blocks.SANDSTONE_STAIRS, Blocks.RED_SANDSTONE_STAIRS)
            .put(Blocks.SANDSTONE_SLAB, Blocks.RED_SANDSTONE_SLAB)
            .put(Blocks.SANDSTONE_WALL, Blocks.RED_SANDSTONE_WALL)
            .put(Blocks.CUT_SANDSTONE, Blocks.CUT_RED_SANDSTONE)
            .put(Blocks.CUT_SANDSTONE_SLAB, Blocks.CUT_RED_SANDSTONE_SLAB)
            .put(Blocks.CHISELED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE)
            .put(Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE)
            .put(Blocks.SMOOTH_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS)
            .put(Blocks.SMOOTH_SANDSTONE_SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB)
            .build();

    @Override
    public List<StructureTemplate.StructureBlockInfo> finalizeProcessing(
            ServerLevelAccessor level, BlockPos offset, BlockPos pos,
            List<StructureTemplate.StructureBlockInfo> originalBlockInfos,
            List<StructureTemplate.StructureBlockInfo> processedBlockInfos,
            StructurePlaceSettings settings) {

        var biome = level.getUncachedNoiseBiome(
                QuartPos.fromBlock(offset.getX()),
                QuartPos.fromBlock(offset.getY()),
                QuartPos.fromBlock(offset.getZ())
        );

        if (!biome.is(Tags.Biomes.IS_BADLANDS)) {
            return processedBlockInfos;
        }

        var inverse = SANDSTONE_MAPPING.inverse();
        var result = new ArrayList<StructureTemplate.StructureBlockInfo>(processedBlockInfos.size());
        for (var info : processedBlockInfos) {
            var state = info.state();
            var swapped = SANDSTONE_MAPPING.get(state.getBlock());
            if (swapped == null) swapped = inverse.get(state.getBlock());

            result.add(swapped == null
                    ? info
                    : new StructureTemplate.StructureBlockInfo(info.pos(), swapped.withPropertiesOf(state), info.nbt()));
        }
        return result;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return DEProcessorTypes.BADLANDS_FROM_DESERT.get();
    }
}
