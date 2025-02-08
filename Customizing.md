Starting with 1.21.4-6.1 all modifications have to be done via a data pack.

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
- you can add or remove any biomes or biome-tags in `values`
- if you want to remove a biome that is not explicitly in `values` add it to `remove`

## Loot Tables
- there is no template right now.

## Using the data pack
- delete all .json files that you didn't change
- when creating a world add the data pack
- test the data pack and check the log for any errors
- let us know what you changed so we can improve the default values
- you may need to update your data pack with major updates, check [changelog](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/master/changelog.md) for any notes

## Older versions
For older versions of Dungeons Enhanced check: https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/Customizing.md

### What happened to the config?
We are aware that the config was a convenient and easy way to customize how structures generate.
Mojang and the modding community are pushing towards data packs because they represent a uniform way of modifications.
Unfortunately the config caused too many problems (e.g. with Structurify) so we decided to fully replace it with data packs
