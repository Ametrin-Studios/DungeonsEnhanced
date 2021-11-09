package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEUndergroundStructure;

public class DEDungeonVariant extends DEUndergroundStructure {
    public DEDungeonVariant(){
        super(DEConfig.COMMON.dungeon_variant, Offset(-6, 0, -6),"dungeon_variant/zombie", "dungeon_variant/skeleton", "dungeon_variant/spider");
    }
}