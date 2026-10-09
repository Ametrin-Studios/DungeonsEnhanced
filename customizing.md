Make sure you are on the right page:

| mc version      | mod version   | guide                                                                                                                       |
|-----------------|---------------|-----------------------------------------------------------------------------------------------------------------------------|
| 1.16.5 - 1.19.2 | all           | check the config file                                                                                                       |
| 1.19.4 - 1.20.1 | before 5.4.0  | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/customizing.md)                                   |
| 1.20.4          | all           | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/customizing.md)                                   |
| 1.21.4          | 6.0           | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/1.20.1/customizing.md)                                   |
| 1.20.1          | 5.4.0 +       | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/3a86d2706c25fef9912c5fa47d82a3520392a826/customizing.md) |
| 1.21.4 - 26.1.2 | 6.1.0 - 6.4.1 | [go here](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/3a86d2706c25fef9912c5fa47d82a3520392a826/customizing.md) |
| all             | 7.0.0 +       | this page                                                                                                                   |

# Customizing Dungeons Enhanced
Dungeons Enhanced can be customized with a data pack.  
Check out [datapack.wiki](https://datapack.wiki/), [misode.github.io](https://misode.github.io/) and the [Minecraft Wiki](https://minecraft.wiki/w/Tutorial:Creating_a_data_pack) if you need help.  
You can ask question on the [Ametrin Studios discord](https://discord.gg/Ye6WxRV2Tt) or the official [NeoForge discord](https://discord.com/invite/UvedJ9m)

1. Download the [DATA-PACK-TEMPLATE](DATA-PACK-TEMPLATE) folder.
2. Edit the files you want (see below).
3. Delete every file you did not change.

All file references below are in `data/dungeons_enhanced`.  
All default files (loot tables, structures, processors, advancements, …) are in [src/generated/resources/data/dungeons_enhanced](src/generated/resources/data/dungeons_enhanced). Copy any additional file you need into your data pack at the same path to overwrite it.

## How often structures spawn
Files: `worldgen/structure_set/<structure>.json`

| field                    | meaning                                                                                  |
|--------------------------|------------------------------------------------------------------------------------------|
| `spacing`                | average distance (in chunks) between spawn attempts. Bigger = rarer                      |
| `random_offset`          | random offset of each attempt (in chunks). Must be lower than `spacing`. 0 = strict grid |
| `probability`            | chance an attempt succeeds (0 to 1). Smaller = rarer                                     |
| `min_chunks_from_center` | no spawns closer than this (in chunks) to the world center (0, 0)                        |
| `weight`                 | how often each structure is picked if a set has several                                  |

Leave `salt` and `type` alone.

## Where structures spawn
Files: `tags/worldgen/biome/has_structure/<structure>.json`

Add biomes or biome tags to `values` to allow the structure there or to `remove` to block it.
```json5
// all cold overworld biomes, except aquatic biomes and ice spikes
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

To block **all** Dungeons Enhanced structures in a biome, add it to `tags/worldgen/biome/no_structures.json`.

## Spawners
- normal spawners: `ametrin_structures/spawner_profile`
- trial spawners: `trial_spawner`
- To change a mob's armor and weapons, edit its equipment loot table (`loot_table/equipment`), not the spawner.

Not every structure uses spawner profiles yet.

## Loot tables
There is no template for loot tables. Prefer [Global Loot Modifiers](https://docs.neoforged.net/docs/resources/server/loottables/glm/) over overwriting them directly.  
Note that some loot tables are shared by multiple structures.

## Testing
- Create a new world with your data pack and check the log for errors.
- After major mod updates, check the [changelog](https://github.com/Ametrin-Studios/DungeonsEnhanced/blob/main/changelog.md). Your data pack may need changes.
- Let us know what you changed, it helps us improve the default settings!

## What happened to the config?
Data packs are more powerful and the standard way to customize worldgen so we fully replaced the config.
Our config-patch caused too many problems (e.g. with Structurify) so we decided it was not worth keeping.
