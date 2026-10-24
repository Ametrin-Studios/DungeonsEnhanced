package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.world.structure.processor.DEBadlandsFromDesertProcessor;
import com.ametrin.dungeons_enhanced.world.structure.processor.DEPaleMonsterMazeProcessor;
import com.ametrin.dungeons_enhanced.world.structure.processor.DESwapDeadCoralsProcessor;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

//import com.ametrin.dungeons_enhanced.world.structure.processor.DEUnderwaterProcessor;

public final class DEProcessorTypes {
    public static final DeferredRegister<StructureProcessorType<?>> REGISTER = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, DungeonsEnhanced.MOD_ID);

    public static final Supplier<StructureProcessorType<DESwapDeadCoralsProcessor>> SWAP_DEAD_CORALS = REGISTER.register("swap_dead_corals", () -> () -> DESwapDeadCoralsProcessor.CODEC);
    public static final Supplier<StructureProcessorType<DEPaleMonsterMazeProcessor>> PALE_MONSTER_MAZE = REGISTER.register("pale_monster_maze", () -> () -> DEPaleMonsterMazeProcessor.CODEC);
    public static final Supplier<StructureProcessorType<DEBadlandsFromDesertProcessor>> BADLANDS_FROM_DESERT = REGISTER.register("badlands_from_desert", () -> () -> DEBadlandsFromDesertProcessor.CODEC);
}
