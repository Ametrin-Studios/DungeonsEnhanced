package com.ametrin.dungeons_enhanced.world.structure.processor;

import com.ametrin.dungeons_enhanced.registry.DEProcessorTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HangingMossBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jspecify.annotations.Nullable;

public final class DEPaleMonsterMazeProcessor extends StructureProcessor {
    public static final DEPaleMonsterMazeProcessor INSTANCE = new DEPaleMonsterMazeProcessor();
    public static final MapCodec<DEPaleMonsterMazeProcessor> CODEC = MapCodec.unit(INSTANCE);

    @Override
    public StructureTemplate.StructureBlockInfo process(LevelReader level, BlockPos targetPosition, BlockPos referencePos, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo processedBlockInfo, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        if (processedBlockInfo.state().is(Blocks.DARK_OAK_LEAVES)) {
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), Blocks.PALE_OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), null);
        }

        if (processedBlockInfo.state().is(Blocks.MOSS_CARPET)) {
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), Blocks.PALE_MOSS_CARPET.defaultBlockState(), null);
        }

        if (processedBlockInfo.state().is(Blocks.CAVE_VINES)) {
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), Blocks.PALE_HANGING_MOSS.defaultBlockState(), null);
        }

        if (processedBlockInfo.state().is(Blocks.CAVE_VINES_PLANT)) {
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), Blocks.PALE_HANGING_MOSS.defaultBlockState().setValue(HangingMossBlock.TIP, false), null);
        }

        var processed = processedBlockInfo.state();
        if (processed.is(Blocks.VINE)) {
            if (processed.getValue(VineBlock.UP)) {
                return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), Blocks.PALE_HANGING_MOSS.defaultBlockState(), null);
            }
            return new StructureTemplate.StructureBlockInfo(processedBlockInfo.pos(), Blocks.RESIN_CLUMP.withPropertiesOf(processed), null);
        }

        return processedBlockInfo;
    }

    @Override
    protected StructureProcessorType<?> getType() {
        return DEProcessorTypes.PALE_MONSTER_MAZE.get();
    }
}
