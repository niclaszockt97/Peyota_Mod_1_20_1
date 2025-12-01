package net.niclaszockt97.peyotamod.block.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;


public class WorkbenchBlock extends Block {
    public WorkbenchBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos,
                                 Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

        //Display current Biom in chat
        Holder<Biome> biomeHolder = pLevel.getBiome(pPos);
        pPlayer.displayClientMessage(
                Component.literal("Biome-ID: " + biomeHolder.unwrapKey().get().location()).withStyle(ChatFormatting.AQUA),
                false
        );

        //Display XP to next level as chat message
        int level_p = pPlayer.getXpNeededForNextLevel();
        pPlayer.displayClientMessage(
                Component.literal("XP To next LV.: " + level_p).withStyle(ChatFormatting.AQUA),
                false
        );


        pLevel.playSound(pPlayer, pPos, SoundEvents.NOTE_BLOCK_BANJO.get(), SoundSource.BLOCKS, 1f, 4f);
        return InteractionResult.SUCCESS;
    }
}