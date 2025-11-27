package net.niclaszockt97.peyotamod.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.niclaszockt97.peyotamod.PeyotaMod;
import net.niclaszockt97.peyotamod.menu.FiveCustomCraftingMenu;

public class FiveCustomCraftingScreen extends AbstractContainerScreen<FiveCustomCraftingMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(PeyotaMod.MOD_ID, "textures/gui/five_custom_crafting.png");

    public FiveCustomCraftingScreen(FiveCustomCraftingMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 200;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        guiGraphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, delta);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }
}
