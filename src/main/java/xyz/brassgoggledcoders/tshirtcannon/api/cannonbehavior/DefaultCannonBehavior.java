package xyz.brassgoggledcoders.tshirtcannon.api.cannonbehavior;

import net.minecraft.entity.Entity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DefaultCannonBehavior implements ICannonBehavior {
    @Override
    @Nonnull
    public ActionResult<ItemStack> fire(ItemStack ammo, ItemStack cannon, IItemTier cannonTier, @Nullable Entity wielder,
                                        @Nonnull World world, double posX, double posY, double posZ, float rotationYaw, float rotationPitch) {

        world.playEvent(1000, new BlockPos(posX, posY, posZ), 0);
        return ActionResult.resultFail(ammo);
    }
}
