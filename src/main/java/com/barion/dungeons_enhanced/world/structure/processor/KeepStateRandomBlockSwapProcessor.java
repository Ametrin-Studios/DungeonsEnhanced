package com.barion.dungeons_enhanced.world.structure.processor;

import com.barion.dungeons_enhanced.registry.DEProcessorTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;

public final class KeepStateRandomBlockSwapProcessor extends StructureProcessor {
    public static final MapCodec<KeepStateRandomBlockSwapProcessor> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("condition").forGetter(processor -> processor.condition),
                    Codec.FLOAT.fieldOf("chance").forGetter(processor -> processor.chance),
                    BuiltInRegistries.BLOCK.byNameCodec().fieldOf("change_to").forGetter(processor -> processor.changeTo))
            .apply(instance, KeepStateRandomBlockSwapProcessor::new));

    public final Block condition;
    public final float chance;
    public final Block changeTo;

    public KeepStateRandomBlockSwapProcessor(Block condition, float chance, Block changeTo) {
        this.condition = condition;
        this.chance = chance;
        this.changeTo = changeTo;
    }

    @Override
    @ParametersAreNonnullByDefault
    public StructureTemplate.StructureBlockInfo process(LevelReader level, BlockPos pos, BlockPos pos2, StructureTemplate.StructureBlockInfo existing, StructureTemplate.StructureBlockInfo placed, StructurePlaceSettings settings, @Nullable StructureTemplate template) {
        if (placed.state().is(condition) && (chance == 1 || settings.getRandom(placed.pos()).nextFloat() < this.chance)) {
            return new StructureTemplate.StructureBlockInfo(placed.pos(), changeTo.withPropertiesOf(placed.state()), placed.nbt());
        }
        return placed;
    }

    @Override
    protected @NotNull StructureProcessorType<?> getType() {
        return DEProcessorTypes.KEEP_STATE_RANDOM_BLOCK_SWAP;
    }
}