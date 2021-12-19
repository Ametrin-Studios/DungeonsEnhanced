package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEPiece;
import com.barion.dungeons_enhanced.structures.prefabs.DEUndergroundStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEDungeonVariant extends DEUndergroundStructure {
    public DEDungeonVariant(){
        super(DEConfig.COMMON.dungeon_variant, Offset(-6, 0, -6), true, new DEPiece("dungeon_variant/zombie"), new DEPiece("dungeon_variant/skeleton"), new DEPiece("dungeon_variant/spider"));
    }
}