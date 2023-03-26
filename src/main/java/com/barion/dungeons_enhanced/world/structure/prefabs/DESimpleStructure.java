package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePieces;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Supplier;

import static com.barion.dungeons_enhanced.DEStructures.*;
import static com.barion.dungeons_enhanced.DEUtil.pieceBuilder;

public class DESimpleStructure extends DEBaseStructure {
    public static final Codec<DESimpleStructure> CODEC_HAY_STORAGE = simpleCodec(DESimpleStructure::HayStorage);
    public static DESimpleStructure HayStorage(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().add("hay_storage/small").add("hay_storage/big").build(), HAY_STORAGE::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_JUNGLE_MONUMENT = simpleCodec(DESimpleStructure::JungleMonument);
    public static DESimpleStructure JungleMonument(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().yOffset(-9).add("jungle_monument").build(), JUNGLE_MONUMENT::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_MINERS_HOUSE = simpleCodec(DESimpleStructure::JungleMonument);
    public static DESimpleStructure MinersHouse(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().add("miners_house").build(), MINERS_HOUSE::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_MUSHROOM_HOUSE = simpleCodec(DESimpleStructure::MushroomHouse);
    public static DESimpleStructure MushroomHouse(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().add("mushroom_house/red").add("mushroom_house/brown").build(), MUSHROOM_HOUSE::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_RUINED_BUILDING = simpleCodec(DESimpleStructure::RuinedBuilding);
    public static DESimpleStructure RuinedBuilding(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().weight(3).add("ruined_building/house").weight(2).add("ruined_building/house_big").weight(3).add("ruined_building/barn").build(), RUINED_BUILDING::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_STABLES = simpleCodec(DESimpleStructure::Stables);
    public static DESimpleStructure Stables(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().yOffset(-6).add("stables").build(), STABLES::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_TALL_WITCH_HUT = simpleCodec(DESimpleStructure::TallWitchHut);
    public static DESimpleStructure TallWitchHut(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().yOffset(-3).add("tall_witch_hut").build(), TALL_WITCH_HUT::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_TREE_HOUSE = simpleCodec(DESimpleStructure::TreeHouse);
    public static DESimpleStructure TreeHouse(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().add("tree_house").build(), TREE_HOUSE::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_TOWER_OF_THE_UNDEAD = simpleCodec(DESimpleStructure::TowerOfTheUndead);
    public static DESimpleStructure TowerOfTheUndead(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().weight(3).add("tower_of_the_undead/small").weight(2).add("tower_of_the_undead/big").build(), TOWER_OF_THE_UNDEAD::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_WATCH_TOWER = simpleCodec(DESimpleStructure::WatchTower);
    public static DESimpleStructure WatchTower(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().add("watch_tower").build(), WATCH_TOWER::getType);
    }

    public static final Codec<DESimpleStructure> CODEC_WITCH_TOWER = simpleCodec(DESimpleStructure::WitchTower);
    public static DESimpleStructure WitchTower(StructureSettings settings){
        return new DESimpleStructure(settings, pieceBuilder().weight(3).add("witch_tower/normal").weight(2).add("witch_tower/big").build(), WITCH_TOWER::getType);
    }



    public DESimpleStructure(StructureSettings settings, DEStructurePieces variants, Supplier<StructureType<?>> type){
        super(settings, variants, type);
    }

    protected static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final DEStructurePieces.Piece piece = variants.getRandomPiece(context.random());
        final BlockPos rawPos = getGenPos(context.chunkPos()).above(piece.yOffset);
        final Rotation rotation = Rotation.getRandom(context.random());

        final Vec3i size = context.structureTemplateManager().get(piece.Resource).get().getSize(rotation);

        final Pair<Float, Boolean> result =  DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 4, context.chunkGenerator(), context.heightAccessor(), context.randomState());
        if(!result.getSecond()) {return Optional.empty();}
        final BlockPos pos = rawPos.atY(Math.round(result.getFirst())).above(piece.yOffset);

        return at(pos, (builder)-> generatePieces(builder, pos, piece, rotation, context, DESimpleStructure::assemble));
    }

    protected BlockPos getGenPos(ChunkPos chunkPos){
        return DEUtil.ChunkPosToBlockPos(chunkPos);
    }

    public static class Piece extends DEBaseStructure.Piece{
        public Piece(StructureTemplateManager structureManager, ResourceLocation templateName, BlockPos pos, Rotation rotation){
            super(DEStructures.RUINED_BUILDING.getPieceType(), structureManager, templateName, pos, rotation);
        }

        public Piece(StructurePieceSerializationContext serializationContext, CompoundTag nbt){
            super(DEStructures.RUINED_BUILDING.getPieceType(), serializationContext, nbt);
        }
    }
}