package xyz.brassgoggledcoders.tshirtcannon.api.cannonbehavior.arrow;

import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public interface IArrowEntityFactory {
    @Nonnull
    default ArrowEntity create(ItemStack ammoStack, World world, double x, double y, double z) {
        return this.create(world, x, y, z);
    }

    @Nonnull
    ArrowEntity create(World world, double x, double y, double z);

}
