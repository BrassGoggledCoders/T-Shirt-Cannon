package xyz.brassgoggledcoders.tshirtcannon.container;

import com.hrznstudio.titanium.container.BasicContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.ItemStack;

public class ItemStackContainer extends BasicContainer {
    private final ItemStack itemStack;

    public ItemStackContainer(ContainerType type, int id, ItemStack itemStack) {
        super(type, id);
        this.itemStack = itemStack;

    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
