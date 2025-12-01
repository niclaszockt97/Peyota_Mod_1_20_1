package net.niclaszockt97.peyotamod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.List;

public class PeyotaRod extends Item {
    public PeyotaRod(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, @org.jetbrains.annotations.Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.peyotamod.peyota_rod")
                .withStyle(ChatFormatting.YELLOW));
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level.isClientSide) {
            // Clientseitig Partikel erzeugen
            for (int i = 0; i < 10; i++) {
                double offsetX = (level.random.nextDouble() - 0.5) * 0.5;
                double offsetY = level.random.nextDouble() * 0.5;
                double offsetZ = (level.random.nextDouble() - 0.5) * 0.5;

                level.addParticle(
                        ParticleTypes.FLAME,
                        player.getX() + offsetX,
                        player.getY() + 1.0 + offsetY,
                        player.getZ() + offsetZ,
                        0, 0.05, 0
                );
            }
        } else {
            // Block, auf den der Spieler schaut
            HitResult hitResult = player.pick(20.0D, 0.0F, false);

            player.sendSystemMessage(Component.literal("Player:"));
            player.sendSystemMessage(Component.literal("X= " + (int) player.getX() + " Y= " + (int) player.getY() + " Z: " + (int) player.getZ()));

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockHitResult blockHit = (BlockHitResult) hitResult;
                BlockPos pos = blockHit.getBlockPos();

                // Nachricht in mehreren Zeilen ausgeben
                player.sendSystemMessage(Component.literal("View: :"));
                player.sendSystemMessage(Component.literal("X=" + (int) pos.getX() + " Y=" + (int) pos.getY() +  " Z= " + (int) pos.getZ()));


            }
        }

        return InteractionResultHolder.success(stack); // immer zurückgeben
    }
}