package com.barion.dungeons_enhanced.world.structure.prefabs;

import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.DEUtil;
import com.barion.dungeons_enhanced.world.gen.DETerrainAnalyzer;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEPieceAssembler;
import com.barion.dungeons_enhanced.world.structure.prefabs.utils.DEStructurePieces;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
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

public class DEGroundStructure extends DEBaseStructure {

    public static final String ID_HAY_STORAGE = "hay_storage";
    public static final Codec<DEGroundStructure> CODEC_HAY_STORAGE = simpleCodec(DEGroundStructure::HayStorage);
    public static DEGroundStructure HayStorage(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().add("hay_storage/small").add("hay_storage/big").build(), HAY_STORAGE::getType);
    }

    public static final String ID_JUNGLE_MONUMENT = "jungle_monument";
    public static final Codec<DEGroundStructure> CODEC_JUNGLE_MONUMENT = simpleCodec(DEGroundStructure::JungleMonument);
    public static DEGroundStructure JungleMonument(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().yOffset(-9).add("jungle_monument").build(), JUNGLE_MONUMENT::getType);
    }

    public static final String ID_MINERS_HOUSE = "miners_house";
    public static final Codec<DEGroundStructure> CODEC_MINERS_HOUSE = simpleCodec(DEGroundStructure::JungleMonument);
    public static DEGroundStructure MinersHouse(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().add("miners_house").build(), MINERS_HOUSE::getType);
    }

    public static final String ID_MUSHROOM_HOUSE = "mushroom_house";
    public static final Codec<DEGroundStructure> CODEC_MUSHROOM_HOUSE = simpleCodec(DEGroundStructure::MushroomHouse);
    public static DEGroundStructure MushroomHouse(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().add("mushroom_house/red").add("mushroom_house/brown").build(), MUSHROOM_HOUSE::getType);
    }

    public static final String ID_RUINED_BUILDING = "ruined_building";
    public static final Codec<DEGroundStructure> CODEC_RUINED_BUILDING = simpleCodec(DEGroundStructure::RuinedBuilding);
    public static DEGroundStructure RuinedBuilding(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().weight(3).add("ruined_building/house").weight(2).add("ruined_building/house_big").weight(3).add("ruined_building/barn").build(), RUINED_BUILDING::getType);
    }

    public static final String ID_STABLES = "stables";
    public static final Codec<DEGroundStructure> CODEC_STABLES = simpleCodec(DEGroundStructure::Stables);
    public static DEGroundStructure Stables(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().yOffset(-6).add("stables").build(), STABLES::getType);
    }

    public static final String ID_TALL_WITCH_HUT = "tall_witch_hut";
    public static final Codec<DEGroundStructure> CODEC_TALL_WITCH_HUT = simpleCodec(DEGroundStructure::TallWitchHut);
    public static DEGroundStructure TallWitchHut(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().yOffset(-3).add("tall_witch_hut").build(), TALL_WITCH_HUT::getType);
    }

    public static final String ID_TREE_HOUSE = "tree_house";
    public static final Codec<DEGroundStructure> CODEC_TREE_HOUSE = simpleCodec(DEGroundStructure::TreeHouse);
    public static DEGroundStructure TreeHouse(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().add("tree_house").build(), TREE_HOUSE::getType);
    }

    public static final String ID_TOWER_OF_THE_UNDEAD = "tower_of_the_undead";
    public static final Codec<DEGroundStructure> CODEC_TOWER_OF_THE_UNDEAD = simpleCodec(DEGroundStructure::TowerOfTheUndead);
    public static DEGroundStructure TowerOfTheUndead(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().weight(3).add("tower_of_the_undead/small").weight(2).add("tower_of_the_undead/big").build(), TOWER_OF_THE_UNDEAD::getType);
    }

    public static final String ID_WATCH_TOWER = "watch_tower";
    public static final Codec<DEGroundStructure> CODEC_WATCH_TOWER = simpleCodec(DEGroundStructure::WatchTower);
    public static DEGroundStructure WatchTower(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().add("watch_tower").build(), WATCH_TOWER::getType);
    }

    public static final String ID_WITCH_TOWER = "witch_tower";
    public static final Codec<DEGroundStructure> CODEC_WITCH_TOWER = simpleCodec(DEGroundStructure::WitchTower);
    public static DEGroundStructure WitchTower(StructureSettings settings){
        return new DEGroundStructure(settings, pieceBuilder().weight(3).add("witch_tower/normal").weight(2).add("witch_tower/big").build(), WITCH_TOWER::getType);
    }



    public DEGroundStructure(StructureSettings settings, DEStructurePieces variants, Supplier<StructureType<?>> type){
        super(settings, variants, type);
    }

    protected static void assemble(DEPieceAssembler.Context context) {
        context.piecesBuilder().addPiece(new Piece(context.structureManager(), context.piece(), context.pos(), context.rotation()));
    }

    @Override @Nonnull
    public Optional<GenerationStub> findGenerationPoint(@Nonnull GenerationContext context) {
        final var piece = variants.getRandomPiece(context.random());
        final var rawPos = getGenPos(context.chunkPos()).above(piece.yOffset);
        final var rotation = Rotation.getRandom(context.random());

        final var size = context.structureTemplateManager().getOrCreate(piece.Resource).getSize(rotation);

        final var result = DETerrainAnalyzer.isFlatEnough(rawPos, size, 1, 4, context.chunkGenerator(), context.heightAccessor(), context.randomState());
        if(!result.getSecond()) {return Optional.empty();}
        final var pos = rawPos.atY(Math.round(result.getFirst())).above(piece.yOffset);

        return at(pos, (builder)-> generatePieces(builder, pos, piece, rotation, context, DEGroundStructure::assemble));
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