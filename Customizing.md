## Biomes to generate in
- open the Dungeons Enhanced common config file in the config folder (`dungeons_enhanced-common.toml`)
- find the structure you want to modify
- Use # to define a tag. Use ! to define a blacklist.
- Example: `["minecraft:plains, #minecraft:is_forest, !minecraft:birch_forest, !#minecraft:has_structure/woodland_mansion"]` this will generate in the plains and all forests except birch forests and biomes that have a woodland_mansion

Example
```toml
[druid_circle]
    #
    # The biomes this structure should generate in. Use # to define a tag. Use ! to define a blacklist. If no values are set, the structure's default biomes will be used.
    # Example: ["minecraft:plains, #minecraft:is_forest, !minecraft:birch_forest, !#minecraft:has_structure/woodland_mansion"]
    # Default: ["#forge:is_plains"]
    "dungeons_enhanced:druid_circle" = ["#forge:is_plains", "minecraft:birch_forest"]
```

## Structure Frequency
- [download](https://minhaskamal.github.io/DownGit/#/home?url=https://github.com/BarionLP/DungeonsEnhanced/tree/master/DATA-PACK-TEMPLATE) the DATA-PACK-TEMPLATE folder and rename it
- locate the json file of the structure you want to modify in `data/dungeons_enhanced/worldgen/structure_set`
- open it with a text editor and modify its values
- ONLY MODIFY `spacing`, `frequency` and `offset` (if you know what you're doing you may change everything)
  - spacing: the average distance between generation attempts
  - offset: offsets the generation attempt randomly, MUST be lower than spacing
  - frequency: probability for the generation attempt to happen
- delete all structure files that you didn't change
- when creating a world add the data pack
- test the data pack and check the log for any errors
- let us know what you changed and why so we can balance the default values better
- you may need to update your data pack with major updates, check the [changelog](https://github.com/BarionLP/DungeonsEnhanced/blob/master/changelog.md) for any notes

## Loot Tables
- data pack only there is no template right now.
