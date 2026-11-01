package com.tchongass.musicalinstruments;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(Constants.MOD_ID)
public class ExampleMod {

    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public ExampleMod(IEventBus eventBus) {
        Constants.LOG.info("Loading {}", Constants.MOD_NAME);
        TABS.register("main_tab", () -> ModCreativeTabs
                .createMainTab(CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0))
                .build());
        TABS.register(eventBus);
    }
}
