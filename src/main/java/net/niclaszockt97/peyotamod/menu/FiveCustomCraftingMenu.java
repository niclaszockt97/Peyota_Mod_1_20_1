package net.niclaszockt97.peyotamod.menu;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;


public class FiveCustomCraftingMenu extends AbstractContainerMenu {

    private final SimpleContainer craftMatrix = new SimpleContainer(25); // 5x5 Grid
    private final SimpleContainer craftResult = new SimpleContainer(1);        // Output Slot

    public FiveCustomCraftingMenu(int id, Inventory playerInventory) {
        super(ModMenus.FIVE_CUSTOM_CRAFTING.get(), id);

        int cpx = 9, cpy = 8;

        // Crafting Grid
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                int index = col + row * 5;
                this.addSlot(new Slot(craftMatrix, index, cpx + col * 18, cpy + row * 18) {
                    @Override
                    public void setChanged() {
                        super.setChanged();
                        updateCraftingResult(playerInventory.player);
                    }
                });
            }
        }

        // Output Slot
        this.addSlot(new OutputSlot(craftResult, 0, 144, 35));

        // Player Inventory
        int px = 8, py = 108;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, px + col * 18, py + row * 18));
            }
        }

        // Hotbar
        int hbx = 8, hby = 166;
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, hbx + col * 18, hby));
        }
    }


    public boolean stillValid(Player player) {
        return true;
    }

    public void removed(Player player) {
        super.removed(player);
        for (int i = 0; i < craftMatrix.getContainerSize(); i++) {
            player.drop(craftMatrix.getItem(i), false);
        }
    }

    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        int craftingSize = craftMatrix.getContainerSize(); // 25
        int outputIndex = craftingSize;

        if (index < craftingSize) {
            if (!this.moveItemStackTo(stack, outputIndex + 1, this.slots.size(), true)) return ItemStack.EMPTY;
        } else if (index == outputIndex) {
            if (!this.moveItemStackTo(stack, outputIndex + 1, this.slots.size(), true)) return ItemStack.EMPTY;
        } else {
            if (!this.moveItemStackTo(stack, 0, craftingSize, false)) return ItemStack.EMPTY;
        }

        if (stack.isEmpty()) slot.set(ItemStack.EMPTY);
        else slot.setChanged();

        slot.onTake(player, stack);
        return copy;
    }

    private void updateCraftingResult(Player player) {
        var recipeManager = player.level().getRecipeManager();

    }

    public static class Provider implements MenuProvider {
        private final BlockPos pos;
        public Provider(BlockPos pos) { this.pos = pos; }

        public Component getDisplayName() {
            return Component.literal("Five Custom Crafting");
        }

        public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
            return new FiveCustomCraftingMenu(id, inv);
        }
    }

    public class OutputSlot extends Slot {
        public OutputSlot(SimpleContainer inventory, int index, int x, int y) {
            super(inventory, index, x, y);
        }

        public boolean mayPlace(ItemStack stack) {
            return false;
        }

        public void onTake(Player player, ItemStack stack) {
            super.onTake(player, stack);
            // Items im Crafting Grid reduzieren
            for (int i = 0; i < craftMatrix.getContainerSize(); i++) {
                ItemStack slotStack = craftMatrix.getItem(i);
                if (!slotStack.isEmpty()) slotStack.shrink(1);
            }
        }
    }
}
