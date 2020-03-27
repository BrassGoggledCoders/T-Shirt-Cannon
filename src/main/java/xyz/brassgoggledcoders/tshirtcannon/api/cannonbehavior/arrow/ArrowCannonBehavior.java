package xyz.brassgoggledcoders.tshirtcannon.api.cannonbehavior.arrow;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.AbstractArrowEntity.PickupStatus;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.tshirtcannon.api.cannonbehavior.ICannonBehavior;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ArrowCannonBehavior implements ICannonBehavior {
    private final IArrowEntityFactory arrowEntityFactory;

    public ArrowCannonBehavior(IArrowEntityFactory arrowEntityFactory) {
        this.arrowEntityFactory = arrowEntityFactory;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> fire(ItemStack ammo, ItemStack cannon, IItemTier cannonTier, @Nullable Entity shooter,
                                        @Nonnull World world, double posX, double posY, double posZ, float rotationYaw,
                                        float rotationPitch) {
        if (ammo.getItem() instanceof ArrowItem) {
            ArrowEntity arrowEntity = arrowEntityFactory.create(ammo, world, posX, posY, posZ);
            arrowEntity.pickupStatus = PickupStatus.ALLOWED;
            shoot(arrowEntity, rotationPitch, rotationYaw, 2.5F, 1.0F);
            if (shooter != null) {
                arrowEntity.setMotion(arrowEntity.getMotion().add(shooter.getMotion().x,
                        shooter.onGround ? 0.0D : shooter.getMotion().y, shooter.getMotion().z));
            }
            world.addEntity(arrowEntity);
            world.playEvent(1002, new BlockPos(posX, posY, posZ), 0);
            ammo.shrink(1);
            return ActionResult.resultSuccess(ammo);
        } else {
            return ActionResult.resultFail(ammo);
        }
    }

    private void shoot(ArrowEntity arrowEntity, float pitch, float yaw, float velocity, float inaccuracy) {
        float x = -MathHelper.sin(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        float y = -MathHelper.sin(pitch * ((float)Math.PI / 180F));
        float z = MathHelper.cos(yaw * ((float)Math.PI / 180F)) * MathHelper.cos(pitch * ((float)Math.PI / 180F));
        arrowEntity.shoot(x, y, z, velocity, inaccuracy);
    }
}
