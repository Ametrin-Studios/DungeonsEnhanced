package com.ametrin.dungeons_enhanced.world.structure.processor;

import com.ametrin.dungeons_enhanced.registry.DEProcessorTypes;
import com.ametrin.structures.api.ASTags;
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

@Deprecated //TODO: see if AS can do that
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

        if (placed.state().is(ASTags.Blocks.FOAM)) {
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