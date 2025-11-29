package net.niclaszockt97.peyotamod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.niclaszockt97.peyotamod.PeyotaMod;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PeyotaMod.MOD_ID);

    public static final RegistryObject<Item> PEYOTA_NUGGET = ITEMS.register("peyota_nugget",
            () -> new Item(new Item.Properties()
                    .stacksTo(16)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final RegistryObject<Item> PEYOTA_BRUSH = ITEMS.register("peyota_brush",
            () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.COMMON)
            ));

    public static final RegistryObject<Item> PEYOTA_ROD = ITEMS.register("peyota_rod",
            () -> new PeyotaRod(new Item.Properties()
            )
    );

    public static void register(IEventBus eventBus) {

        ITEMS.register(eventBus);
    }
}
