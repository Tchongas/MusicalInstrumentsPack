# Musical Instruments Pack

A Fabric wrapper for a combined datapack/resource pack adding musical-instrument variants of vanilla items. Java adds creative-tab integration; recipes, functions, models, and textures supply the content.

## Build

Requires Java 25, Python 3.11+, and a sibling `../ModTooling` checkout matching `.devin/mod.json` (`0.1.0`). Other locations: set `MOD_TOOLING_HOME`.

```powershell
.\gradlew.bat build
python ../ModTooling/modtool.py verify
.\gradlew.bat :fabric:runClient
```

Successful builds put the production Fabric JAR, combined pack ZIP, and checksum manifest into `output/<version>/`. Older outputs are retained; only manifest-listed files are candidates for installation/publishing. `:fabric:build` remains supported.

Install the JAR with Fabric Loader and Fabric API versions from `.devin/mod.json`. Alternatively, install the same combined ZIP in both the world's datapacks and the client's resource packs. A datapack does not automatically enable its client assets.

## Configuration and QA

- `.devin/mod.json`: identity, versions, outputs, and per-artifact CurseForge/Modrinth project IDs. No credentials.
- `gradle.properties`: JVM settings only.
- `python ../ModTooling/modtool.py install`: build + local-instance QA installation once ignored `.devin/qa.local.json` is configured.
- `python ../ModTooling/modtool.py plan`: offline publishing preview after setting real IDs and writing release notes.
- GitHub **Validated release** workflow: builds/checks first, then publishes the exact candidate through a separately protected `publishing` environment. Required reviewers must be configured in GitHub.

Full local-instance and GitHub setup: `../ModTooling/README.md`; field reference: `../ModTooling/CONFIG.md`. Publishing is intentionally blocked until actual project IDs, release notes, and GitHub configuration are supplied. NeoForge integration is not implemented yet.

## Content

- `fabric/src/main/resources/data/`: recipes, advancements, functions, tags.
- `fabric/src/main/resources/assets/`: item selectors, models, textures.
- `creative_tab_config.json` in that resources directory: tab and item entries.
- `fabric/src/main/java/`: wrapper/creative tab.

See `QUICKSTART.md` for adding instruments, `TEMPLATE_GUIDE.md` for design, and `AGENTS.md` for concise AI context.
