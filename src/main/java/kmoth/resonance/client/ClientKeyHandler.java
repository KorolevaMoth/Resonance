package kmoth.resonance.client;

import kmoth.resonance.network.BladeResonancePayload;
import kmoth.resonance.network.UnlockBladeResonancePayload;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = "resonance")
public class ClientKeyHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null) {
            return;
        }

        while (ModKeyMappings.BLADE_RESONANCE.consumeClick()) {

            PacketDistributor.sendToServer(
                    new BladeResonancePayload()
            );
        }

        while (ModKeyMappings.UNLOCK_BLADE_RESONANCE.consumeClick()) {

            PacketDistributor.sendToServer(
                    new UnlockBladeResonancePayload()
            );
        }
    }
}