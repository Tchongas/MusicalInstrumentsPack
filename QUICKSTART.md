# Quick Reference

## Build and run

Run from the repository root:

```powershell
.\gradlew.bat :fabric:build
.\gradlew.bat :fabric:runClient
```

Successful builds place the Fabric JAR, combined datapack/resource-pack ZIP, and checksum manifest in `output/<version>/`. Requires Java 25, Python 3.11+, and sibling ModTooling. Install the ZIP in a world's `datapacks` directory and also enable it as a resource pack when custom models and textures are required. Build/QA/release setup: `README.md`.

## Add an instrument

1. Choose a unique custom-model-data string.
2. Add the display name and value to `fabric/src/main/resources/creative_tab_config.json`.
3. Add a matching case to `fabric/src/main/resources/assets/minecraft/items/carrot_on_a_stick.json`.
4. Add the model under `fabric/src/main/resources/assets/minecraft/models/item/`.
5. Add textures under `fabric/src/main/resources/assets/minecraft/textures/item/carrot_on_a_stick/`.
6. Add or update the recipe under `fabric/src/main/resources/data/flutedatapack/recipe/`.
7. Rebuild the Fabric JAR.

## Creative tab

Generated automatically at build time from datapack recipes — every `minecraft:item_name` + `minecraft:custom_model_data` in `data/**/recipe/*.json` becomes a tab entry. Adding a recipe adds the item to the tab; no config file needed.

Tab title/icon come from `pack.tab_name` / `pack.icon_custom_model_data` in `modsdev.json` (or `.devin/mod.json`).

## Recipe components

Use namespaced component keys such as `minecraft:item_name` and `minecraft:custom_model_data`. `minecraft:item_name` produces a normal, non-italic item name.

## Main locations

| Content | Location |
|---|---|
| Creative-tab configuration | `fabric/src/main/resources/creative_tab_config.json` |
| Item selector | `fabric/src/main/resources/assets/minecraft/items/carrot_on_a_stick.json` |
| Models | `fabric/src/main/resources/assets/minecraft/models/item/` |
| Textures | `fabric/src/main/resources/assets/minecraft/textures/item/carrot_on_a_stick/` |
| Recipes and advancements | `fabric/src/main/resources/data/flutedatapack/` |
| Functions | `fabric/src/main/resources/data/flute_func/function/` |
| Fabric Java integration | `fabric/src/main/java/` |
