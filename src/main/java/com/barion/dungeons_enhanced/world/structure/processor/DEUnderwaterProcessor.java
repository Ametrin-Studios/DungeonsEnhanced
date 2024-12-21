package com.barion.dungeons_enhanced.world.structure.processor;

import com.barion.dungeons_enhanced.registry.DEProcessorTypes;
import com.legacy.structure_gel.data.GelTags;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.gen.feature.template.IStructureProcessorType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.StructureProcessor;
import net.minecraft.world.gen.feature.template.Template;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public final class DEUnderwaterProcessor extends StructureProcessor {
    public static final DEUnderwaterProcessor INSTANCE = new DEUnderwaterProcessor();
    public static final Codec<DEUnderwaterProcessor> CODEC = Codec.unit(() -> INSTANCE);

    private DEUnderwaterProcessor() { }

    @Override
    @Nullable
    @ParametersAreNonnullByDefault
    public Template.BlockInfo process(IWorldReader world, BlockPos pos, BlockPos pos2, Template.BlockInfo existing, Template.BlockInfo placed, PlacementSettings settings, @Nullable Template template) {
        if (placed.state.is(Blocks.AIR)) {
            return null;
        }
        if (placed.state.is(GelTags.GEL)) {
            return new Template.BlockInfo(placed.pos, Blocks.WATER.defaultBlockState(), null);
        }
        if (placed.state.hasProperty(BlockStateProperties.WATERLOGGED)) {
            return new Template.BlockInfo(placed.pos, placed.state.setValue(BlockStateProperties.WATERLOGGED, true), placed.nbt);
        }

        return placed;
    }

    @Override
    @Nonnull
    protected IStructureProcessorType<?> getType() {
        return DEProcessorTypes.UNDERWATER;
    }
}