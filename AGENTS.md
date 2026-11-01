# MusicalInstrumentsPack context

- Data-driven Fabric+NeoForge wrapper for MC 26.3 / Java 25. `fabric/src/main/resources/{data,assets}` is shared content; Java adds the creative tab per loader. NeoForge reuses `Constants`/`ModCreativeTabs` and all resources via sourceSet includes; only loader-specific files are excluded.
- Config source of truth: root `../modsdev.json` → `projects.MusicalInstrumentsPack` (`.devin/mod.json` here is an optional standalone fallback). `gradle.properties` = JVM flags only.
- Interface is the root CLI `python ../modsdev.py`; direct `gradlew build`/`:<loader>:build` also works. Outputs: `output/<version>/` (jar per loader + combined pack ZIP + SHA-256 manifest); root `output/` gets copies.
- fabric/src/main/resources/META-INF/accesstransformer.cfg is shared and widens `CreativeModeTab$Output` for NeoForge; `musicalinstruments.accesswidener` does the same for Fabric (official namespace). NeoForge entrypoint registers the tab via DeferredRegister; Fabric via Registry.register.
- Items: vanilla carrot_on_a_stick + custom_model_data strings + `minecraft:item_name`; recipe components namespaced; `recipe_crafted` uses `conditions.recipes`. pack.mcmeta min/max [97,1]–[121,0]; only MC 26.3 is claimed/tested.
- Fabric API uses `Identifier.fromNamespaceAndPath`; tab builder needs Row+column. Don't infer older-MC support.
- Content edits → QUICKSTART; wrapper design → TEMPLATE_GUIDE; QA/CI/fields → ../ModTooling/{README,CONFIG}.md. Working tree has a large uncommitted migration — preserve it.
