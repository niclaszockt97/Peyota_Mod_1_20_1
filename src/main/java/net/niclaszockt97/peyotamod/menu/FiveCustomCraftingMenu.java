package net.niclaszockt97.peyotamod.menu;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.MenuType;

public class FiveCustomCraftingMenu extends AbstractContainerMenu {

    private final SimpleContainer craftMatrix = new SimpleContainer(25); // 5x5 Grid

    public static MenuType<FiveCustomCraftingMenu> TYPE;

    public FiveCustomCraftingMenu(int id, Inventory playerInventory) {
        super(ModMenus.FIVE_CUSTOM_CRAFTING.get(), id);

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
            }
        }

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
            }
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        for (int i = 0; i < craftMatrix.getContainerSize(); i++) {
            player.drop(craftMatrix.getItem(i), false);
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack result = stack.copy();

            }


            return result;
        }
        return ItemStack.EMPTY;
    }
}
