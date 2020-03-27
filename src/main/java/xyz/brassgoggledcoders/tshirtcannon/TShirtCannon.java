package xyz.brassgoggledcoders.tshirtcannon;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import xyz.brassgoggledcoders.tshirtcannon.datagen.TShirtCannonRecipeProvider;

@Mod(TShirtCannon.ID)
public class TShirtCannon {
    public static final String ID = "tshirtcannon";

    public TShirtCannon() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        TShirtCannonContent.register(modBus);
        modBus.addListener(this::gatherProviders);
        modBus.addListener(this::commonSetup);
    }

    public void gatherProviders(GatherDataEvent event) {
        if (event.includeServer()) {
            event.getGenerator().addProvider(new TShirtCannonRecipeProvider(event.getGenerator()));
        }
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        CannonBehaviors.register();
    }
}
