package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.structures.prefabs.DEUndergroundStructure;

import static com.barion.dungeons_enhanced.DEUtil.Offset;

public class DEDungeonVariant extends DEUndergroundStructure {
    public DEDungeonVariant(){
        super(DEConfig.COMMON.dungeon_variant, "dungeon_variant/zombie", "dungeon_variant/skeleton", "dungeon_variant/spider");
        Offset = Offset(-6, 0, -6);
    }
}
