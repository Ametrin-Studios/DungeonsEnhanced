package com.ametrin.dungeons_enhanced.registry;

import com.ametrin.dungeons_enhanced.DungeonsEnhanced;
import com.ametrin.dungeons_enhanced.world.structure.DEIcePitPieces;
import com.ametrin.structures.registry.ASRegistries;
import com.ametrin.structures.structure.simple.PieceSourceType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class DEPieceSources {
    public static final DeferredRegister<PieceSourceType> REGISTER = DeferredRegister.create(ASRegistries.PIECE_SOURCE_TYPE, DungeonsEnhanced.MOD_ID);

    public static final DeferredHolder<PieceSourceType, PieceSourceType> ICE_PIT = REGISTER.register(DEStructureIDs.ICE_PIT, () -> new PieceSourceType(DEIcePitPieces.CODEC));
}
