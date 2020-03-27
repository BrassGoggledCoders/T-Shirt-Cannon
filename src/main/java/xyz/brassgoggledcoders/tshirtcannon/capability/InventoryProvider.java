package xyz.brassgoggledcoders.tshirtcannon.capability;

import com.hrznstudio.titanium.component.inventory.InventoryComponent;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class InventoryProvider implements ICapabilitySerializable<CompoundNBT> {
    private final InventoryComponent<?> inventoryComponent;
    private final LazyOptional<IItemHandler> lazyOptional;

    public InventoryProvider() {
        this.inventoryComponent = new InventoryComponent<>("T-Shirt Cannon",71, 35, 4)
                .setSlotPosition((index) -> Pair.of((index % 2) * 18, (index / 2) * 18));
        this.lazyOptional = LazyOptional.of(() -> inventoryComponent);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, lazyOptional);
    }

    @Override
    public CompoundNBT serializeNBT() {
        return inventoryComponent.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        inventoryComponent.deserializeNBT(nbt);
    }

    public InventoryComponent<?> get() {
        return inventoryComponent;
    }
}
