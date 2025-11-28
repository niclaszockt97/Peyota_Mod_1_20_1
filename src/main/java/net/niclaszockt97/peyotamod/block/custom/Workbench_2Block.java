package net.niclaszockt97.peyotamod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.niclaszockt97.peyotamod.menu.FiveCustomCraftingMenu;

public class Workbench_2Block extends Block {

    public Workbench_2Block(Properties properties) {
        super(properties);
    }



    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {

        if (!world.isClientSide) { // Server
            if (player instanceof ServerPlayer serverPlayer) {
                NetworkHooks.openScreen(serverPlayer,
                        new SimpleMenuProvider(
                                (id, inv, extra) -> new FiveCustomCraftingMenu(id, inv),
                                Component.literal("")
                        ),
                        pos
                );
            }
        }

        return InteractionResult.sidedSuccess(world.isClientSide);
    }



}
