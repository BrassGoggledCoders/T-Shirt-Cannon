package xyz.brassgoggledcoders.tshirtcannon;

import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.brassgoggledcoders.tshirtcannon.item.TShirtCannonItem;

public class TShirtCannonContent {
    private static final DeferredRegister<Item> ITEM = new DeferredRegister<>(ForgeRegistries.ITEMS, TShirtCannon.ID);

    public static final RegistryObject<TShirtCannonItem> STONE_CANNON = ITEM.register("stone_cannon",
            () -> new TShirtCannonItem(ItemTier.STONE));
    public static final RegistryObject<TShirtCannonItem> IRON_CANNON = ITEM.register("iron_cannon",
            () -> new TShirtCannonItem(ItemTier.IRON));
    public static final RegistryObject<TShirtCannonItem> GOLD_CANNON = ITEM.register("gold_cannon",
            () -> new TShirtCannonItem(ItemTier.GOLD));
    public static final RegistryObject<TShirtCannonItem> DIAMOND_CANNON = ITEM.register("diamond_cannon",
            () -> new TShirtCannonItem(ItemTier.DIAMOND));

    public static void register(IEventBus modBus) {
        ITEM.register(modBus);
    }
}
