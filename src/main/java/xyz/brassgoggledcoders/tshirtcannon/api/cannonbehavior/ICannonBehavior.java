package xyz.brassgoggledcoders.tshirtcannon.api.cannonbehavior;

import net.minecraft.entity.Entity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface ICannonBehavior {
    @Nonnull
    ActionResult<ItemStack> fire(ItemStack ammo, ItemStack cannon, IItemTier cannonTier, @Nullable Entity shooter,
                                 @Nonnull World world, double posX, double posY, double posZ, float rotationYaw,
                                 float rotationPitch);
}
