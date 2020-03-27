package xyz.brassgoggledcoders.tshirtcannon;

import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Items;
import xyz.brassgoggledcoders.tshirtcannon.api.TShirtCannonAPI;
import xyz.brassgoggledcoders.tshirtcannon.api.cannonbehavior.arrow.ArrowCannonBehavior;

public class CannonBehaviors {
    public static void register() {
        TShirtCannonAPI.addBehavior(Items.ARROW, new ArrowCannonBehavior(ArrowEntity::new));
    }
}
