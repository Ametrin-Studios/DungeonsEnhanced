package com.barion.dungeons_enhanced;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ChestBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class DEUtil{
    public static void createChest(ICreateChest chestCreator, IServerWorld world, MutableBoundingBox bounds, Random rand, BlockPos pos, ResourceLocation lootTable, Rotation rotation, String[] data) {
        Direction facing = data.length > 1 ? Direction.byName(data[1]) : Direction.NORTH;
        ChestType chestType = data.length > 2 ? data[2].equals(ChestType.LEFT.getSerializedName()) ? ChestType.LEFT : (data[2].equals(ChestType.RIGHT.getSerializedName()) ? ChestType.RIGHT : ChestType.SINGLE) : ChestType.SINGLE;
        createChest(chestCreator, world, bounds, rand, pos, lootTable, rotation, facing, chestType);
    }

    public static void createChest(ICreateChest chestCreator, IServerWorld world, MutableBoundingBox bounds, Random rand, BlockPos pos, ResourceLocation lootTable, Rotation rotation, Direction facing, ChestType chestType) {
        boolean waterlog = world.getFluidState(pos).getType() == Fluids.WATER;
        BlockState chest = Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, facing).setValue(ChestBlock.TYPE, chestType).setValue(ChestBlock.WATERLOGGED, waterlog).rotate(world, pos, rotation);
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 2);
        chestCreator.createChest(world, bounds, rand, pos, lootTable, chest);
    }

    public static interface ICreateChest{
        boolean createChest(IServerWorld world, MutableBoundingBox bounds, Random rand, BlockPos pos, ResourceLocation lootTable, @Nullable BlockState chest);
    }
}