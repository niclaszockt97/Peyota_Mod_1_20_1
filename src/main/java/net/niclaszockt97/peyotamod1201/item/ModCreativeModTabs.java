package net.niclaszockt97.peyotamod1201.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.niclaszockt97.peyotamod1201.PeyotaMod;
import net.niclaszockt97.peyotamod1201.block.ModBlocks;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, PeyotaMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PEYOTA_TAB = CREATIVE_MOD_TABS.register("peyota_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PEYOTA_NUGGET.get()))
                    .title(Component.translatable("creativetab.peyota_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        //Items in Creative Tab einfügen.
                        pOutput.accept(ModBlocks.PEYOTA_WORKBENCH.get());
                        pOutput.accept(ModBlocks.PEYOTA_WORKBENCH_2.get());
                        pOutput.accept(ModItems.PEYOTA_NUGGET.get());
                        pOutput.accept(ModItems.PEYOTA_BRUSH.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MOD_TABS.register(eventBus);
    }


}
