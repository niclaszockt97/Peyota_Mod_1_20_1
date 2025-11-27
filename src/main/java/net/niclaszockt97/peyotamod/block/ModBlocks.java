package net.niclaszockt97.peyotamod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.niclaszockt97.peyotamod.PeyotaMod;
import net.niclaszockt97.peyotamod.item.ModItems;


import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PeyotaMod.MOD_ID);


    public static final RegistryObject<Block> PEYOTA_WORKBENCH =registerBlock("peyota_workbench",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));

    public static final RegistryObject<Block> PEYOTA_WORKBENCH_2 =registerBlock("peyota_workbench_2",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE)));

    private static  <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registryBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>RegistryObject<Item> registryBlockItem(String name,RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () ->new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
