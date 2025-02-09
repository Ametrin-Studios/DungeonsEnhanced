package com.barion.dungeons_enhanced.world.structure.processor;

import com.barion.dungeons_enhanced.registry.DEProcessorTypes;
import com.legacy.structure_gel.api.data.tags.SGTags;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public final class DEUnderwaterProcessor extends StructureProcessor {
    public static final DEUnderwaterProcessor INSTANCE = new DEUnderwaterProcessor();
    public static final MapCodec<DEUnderwaterProcessor> CODEC = MapCodec.unit(INSTANCE);

    private DEUnderwaterProcessor() { }

    @Override
    @Nullable
    @ParametersAreNonnullByDefault
    public StructureTemplate.StructureBlockInfo process(LevelReader level, BlockPos pos, BlockPos pos2, StructureTemplate.StructureBlockInfo existing, StructureTemplate.StructureBlockInfo placed, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        if (placed.state().is(Blocks.AIR)) {
            return null;
        }

        if (placed.state().is(SGTags.BlockTags.GEL)) {
            return new StructureTemplate.StructureBlockInfo(placed.pos(), Blocks.WATER.defaultBlockState(), null);
        }

        if (placed.state().hasProperty(BlockStateProperties.WATERLOGGED)) {
            return new StructureTemplate.StructureBlockInfo(placed.pos(), placed.state().setValue(BlockStateProperties.WATERLOGGED, true), placed.nbt());
        }

        return placed;
    }

    @Override
    @Nonnull
    protected StructureProcessorType<?> getType() {
        return DEProcessorTypes.UNDERWATER;
    }
}