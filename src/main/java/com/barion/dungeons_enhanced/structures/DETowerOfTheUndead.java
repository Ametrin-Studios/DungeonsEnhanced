package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DETowerOfTheUndead extends DESimpleStructure {
    public DETowerOfTheUndead(){
        super(DEConfig.COMMON.tower_of_the_undead);
        MainPiece = DEStructures.locate("tower_of_the_undead");
        Offset = Offset(-7,0,-7);
    }

    @Override
    public int getSeed() {
        return 2636541;
    }
}
