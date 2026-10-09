make sure you are on the right page

| mc version      | mod version   | page                                                                                                                        |
|-----------------|---------------|-----------------------------------------------------------------------------------------------------------------------------|
| 1.16.5 - 1.19.2 | all           | check the config file                                                                                                       |
| 1.19.4 - 1.20.1 | before 5.4.0  | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/customizing.md)                                   |
| 1.20.4          | all           | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/customizing.md)                                   |
| 1.21.4          | 6.0           | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/customizing.md)                                   |
| 1.20.1          | 5.4.0 +       | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/3a86d2706c25fef9912c5fa47d82a3520392a826/customizing.md) |
| 1.21.4 - 26.1.2 | 6.1.0 - 7.0.0 | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/3a86d2706c25fef9912c5fa47d82a3520392a826/customizing.md) |
| all             | 7.0.0 +       | this page                                                                                                                   |


# Customize Dungeons Enhanced
- You should be familiar with data pack creation in general. Some sources to help you:
  - https://datapack.wiki/
  - https://misode.github.io/
  - https://minecraft.wiki/
  - https://mcsrc.dev/
- download the DATA-PACK-TEMPLATE folder as a start. Once you are done, delete all .json files you did not change.
- there are more things to overwrite in [src/generated/resources/data/dungeons_enhanced](src/generated/resources/data/dungeons_enhanced)

## Structure Frequency
- locate the .json file of the structure you want to modify in `data/dungeons_enhanced/worldgen/structure_set`
- open it with a text editor and modify it
  - spacing: the average distance between generation attempts. Bigger is rarer.
  - random_offset: offsets the generation attempt randomly, MUST be lower than spacing. Set it to 0 for a strict grid pattern
  - probably: probability for the generation attempt to succeed. Between 0 and 1. Smaller is rarer.
- never change things you don't fully understand

## Biomes
- locate the .json file of the structure you want to modify in `data/dungeons_enhanced/tags/worldgen/biome/has_structure`
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
```
- you can add biomes to `no_structures.json` to prevent all Dungeons Enhanced structures from generating in them

## Loot Tables
- there is no template right now
- check out https://docs.neoforged.net/docs/resources/server/loottables/glm/ for a general guide
- some loot tables are used in more structures than their original structure

## Spawners
- some structures use spawner profiles which let data packs modify the spawner.
- not all structures support this yet
- see `data/dungeons_enhanced/ametrin_structures/spawner_profile` for normal spawners
- see `data/dungeons_enhanced/trial_spawner` for trial spawners
- to change the mobs equipment, modify the corresponding equipment loot table instead of the spawner 

## Using the data pack
- delete all .json files you did not change
- create a new world with the data pack
- test the data pack and check the log for errors
- let us know what you changed so we can improve our default values
- you probably need to update your data pack with major updates, check the [changelog](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/main/changelog.md) for notes

### What happened to the config?
We are aware that the config was a convenient and easy way to customize how structures generate.  
Mojang and the modding community are pushing towards data packs because they represent are a more powerful way of modifications.  
Unfortunately our config-patch caused too many problems (e.g. with Structurify) so we decided to fully replace it with data packs.
