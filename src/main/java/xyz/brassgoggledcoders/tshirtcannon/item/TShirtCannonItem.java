package xyz.brassgoggledcoders.tshirtcannon.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import xyz.brassgoggledcoders.tshirtcannon.api.TShirtCannonAPI;
import xyz.brassgoggledcoders.tshirtcannon.api.cannonbehavior.ICannonBehavior;
import xyz.brassgoggledcoders.tshirtcannon.capability.InventoryProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class TShirtCannonItem extends TieredItem {
    public TShirtCannonItem(IItemTier itemTier) {
        this(itemTier, new Properties().group(ItemGroup.MISC));
    }

    public TShirtCannonItem(IItemTier itemTier, Properties properties) {
        super(itemTier, properties);
    }

    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, @Nonnull Hand hand) {
        ItemStack cannonStack = player.getHeldItem(hand);
        player.getCooldownTracker().setCooldown(this, 20);
        return cannonStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
                .map(itemHandler -> fireCannon(cannonStack, itemHandler, world, player, hand))
                .orElseGet(() -> super.onItemRightClick(world, player, hand));
    }

    private ActionResult<ItemStack> fireCannon(ItemStack cannonStack, IItemHandler itemHandler, World world,
                                               PlayerEntity playerEntity, Hand hand) {
        if (!world.isRemote()) {
            ItemStack firingStack = getFiringItemStack(itemHandler, world.getRandom());
            if (!firingStack.isEmpty()) {
                ActionResult<ItemStack> firingResult = this.getCannonBehavior(firingStack)
                        .fire(firingStack, cannonStack, this.getTier(), playerEntity, world, playerEntity.getPosX(),
                                playerEntity.getPosYEye() - (double) 0.1F, playerEntity.getPosZ(),
                                playerEntity.rotationYaw, playerEntity.rotationPitch);

                if (firingResult.getType() == ActionResultType.SUCCESS) {
                    cannonStack.damageItem(1, playerEntity, value -> value.sendBreakAnimation(hand));
                }
                return new ActionResult<>(firingResult.getType(), cannonStack);
            } else {
                world.playEvent(1001, playerEntity.getPosition(), 0);
            }
        }

        return ActionResult.resultConsume(cannonStack);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new InventoryProvider();
    }

    private ICannonBehavior getCannonBehavior(ItemStack itemStack) {
        return TShirtCannonAPI.getBehavior(itemStack.getItem())
                .orElse(TShirtCannonAPI.DEFAULT);
    }

    private ItemStack getFiringItemStack(IItemHandler itemHandler, Random random) {
        int chance = 1;

        ItemStack firingItemStack = ItemStack.EMPTY;
        for (int slot = 0; slot < itemHandler.getSlots(); ++slot) {
            ItemStack check = itemHandler.getStackInSlot(slot);
            if (!check.isEmpty() && random.nextInt(chance++) == 0) {
                firingItemStack = check;
            }
        }

        return firingItemStack;
    }
}
