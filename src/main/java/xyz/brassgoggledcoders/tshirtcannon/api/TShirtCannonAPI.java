package xyz.brassgoggledcoders.tshirtcannon.api;

import com.google.common.collect.Maps;
import net.minecraft.item.Item;
import xyz.brassgoggledcoders.tshirtcannon.api.cannonbehavior.DefaultCannonBehavior;
import xyz.brassgoggledcoders.tshirtcannon.api.cannonbehavior.ICannonBehavior;

import java.util.Map;
import java.util.Optional;

public class TShirtCannonAPI {
    public static final ICannonBehavior DEFAULT = new DefaultCannonBehavior();

    private static final Map<Item, ICannonBehavior> CANNON_BEHAVIORS = Maps.newHashMap();

    public static void addBehavior(Item item, ICannonBehavior behavior) {
        CANNON_BEHAVIORS.put(item, behavior);
    }

    public static Optional<ICannonBehavior> getBehavior(Item item) {
        return Optional.of(CANNON_BEHAVIORS.get(item));
    }
}
