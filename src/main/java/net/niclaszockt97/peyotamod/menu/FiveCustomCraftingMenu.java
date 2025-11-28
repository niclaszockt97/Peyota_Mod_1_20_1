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
    private final SimpleContainer output = new SimpleContainer(1);      // Output Slot

    public static MenuType<FiveCustomCraftingMenu> TYPE;

    public FiveCustomCraftingMenu(int id, Inventory playerInventory) {
        super(ModMenus.FIVE_CUSTOM_CRAFTING.get(), id);

        int cgridStartX = 9;
        int cgridStartY = 8;

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                this.addSlot(new Slot(
                        craftMatrix,
                        col + row * 5,
                        cgridStartX + col * 18,
                        cgridStartY + row * 18
                ));
            }
        }

        int ogridStartX = 148;
        int ogridStartY = 45;

        this.addSlot(new Slot(output, 0, ogridStartX, ogridStartY));

        // Spieler-Inventar
        int pivx = 8;
        int pivy = 108;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, pivx + col * 18, pivy + row * 18));
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
        player.drop(output.getItem(0), false);
    }

    public ItemStack getItem(int x, int y) {
        return craftMatrix.getItem(y * 5 + x);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack result = stack.copy();

            if (index == 25) { // Output → Spieler
                if (!moveItemStackTo(stack, 26, this.slots.size(), true)) return ItemStack.EMPTY;
                slot.onQuickCraft(stack, result);
            } else if (index < 25) { // Crafting → Spieler
                if (!moveItemStackTo(stack, 26, this.slots.size(), false)) return ItemStack.EMPTY;
            } else { // Spieler → Crafting
                if (!moveItemStackTo(stack, 0, 25, false)) return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
            else slot.setChanged();

            return result;
        }
        return ItemStack.EMPTY;
    }
}
