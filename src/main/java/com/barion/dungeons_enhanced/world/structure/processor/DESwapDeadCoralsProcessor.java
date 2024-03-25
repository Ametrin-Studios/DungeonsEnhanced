package com.barion.dungeons_enhanced.world.structure.processor;

import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.registry.DEProcessorTypes;
import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;

public class DESwapDeadCoralsProcessor extends StructureProcessor {
    public static final DESwapDeadCoralsProcessor INSTANCE = new DESwapDeadCoralsProcessor();
    public static final Codec<DESwapDeadCoralsProcessor> CODEC = Codec.unit(() -> INSTANCE);

    private static final Map<Block, Block> DEATH_TO_LIVING_CORAL = new ImmutableMap.Builder<Block, Block>()
            .put(Blocks.DEAD_BRAIN_CORAL, Blocks.BRAIN_CORAL)
            .put(Blocks.DEAD_BUBBLE_CORAL, Blocks.BUBBLE_CORAL)
            .put(Blocks.DEAD_FIRE_CORAL, Blocks.FIRE_CORAL)
            .put(Blocks.DEAD_HORN_CORAL, Blocks.HORN_CORAL)
            .put(Blocks.DEAD_TUBE_CORAL, Blocks.TUBE_CORAL)

            .put(Blocks.DEAD_BRAIN_CORAL_BLOCK, Blocks.BRAIN_CORAL_BLOCK)
            .put(Blocks.DEAD_BUBBLE_CORAL_BLOCK, Blocks.BUBBLE_CORAL_BLOCK)
            .put(Blocks.DEAD_FIRE_CORAL_BLOCK, Blocks.FIRE_CORAL_BLOCK)
            .put(Blocks.DEAD_HORN_CORAL_BLOCK, Blocks.HORN_CORAL_BLOCK)
            .put(Blocks.DEAD_TUBE_CORAL_BLOCK, Blocks.TUBE_CORAL_BLOCK)

            .put(Blocks.DEAD_BRAIN_CORAL_FAN, Blocks.BRAIN_CORAL_FAN)
            .put(Blocks.DEAD_BUBBLE_CORAL_FAN, Blocks.BUBBLE_CORAL_FAN)
            .put(Blocks.DEAD_FIRE_CORAL_FAN, Blocks.FIRE_CORAL_FAN)
            .put(Blocks.DEAD_HORN_CORAL_FAN, Blocks.HORN_CORAL_FAN)
            .put(Blocks.DEAD_TUBE_CORAL_FAN, Blocks.TUBE_CORAL_FAN)

            .put(Blocks.DEAD_BRAIN_CORAL_WALL_FAN, Blocks.BRAIN_CORAL_WALL_FAN)
            .put(Blocks.DEAD_BUBBLE_CORAL_WALL_FAN, Blocks.BUBBLE_CORAL_WALL_FAN)
            .put(Blocks.DEAD_FIRE_CORAL_WALL_FAN, Blocks.FIRE_CORAL_WALL_FAN)
            .put(Blocks.DEAD_HORN_CORAL_WALL_FAN, Blocks.HORN_CORAL_WALL_FAN)
            .put(Blocks.DEAD_TUBE_CORAL_WALL_FAN, Blocks.TUBE_CORAL_WALL_FAN)
            .build();

    private DESwapDeadCoralsProcessor() {}

    @Override @Nullable @ParametersAreNonnullByDefault
    public Template.BlockInfo process(IWorldReader world, BlockPos pos1, BlockPos pos2, Template.BlockInfo existing, Template.BlockInfo placed, PlacementSettings settings, @Nullable Template template) {
        if(!DEATH_TO_LIVING_CORAL.containsKey(placed.state.getBlock())) return placed;

        BlockState livingCoral = DEUtil.withPropertiesOf(DEATH_TO_LIVING_CORAL.get(placed.state.getBlock()), placed.state);
        return new Template.BlockInfo(placed.pos, livingCoral, null);
    }

    @Override @Nonnull
    protected IStructureProcessorType<?> getType() {return DEProcessorTypes.SWAP_DEAD_CORALS_PROCESSOR;}
}
