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

        // Crafting Grid 5x5
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                this.addSlot(new Slot(craftMatrix, col + row * 5, 44 + col * 18, 17 + row * 18));
            }
        }

        // Player Inventory (3x9)
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 104 + row * 18));
            }
        }

        // Player Hotbar (1x9)
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, 162));
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

    // --- WICHTIG: quickMoveStack implementieren ---
    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            ItemStack result = stack.copy();

            // Spieler-Inventar <-> Crafting Grid Movement
            if (index < 25) { // Crafting Grid
                if (!moveItemStackTo(stack, 25, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else { // Spieler-Inventar
                if (!moveItemStackTo(stack, 0, 25, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            return result;
        }
        return ItemStack.EMPTY;
    }
}

