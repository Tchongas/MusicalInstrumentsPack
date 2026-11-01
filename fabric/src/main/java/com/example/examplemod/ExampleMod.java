package com.tchongass.musicalinstruments;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public class ExampleMod implements ModInitializer {

    private static final ResourceKey<CreativeModeTab> MAIN_TAB_KEY =
            ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(),
                    Identifier.fromNamespaceAndPath(Constants.MOD_ID, "main_tab"));

    @Override
    public void onInitialize() {

        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Loading {}", Constants.MOD_NAME);

        // Register custom creative tab
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MAIN_TAB_KEY,
                ModCreativeTabs.createMainTab(
                        CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)).build());
    }
}
