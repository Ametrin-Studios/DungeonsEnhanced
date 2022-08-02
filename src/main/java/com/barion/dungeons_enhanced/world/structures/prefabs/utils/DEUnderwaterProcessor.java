package com.barion.dungeons_enhanced.world.structures.prefabs.utils;

import com.barion.dungeons_enhanced.DEUtil;
import com.legacy.structure_gel.api.tags.GelBlockTags;
import com.mojang.serialization.Codec;
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

public class DEUnderwaterProcessor extends StructureProcessor {
    public static final DEUnderwaterProcessor Instance = new DEUnderwaterProcessor();
    public static final Codec<DEUnderwaterProcessor> CODEC = Codec.unit(() -> Instance);

    private DEUnderwaterProcessor() {}

    @Override @Nullable @ParametersAreNonnullByDefault
    public StructureTemplate.StructureBlockInfo process(LevelReader level, BlockPos pos, BlockPos pos2, StructureTemplate.StructureBlockInfo existing, StructureTemplate.StructureBlockInfo placed, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        if(placed.state.is(Blocks.AIR)){
            return null;
        }

        if(placed.state.is(GelBlockTags.GEL)){
            return new StructureTemplate.StructureBlockInfo(placed.pos, Blocks.WATER.defaultBlockState(), null);
        }

        if(placed.state.hasProperty(BlockStateProperties.WATERLOGGED)){
            return new StructureTemplate.StructureBlockInfo(placed.pos, placed.state.setValue(BlockStateProperties.WATERLOGGED, true), null);
        }

        return placed;
    }

    @Override @Nonnull
    protected StructureProcessorType<?> getType() {return DEUtil.Processors.Types.Underwater;}
}