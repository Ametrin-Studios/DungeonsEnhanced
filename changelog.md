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