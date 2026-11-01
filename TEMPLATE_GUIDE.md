# Fabric Datapack Wrapper Guide

## Design

Musical Instruments Pack is a Fabric mod that packages datapack and resource-pack content in one distributable JAR. It does not register custom item types. Each instrument is a vanilla `minecraft:carrot_on_a_stick` carrying item components that select a custom appearance and name.

Fabric supplies the wrapper needed to load the resources and register a dedicated creative tab. Content changes normally require JSON, function, model, or texture changes rather than new Java code.

## Resource flow

1. A recipe under `fabric/src/main/resources/data/flutedatapack/recipe/` creates a vanilla stack with `minecraft:custom_model_data` and `minecraft:item_name` components.
2. `assets/minecraft/items/carrot_on_a_stick.json` reads the custom-model-data string and selects a model.
3. The selected model under `assets/minecraft/models/item/` references textures under `assets/minecraft/textures/item/`.
4. `creative_tab_config.json` lists equivalent stacks for the Fabric creative tab.

## Adding an instrument

1. Choose a unique custom-model-data string.
2. Add an entry to `creative_tab_config.json`.
3. Add a selector case to `assets/minecraft/items/carrot_on_a_stick.json`.
4. Add the item model and texture.
5. Add the recipe and any advancement or function changes.
6. Run `./gradlew :fabric:build`.

The same custom-model-data value must be used by the recipe, item selector, and creative-tab configuration.

## Java wrapper

The Fabric entrypoint registers one `CreativeModeTab`. `ModCreativeTabs` reads the JSON configuration and builds vanilla item stacks using:

- `DataComponents.CUSTOM_MODEL_DATA`
- `DataComponents.ITEM_NAME`
- `CustomModelData` string values

The access widener permits the creative-tab display callback to use `CreativeModeTab.Output` on Minecraft 26.3.

## Commands

```powershell
.\gradlew.bat :fabric:build
.\gradlew.bat :fabric:runClient
.\gradlew.bat :fabric:runServer
```

Successful builds write the production Fabric JAR, a combined ZIP containing `pack.mcmeta`, `data/`, and `assets/`, and a checksum manifest into `output/<version>/`. When used without the Fabric mod, install the ZIP as both a datapack and a resource pack so Minecraft loads both the server-side data and client-side visuals.

## Version configuration

Edit `.devin/mod.json` to update Minecraft, Java, Fabric Loader/API/Loom, mod metadata, outputs, and platform IDs. `gradle.properties` holds JVM flags only. Shared tooling configuration and limitations: `../ModTooling/CONFIG.md`.
