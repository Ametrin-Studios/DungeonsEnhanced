package com.barion.dungeons_enhanced.structures;

import com.barion.dungeons_enhanced.DEConfig;
import com.barion.dungeons_enhanced.DEStructures;
import com.barion.dungeons_enhanced.structures.prefabs.DESimpleStructure;

public class DEHayStorage extends DESimpleStructure {
    public DEHayStorage(){
        super(DEConfig.COMMON.hay_Storage);
        MainPiece = DEStructures.locate("hay_storage");
        Offset = Offset(-7,0,-7);
    }

    @Override
    public int getSeed() {
        return 254661671;
    }
}
