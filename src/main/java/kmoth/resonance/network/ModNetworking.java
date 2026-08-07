package kmoth.resonance.network;

import kmoth.resonance.skill.BladeResonanceSkill;
import kmoth.resonance.skill.BladeResonanceUnlocks;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber(
        modid = "resonance",
        bus = EventBusSubscriber.Bus.MOD
)
public class ModNetworking {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {

        var registrar = event.registrar("1");

        registrar.playToServer(
                BladeResonancePayload.TYPE,
                BladeResonancePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {

                    if (context.player() instanceof ServerPlayer player) {
                        BladeResonanceSkill.activate(player);
                    }
                })
        );

        registrar.playToServer(
                UnlockBladeResonancePayload.TYPE,
                UnlockBladeResonancePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {

                    if (context.player() instanceof ServerPlayer player) {
                        BladeResonanceUnlocks.tryUnlock(player);
                    }
                })
        );
    }
}