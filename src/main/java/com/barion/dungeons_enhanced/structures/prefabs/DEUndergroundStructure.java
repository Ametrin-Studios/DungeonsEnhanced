package com.barion.dungeons_enhanced.structures.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.legacy.structure_gel.api.config.StructureConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureManager;

public class DEUndergroundStructure extends DEBaseStructure {

    public DEUndergroundStructure(String resource, BlockPos offset, StructureConfig config){
        this(config, offset, resource);
    }
    public DEUndergroundStructure(StructureConfig config, BlockPos offset, String... resources){
        super(config, GenerationType.undergound, offset, resources);
    }

    public static class Piece extends DEBaseStructure.Piece {
        public Piece(StructureManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation, int componentType){
            super(DEStructures.DungeonVariant.getPieceType(), structureManager, templateName, pos, rotation, componentType);
        }
        public Piece(StructureManager structureManager, ResourceLocation location, BlockPos pos, Rotation rotation) {
            this(structureManager, location, pos, rotation, 0);
        }
        public Piece(ServerLevel level, CompoundTag nbt) {
            super(DEStructures.DungeonVariant.getPieceType(), level, nbt);
        }
    }
}