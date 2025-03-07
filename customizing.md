make sure you are on the right page

| mc version      | mod version  | page                                                                                      |
|-----------------|--------------|-------------------------------------------------------------------------------------------|
| 1.16.5 - 1.19.2 | all          | check the config file                                                                     |
| 1.19.4 - 1.20.1 | before 5.4.0 | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/customizing.md) |
| 1.20.4          | all          | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/customizing.md) |
| 1.21.4          | 6.0          | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/customizing.md) |
| 1.20.1          | 5.4.0 +      | this page                                                                                 |
| 1.21.4          | 6.1.0 +      | this page                                                                                 |


- [download](https://download-directory.github.io/?url=https%3A%2F%2Fgithub.com%2FAmetrin-Studios%2FDungeonsEnhanced%2Ftree%2Fmaster%2FDATA-PACK-TEMPLATE) the DATA-PACK-TEMPLATE folder (you may rename it)

## Structure Frequency
- locate the json file of the structure you want to modify in `data/dungeons_enhanced/worldgen/structure_set`
- open it with a text editor and modify its values
- ONLY MODIFY `spacing`, `frequency` and `offset` (if you know what you're doing you may change everything)
  - spacing: the average distance between generation attempts
  - offset: offsets the generation attempt randomly, MUST be lower than spacing
  - frequency: probability for the generation attempt to happen

## Biomes
- locate the json file of the structure you want to modify in `data/dungeons_enhanced/tags/worldgen/biome/has_structure`
- you can add any biome or biome-tag in `values` to make the structure appear there
- if you don't want the structure to appear there add the biome or biome-tag to `remove`
```json5
// this will add all cold overworld biomes
// except aquatic biomes and ice spikes
{
  "values": [
    "#c:is_cold/overworld"
  ],
  "remove": [
    "#c:is_aquatic",
    "minecraft:ice_spikes"
  ]
}
// '#c:...' is only available in NeoForge
```
- you can add biomes to `no_structures.json` to prevent all Dungeons Enhanced structures from generating in them

## Loot Tables
- there is no template right now

## Using the data pack
- delete all .json files that you didn't change
- when creating a world add the data pack
- test the data pack and check the log for any errors
- let us know what you changed so we can improve the default values
- you may need to update your data pack with major updates, check [changelog](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/master/changelog.md) for any notes

### What happened to the config?
We are aware that the config was a convenient and easy way to customize how structures generate.
Mojang and the modding community are pushing towards data packs because they represent a uniform way of modifications.
Unfortunately your config-patch caused too many problems (e.g. with Structurify) so we decided to fully replace it with data packs.
