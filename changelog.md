## 4.2.1
* reimplemented biome config (spreading needs data packs for now)
* added Sunken Shrine variant
* tweaked config
* tweaked spreading
* spaced out Pillager Camp
* updated translations
* added Spanish
* added French
* added Romanian
* added Portuguese
* requires Structure Gel API 2.11.0 

## 4.2
* ported to 1.19.4
* fixed canon in Flying Dutchman
* Monster Maze now generates between -48 and -16
* requires Structure Gel API 2.10.0

## 4.1
* added german translation
* updated english translation
* fix DEUtil#ChunkPosToBlockPosFromHeightMap using wrong coordinates causing structures to float (#28)
* requires Structure Gel API 2.7.0

## 4.0
* ported to 1.19.2
* updated config as `BiomeDictionary` got removed (needs more testing)
* jigsaw structures no longer have access to the terrain analyzer as they got hard-coded (we and structure gel already work on a solution)
* increased Monster Maze size again thanks to Structure Gels `ExtendedJigsawStructure`