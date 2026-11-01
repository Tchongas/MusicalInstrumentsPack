package com.tchongass.musicalinstruments;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.CustomModelData;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Shared creative tab definitions. Reads item list from creative_tab_config.json.
 *
 * All "custom items" are vanilla carrot_on_a_stick with custom_model_data and
 * item_name components. The visual appearance is handled entirely by the datapack
 * (item model JSON + textures). This class only needs to build the correct
 * ItemStacks so they show up in the creative inventory.
 */
public class ModCreativeTabs {

    private static final JsonObject CONFIG = loadConfig();

    private static JsonObject loadConfig() {
        try (InputStream is = ModCreativeTabs.class.getResourceAsStream("/creative_tab_config.json")) {
            if (is == null) throw new RuntimeException("creative_tab_config.json not found in resources!");
            return new Gson().fromJson(new InputStreamReader(is, StandardCharsets.UTF_8), JsonObject.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load creative_tab_config.json", e);
        }
    }

    public static CreativeModeTab.Builder createMainTab(CreativeModeTab.Builder builder) {
        String tabName = CONFIG.get("tab_name").getAsString();
        String iconCmd = CONFIG.get("icon_custom_model_data").getAsString();

        return builder
                .title(Component.literal(tabName))
                .icon(() -> createItem(tabName, iconCmd))
                .displayItems((params, output) -> {
                    JsonArray items = CONFIG.getAsJsonArray("items");
                    for (JsonElement element : items) {
                        JsonObject item = element.getAsJsonObject();
                        String name = item.get("name").getAsString();
                        String cmd = item.get("custom_model_data").getAsString();
                        output.accept(createItem(name, cmd));
                    }
                });
    }

    /**
     * Creates a carrot_on_a_stick ItemStack with the given custom_model_data
     * and item_name. Uses ITEM_NAME (not CUSTOM_NAME) so the name renders
     * in normal style (not italic).
     */
    private static ItemStack createItem(String name, String customModelData) {
        ItemStack stack = new ItemStack(Items.CARROT_ON_A_STICK);
        stack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(List.of(), List.of(), List.of(customModelData), List.of()));
        stack.set(DataComponents.ITEM_NAME, Component.literal(name));
        return stack;
    }
}
