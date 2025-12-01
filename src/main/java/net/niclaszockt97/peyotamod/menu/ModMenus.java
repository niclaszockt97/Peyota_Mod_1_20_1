// src/main/java/net/niclaszockt97/peyotamod/menu/ModMenus.java
package net.niclaszockt97.peyotamod.menu;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraft.world.inventory.MenuType;

public class ModMenus {

    // DeferredRegister für MenuTypes, ModID muss korrekt sein
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, "peyotamod");

    // Registrierung des 5x5 Crafting Menu
    public static final RegistryObject<MenuType<FiveCustomCraftingMenu>> FIVE_CUSTOM_CRAFTING =
            MENUS.register("five_custom_crafting",
                    () -> IForgeMenuType.create((id, inv, extraData) -> new FiveCustomCraftingMenu(id, inv)));


}
